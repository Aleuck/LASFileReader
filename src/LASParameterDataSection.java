
import java.util.Iterator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleuck
 */
public interface LASParameterDataSection extends LASSectionInterface {
    boolean hasParameter(String mnemonic);
    LASParameterDataLine getParameter(String mnemonic);
}
