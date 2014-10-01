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
    protected double startDepth;
    protected double endDepth;
    protected double step;
    protected LASSection well;
    private final Map<String,LASSection> sections;
    
    public LASFile() {
        sections = new HashMap<>();
    }
    public LASSection getWell() {
        return well;
    }
}