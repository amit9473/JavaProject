package org.jsp.employeedatamanagement.exception;

@SuppressWarnings("serial")
public class EmployeeNotFoundException extends RuntimeException
{
	@Override
	public String toString() 
	{
		return "Employee details not found";
	}
}
