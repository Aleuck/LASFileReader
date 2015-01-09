/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import LAS.LASFile;
import LAS.LASFileReader;
import LAS.LASParameterDataLine;
import java.io.File;
import java.nio.file.Path;

/**
 *
 * @author aleuck
 */
public class LASFileTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        LASFile lasFile;
        Path filePath;
        //lasFile = LASFileReader.open(null);
        for (String arg : args) {
            System.out.println(arg);
        }
        if (args.length > 0) {
            filePath = new File(args[0]).toPath();
            lasFile = LASFileReader.open(filePath);
            for (LASParameterDataLine parameter : lasFile.getVersionSection()) {
                System.out.println(parameter.getMnemonic());
            }
        }
    }
}