package LAS;


import java.util.HashMap;
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
public class LASParameterDataSection_3 implements LASParameterDataSection {
    protected String title;
    protected List<String> lines;
    HashMap<String, LASParameterDataLine_3> parameters;
    public LASParameterDataSection_3() {}
    public Set<String> mnemonicSet() {
        return parameters.keySet();
    }
    @Override
    public boolean hasParameter(String mnemonic) {
        return parameters.containsKey(mnemonic);
    }
    @Override
    public LASParameterDataLine getParameter(String mnemonic) {
        return parameters.get(mnemonic);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public List<String> getLines() {
        return lines;
    }
}
