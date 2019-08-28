package org.jsp.employeedatamanagement.databaseconnectivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnectivityStatement 
{
	public static void query()
	{
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String sql = "select * from test.employee where EmpDept='Developer'";
		try 
		{
			Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if(resultSet.next())
			{
				int empId = resultSet.getInt("EmpId");
				String empName = resultSet.getString("EmpName");
				String empDept = resultSet.getString("EmpDept");
				int empAge = resultSet.getInt("EmpAge");
				double empSalary = resultSet.getDouble("EmpSalary");
				System.out.println("Employee details selected are as follows:");
				System.out.println("EmpId: "+empId+", EmpName: "+empName+", EmpDept: "+empDept+", EmpAge: "+empAge+", EmpSalary: "+empSalary);
			}
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("Exception caught");
		}
	}
	public static void updateInsert()
	{
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String sql = "insert into test.employee values(EmpId=16,EmpName='Nishant',EmpDept='Testing',EmpAge=32,EmpSalary=6.54)";
		try 
		{
			Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("Exception caught");
		}
	}
	public static void updateUpdate()
	{
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String sql = "update test.employee set EmpDept='Tester' where EmpDept='Testing'";
		try 
		{
			Connection connection = DriverManager.getConnection(url);
			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);
			connection.close();
		}
		catch (SQLException e)
		{
			System.out.println("Exception caught");
		}
	}
	public static void main(String[] args) 
	{
		updateInsert();
	}
}
