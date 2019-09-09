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
		String sql = "select first_name, middle_name, last_name, admin_id as username, admin_password as password, phone_number, email_id "+
				     "from emp_mng_sys.admin_list "+ 
				     "join emp_mng_sys.employee_list " + 
				     "on admin_list.admin_emp_id = employee_list.emp_id;";
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
	
	/*
	public static void main(String[] args) 
	{
		resultSet = check();
		try
		{
			while(resultSet.next())
			{
				String mName = resultSet.getString("middle_name");
				if(strCheck(mName))
					System.out.println("1");
			}
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				connection.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private static boolean strCheck(String s)
	{
		if(s == null || s.isEmpty())
			return true;
		return false;
	}
	*/
	
}
