package LAS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aleuck
 */
public interface LASFileInterface {
    LASVersion getVersion();
    LASSection getWell();
    LASSection getSection(String sectionTitle);
}
