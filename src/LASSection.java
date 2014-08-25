
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
public class LASSection implements LASSectionInterface {
    private String title;
    private List<String> lines;
    public LASSection(String secTitle, List<String> secLines) {
        title = secTitle;
        lines = secLines;
    }
    public LASSection(LASSection sec) {
        this(sec.getTitle(), sec.getLines());
    }
    public LASSection(List<String> sec) {
        this(
            sec.get(0).trim().substring(1), // removing the ~
            sec.subList(1, sec.size())
        );
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public List<String> getLines() {
        return new ArrayList<>(lines);
    }
}