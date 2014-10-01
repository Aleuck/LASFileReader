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
public abstract class LASSection {
    protected String title;
    protected List<String> lines;
    public String getTitle() {
        return title;
    }
    public List<String> getLines() {
        return new ArrayList<>(lines);
    }
}