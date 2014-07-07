import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Dictionary;
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
     * @param filePath
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
     * 
     * @param sections
     * @return The version of the lasFile
     */
    private static LASVersion checkVersion(List<List<String>> sections) {
        int i = 0;
        LASVersion version = LASVersion.unknown;
        List<String> currentSection;
        String sectionTitle;
        Dictionary mnemonics;
        //System.out.println("Looking for version section.");
        lfv: while (i < sections.size() && version == LASVersion.unknown) {
            currentSection = sections.get(i);
            sectionTitle = currentSection.get(0).trim().toUpperCase();
            //System.out.println(sectionTitle);
            if (sectionTitle.startsWith("~VERSION") || sectionTitle.compareTo("~V") == 0) {
                //System.out.println("found!");
                //System.out.println("Looking for VERS. mnemonic");
                for (String line : currentSection.subList(1, currentSection.size())) {
                    line = line.trim();
                    //System.out.println(line);
                    if (!line.startsWith("~") && !line.startsWith("#")) {
                        try {
                            LASParameterDataLine pd = new LASParameterDataLine_dummy(line);
                            if (pd.mnemonic.equals("VERS")) {
                                if (pd.value.startsWith("2.0")) {
                                    version = LASVersion.v2_0;
                                } else if (pd.value.startsWith("3.0")) {
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
        // version not identified.
        return version;
    }
    private static LASFile open2_0(List<List<String>> sections) {
        // TODO: parse 2.0 LAS File
        System.out.println("2.0");
        return new LASFile_2_0();
    }
    private static LASFile open3_0(List<List<String>> sections) throws Exception {
        // TODO: parse 3.0 LAS File
        LASFile_3 lasFile = new LASFile_3();
        List<String> currentSection;
        String delimiter = " ";
        System.out.println("3.0");
        
        // ~Version
        currentSection = sections.get(0);
        if (!"~Version".equals(currentSection.get(0).trim())) {
            throw new Exception("First section must be ~Version in LAS 3.0.");
        }
        for (int i = 1 ;i < currentSection.size(); i++) {
            String line = currentSection.get(i);
            if (!line.startsWith("#")) {
                try {
                    LASParameterDataLine_3 data = new LASParameterDataLine_3(line);
                    switch (data.mnemonic) {
                        case "VERS":
                            // TODO
                            break;
                        case "WRAP":
                            // TODO
                            break;
                        case "DLM":
                            // TODO
                            break;
                    }
                }
                catch (Exception e) {
                    // do nothing
                }
            }
        }
        // ~Well
        currentSection = sections.get(1);
        if (!"~Well".equals(currentSection.get(0).trim())) {
            throw new Exception("Second section must be ~Well in LAS 3.0.");
        }
        for (int i = 1; i < currentSection.size(); i++) {
            String line = currentSection.get(i);
            if (!line.startsWith("#")) {
                try {
                    LASParameterDataLine_3 data = new LASParameterDataLine_3(line);
                    switch (data.mnemonic) {
                        case "STRT":
                            // TODO
                            break;
                        case "STOP":
                            // TODO
                            break;
                        case "STEP":
                            // TODO
                            break;
                        case "NULL":
                            // TODO
                            break;
                        case "COMP":
                            // TODO
                            break;
                        case "WELL":
                            // TODO
                            break;
                        case "FLD":
                            // TODO
                            break;
                        case "LOC":
                            // TODO
                            break;
                        case "SRVC":
                            // TODO
                            break;
                        case "CTRY":
                            // TODO
                            break;
                        case "DATE":
                            // TODO
                            break;
                    }
                }
                catch (Exception e) {
                    // do nothing
                }
            }
        }
        for (int i = 2; i < sections.size(); i++) {
            
        }
        return lasFile;
    }
}