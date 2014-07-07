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
public class LASSection {
    private final Map<String,LASParameterDataLine> parameters;

    public LASSection() {
        parameters = new HashMap<>();
    }
    protected void add(LASParameterDataLine data) {
        parameters.put(data.mnemonic, data);
        parameters.get(data.mnemonic);
    }
    public LASParameterDataLine get(String mnemonic) {
        return parameters.get(mnemonic);
    }

    public int size() {
        return parameters.size();
    }

    public boolean isEmpty() {
        return parameters.isEmpty();
    }

    public Object get(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object put(Object k, Object v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Object remove(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}