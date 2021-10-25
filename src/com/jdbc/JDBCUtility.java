package com.jdbc;

import java.sql.*;
import java.util.*;

public class JDBCUtility {

	private String url, userID, pwd, header, fileName;
	private Connection con;

	private void setConnection() throws Exception {

		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, userID, pwd);

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

				row.append(rs.getString(i).trim());
				row.append(",");

			}
			row.append(rs.getString(noOfColumns).trim());
			al.add(row.toString());

		}
		rs.close();
		smt.close();

		return al;

	}

	
	public JDBCUtility(String url, String userID, String pwd) throws Exception {

		this.url = url;
		this.userID = userID;
		this.pwd = pwd;
		setConnection();

	}

}