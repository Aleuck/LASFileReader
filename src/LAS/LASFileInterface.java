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
    LASParameterDataSection getVersion();
    LASParameterDataSection getWell();
    LASLogData[] getData();
}