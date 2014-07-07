/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleuck
 */
public abstract class LASParameterDataLine {
    protected String mnemonic;
    protected String unit = "";
    protected String value = "";
    public String getMnemonic() {
        return mnemonic;
    }
    public String getUnit() {
        return unit;
    }
    public String getValue() {
        return value;
    }
}
