package com.jdbc;



import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author udhaya
 */
public class Csvcreation{

    private String fileName;
    private boolean append;
    private PrintWriter out;
    
    public Csvcreation(String fileName, boolean append){
        this.fileName = fileName;
        this.append = append;
    
    
    }
        
    public void Ofile(){
        try{
            out = new PrintWriter(new BufferedWriter(new FileWriter(new File(fileName),append)));
         }catch(Exception e){
            e.printStackTrace();
	 }
    }
    
    public void Wfile(String currentLine){
                out.println(currentLine);  
        
    }
    
     
    public void Cfile(){
    
        out.flush();
        out.close();

    
    }
    
    
    
}