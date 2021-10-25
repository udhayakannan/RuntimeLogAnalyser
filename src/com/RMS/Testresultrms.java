/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.RMS;

import com.ESB.*;
import com.jdbc.Csvcreation;
import com.jdbc.JDBCUtility;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author ukannan_np
 */
public class Testresultrms {

    public void Rmsresults(String starttime,String Endtime) throws FileNotFoundException, Exception {

        
        JDBCUtility jdbc1 = new JDBCUtility("jdbc:oracle:thin:@iecwxuvuodb320.primark.local:1521/SRV_PRF_MOM_SUPPORT.PRIMARK.LOCAL","PERF03","prf3321");

       Csvcreation csv=new Csvcreation("RMSResult.txt",true);
       csv.Ofile();
       csv.Wfile("RMS Test Results Date - "+starttime.substring(0,10));
       csv.Wfile("Scenario"+","+"Starttime"+","+"Endtime"+","+"DB Count");
            ArrayList<String> data = new ArrayList();

            String query = "select nvl(count(*),0) from item_master where  ITEM_DESC_SECONDARY like 'Test%' and to_char(LAST_UPDATE_DATETIME, 'YYYYMMDDHH24MISS') > "+starttime+"  and to_char(LAST_UPDATE_DATETIME, 'YYYYMMDDHH24MISS') <"+ Endtime;

            data = jdbc1.collectData(query);

            
            System.out.println("RMS Data:" + data);
            for (String line : data) {

               csv.Wfile("Item Master"+","+starttime+","+Endtime+","+line);

                System.out.println("ItemId =" + line);
              
            }
            
            ArrayList<String> data1 = new ArrayList();

            String query1 = "select nvl(count(*),0) from RMS.TSFHEAD where COMMENT_DESC > '"+starttime+"' and COMMENT_DESC < '"+Endtime+"'";

            data1 = jdbc1.collectData(query1);

            
            System.out.println("RMS Data:" + data1);
            for (String line : data1) {

               csv.Wfile("Transferorder"+","+starttime+","+Endtime+","+line);

                System.out.println("Transferorder =" + line);
              
            }
csv.Cfile();
    }
        

    
}
