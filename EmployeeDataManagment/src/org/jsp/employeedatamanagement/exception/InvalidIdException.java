package org.jsp.employeedatamanagement.exception;

@SuppressWarnings("serial")
public class InvalidIdException extends RuntimeException
{
	@Override
	public String toString() 
	{
		return "Id must be triple digit integer type data";
	}
	
}