
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
public abstract class LASFile implements LASFileInterface {
    protected double startDepth;
    protected double endDepth;
    protected double step;
    protected String wellName;
    protected String wellCompany;
    protected String wellField;
    protected String wellLocation;
    protected String wellState;
    protected String wellCountry;
    protected String wellServiceCompany;
    protected String date;
    private final Map<String,LASSection> sections;
    
    public LASFile() {
        sections = new HashMap<>();
    }
    
    @Override
    public String getCompany() {
        return wellCompany;
    }

    @Override
    public String getWell() {
        return wellName;
    }

    @Override
    public String getField() {
        return wellField;
    }

    @Override
    public String getLocation() {
        return wellLocation;
    }

    @Override
    public String getState() {
        return wellState;
    }

    @Override
    public String getCountry() {
        return wellCountry;
    }

    @Override
    public String getServiceCompany() {
        return wellServiceCompany;
    }

    @Override
    public String getDate() {
        return date;
    }

    @Override
    public LASVersion getVersion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public LASSection getSection(String sectionTitle) {
        return sections.get(sectionTitle);
    }
}