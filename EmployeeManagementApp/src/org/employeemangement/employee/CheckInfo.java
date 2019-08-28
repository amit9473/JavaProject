package org.employeemangement.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class CheckInfo
{

	static String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	static Connection connection = null;
	static ResultSet resultSet = null;
	static Statement statement = null;
	
	public static ResultSet check()
	{
		String sql = "select * "+
				     "from emp_mng_sys.employee_list ";
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return resultSet;
	}
}
