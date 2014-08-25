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
        List<LASSection> sections = new ArrayList<>();
        int last = -1;
        int i;
        // separating each section as a sublist of lines
        //System.out.print("Separating sections...");
        for (i = 0; i < lines.size(); i++) {
            if (lines.get(i).startsWith("~")) {
                if (last >= 0) {
                    sections.add(new LASSection(lines.subList(last, i)));
                }
                last = i;
            }
        }
        // add last section to section list.
        sections.add(new LASSection(lines.subList(last, i)));
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
    private static LASVersion checkVersion(List<LASSection> sections) {
        int i = 0;
        LASVersion version = LASVersion.unknown;
        LASSection currentSection;
        String sectionTitle;
        Dictionary mnemonics;
        lfv: while (i < sections.size() && version == LASVersion.unknown) {
            currentSection = sections.get(i);
            sectionTitle = currentSection.getTitle().trim().toUpperCase();
            // Looking for version information
            if (sectionTitle.startsWith("~V")) {
                // Found possible version section
                for (String line : currentSection.getLines()) {
                    line = line.trim();
                    if (!line.startsWith("~") && !line.startsWith("#")) {
                        // Trying to read parameter data lines
                        try {
                            LASParameterDataLine pd = new LASParameterDataLine_dummy(line);
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
    private static LASFile open2_0(List<LASSection> sections) {
        // TODO: parse 2.0 LAS File
        System.out.println("2.0");
        return new LASFile_2();
    }
    private static LASFile open3_0(List<LASSection> sections) throws Exception {
        // TODO: parse 3.0 LAS File
        LASFile_3 lasFile = new LASFile_3();
        LASSection currentSection;
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
        if (!"~Version".equals(currentSection.getTitle().trim())) {
            throw new Exception("First section must be ~Version in LAS 3.0.");
        }
        
        for (String line : currentSection.getLines()) {
            LASParameterDataLine_3 data = new LASParameterDataLine_3("M.U V:D");
            if (!line.startsWith("#")) {
                try {
                    data = new LASParameterDataLine_3(line);
                }
                catch (Exception e) {
                    // do nothing
                }
                
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
        if (!"~Well".equals(currentSection.getTitle().trim())) {
            throw new Exception("Second section must be ~Well in LAS 3.0.");
        }
        
        for (int i = 2; i < sections.size(); i++) {
            
        }
        return lasFile;
    }
}