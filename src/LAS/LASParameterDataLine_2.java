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
public class LASParameterDataLine_2 implements LASParameterDataLine {
    protected String mnemonic = "";
    protected String value = "";
    protected String unit = "";
    protected String description = "";
    protected String format = "";
    protected List<String> assocList = new ArrayList<>();
    
    public LASParameterDataLine_2() throws Exception {
        // MNEM.UNIT   VALUE : DESCRIPTION {Format} | Assoc1,Assoc2 ...
//        Pattern regexPattern;
//        Matcher regexMatcher;
//        int periodIndex = 
//                line.contains(".") ? line.indexOf(".") : 0;
//        int spaceIndex = 
//                line.substring(periodIndex + 1).indexOf(" ") + periodIndex + 1;
//        int colonIndex = 
//                line.contains(":") ? line.indexOf(":") : line.length();
//        
//        mnemonic = line.substring(0, periodIndex).trim();
//        regexPattern = Pattern.compile("^[^.: \t]+$");
//        regexMatcher = regexPattern.matcher(mnemonic);
//        if (!regexMatcher.matches()) {
//            throw new Exception("Invalid parameter");
//        }
//        if (spaceIndex > (periodIndex + 1) && spaceIndex < colonIndex) {
//            unit = line.substring(periodIndex + 1, spaceIndex).trim();
//            regexPattern = Pattern.compile("^[^: \t]*$");
//            regexMatcher = regexPattern.matcher(unit);
//            if (!regexMatcher.matches()) {
//                throw new Exception("Invalid parameter");
//            }
//        }
//        if (spaceIndex < colonIndex) {
//            value = line.substring(spaceIndex, colonIndex).trim();
//            regexPattern = Pattern.compile("^[^:]*$");
//            regexMatcher = regexPattern.matcher(value);
//            if (!regexMatcher.matches()) {
//                throw new Exception("Invalid parameter");
//            }
//        }
        // TODO: get DESCRIPTION and what comes after.
    }

    @Override
    public String getMnemonic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getUnit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getFormat() {
        return format;
    }

    @Override
    public List<String> getAssoc() {
        return new ArrayList<>();
    }
}
