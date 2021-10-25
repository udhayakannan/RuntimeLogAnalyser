/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;
import com.parameters.Bean;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**
 *
 * @author ukannan_np
 */
public class Filecreation implements Bean {
  

 


    public void makedirectory(String filename)  {
               
         
     //"\\nmon"
        // Creating a File object
        File file = new File(path+filename);
        // Creating the directory
        boolean bool = file.mkdir();
        if (bool) {
            System.out.println("Directory created successfully");
        } else {
            System.out.println("Sorry couldn’t create specified directory");
        }
    }
}

