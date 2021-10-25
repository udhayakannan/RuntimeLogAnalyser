/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ESB;

import com.Logdetails.Fileprocess;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jdbc.Csvcreation;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.FileNotFoundException; 
import java.io.FileReader; 
import java.io.FileWriter;
import java.io.IOException; 
/**
 *
 * @author ukannan_np
 */
public class TestdataExtractESB {
    
    public void dbservernode3() throws IOException
    {
        System.out.println("Input Stream");
         String host = "172.19.91.103";
        String user = "oramqftpfm";
        String password = "oramqftpfm123";
     //   String command="/home/psbabu/Automation.sh";
        connect1(host,user,password);
    }
    public void connect1(String host,String user,String password) throws IOException {
System.out.println (host+" Connection Establish");
   Date d = new Date();
   SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String starttime = f.format(new Date());

     
    /*   String host = "iecwxuvuapp322";
        String user = "orawls";
        String password = "SX1JBuoatA";*/
        String command = "ps -ef | grep java";
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(user,host, 22);
            System.out.println (host +" Connected");
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
            System.out.println (host +" Connected");
          
        Channel channel = session.openChannel("sftp");
ChannelSftp sftpChannel = (ChannelSftp) channel;
                sftpChannel.connect();
              //  String filename="Rcptcnf2020060907444651.xml"
              int k=51;
              int min = 10;
      int max = 90;
      //Generate random double value from 50 to 100 
      System.out.println("Random value in double from "+min+" to "+max+ ":");
      double random_double = Math.random() * (max - min + 1) + min;
      int num=(int)random_double;
              
      Fileprocess s = new Fileprocess();
                for(int i=1;i<12;i++)
                {
                    String dest="Rcptcnf20200708074446"+num+".xml";
sftpChannel.get("/home/oramqftpfm/05 ESB Test Data_backup/Randomizefiles/Rcptcnf20200609074446"+k+".xml", dest);
s.Download(host, user,password,dest,"/home/oramqftpfm/05 ESB Test Data_backup/Randomizefiles/New/");
                }       
                sftpChannel.exit();
        
        session.disconnect();
//csv.Wfile(host+",Pre check completed");
        System.out.println(host+" precheck Done!");
    //    csv.Cfile();
    }
    catch(Exception e)
    {
        System.err.println("Error: " + e);
    }
   
    }
    
    public void read() throws IOException
    {
     int ch; 
  
        // check if File exists or not 
        
        
        try
        { 
               int k=51;
              int min =100000;
      int max = 999999;
      //Generate random double value from 50 to 100 
      System.out.println("Random value in double from "+min+" to "+max+ ":");
     
      Fileprocess s = new Fileprocess();
                for(int i=1;i<12;i++)
                {
                    FileReader fr=null; 
        FileWriter fw=null;
                   double random_double = Math.random() * (max - min + 1) + min;
      int num=(int)random_double;
                    String dest="H:\\06_Code\\01_Java\\LogAnalyser10.0-master\\LogAnalyser10.0-master\\build\\New\\Rcptcnf2020070807"+num+".xml";
                    String sour="H:\\06_Code\\01_Java\\LogAnalyser10.0-master\\LogAnalyser10.0-master\\build\\New folder\\Rcptcnf20200609074446"+k+".xml";
                    System.out.println(sour);
            fr = new FileReader(sour); 
             fw=new FileWriter(dest); 
            k=k+1;
            while ((ch=fr.read())!=-1) 
        {
          //  System.out.print((char)ch); 
            fw.write((char)ch);
        }
              fr.close(); 
        fw.close();
                }
                
        } 
        catch (FileNotFoundException fe) 
        { 
            System.out.println("File not found"); 
        } 
  
        // read from FileReader till the end of file 
        
  
        // close the file 
      
    } 
        
    }

