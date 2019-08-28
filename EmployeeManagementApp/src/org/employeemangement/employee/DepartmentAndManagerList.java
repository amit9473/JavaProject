package org.employeemangement.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@SuppressWarnings("serial")
@WebServlet("/DepartmentAndManagerList")
public class DepartmentAndManagerList extends GenericServlet
{
	
	static String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
	static Connection connection = null;
	static String sql = null;
	static ResultSet resultSet = null;
	static Statement statement = null;
	
	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	{
		PrintWriter out = res.getWriter();
		resultSet = dmList();
		try 
		{
			out.println("----------------------------------------------------");
		    out.printf("%-3s %-10s %-15s %-25s", "Id", "Name", "Department Id", "Department Name");
		    out.println();
		    out.println("----------------------------------------------------");
			while(resultSet.next())
			{
				int empId = Integer.parseInt(resultSet.getString("emp_id"));
				String name = resultSet.getString("name");
				int departmentId = Integer.parseInt(resultSet.getString("department_id"));
				String departmentName = resultSet.getString("department_name");
				
				out.format("%-3d %-10s %-15d %-25s", empId, name, departmentId, departmentName);
			    out.println();

			}

		    out.println("----------------------------------------------------");
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
	
	public static ResultSet dmList()
	{
		String sql = "SELECT mgr_emp_id AS emp_id, mgr_name AS name, mgr_dept_id AS department_id, dept_name AS department_name\r\n" + 
					 "FROM emp_mng_sys.mgr_list AS mgr\r\n" + 
					 "JOIN emp_mng_sys.dept_list AS dept\r\n" + 
					 "ON mgr.mgr_dept_id = dept.dept_id\r\n" + 
					 "ORDER BY mgr_emp_id";
		
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
