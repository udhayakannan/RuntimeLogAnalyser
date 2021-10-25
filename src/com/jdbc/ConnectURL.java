/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdbc;

/**
 *
 * @author ukannan_np
 */
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class ConnectURL {

String connectionUrl = "jdbc:sqlserver://172.19.91.251:1433;databaseName=ESB_NONPROD_RETAIL;user=esbdbusr;password=Primark18";
Connection con;
Statement stmt;
ResultSet rs;
int SignLibId, BatchNum, BatchId, SignId;

public void SQLServerConnection1() throws SQLException, IOException {

// DB Connectivity details

con = DriverManager.getConnection(connectionUrl);
stmt = con.createStatement();

rs = stmt.executeQuery(
"select GLOBALTRANSACTIONID, max_auditts,min_auditts,\n" +
"CONVERT(varchar(12), cast(DATEADD(MILLISECOND, DATEDIFF(MILLISECOND, min_auditts,max_auditts), 0) as time(3)), 114) AS elapsed_time from (\n" +
"SELECT distinct (GLOBALTRANSACTIONID) as GLOBALTRANSACTIONID, \n" +
"min(cast(AUDIT_TIMESTAMP as time(3))) as min_auditts,\n" +
"max(cast(AUDIT_TIMESTAMP as time(3))) as max_auditts\n" +
"    FROM [ESB_NONPROD_RETAIL].[dbo].ESB_AUDIT_LOGGER with (nolock) \n" +
"  where 1 = 1\n" +
"  and INTERFACE_ID IN ('1110','1156','1227','1189')\n" +
"--  and GLOBALTRANSACTIONID = '0000d4d6-16e3-11ea-ab80-ac135b710000'\n" +
" and AUDIT_TIMESTAMP >= '2020-06-04 14:05:00.000'\n" +
"and AUDIT_TIMESTAMP <= '2020-06-04 15:05:00.000'\n" +
"  group by GLOBALTRANSACTIONID)a\n" +
"  order by GLOBALTRANSACTIONID asc ");
ResultSetMetaData rsmd = rs.getMetaData();
int noOfColumns = rsmd.getColumnCount();
while (rs.next()) {


StringBuffer row = new StringBuffer();

			for (int i = 1; i < noOfColumns; i++) {

				row.append(rs.getString(i).trim());
				row.append(",");

			}
			row.append(rs.getString(noOfColumns).trim());
			
                        System.out.println(row.toString());


}
rs.close();
stmt.close();
con.close();

System.out.println("From DAO, connection obtained ");
}

public static void main(String[] args) throws SQLException, IOException {

ConnectURL pConnectURL = new ConnectURL();
pConnectURL.SQLServerConnection1();

}

public void Esbresultsfrmrms() throws FileNotFoundException, Exception {

        
        JDBC jdbc1 = new JDBC("jdbc:oracle:thin:@iecwxuvuodb320.primark.local:1521/SRV_PRF_MOM_SUPPORT.PRIMARK.LOCAL","PERF03","prf3321","sqlserver");

       
            ArrayList<String> data = new ArrayList();

            String query = "SELECT [INTERFACE_ID]\n" +
"      ,[INTERFACENAME]\n" +
"      ,[BROKERNAME]\n" +
"      ,[EXECUTIONGROUP]\n" +
"      ,[QUEUEMANAGER],\n" +
"	  COUNT(*) AS TOTAL\n" +
"  FROM [ESB_NONPROD_RETAIL].[dbo].[ESB_AUDIT_LOGGER] WITH (NOLOCK)\n" +
"  WHERE 1=1\n" +
"    AND [AUDITTYPE] = 'RIB Consumer Request'\n" +
"	AND [AUDIT_TIMESTAMP] >= '2020-06-09 11:45:00.0000000'\n" +
"	AND [AUDIT_TIMESTAMP] < '2020-06-09 12:45:00.0000000'\n" +
"GROUP BY [INTERFACE_ID]\n" +
"        ,[INTERFACENAME]\n" +
"        ,[BROKERNAME]\n" +
"        ,[EXECUTIONGROUP]\n" +
"        ,[QUEUEMANAGER]\n" +
"ORDER BY [INTERFACE_ID]\n" +
"        ,[INTERFACENAME]\n" +
"        ,[BROKERNAME]\n" +
"        ,[EXECUTIONGROUP]\n" +
"        ,[QUEUEMANAGER]";

            data = jdbc1.collectData(query);

            
            System.out.println("RMS Data:" + data);
            for (String line : data) {

               

                System.out.println("ItemId =" + line);
                

            }
        }
        
public void SQLServerConnection(String Hostname,String database,String User,String pwd,String sql) throws SQLException, IOException {

// DB Connectivity details
connectionUrl="jdbc:sqlserver://"+Hostname+";databaseName="+database+";user="+User+";password="+pwd;
con = DriverManager.getConnection(connectionUrl);
stmt = con.createStatement();
rs = stmt.executeQuery(sql);
ResultSetMetaData rsmd = rs.getMetaData();
int noOfColumns = rsmd.getColumnCount();
while (rs.next()) {


StringBuffer row = new StringBuffer();

			for (int i = 1; i < noOfColumns; i++) {

				row.append(rs.getString(i).trim());
				row.append(",");

			}
			row.append(rs.getString(noOfColumns).trim());
			
                        System.out.println(row.toString());


}
rs.close();
stmt.close();
con.close();

System.out.println("From DAO, connection obtained ");
    
}
}
 
