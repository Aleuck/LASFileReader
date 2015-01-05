package LAS;


import java.util.ArrayList;
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
public class LASParameterDataSection implements Iterable {
    protected String title;
    protected HashMap<String, Integer> parametersMap = new HashMap<>();
    protected List<LASParameterDataLine> parameters = new ArrayList<>();
    public String getTitle() {
        return title;
    }
    public boolean hasParameter(String mnemonic) {
        return parametersMap.containsKey(mnemonic);
    }
    public Set<String> mnemonicSet() {
        return parametersMap.keySet();
    }
    public LASParameterDataLine getParameter(int index) {
        return parameters.get(index);
    }
    public LASParameterDataLine getParameter(String mnemonic) {
        return getParameter(parametersMap.get(mnemonic));
    }
    protected void addParameter(LASParameterDataLine parameterDataLine) {
        int i = parameters.size();
        parameters.add(parameterDataLine);
        parametersMap.put(parameterDataLine.getMnemonic(), i);
    }

    @Override
    public Iterator iterator() {
        return parameters.iterator();
    }
}