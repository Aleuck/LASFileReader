package LAS;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template lasFile, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleuck
 */
public abstract class LASFileReader {
    /**
     * 
     * @param filePath file that will be parsed.
     * @return A {@link LASFile} object.
     * @throws IOException
     * @throws Exception
     */

    public static LASFile open(Path filePath) throws IOException, Exception {
        // each line as a string in a list
        //System.out.print("Reading lasFile...");
        List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);
        //System.out.println("done!");
        List<List<String>> sections = new ArrayList<>();
        int last = -1;
        int i;
        // separating each section as a sublist of lines
        //System.out.print("Separating sections...");
        for (i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith("~")) {
                if (last >= 0) {
                    sections.add(lines.subList(last, i));
                }
                last = i;
            }
        }
        // add last section to section list.
        sections.add(lines.subList(last, i));
        //System.out.println("done!");
        // verify LAS version of lasFile
        switch (checkVersion(sections)) {
            case v2_0:
                return open2_0(sections);
            case v3_0:
                return open3_0(sections);
            default:
                // defaults to exception.
                throw new Exception("Unsupported file.");
        }
    }
    /**
     * Returns the {@link LASVersion} of the file, unknown version if it fails.
     * @param sections all de sections of the file as a list of sections.
     * @return the version of the LAS file.
     */
    private static LASVersion checkVersion(List<List<String>> sections) {
        int i = 0;
        LASVersion version = LASVersion.unknown;
        List<String> currentSection;
        String sectionTitle;
        Dictionary mnemonics;
        lfv: while (i < sections.size() && version == LASVersion.unknown) {
            currentSection = sections.get(i);
            sectionTitle = currentSection.get(0).trim().toUpperCase();
            // Looking for version information
            if (sectionTitle.startsWith("~V")) {
                // Found possible version section
                for (String line : currentSection) {
                    line = line.trim();
                    if (!line.startsWith("~") && !line.startsWith("#")) {
                        // Trying to read parameter data lines
                        try {
                            LASParameterDataLine pd = buildParameterDataLine2(line);
                            // Getting the version.
                            if (pd.getMnemonic().equals("VERS")) {
                                if (pd.getValue().startsWith("2.0")) {
                                    version = LASVersion.v2_0;
                                } else if (pd.getValue().startsWith("3.0")) {
                                    version = LASVersion.v3_0;
                                }
                                break lfv;
                            }
                        }
                        catch (Exception e) {
                            // do nothing
                        }
                    }
                }
            }
            i++;
        }
        // returns version found or unknown version.
        return version;
    }
    private static LASFile open2_0(List<List<String>> sections) {
        // TODO: parse 2.0 LAS File
        List<String> currentSection;
        LASFile lasFile = new LASFile();
        boolean wrap;
        boolean found = false;
        for (int i = 0; i < sections.size() && !found ; i++) {
            currentSection = sections.get(i);
            if (currentSection.get(0).startsWith("~V")) {
                found = true;
                
            }
        }
        System.out.println("2.0");
        return new LASFile();
    }
    private static LASFile open3_0(List<List<String>> sections) throws Exception {
        // TODO: parse 3.0 LAS File
        LASFile lasFile = new LASFile();
        List<String> currentSection;
        HashMap<String,String> delimiters;
        delimiters = new HashMap<>();
        delimiters.put("SPACE", " ");
        delimiters.put("COMMA", ",");
        delimiters.put("TAB", "\t");
        boolean wrap;
        String delimiter = " ";
        double nullValue = 0;
        
        System.out.println("3.0");
        
        // ~Version
        currentSection = sections.get(0);
        if (!"~Version".equals(currentSection.get(0).trim())) {
            throw new Exception("First section must be ~Version in LAS 3.0.");
        }
        
        for (String line : currentSection) {
            LASParameterDataLine data = new LASParameterDataLine();
            if (!line.startsWith("#")) {
               
                switch (data.mnemonic) {
                    case "VERS":
                        // do nothing
                        break;
                    case "WRAP":
                        if (!data.getValue().equalsIgnoreCase("yes")) {
                            throw new Exception("The only legal valid for WRAP in LAS 3.0 is No.");
                        }
                        break;
                    case "DLM":
                        if (!delimiters.containsKey(data.getValue())) {
                            throw new Exception("Invalid DLM value.");
                        } else {
                            delimiter = delimiters.get(data.getValue());
                        }
                        break;
                }
            }
        }
        // ~Well
        currentSection = sections.get(1);
        if (!"~Well".equals(currentSection.get(0).trim())) {
            throw new Exception("Second section must be ~Well in LAS 3.0.");
        }
        
        for (int i = 2; i < sections.size(); i++) {
            
        }
        return lasFile;
    }
    public static LASParameterDataSection buildParamaterDataSection2(List<String> section) throws Exception {
        LASParameterDataSection thisSection = new LASParameterDataSection();
        String title = section.remove(0).trim();
        thisSection.title = title;
        for (String line : section) {
            line = line.trim();
            if (!line.startsWith("#")) {
                thisSection.addParameter(buildParameterDataLine2(line));
            }
        }
        return thisSection;
    }
    public static LASParameterDataLine buildParameterDataLine2(String line) throws Exception {
        // MNEM.UNIT   VALUE : DESCRIPTION
        LASParameterDataLine thisParameterData = new LASParameterDataLine();
        int idxDot, idxVal, idxDes;
        
        idxDot = line.indexOf('.');
        idxDot = idxDot < 0 ? 0 : idxDot;
        idxVal = line.indexOf(' ', idxDot + 1);
        idxVal = idxVal < idxDot + 1 ? idxDot + 1 : idxVal;
        idxDes = line.indexOf(':', idxVal + 1);
        idxDes = idxDes < idxVal + 1 ? line.length() - 1 : idxDes;
        
        thisParameterData.mnemonic = line.substring(0, idxDot).trim();
        thisParameterData.unit = line.substring(idxDot + 1, idxVal).trim();
        thisParameterData.value = line.substring(idxVal, idxDes).trim();
        thisParameterData.description = line.substring(idxDes + 1, line.length()).trim();
        
        return thisParameterData;
    }
}