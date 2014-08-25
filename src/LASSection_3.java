
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleuck
 */
public class LASSection_3 extends LASSection {
    protected String title;
    public LASSection_3(LASSection section, String delimiter) {
        super(section);
        String newTitle = getTitle();
        int barIndex = newTitle.indexOf("|");
        int spaceIndex = newTitle.indexOf(" ");
        barIndex = barIndex >= 0 ? barIndex : newTitle.length();
        spaceIndex = spaceIndex >= 0 ? spaceIndex : newTitle.length();
        int end = Math.min(barIndex, spaceIndex);
        title = newTitle.substring(0, end).trim();
    }
}
