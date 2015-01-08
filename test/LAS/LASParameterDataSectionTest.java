/*
 * Copyright (C) 2015 aleuck
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package LAS;

import java.util.Iterator;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aleuck
 */
public class LASParameterDataSectionTest {
    
    public LASParameterDataSectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTitle method, of class LASParameterDataSection.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        LASParameterDataSection instance = new LASParameterDataSection();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
    }

    /**
     * Test of hasParameter method, of class LASParameterDataSection.
     */
    @Test
    public void testHasParameter() {
        System.out.println("hasParameter");
        String mnemonic = "";
        LASParameterDataSection instance = new LASParameterDataSection();
        boolean expResult = false;
        boolean result = instance.hasParameter(mnemonic);
        assertEquals(expResult, result);
    }

    /**
     * Test of mnemonicSet method, of class LASParameterDataSection.
     */
    @Test
    public void testMnemonicSet() {
        System.out.println("mnemonicSet");
        LASParameterDataSection instance = new LASParameterDataSection();
        Set<String> expResult = null;
        Set<String> result = instance.mnemonicSet();
        assertTrue(result.isEmpty());
    }

    /**
     * Test of getParameter method, of class LASParameterDataSection.
     */
    @Test
    public void testGetParameter_int() {
        System.out.println("getParameter");
        int index = 0;
        LASParameterDataSection instance = new LASParameterDataSection();
        LASParameterDataLine expResult = null;
        LASParameterDataLine result = instance.getParameter(index);
        assertEquals(expResult, result);
    }

    /**
     * Test of getParameter method, of class LASParameterDataSection.
     */
    @Test
    public void testGetParameter_String() {
        System.out.println("getParameter");
        String mnemonic = "";
        LASParameterDataSection instance = new LASParameterDataSection();
        LASParameterDataLine expResult = null;
        LASParameterDataLine result = instance.getParameter(mnemonic);
        assertEquals(expResult, result);
    }

    /**
     * Test of addParameter method, of class LASParameterDataSection.
     */
    @Test
    public void testAddParameter() {
        System.out.println("addParameter");
        LASParameterDataLine parameterDataLine = new LASParameterDataLine();
        LASParameterDataSection instance = new LASParameterDataSection();
        instance.addParameter(parameterDataLine);
    }
    
    @Test
    public void testAddParameter_then_hasParameter() {
        System.out.println("addParameter then hasParameter");
        LASParameterDataLine parameterDataLine = new LASParameterDataLine();
        LASParameterDataSection instance = new LASParameterDataSection();
        parameterDataLine.mnemonic = "WRAP";
        parameterDataLine.value = "NO";
        instance.addParameter(parameterDataLine);
        assertTrue(instance.hasParameter("WRAP"));
        assertEquals(parameterDataLine, instance.getParameter("WRAP"));
    }

    /**
     * Test of iterator method, of class LASParameterDataSection.
     */
    @Test
    public void testIterator() {
        System.out.println("iterator");
        LASParameterDataSection instance = new LASParameterDataSection();
        Iterator result = instance.iterator();
        assertFalse(result.hasNext());
    }
    
}
