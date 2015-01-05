package LAS;

import java.util.ArrayList;
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
public class LASLogData {
    protected LASParameterDataSection logParameter = new LASParameterDataSection();
    protected LASParameterDataSection logDefinition = new LASParameterDataSection();
    protected List<LASLogDataRecord> logRecords = new ArrayList<>();
}
