package org.jsp.employeedatamanagement.validation;

import java.util.InputMismatchException;
import java.util.regex.Pattern;
import org.jsp.employeedatamanagement.exception.InvalidAgeException;
import org.jsp.employeedatamanagement.exception.InvalidIdException;

public class ValidateData
{
	static boolean status = false;
	
	
	public static boolean validateId(int eId)
	{
		try
		{
			try
			{
				if(eId<=999)
				{
					status = true;
				}
				else
				{
					throw new InvalidIdException();
				}
			}
			catch(InvalidIdException i)
			{
				System.out.println(i);
			}
		}
		catch(InputMismatchException i)
		{
			System.out.println("Id must be triple digit integer data type");
		}
		return status;
	}
	
	
	public static boolean validateAge(int eAge)
	{
		try
		{
			if(eAge>=22 && eAge<=60)
			{
				status = true;
			}
			else
			{
				status = false;
				throw new InvalidAgeException();
			}
		}
		catch(InvalidAgeException i)
		{
			System.out.println(i);
		}
		return status;
	}
	
	
	public static boolean validateName(String eName)
	{
		Pattern p = Pattern.compile("[A-Z a-z]+");
		status = p.matcher(eName).matches();
		return status;
	}
}
