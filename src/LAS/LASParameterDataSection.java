package LAS;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleuck
 */
public class LASParameterDataSection {
    protected String title;
    protected HashMap<String, LASParameterDataLine> parameters = new HashMap<>();
    public Set<String> mnemonicSet() {
        return parameters.keySet();
    }
    public boolean hasParameter(String mnemonic) {
        return parameters.containsKey(mnemonic);
    }
    public LASParameterDataLine getParameter(String mnemonic) {
        return parameters.get(mnemonic);
    }

    public String getTitle() {
        return title;
    }
    protected void addParameter(LASParameterDataLine parameterDataLine) {
        parameters.put(parameterDataLine.getMnemonic(), parameterDataLine);
    }
}