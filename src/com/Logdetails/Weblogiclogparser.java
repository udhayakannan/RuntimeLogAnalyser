/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Logdetails;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author ukannan_np
 */
public class Weblogiclogparser {
     public void rmsparse(String logfile) throws IOException 
 {   
    
BufferedReader reader;
BufferedWriter writer;
BufferedWriter writer1;
BufferedWriter writer2;
HashSet<String> uniqueSets;
     uniqueSets = new HashSet<String>();
List<String> list = new ArrayList<String>();
String[] fileResposne = null;

writer1 = new BufferedWriter(new FileWriter("ErrorDetails.csv"));
writer2 = new BufferedWriter(new FileWriter("ErrorCount.csv"));

StringBuilder sb = new StringBuilder();
reader = new BufferedReader(new FileReader(logfile));
writer = new BufferedWriter(new FileWriter("ErrorCode.csv"));

String line;
while ((line = reader.readLine()) != null) {
     String buffer = line.toLowerCase();
      if (buffer.contains("[error]")) {
fileResposne = line.split("]");
writer.write(fileResposne[0].substring(1, 20) + "," + fileResposne[3].substring(2, 11));
writer.write(System.getProperty("line.separator"));
uniqueSets.add(fileResposne[3].substring(2, 11));
list.add(fileResposne[3].substring(2, 11));
if (fileResposne.length == 15) {
writer1.write(fileResposne[0].substring(1, 20) + "," + fileResposne[3].substring(2, 11) + ","
+ fileResposne[13] + "," + fileResposne[14]);
writer1.write(System.getProperty("line.separator"));

} else if (fileResposne.length == 14) {
writer1.write(fileResposne[0].substring(1, 20) + "," + fileResposne[3].substring(2, 11) + ","
+ fileResposne[13]);
writer1.write(System.getProperty("line.separator"));

} else {
writer1.write(fileResposne[0].substring(1, 20) + "," + fileResposne[3].substring(6, 11) + ","
+ fileResposne[12]);
writer1.write(System.getProperty("line.separator"));

}

}
}

writer2.write("The unique Error Codes Present in the logs are:" + uniqueSets.size());
writer2.write(System.getProperty("line.separator"));
writer2.write("The Unique Error Codes are:");
writer2.write(System.getProperty("line.separator"));
for (String h : uniqueSets) {
writer2.write(h);
writer2.write(System.getProperty("line.separator"));
}
writer2.write("The occurrence of Each Error Codes are:");
writer2.write(System.getProperty("line.separator"));
Set<String> distinct = new HashSet<String>(list);
for (String s : distinct) {

writer2.write(s + ": " + Collections.frequency(list, s));
writer2.write(System.getProperty("line.separator"));
}
reader.close();
writer.close();
writer1.close();
writer2.close();

    }

}
