package LAS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleuck
 */
public class LASFile_3 extends LASFile {
    @Override
    public LASVersion getVersion() {
        return LASVersion.v3_0;
    }
    @Override
    public LASSection getSection(String sectionTitle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}