package LAS;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleuck
 */
public class LASFile implements LASFileInterface {
    protected LASVersion version;
    protected LASParameterDataSection version_section;
    protected LASParameterDataSection well_section;
    protected LASParameterDataSection curve_sections;
    protected LASParameterDataSection parameters_sections;
    protected List<LASLogData> data;
    
    @Override
    public LASVersion getVersion() {
        return version;
    }

    @Override
    public LASParameterDataSection getVersionSection() {
        return version_section;
    }
    
    @Override
    public LASParameterDataSection getWellSection() {
        return well_section;
    }

    @Override
    public List<LASLogData> getData() {
        return data;
    }
}