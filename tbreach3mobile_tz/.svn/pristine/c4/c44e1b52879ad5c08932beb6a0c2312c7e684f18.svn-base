/**
 * Enum to represent type of Exception occurred in Application
 */

package com.ihsinformatics.tbreach3tanzania.mobile.shared;

/**
 * @author owais.hussain@irdresearch.org
 * 
 */
public class ErrorProvider
{
	// Standard
	public static final int	AUTHENTICATION_ERROR	= 0;
	public static final int	DATA_ACCESS_ERROR		= 1;
	public static final int	DATA_CONNECTION_ERROR	= 2;
	public static final int	DATA_MISMATCH_ERROR		= 3;
	public static final int	DELETE_ERROR			= 4;
	public static final int	DUPLICATION_ERROR		= 5;
	public static final int	EMPTY_DATA_ERROR		= 6;
	public static final int	INSERT_ERROR			= 7;
	public static final int	INVALID_DATA_ERROR		= 8;
	public static final int	ITEM_NOT_FOUND			= 9;
	public static final int	OUT_OF_RANGE			= 10;
	public static final int	PARAMETER_MISSING		= 11;
	public static final int	PARSING_ERROR			= 12;
	public static final int	SESSION_EXPIRED			= 13;
	public static final int	UNKNOWN_ERROR			= 14;
	public static final int	UPDATE_ERROR			= 15;

	// Custom
	public static final int	USER_NOT_FOUND			= 21;
	public static final int	USER_ROLE_UNDEFINED		= 22;
	public static final int PASSWORD_INCORRECT		= 23;
	public static final int	INVALID_DATE_OR_TIME	= 24;

	public static String getError (int errorType)
	{
		String error = "";
		switch (errorType)
		{
			case ErrorProvider.AUTHENTICATION_ERROR :
				error = "Authentication failed! Please enter valid password/code.";
				break;
			case ErrorProvider.DATA_ACCESS_ERROR :
				error = "Access to data failed! You may not have sufficient rights.";
				break;
			case ErrorProvider.DATA_CONNECTION_ERROR :
				error = "Could not connect to Data source! Please check your connection settings.";
				break;
			case ErrorProvider.DATA_MISMATCH_ERROR :
				error = "Data or value(s) did not match!";
				break;
			case ErrorProvider.DELETE_ERROR :
				error = "Error in deleting data. Some other data may be dependent on the item you want to delete.";
				break;
			case ErrorProvider.DUPLICATION_ERROR :
				error = "Duplication error! Another copy of the same data exists in the database.";
				break;
			case ErrorProvider.EMPTY_DATA_ERROR :
				error = "Empty data field! Please fill in the required field(s) first.";
				break;
			case ErrorProvider.INSERT_ERROR :
				error = "Error in inserting data. Please make sure you are not violating validation rules.";
				break;
			case ErrorProvider.ITEM_NOT_FOUND :
				error = "Data not found.";
				break;
			case ErrorProvider.INVALID_DATA_ERROR :
				error = "Invalid value! Please make sure your input is in correct format.";
				break;
			case ErrorProvider.INVALID_DATE_OR_TIME:
				error = "Date or Time is invalid. Make sure you have entered appropriate date/time.";
				break;
			case ErrorProvider.OUT_OF_RANGE :
				error = "Value out of range!";
				break;
			case ErrorProvider.PARAMETER_MISSING :
				error = "One or more Parameters are invalid or missing. Please make sure you have defined valid parameters.";
				break;
			case ErrorProvider.PASSWORD_INCORRECT:
				error = "One or more Parameters are invalid or missing. Please make sure you have defined valid parameters.";
				break;
			case ErrorProvider.PARSING_ERROR :
				error = "Data cannot be parsed. Please make sure data is in valid format.";
				break;
			case ErrorProvider.SESSION_EXPIRED :
				error = "Session expired! Please re-login to continue operation.";
				break;
			case ErrorProvider.UNKNOWN_ERROR :
				error = "Unknown error encountered! Please check error log for details.";
				break;
			case ErrorProvider.UPDATE_ERROR :
				error = "Error in updating data. Please make sure you are not violating validation rules.";
				break;
			case ErrorProvider.USER_NOT_FOUND :
				error = "User not found! Please enter correct user name.";
				break;
			case ErrorProvider.USER_ROLE_UNDEFINED :
				error = "User role not found or undefined! Please contact Administrator or select a different Role.";
				break;
		}
		return error;
	}
}