/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

/**
 *
 * @author ukannan_np
 */
 import java.io.*;
import java.util.*;
public class ReadPropertiesFile{
    public static void main(String args[]) throws IOException {
     
      Properties prop1 = readPropertiesFile("ESBSQL.properties");
      System.out.println("username: "+ prop1.getProperty("SQL1"));
     /* Properties prop = readPropertiesFile("DB.properties");
      System.out.println("username: "+ prop.getProperty("username"));
      System.out.println("password: "+ prop.getProperty("password"));
       System.out.println("SqlServer: "+ prop.getProperty("SqlServer"));*/
   }
   public static Properties readPropertiesFile(String fileName) throws IOException {
      FileInputStream fis = null;
      Properties prop = null;
      try {
         fis = new FileInputStream(fileName);
         prop = new Properties();
         prop.load(fis);
      } catch(FileNotFoundException fnfe) {
         fnfe.printStackTrace();
      } catch(IOException ioe) {
         ioe.printStackTrace();
      } finally {
         fis.close();
      }
      return prop;
   }
}