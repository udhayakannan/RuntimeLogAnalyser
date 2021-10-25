package com.jdbc;

import com.microsoft.sqlserver.jdbc.StringUtils;
import java.sql.*;
import java.util.*;

public class JDBC {

	private String url, userID, pwd, header, fileName,drvr,connection;
	private Connection con;

	private void setConnection() throws Exception {

		if(drvr.contains("oracle"))
                {
                Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, userID, pwd);
                }
                else if(drvr.contains("sqlserver"))
                {
                     connection=url+";user="+userID+";password="+pwd;
                    con = DriverManager.getConnection(connection);
                }
	}

	public void setHeader(String header) {

		this.header = header;
	}

	public void setFileName(String fileName) {

		this.fileName = fileName;
	}

	public ArrayList collectData(String sql) throws Exception {

		ArrayList<String> al = new ArrayList();
		Statement smt = con.createStatement();

		ResultSet rs = smt.executeQuery(sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		int noOfColumns = rsmd.getColumnCount();
		while (rs.next()) {

			StringBuffer row = new StringBuffer();

			for (int i = 1; i < noOfColumns; i++) {
                            String data="";
                            System.out.println("com.jdbc.JDBC.collectData()"+rs.getString(i));
                           boolean res =  StringUtils.isEmpty(rs.getString(i));
                          // boolean res=(rs.getString(i).trim().isEmpty() || rs.getString(i).length()==0);
                            if(!res)
                            {
                                data="false";
                            }
                            else if( rs.getString(i) == null)
                            {data="false";
                            }
                            else
                            {
                                data=rs.getString(i);
                            }
				row.append(rs.getString(i));
				row.append(",");

			}
			row.append(rs.getString(noOfColumns).trim());
			al.add(row.toString());

		}
		rs.close();
		smt.close();

		return al;

	}
public  PreparedStatement insert (String sql) throws Exception {

		
		Statement smt = con.createStatement();
                
                PreparedStatement stmt = con.prepareStatement(sql);


		return stmt;
		

	}
public  Statement update (String sql) throws Exception {

		
		Statement smt = con.createStatement();
                
                

		return smt;
		

	}
public  void commit () throws Exception {

		
		con.commit();

	}	


	public JDBC(String url, String userID, String pwd,String driver) throws Exception {

		this.url = url;
		this.userID = userID;
		this.pwd = pwd;
                this.drvr=driver;
		setConnection();

	}

}