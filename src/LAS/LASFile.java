package LAS;


import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alexandre Leuck
 */
public class LASFile implements LASFileInterface {
    protected LASVersion version;
    protected LASParameterDataSection version_section;
    protected LASParameterDataSection well_section;
    protected Map<String,LASData> data = new HashMap<>();
    // LAS 2.0 specific
        protected LASParameterDataSection other_section;
    // LAS 3.0 specific
        protected LASParameterDataSection core_section;
        protected LASParameterDataSection inclinometry_section;

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
    /**
     * LAS 2.0 specific
     * @return 
     */
    public LASParameterDataSection getOtherSection() {
        return other_section;
    }
    /**
     * LAS 3.0 specific
     * @return 
     */
    public LASParameterDataSection getCoreSection() {
        return core_section;
    }
    /**
     * LAS 3.0 specific
     * @return 
     */
    public LASParameterDataSection getInclinometrySection() {
        return inclinometry_section;
    }

    @Override
    public LASData getData(String title) {
        return data.get(title);
    }
    @Override
    public LASData getData() {
        return data.get("Log");
    }
}