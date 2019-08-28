package org.employeemangement.login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class CheckQuery
{
	
	static String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	static Connection connection = null;
	static ResultSet resultSet = null;
	static Statement statement = null;
	
	public static ResultSet check()
	{
		String sql = "select admin_list.admin_id, admin_list.admin_password, employee_list.phone_number, employee_list.email_id "+
				     "from emp_mng_sys.admin_list, emp_mng_sys.employee_list "+ 
				     "where admin_list.admin_emp_id=employee_list.emp_id;";
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
