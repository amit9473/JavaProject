package org.jsp.employeedatamanagement.converttofile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.jsp.employeedatamanagement.employee.Employee;
import org.jsp.employeedatamanagement.employee.EmployeeList;

public class EmployeeToFile
{
	private static File f = new File("D:\\Coding\\JavaWorkSpace\\EmployeeDataManagment\\src\\EmployeeStorage\\EmpStr.ser");
	public static void serialize()
	{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try
		{
			f.createNewFile();
			fos = new FileOutputStream(f);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(EmployeeList.employee);
			oos.flush();
		}
		catch(IOException io)
		{
			System.out.println("Exception caught during serialization");
		}
		finally
		{
			try
			{
				fos.close();
				oos.close();
			}
			catch(IOException io)
			{
				System.out.println("Exception caught during serialization");
			}
		}
	}
	@SuppressWarnings("unchecked")
	public static void deserialize()
	{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try
		{
			fis = new FileInputStream(f);
			ois = new ObjectInputStream(fis);
			EmployeeList.employee = (ArrayList<Employee>)ois.readObject();
		}
		catch(IOException io)
		{
			System.out.println("Exception caught during deserialization");
		}
		catch(ClassNotFoundException co)
		{
			System.out.println(co);
		}
		finally
		{
			try
			{
				fis.close();
				ois.close();
			}
			catch(IOException io)
			{
				System.out.println("Exception caught during deserialization");
			}
		}
	}
}
