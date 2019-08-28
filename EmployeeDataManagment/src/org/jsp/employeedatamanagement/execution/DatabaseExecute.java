package org.jsp.employeedatamanagement.execution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.jsp.employeedatamanagement.databaseconnectivity.DatabaseConnectivityPrepared;
import org.jsp.employeedatamanagement.employee.Employee;
import org.jsp.employeedatamanagement.employee.EmployeeDetails;

public class DatabaseExecute extends Thread
{
	Scanner scanner = new Scanner(System.in);
	
	@Override
	public void run()
	{
		char choice = '\u0000';
		System.out.println("Welcome");
		do
		{
			databaseExecute();
			System.out.println("==============================================================================================================");
			System.out.println("Press y : to continue or any key to stop");
			System.out.println("Enter your choice:");
			choice = scanner.next().charAt(0);
		}while(choice == 'y' || choice == 'Y');
		System.out.println("You can shut the program down");
	}


	public void databaseExecute()
	{
		int input = 0;
		boolean status = false;
		System.out.println("Press 1 : To add Employee details");
		System.out.println("Press 2 : To pull Employee's details usind id");
		System.out.println("Press 3 : To pull Employee's details using age");
		System.out.println("Press 4 : To pull Employee's details using name");
		System.out.println("Press 5 : To pull Employee's details usind department");
		System.out.println("Press 6 : To delete Employee's details");
		System.out.println("Press 7 : To view Employee's list");
		System.out.println("Press 8 : To update any details");
		System.out.println("Enter your choice:");
		do 
		{
			try 
			{
				input = scanner.nextInt();
				status = true;
			} 
			catch (Exception e) 
			{
				System.err.println("Only integer input allowed");
				scanner.nextLine();
			} 
		} while (!status);
		System.out.println();
		switch (input) 
		{
			case 1: DatabaseConnectivityPrepared.executeInsert();
					break;
			case 2: try 
					{
						Connection connection = DatabaseConnectivityPrepared.connect();
						String sql = "Select * from test.employee where EmpId=?";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						System.out.println("Enter the id");
						preparedStatement.setInt(1, scanner.nextInt());
						ResultSet resultSet = preparedStatement.executeQuery();
						while(resultSet.next())
						{
							int id = resultSet.getInt("EmpId");
							String name = resultSet.getString("EmpName");
							String department = resultSet.getString("EmpDept");
							double Salary = resultSet.getDouble("EmpSalary");
							int age = resultSet.getInt("EmpAge");
							Employee employee = new Employee(id, age, name, department, Salary);
							System.out.println(employee);
						}
						connection.close();
					}
					catch (SQLException e)
					{
						System.err.println("Exception caught");
					}
					break;
			case 3: try 
					{
						Connection connection = DatabaseConnectivityPrepared.connect();
						String sql = "Select * from test.employee where EmpAge=?";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						System.out.println("Enter the age");
						preparedStatement.setInt(1, scanner.nextInt());
						ResultSet resultSet = preparedStatement.executeQuery();
						while(resultSet.next())
						{
							int id = resultSet.getInt("EmpId");
							String name = resultSet.getString("EmpName");
							String department = resultSet.getString("EmpDept");
							double Salary = resultSet.getDouble("EmpSalary");
							int age = resultSet.getInt("EmpAge");
							Employee employee = new Employee(id, age, name, department, Salary);
							System.out.println(employee);
						}
						connection.close();
					}
					catch (SQLException e)
					{
						System.err.println("Exception caught");
					}
					break;
			case 4: try 
					{
						Connection connection = DatabaseConnectivityPrepared.connect();
						String sql = "Select * from test.employee where EmpName=?";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						System.out.println("Enter the name");
						preparedStatement.setString(1, scanner.next());
						ResultSet resultSet = preparedStatement.executeQuery();
						while(resultSet.next())
						{
							int id = resultSet.getInt("EmpId");
							String name = resultSet.getString("EmpName");
							String department = resultSet.getString("EmpDept");
							double Salary = resultSet.getDouble("EmpSalary");
							int age = resultSet.getInt("EmpAge");
							Employee employee = new Employee(id, age, name, department, Salary);
							System.out.println(employee);
						}
						connection.close();
					}
					catch (SQLException e)
					{
						System.err.println("Exception caught");
					}
					break;
			case 5: try 
					{
						Connection connection = DatabaseConnectivityPrepared.connect();
						String sql = "Select * from test.employee where EmpDept=?";
						PreparedStatement preparedStatement = connection.prepareStatement(sql);
						System.out.println("Select the department");
						preparedStatement.setString(1, EmployeeDetails.setDepartment());
						ResultSet resultSet = preparedStatement.executeQuery();
						while(resultSet.next())
						{
							int id = resultSet.getInt("EmpId");
							String name = resultSet.getString("EmpName");
							String department = resultSet.getString("EmpDept");
							double Salary = resultSet.getDouble("EmpSalary");
							int age = resultSet.getInt("EmpAge");
							Employee employee = new Employee(id, age, name, department, Salary);
							System.out.println(employee);
						}
						connection.close();
					}
					catch (SQLException e)
					{
						System.err.println("Exception caught");
					}
					break;
			case 6: DatabaseConnectivityPrepared.executeDelete();
					break;
			case 7: DatabaseConnectivityPrepared.displayList();
					break;
			case 8: DatabaseConnectivityPrepared.executeUpdate();
					break;
			default:System.out.println("Invalid Input");
					break;
		}
	}
}
