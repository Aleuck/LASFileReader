
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleuck
 */
public interface LASParameterDataLine {
    String getMnemonic();
    String getUnit();
    String getValue();
    String getFormat();
    List<String> getAssoc();
}
