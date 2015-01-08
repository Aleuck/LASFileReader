/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LAS;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alxandre Leuck
 */
public class LASFileReaderTest {
    
    public LASFileReaderTest() {
    }
    
    @After
    public void tearDown() {
    }

//    /**
//     * Test of open method, of class LASFileReader.
//     */
//    @Test
//    public void testOpen() throws Exception {
//        System.out.println("open");
//        Path filePath = null;
//        LASFile expResult = null;
//        LASFile result = LASFileReader.open(filePath);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of buildParamaterDataSection2 method, of class LASFileReader.
     */
    @Test
    public void testBuildParamaterDataSection2() throws Exception {
        System.out.println("buildParamaterDataSection2");
        List<String> section = new ArrayList<>();
        section.add("~Version");
        section.add("#MNEM.UNIT VALUE : DESCRIPTION");
        section.add(" VERS.     2.0   :  LAS 2.0   ");
        section.add(" WRAP.     YES   :  asdasd  asdad ");
        LASParameterDataSection result = new LASParameterDataSection();
        try {
            result = LASFileReader.buildParamaterDataSection2(section);
        }
        catch (Exception e) {
            fail("Failed to parse valid section.");
        }
        assertEquals("error section title","~Version",result.getTitle());
        assertEquals(true,result.hasParameter("VERS"));
        assertEquals(true,result.hasParameter("WRAP"));
        assertEquals(false,result.hasParameter("WRAPP"));
    }

    /**
     * Tests cases of buildParameterDataLine2 method, of class LASFileReader.
     */
    @Test
    public void testBuildParameterDataLine2_version() throws Exception {
        System.out.println("buildParameterDataLine2");
        String line = " VERS. 2.0 : CWLS log ASCII Standard -VERSION 2.0";
        LASParameterDataLine result = LASFileReader.buildParameterDataLine2(line);
        assertEquals("error on mnemonic","VERS", result.getMnemonic());
        assertEquals("error on unit","", result.getUnit());
        assertEquals("error on value","2.0", result.getValue());
        assertEquals("error on description","CWLS log ASCII Standard -VERSION 2.0", result.description);
    }
    
    @Test
    public void testBuildParameterDataLine2_step() throws Exception {
        System.out.println("buildParameterDataLine2");
        String line = "STEP.M      100     : STEP";
        LASParameterDataLine result = LASFileReader.buildParameterDataLine2(line);
        assertEquals("error on mnemonic", "STEP", result.getMnemonic());
        assertEquals("error on unit", "M", result.getUnit());
        assertEquals("error on value", "100", result.getValue());
        assertEquals("error on description", "STEP", result.description);
    }
    
    @Test
    public void testBuildParameterDataLine2_gamma() throws Exception {
        System.out.println("buildParameterDataLine2");
        String line = "GR  .GAPI     45 310 01 00 : 9        GAMMA RAY";
        LASParameterDataLine result = LASFileReader.buildParameterDataLine2(line);
        assertEquals("error on mnemonic", "GR", result.getMnemonic());
        assertEquals("error on unit", "GAPI", result.getUnit());
        assertEquals("error on value", "45 310 01 00", result.getValue());
        assertEquals("error on description", "9        GAMMA RAY", result.description);
    }
}
