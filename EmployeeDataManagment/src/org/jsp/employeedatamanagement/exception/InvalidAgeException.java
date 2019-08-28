package org.jsp.employeedatamanagement.exception;

@SuppressWarnings("serial")
public class InvalidAgeException extends RuntimeException
{
	@Override
	public String toString()
	{
		return "InvalidAgeException (Child Labour and Senior Citizen not allowed ====> should be in range [22-60])";
	}
	
}
