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

import java.util.List;
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
public class LASParameterDataLineTest {
    
    public LASParameterDataLineTest() {
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
     * Test of getMnemonic method, of class LASParameterDataLine.
     */
    @Test
    public void testGetMnemonic() {
        System.out.println("getMnemonic");
        LASParameterDataLine instance = new LASParameterDataLine();
        String expResult = "";
        String result = instance.getMnemonic();
        assertEquals(expResult, result);
    }

    /**
     * Test of getUnit method, of class LASParameterDataLine.
     */
    @Test
    public void testGetUnit() {
        System.out.println("getUnit");
        LASParameterDataLine instance = new LASParameterDataLine();
        String expResult = "";
        String result = instance.getUnit();
        assertEquals(expResult, result);
    }

    /**
     * Test of getValue method, of class LASParameterDataLine.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        LASParameterDataLine instance = new LASParameterDataLine();
        String expResult = "";
        String result = instance.getValue();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFormat method, of class LASParameterDataLine.
     */
    @Test
    public void testGetFormat() {
        System.out.println("getFormat");
        LASParameterDataLine instance = new LASParameterDataLine();
        String expResult = "";
        String result = instance.getFormat();
        assertEquals(expResult, result);
    }

    /**
     * Test of getAssoc method, of class LASParameterDataLine.
     */
    @Test
    public void testGetAssoc() {
        System.out.println("getAssoc");
        LASParameterDataLine instance = new LASParameterDataLine();
        List<String> result = instance.getAssoc();
        assertTrue(result.isEmpty());
    }
    
}
