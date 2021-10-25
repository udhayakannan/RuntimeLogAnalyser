/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ESB;

import static com.bean.ReadPropertiesFile.readPropertiesFile;
import com.jdbc.JDBC;
import com.jdbc.JDBCUtility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

/**
 *
 * @author ukannan_np
 */
public class Testresultesb {

    public void Esbresultsfrmrms() throws FileNotFoundException, Exception {

        JDBCUtility jdbc1 = new JDBCUtility("jdbc:oracle:thin:@iecwxuvuodb320.primark.local:1521/SRV_PRF_MOM_SUPPORT.PRIMARK.LOCAL", "PERF03", "prf3321");

        ArrayList<String> data = new ArrayList();

        String query = "select supplier_code from INTG.NB_INT_PRE_RCPT_PO_HEAD";

        data = jdbc1.collectData(query);

        System.out.println("RMS Data:" + data);
        for (String line : data) {

            System.out.println("ItemId =" + line);

        }
    }

    public void Esbresultsfrmsql(String testid,String testname,String tvalidation) throws FileNotFoundException, Exception {
        

        Properties prop = readPropertiesFile("DB.properties");
        Properties prop1 = readPropertiesFile("ESBSQL.properties");
        System.out.println("Query: " + prop1.getProperty("SQL3"));
        JDBC jdbc = new JDBC(prop.getProperty("SqlServer"), prop.getProperty("username"), prop.getProperty("password"), "sqlserver");
        ArrayList<String> data = new ArrayList();
        String stats = "",check=tvalidation.toLowerCase(Locale.ITALY),precount="",postcount=" ",statsdetail=" ",prevalind=" ",postvalind=" ";

        
        JDBC jdbc1 = new JDBC(prop.getProperty("perfServer"), prop.getProperty("perfusername"), prop.getProperty("perfpassword"), "oracle");
       for (int i=1;i<9;i++)
       {
           String sql="SQL"+i;
        String data1=prop1.getProperty(sql).split(";")[1];
         stats=prop1.getProperty(sql).split(";")[0];
        data = jdbc.collectData(data1);
        PreparedStatement stmt = jdbc1.insert(prop1.getProperty("insert1"));
        System.out.println("ESB Data:" + data);
        for (String line : data) {
         
if(check.contains("pre"))
{
    precount=line.split(",")[1];
    postcount="0";
    prevalind="Y";
    postvalind="N";
}
else{
    postcount=line.split(",")[1];
    prevalind="N";
    postvalind="Y";
}
String pattern = "dd-MMM-yy HH:mm:ss.000000000";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date1 = new Date();
        String dt = formatter.format(date1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Date:" + date);

        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        System.out.println("Timestamp:" + timestamp.toInstant().toString());
            stmt.setString(1, testname);
            stmt.setInt(2,Integer.parseInt(testid));
            stmt.setString(3, stats);
            stmt.setString(4, line.split(",")[0]);
            stmt.setString(5, precount);
            stmt.setString(6, postcount);
            stmt.setTimestamp(7, timestamp);
            stmt.setTimestamp(8, timestamp);
            stmt.setString(9, prevalind);
            stmt.setString(10, postvalind);
            stmt.addBatch();
            stmt.executeBatch();
            System.out.println("ItemId =" + line);

        }
       
        stmt.close();
       }
        /**
         *
         */

    }
    
  

    public void updatingdata(String testid,String testname,String tvalidation) throws IOException, Exception {
        
        String pattern = "dd-MMM-yy HH:mm:ss.000000000";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date1 = new Date();
        String dt = formatter.format(date1);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = simpleDateFormat.parse(dt);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Date:" + date);
        java.sql.Timestamp timestamp = new java.sql.Timestamp(date.getTime());
        System.out.println("Timestamp:" + timestamp.toInstant().toString());
        Properties prop = readPropertiesFile("DB.properties");
        Properties prop1 = readPropertiesFile("ESBSQL.properties");
        System.out.println("Query: " + prop1.getProperty("SQL3"));
        JDBC jdbc = new JDBC(prop.getProperty("SqlServer"), prop.getProperty("username"), prop.getProperty("password"), "sqlserver");
        ArrayList<String> data = new ArrayList();
        String stats = "",check=tvalidation.toLowerCase(),precount="",postcount=" ",statsdetail=" ",prevalind=" ",postvalind=" ";

        
        JDBC jdbc1 = new JDBC(prop.getProperty("perfServer"), prop.getProperty("perfusername"), prop.getProperty("perfpassword"), "oracle");
        for (int i=1;i<9;i++)
       {
           String sql="SQL"+i;
        String data1=prop1.getProperty(sql).split(";")[1];
         stats=prop1.getProperty(sql).split(";")[0];
        data = jdbc.collectData(data1);
        Statement stmt = jdbc1.update(prop1.getProperty("update1"));
        System.out.println("ESB Data:" + data);
     
        for (String line : data) {
       if(check.contains("pre"))
{
    precount=line.split(",")[1];
    prevalind="Y";
    postvalind="N";
}
else{
    postcount=line.split(",")[1];
    prevalind="N";
    postvalind="Y";
}
        String esbst=line.split(",")[0];
      //  System.out.println("update ESB_STATS set POST_STATS_COUNT = '"+postcount+"' , ModifiedDate = '"+dt+"' , postcheck= 'Y' where testname = '"+testname+"' and esb_modules = '"+stats+"' and esb_stats= '"+esbst+"'");
      String sqlup="update ESB_STATS set POST_STATS_COUNT = '"+postcount+"' , ModifiedDate = '"+dt+"' , postcheck= 'Y' where testname = '"+testname+"' and esb_modules = '"+stats+"' and esb_stats= '"+esbst+"'";  
         stmt.executeUpdate(sqlup);

        /*    stmt.setString(1, postcount);
            stmt.setTimestamp(2, timestamp);
            stmt.setString(3, "Y");
            stmt.setString(4, "IPT4");
            stmt.setString(5, stats);
            stmt.setString(6, line.split(",")[0]);
           stmt.executeUpdate();
          */  jdbc1.commit () ;
            

        }
      
        stmt.close();
       }
        /**
         *
         */


    }
}
