package com.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
private final String url="jdbc:mysql://127.0.0.1:3306/shopping";
private final String userName="root";
private final String password="root";
public Connection getMyConnection()throws Exception
{
	Connection con=null;
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection(url,userName,password);
	}catch(SQLException e)
	{System.out.println(e);}
	return con;
}
public void closeResource(Connection con) throws Exception {
	if(con!=null)
	{
		con.close();
	}
}
public void closeResource(Statement st) throws Exception {
	if(st!=null)
	{
		st.close();
	}
}
}