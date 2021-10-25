/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logdetails;

import com.UI.NmonAnalysis;
import com.parameters.Bean;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ukannan_np
 */
public class Nmonfile implements Bean{
    public void nmonreport(String start,String end) throws IOException
    {
    
        Process proc;
      String jar=jarexec+path+"\\nmon1 -s "+start+"-00 -e "+end+"-00 -g 60";
        proc = Runtime.getRuntime().exec(jar);
        InputStream in = proc.getInputStream();
        InputStream err = proc.getErrorStream();
        System.out.println("com.UI.NmonAnalysis.jButton2ActionPerformed() "+in.toString());
        System.out.println("com.UI.NmonAnalysis.jButton2ActionPerformed()"+err.toString());
    }
}
