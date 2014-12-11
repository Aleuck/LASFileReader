package LAS;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleuck
 */
public class LASParameterDataLine {
    protected String mnemonic = "";
    protected String value = "";
    protected String unit = "";
    protected String description = "";
    protected String format = "";
    protected List<String> assocList = new ArrayList<>();
    
    public LASParameterDataLine() {
    }

    public String getMnemonic() {
        return mnemonic;
    }

    public String getUnit() {
        return unit;
    }

    public String getValue() {
        return value;
    }

    public String getFormat() {
        return format;
    }

    public List<String> getAssoc() {
        return assocList;
    }
}
