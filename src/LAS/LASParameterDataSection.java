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
 * @author Alexandre Leuck
 */
public class LASParameterDataSection implements Iterable {
    protected String title = "";
    private final HashMap<String, Integer> parametersMap = new HashMap<>();
    private final List<LASParameterDataLine> parameters = new ArrayList<>();
    public String getTitle() {
        return title;
    }
    /**
     * Checks whether given mnemonic exists in the section. 
     * @param mnemonic
     * @return true if there is a parameter with specified mnemonic
     */
    public boolean hasParameter(String mnemonic) {
        return parametersMap.containsKey(mnemonic);
    }
    /**
     * 
     * @return a {@link Set} with all available mnemonics.
     */
    public Set<String> mnemonicSet() {
        return parametersMap.keySet();
    }
    /**
     * 
     * @param index
     * @return {@link LASParameterDataLine} or null in case of invalid index.
     */
    public LASParameterDataLine getParameter(int index) {
        if (index < 0 || index >= parameters.size()) {
            return null;
        }
        return parameters.get(index);
    }
    /**
     * 
     * @param mnemonic
     * @return the last parameter with given mnemonic, or null if there is no correspondent parameter.
     */
    public LASParameterDataLine getParameter(String mnemonic) {
        Integer index = parametersMap.get(mnemonic);
        if (index == null) {
            return null;
        }
        return getParameter(index);
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