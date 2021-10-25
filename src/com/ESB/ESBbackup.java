/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ESB;

import com.Logdetails.Accesslog;
import static com.bean.ReadPropertiesFile.readPropertiesFile;
import com.jdbc.JDBC;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import oracle.net.aso.p;

/**
 *
 * @author ukannan_np
 */
public class ESBbackup {
    
     public static void main(String[] args) throws Exception {
        ESBbackup esb=new ESBbackup();
        esb.Esbbackupfrmsql();
    }
     public void Esbbackupfrmsql() throws FileNotFoundException, Exception {
        

        Properties prop = readPropertiesFile("DB.properties");
        Properties prop1 = readPropertiesFile("ESBbkp.properties");
        
        JDBC jdbc = new JDBC(prop.getProperty("SqlServer"), prop.getProperty("username"), prop.getProperty("password"), "sqlserver");
        ArrayList<String> data = new ArrayList();
      //  String stats = "",check=tvalidation.toLowerCase(Locale.ITALY),precount="",postcount=" ",statsdetail=" ",prevalind=" ",postvalind=" ";

        
        JDBC jdbc1 = new JDBC(prop.getProperty("perfServer"), prop.getProperty("perfusername"), prop.getProperty("perfpassword"), "oracle");
       for (int i=1;i<2;i++)
       {
           String sql="SQL"+i;
        String data1=prop1.getProperty(sql);
         //stats=prop1.getProperty(sql).split(";")[0];
        data = jdbc.collectData(data1);
        PreparedStatement stmt = jdbc1.insert(prop1.getProperty("insert1"));
        System.out.println("ESB Data:" + data);
        for (String line : data) {
         

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
        
        stmt.setString(1,"00001860-46DA-42B3-B142-1BFA668298AA");
            stmt.setInt(2,10);
            stmt.setString(3,"AuditMessage");
            stmt.setString(4,"42:18.6");
            stmt.setString(5,"1635e032-7aad-11eb-8b42-ac135b730000");
            stmt.setString(6,"16375412-7aad-11eb-8b42-ac135b730000");
            stmt.setString(7,"null");
            stmt.setString(8,"Oracle Retail");
            stmt.setString(9,"ER");
            stmt.setString(10,"Sales & Returns from Entreprise Returns To RESA");
            stmt.setString(11,"ENTERPRISE RETURNS");
            stmt.setString(12,"RESA");
             stmt.setString(13,"CDM Publisher Request");
            stmt.setString(14,"IIB");
            stmt.setString(15,"IDS3BRK2");
            stmt.setString(16,"UNIX");
            stmt.setString(17,"TcsExecutionGroup2");
            stmt.setString(18,"QDS3BRK2");
            stmt.setString(19,"ER.ESB.SALESRETURNS");
            stmt.setString(20,"pib.enterprisereturns.salesreturns.ENTERPRISERETURNS_ESB_SALESRETURNS_PMF");
             stmt.setString(21,"NULL");
            stmt.setString(22,"<POS></POS>");
             stmt.setString(23,"1208, 546, 2020-12-22 07:33:26.710");
            stmt.setString(24,"NULL");
            stmt.setString(25,"NULL");
            stmt.setString(26,"42:18.6");
            stmt.setTimestamp(27,timestamp);

        
      /*      stmt.setString(1, line.split(",")[0]);
            stmt.setInt(2,Integer.parseInt(line.split(",")[1]));
            stmt.setString(3, line.split(",")[2]);
            stmt.setString(4, line.split(",")[3]);
            stmt.setString(5, line.split(",")[4]);
            stmt.setString(6, line.split(",")[5]);
            stmt.setString(7, line.split(",")[6]);
            stmt.setString(8, line.split(",")[7]);
            stmt.setString(9, line.split(",")[8]);
            stmt.setString(10, line.split(",")[9]);
            stmt.setString(11, line.split(",")[10]);
            stmt.setString(12, line.split(",")[11]);
             stmt.setString(13, line.split(",")[12]);
            stmt.setString(14, line.split(",")[13]);
            stmt.setString(15, line.split(",")[14]);
            stmt.setString(16, line.split(",")[15]);
            stmt.setString(17, line.split(",")[16]);
            stmt.setString(18, line.split(",")[17]);
            stmt.setString(19, line.split(",")[18]);
            stmt.setString(20, line.split(",")[19]);
             stmt.setString(21, line.split(",")[20]);
            stmt.setString(22, line.split(",")[21]);
             stmt.setString(23, line.split(",")[22]);
            stmt.setString(24, line.split(",")[23]);
            stmt.setString(25, line.split(",")[24]);
            stmt.setString(26, line.split(",")[25]);
            stmt.setTimestamp(27, timestamp);
           */
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
     }
