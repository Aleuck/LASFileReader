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
    protected LASParameterDataSection versionSection;
    protected LASParameterDataSection wellSection;
    protected Map<String,LASLogData> data = new HashMap<>();
    // LAS 2.0 specific
        protected LASParameterDataSection other_section;
    // LAS 3.0 specific
    //    protected LASParameterDataSection core_section;
    //    protected LASParameterDataSection inclinometry_section;

    @Override
    public LASVersion getVersion() {
        return version;
    }

    @Override
    public LASParameterDataSection getVersionSection() {
        return versionSection;
    }
    
    @Override
    public LASParameterDataSection getWellSection() {
        return wellSection;
    }
    /**
     * LAS 2.0 specific
     * @return 
     */
    @Override
    public LASParameterDataSection getOtherSection() {
        return other_section;
    }
//    /**
//     * LAS 3.0 specific
//     * @return 
//     */
//    public LASParameterDataSection getCoreSection() {
//        return core_section;
//    }
//    /**
//     * LAS 3.0 specific
//     * @return 
//     */
//    public LASParameterDataSection getInclinometrySection() {
//        return inclinometry_section;
//    }

    @Override
    public LASLogData getData(String title) {
        return data.get(title);
    }
    @Override
    public LASLogData getData() {
        return data.get("Log");
    }
}