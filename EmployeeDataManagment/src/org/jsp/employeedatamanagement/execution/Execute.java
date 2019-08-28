package org.jsp.employeedatamanagement.execution;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.jsp.employeedatamanagement.converttofile.EmployeeToFile;
import org.jsp.employeedatamanagement.employee.Employee;
import org.jsp.employeedatamanagement.employee.EmployeeDataInt;
import org.jsp.employeedatamanagement.employee.EmployeeDetails;
import org.jsp.employeedatamanagement.exception.EmployeeNotFoundException;
import org.jsp.employeedatamanagement.validation.ValidateData;

public class Execute extends Thread 
{
	private static Scanner scan = new Scanner(System.in);
	
	
	@Override
	public void run() 
	{
		System.out.println("Welcome");
		/*
		System.out.println("Press 1 : If using application for the first time else Press 0");
		int data = scan.nextInt();
		if (data == 1)
		{
			EmployeeToFile.serialize();
		}
		else
		{
			EmployeeToFile.deserialize();
		}
		*/
		EmployeeToFile.deserialize();
		char choice;
		do
		{
			getAccess();
			System.out.println("==============================================================================================================");
			System.out.println("Press y : to continue or any key to stop");
			System.out.println("Enter your choice");
			choice = scan.next().charAt(0);
		}while(choice == 'y' || choice == 'Y');
		EmployeeToFile.serialize();
		System.out.println("File Saved");
		System.out.println("You can shut the program down");
	}
	
	
	public static void getAccess() 
	{
		int count_data=0;
		boolean status = false;
		int eAge = 0;
		int eId = 0;
		Employee e;
		int input = 0;
		String eName=null;
		String eDepartment=null;
		EmployeeDataInt emp = EmployeeDetails.getData();
		System.out.println(emp.totalEmployee()+" is the total number of employee's data present in database");
		System.out.println("Press 1 : To add Employee details");
		System.out.println("Press 2 : To pull Employee's details usind id");
		System.out.println("Press 3 : To pull Employee's details using age");
		System.out.println("Press 4 : To pull Employee's details using name");
		System.out.println("Press 5 : To pull Employee's details usind department");
		System.out.println("Press 6 : To delete Employee's details");
		System.out.println("Press 7 : To view Employee's list");
		System.out.println("Press 8 : To update any details");
		do
		{
			try
			{
				input = scan.nextInt();
				status = true;
			}
			catch(InputMismatchException imp)
			{
				System.out.println("Only integer input is allowed");
				scan.next();
			}
		}while(!status);
		switch(input)
		{
		case 1: e = EmployeeDetails.scanData();
				emp.addEmployee(e);
				System.out.println("Employee creation successful");
				System.out.println(e);
				break;
		case 2: do
				{
					try
					{
						System.out.println("Enter employee's Id, should be in 3 digit");
						eId = scan.nextInt();
						status = ValidateData.validateId(eId);
					}
					catch(InputMismatchException ii)
					{
						System.out.println("Invalid input : id should be integer data type");
						scan.nextLine();
					}
					catch(ArrayIndexOutOfBoundsException aa)
					{
						System.out.println("Invalid input : id starts from 001");
						scan.nextLine();
					}
				}while(!status);
				if(eId<=emp.totalEmployee())
				{
					try
					{
						e = emp.checkId(eId);
						System.out.println("Employee details found");
						System.out.println(e);
					}
					catch(ArrayIndexOutOfBoundsException aa)
					{
						System.out.println("Invalid input : id starts from 001");
						scan.nextLine();
					}
				}
				else 
				{
					System.out.println("Employee Id doesn't exist");
				}
				break;
		case 3: do
				{
					try
					{
						System.out.println("Enter employee's age, should be in range [22--60]");
						eAge = scan.nextInt();
						status = ValidateData.validateAge(eAge);
					}
					catch(InputMismatchException ii)
					{
						System.out.println("Invalid input : age should be integer data type");
						scan.nextLine();
					}
				}while(!status);
				try
				{
					int count = 1;
					int size = emp.totalEmployee();
					for(int j=0; j<=size; j++)
					{
						count = emp.checkAge(eAge);
					}
					if(count==0)
					{
						System.out.println("Employee not found");
					}
				}
				catch(EmployeeNotFoundException enfe)
				{
					System.out.println(enfe);
				}
				break;
		case 4: do
				{
					System.out.println("Enter the employee's name");
					eName=scan.next();
					status=ValidateData.validateName(eName);
				}while(!status);
				count_data=emp.checkName(eName);
				System.out.println("Total "+count_data+" employee are there with name: "+eName);
				break;
		case 5: do
				{
					System.out.println("Enter the department's name");
					eDepartment=scan.next();
					status=ValidateData.validateName(eDepartment);
				}while(!status);
				count_data=emp.checkDepartment(eDepartment);
				System.out.println("Total "+count_data+" employee are there in "+eDepartment+" department");
				break;
		case 6: emp.deleteEmployeeData();
				break;
		case 7: emp.displayAllEmployee();
				break;
		case 8: emp.update();
				break;
		default :	System.out.println("Invalid Input"); 
		}
	}
}
