package middleware.org.constants;

public class ApplicationConstants {
	// Login Constants
	public static final int LOGIN_REQUEST_MODELID = 100;
	public static final int LOGIN_RESPONSE_MODELID = 1001;
	public static final int LOGIN_INVALID_CREDENTIALS_ERRORCODE = 15001;
	public static final int LOGIN_INVALID_USER_ERRORCODE = 15002;
	// LOGOUT CONSTANTS
	public static final int LOGOUT_REQUEST_MODELID = 300;
	public static final int LOGOUT_RESPONSE_MODELID = 300;
	// SUCCESS CONSTANTS
	public static final int SUCCESS_RESPONSECODE = 0;
	public static final int FUNCTIONAL_ERROR_RESPONSECODE = 91;
	public static final int TECHNICAL_ERROR_RESPONSECODE = 92;
	// PARSING CONSTANTS
	public static final int PARSE_ROLE_JSON_ERRORCODE = 30902;
	public static final int UNKNOWN_REQUEST_ERRORCODE = 9995;
	public static final int DB_EXCEPTION_ERRORCODE = 9996;
	public static final int UNKNOWN_ERRORCODE = 9999;

	// ADD USERS CONSTANTS
	public static final int ADD_USER_DETAILS_REQUEST_MODELID = 101;
	public static final int ADD_USER_DETAILS_RESPONSE_MODELID = 1011;
	public static final int ADD_USER_INSERT_COUNT_ZERO_ERRORCODE = 40301;
	public static final int ADD_USER_CONVERT_POJO_JSON_ERRORCODE = 40302;
	public static final int ADD_USER_DUPLICATE_USERNAME_ERRORCODE = 40303;
	public static final int ADD_USER_DUPLICATE_PRIMARY_EMAIL_ERRORCODE = 40304;
	public static final int ADD_USER_DUPLICATE_MOBILE_NO_ERRORCODE = 40305;
	// GET USERS CONSTANTS
	public static final int GET_ALL_USER_DEATILS_EMPTY_ERRORCODE = 400;
	public static final int GET_USER_DETAILS_INVALID_USER_ID = 4050;
	public static final int GET_ALL_USER_DETAILS_REQUEST_MODELID = 102;
	public static final int GET_ALL_USER_DETAILS_RESPONSE_MODELID = 102;
	public static final int GET_USER_DETAILS_BY_ID_REQUEST_MODELID = 104;
	public static final int GET_USER_DETAILS_BY_ID_RESPONSE_MODELID = 1041;
	public static final int GET_USER_FILTER_BY_ID_REQUEST_MODELID = 105;
	public static final int GET_USER_FILTER_BY_ID_RESPONSE_MODELID = 1051;
	// UPDATE USERS CONSTANTS
	public static final int UPDATE_USER_REQUEST_MODELID = 103;
	public static final int UPDATE_USER_RESPONSE_MODELID = 1031;

	public static final String GET_USERS_DETAILS_QUERY = "select * from usersdetails";
	public static final String LOGIN_DETAILS_QUERY = "select userName,password from login";
	public static final String ADD_USERS_DETAILS = "insert into usersdetails(firstName,lastName,primaryEmail,contactNumber,userName,Designation,secondaryEmail)  values (?,?,?,?,?,?,?)";
	public static final String GET_USERS_BY_NAME = " select DISTINCT userId from usersdetails";
	public static final String GET_DETAILS_BY_ID_QUERY = "select * from usersdetails where userId=?";
	public static final String UPDATE_DETAILS_QUERY = "update  usersdetails set firstName=?,lastName=?,primaryEmail=?,contactNumber=?,userName=?,Designation=?,secondaryEmail=? where userId=?";

}
