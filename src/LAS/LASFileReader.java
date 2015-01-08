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
 * @author Alexandre Leuck
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
     * @return {@link LASVersion}.
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
            line = line.trim();
            LASParameterDataLine data = new LASParameterDataLine();
            if (!line.startsWith("#") && !line.startsWith("~")) {
               
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
    
    /* LAS 2.0 */
    
    private static LASFile open2_0(List<List<String>> sections) throws Exception {
        // TODO: parse 2.0 LAS File
        LASFile lasFile = new LASFile();
        lasFile.version = LASVersion.v2_0;
        boolean wrap;
        int indexV = -1;
        int indexW = -1;
        int indexC = -1;
        int indexP = -1;
        int indexO = -1;
        int indexA = -1;
        for (int i = 0; i < sections.size() ; i++) {
            String sectionTitle = sections.get(i).get(0).trim();
            if (sectionTitle.startsWith("~V")) {
                indexV = i;
            } else
            if (sectionTitle.startsWith("~W")) {
                indexW = i;
            } else
            if (sectionTitle.startsWith("~C")) {
                indexC = i;
            } else
            if (sectionTitle.startsWith("~P")) {
                indexP = i;
            } else
            if (sectionTitle.startsWith("~O")) {
                indexO = i;
            } else
            if (sectionTitle.startsWith("~A")) {
                indexA = i;
            }
        }
        // Version
        if (indexV == -1) {
            throw new Exception("Missing section ~V");
        }
        lasFile.version_section = buildParamaterDataSection2(sections.get(indexV));
        // Well
        if (indexW == -1) {
            throw new Exception("Missing section ~W");
        }
        lasFile.well_section = buildParamaterDataSection2(sections.get(indexW));
        // Other
        if (indexO != -1) {
            lasFile.other_section = buildParamaterDataSection2(sections.get(indexO));
        }
        // Curve
        if (indexC == -1) {
            throw new Exception("Missing section ~C");
        }
        LASParameterDataSection curve = buildParamaterDataSection2(sections.get(indexC));
        // ASCII
        if (indexA == -1) {
            throw new Exception("Missing section ~A");
        }
        // identifying wrap mode
        if (!lasFile.version_section.hasParameter("WRAP")) {
            throw new Exception("Missing parameter: WRAP.");
        }
        switch (lasFile.version_section.getParameter("WRAP").getValue()) {
            case "YES":
                wrap = true;
                break;
            case "NO":
                wrap = false;
                break;
            default:
                throw new Exception("Invalid parameter value for WRAP.");
        }
        //lasFile.data = buildLogData2();
        System.out.println("2.0");
        return new LASFile();
    }
    protected static LASParameterDataSection buildParamaterDataSection2(List<String> section) {
        LASParameterDataSection thisSection = new LASParameterDataSection();
        String title = section.get(0).trim();
        System.out.println(title);
        thisSection.title = title;
        for (String line : section) {
            line = line.trim();
            if (!line.startsWith("#") && !line.startsWith("~")) {
                LASParameterDataLine parameterDataLine = buildParameterDataLine2(line);
                thisSection.addParameter(parameterDataLine);
                System.out.print(parameterDataLine.mnemonic);
                if (thisSection.hasParameter(parameterDataLine.mnemonic)) {
                    System.out.println("....OK");
                } else {
                    System.out.println("....FAIL");
                }
            }
        }
        return thisSection;
    }
    protected static LASParameterDataLine buildParameterDataLine2(String line) {
        // MNEM.UNIT   VALUE : DESCRIPTION
        LASParameterDataLine parameterDataLine = new LASParameterDataLine();
        int idxDot, idxVal, idxDes;
        idxDot = line.indexOf('.');
        idxDot = idxDot < 0 ? 0 : idxDot;
        idxVal = line.indexOf(' ', idxDot + 1);
        idxVal = idxVal < idxDot + 1 ? idxDot + 1 : idxVal;
        idxDes = line.indexOf(':', idxVal + 1);
        idxDes = idxDes < idxVal + 1 ? line.length() - 1 : idxDes;
        
        parameterDataLine.mnemonic = line.substring(0, idxDot).trim();
        parameterDataLine.unit = line.substring(idxDot + 1, idxVal).trim();
        parameterDataLine.value = line.substring(idxVal, idxDes).trim();
        parameterDataLine.description = line.substring(idxDes + 1, line.length()).trim();
        return parameterDataLine;
    }
    
    /* LAS 3.0 */
}