
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
public class LASParameterDataLine_3 implements LASParameterDataLine {
    protected String mnemonic;
    protected String description = "";
    protected String format = "";
    protected List<String> assocList;
    private String unit;
    private String value;
    
    public LASParameterDataLine_3(String line, String delimiter) throws Exception {
        // MNEM.UNIT   VALUE : DESCRIPTION {Format} | Assoc1,Assoc2 ...
        Pattern regexPattern;
        Matcher regexMatcher;
        int mnemonicEnd = 
                line.contains(".") ? line.indexOf(".") : 0;
        int unitEnd = 
                line.indexOf(" ", mnemonicEnd);
        int colonIndex = 
                line.contains(":") ? line.lastIndexOf(":") : line.length();
        int barIndex = 
                line.contains("|") ? line.lastIndexOf("|") : line.length();
        int leftBracesIndex = barIndex-1;
        int rightBracketsIndex = barIndex;
        if (line.contains("{") && line.contains("}")) {
            rightBracketsIndex = line.lastIndexOf("}",barIndex) ;
            leftBracesIndex = line.lastIndexOf("{",barIndex);
        }
        
        mnemonic = line.substring(0, mnemonicEnd).trim();
        regexPattern = Pattern.compile("^[^.: \t{}[/]|]+$");
        regexMatcher = regexPattern.matcher(mnemonic);
        if (!regexMatcher.matches()) {
            throw new Exception("Invalid parameter");
        }
        if (unitEnd > (mnemonicEnd + 1) && unitEnd < colonIndex) {
            unit = line.substring(mnemonicEnd + 1, unitEnd).trim();
            regexPattern = Pattern.compile("^[^: \t{}|]*$");
            regexMatcher = regexPattern.matcher(unit);
            if (!regexMatcher.matches()) {
                throw new Exception("Invalid parameter");
            }
        }
        if (unitEnd < colonIndex) {
            value = line.substring(unitEnd, colonIndex).trim();
            regexPattern = Pattern.compile("^[^:{}|]*$");
            regexMatcher = regexPattern.matcher(value);
            if (!regexMatcher.matches()) {
                throw new Exception("Invalid parameter");
            }
        }
        format = line.substring(leftBracesIndex + 1, rightBracketsIndex);
        // TODO: get DESCRIPTION and what comes after.
    }
    public LASParameterDataLine_3(String line) throws Exception {
        this(line , " ");
    }

    @Override
    public String getMnemonic() {
        return mnemonic;
    }

    @Override
    public String getUnit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getValue() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getFormat() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getAssoc() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
