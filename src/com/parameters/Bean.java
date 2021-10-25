/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parameters;

import static com.parameters.Bean.path;

/**
 *
 * @author ukannan_np
 */
public interface Bean {
    String currentDir = System.getProperty("user.dir");
    
     String path = currentDir;
       String jarexec="java -jar Wipro_NMONVisualizer_2018-11-05.jar com.ibm.nmon.ReportGenerator ";
}
