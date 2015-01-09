package LAS;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Alexandre Leuck
 */
public class LASData implements Iterable<String[]> {
    protected LASParameterDataSection logDefinition = new LASParameterDataSection(); // Column Definition (Curve)
    protected LASParameterDataSection logParameter = new LASParameterDataSection(); // Parameters
    //protected List<LASLogDataRecord> logRecords = new ArrayList<>(); // Data
    protected List<String[]> logRecords = new ArrayList<>();
    /**
     * @param index
     * @return parameterDataLine with info about the given column.
     */
    public LASParameterDataLine getColumnInfo(int index) {
        return logDefinition.getParameter(index);
    }
    /**
     * @return the number of columns. 
     */
    public int getColumnsCount() {
        return logDefinition.size();
    }

    @Override
    public Iterator<String[]> iterator() {
        return logRecords.iterator();
    }
}