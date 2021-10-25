/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logdetails;

/**
 *
 * @author ukannan_np
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

 

public class Accesslog {
    public static void main(String[] args) {
        String currentDirectory = System.getProperty("user.dir");
        File folder = new File(currentDirectory);
        Accesslog listFiles = new Accesslog();
        System.out.println("reading files before Java8 - Using listFiles() method");
        listFiles.listAllFiles(folder);
    }

 

    // Uses listFiles method
    public void listAllFiles(File folder) {
        System.out.println("In listAllfiles(File) method");
        File[] fileNames = folder.listFiles();
        for (File file : fileNames) {
            // if directory call the same method again
            if (file.isDirectory()) {
                listAllFiles(file);
            } else {
                try {
                    readContent(file);
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

 

            }
        }
    }

 

    public void readContent(File file) throws IOException {
        System.out.println("read file " + file.getCanonicalPath());
       
            BufferedReader br = new BufferedReader(new FileReader(file));
            String strLine;
            // Read lines from the file, returns null when end of stream
            // is reached
            while ((strLine = br.readLine()) != null) {
                System.out.println("Line is - " + strLine);
            }
        }
    }

 

