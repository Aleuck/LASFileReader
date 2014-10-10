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
 * @author aleuck
 */
public abstract class LASFile implements LASFileInterface {
    protected LASParameterDataSection version;
    protected LASParameterDataSection well;
    protected LASParameterDataSection curve;
    protected LASParameterDataSection parameters;
    
    @Override
    public LASParameterDataSection getWell() {
        return well;
    }
}