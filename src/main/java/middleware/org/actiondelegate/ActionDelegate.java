package middleware.org.actiondelegate;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import middleware.org.actionclasses.Adduser;
import middleware.org.actionclasses.GetUserDetails;
import middleware.org.actionclasses.GetUsersDetailsById;
import middleware.org.actionclasses.LoginDetails;
import middleware.org.actionclasses.Logout;
import middleware.org.actionclasses.UpdateUser;
import middleware.org.models.AddUserDetails;
import middleware.org.models.AddUsers;
import middleware.org.models.AddUsersRes;
import middleware.org.models.BigMsgGenRes;
import middleware.org.models.GetAllUsersDetailsReq;
import middleware.org.models.GetAllUsersDetailsRes;
import middleware.org.models.GetUsersByIdReq;
import middleware.org.models.GetUsersByIdRes;
import middleware.org.models.LoginReq;
import middleware.org.models.LoginRes;
import middleware.org.models.LogoutReq;
import middleware.org.models.LogoutRes;
import middleware.org.models.UpdateUsers;
import middleware.org.models.UpdateUsersDetails;
import middleware.org.models.UpdateUsersRes;

public class ActionDelegate {
	private static final Logger LOG = LogManager.getLogger(ActionDelegate.class);
	String js = "";
	ObjectMapper om = new ObjectMapper();

	public String main(String json, Object pojo, Object genRespPojo,HttpServletRequest request) throws JsonProcessingException {
		LOG.info("delegate method in ActionDelegate called");
		Object rtnPojo = null;
		BigMsgGenRes genResp = (BigMsgGenRes) genRespPojo;
		try {
			if (pojo instanceof LoginReq) {
				LOG.info("LoginReq class is called.....");
				String userName = ((LoginReq) pojo).getUserName();
				String password = ((LoginReq) pojo).getPassword();
				String transcationId = ((LoginReq) pojo).getTransactionId();
				List<?> satatus = ((LoginReq) pojo).getStatus();
				LoginRes loginRes = new LoginDetails().login(userName, password, transcationId, satatus);
				rtnPojo = loginRes;
				genResp.setResponseCode(loginRes.getResponseCode());
				genResp.setErrorCode(loginRes.getErrorCode());
			} else if (pojo instanceof AddUsers) {
				LOG.info("AddUsersReq class is called.....");
				AddUserDetails addUsersDetails = ((AddUsers) pojo).getUserDetails();
				String transcationId1 = ((AddUsers) pojo).getTransactionId();
				String userName1 = addUsersDetails.getUserName();
				System.out.println("UserName=" + userName1);
				String firstName = addUsersDetails.getFirstName();
				String lastName = addUsersDetails.getLastName();
				String primaryEmail = addUsersDetails.getPrimaryEmail();
				String secondaryEmail = addUsersDetails.getSecondaryEmail();
				String mobileNumber = addUsersDetails.getMobileNumber();
				String Designation = addUsersDetails.getDesignation();
				AddUsersRes addUsersRes;
				addUsersRes = new Adduser().addUser(firstName, lastName, userName1, primaryEmail, secondaryEmail,
						mobileNumber, Designation, transcationId1, addUsersDetails);
				rtnPojo = addUsersRes;
				genResp.setResponseCode(addUsersRes.getResponseCode());
				genResp.setErrorCode(addUsersRes.getErrorCode());
			} else if (pojo instanceof GetAllUsersDetailsReq) {
				LOG.info("GetAllUsersDetailsReq class is called.....");
				GetAllUsersDetailsRes getUsersRes = new GetUserDetails().getUserDetails();
				rtnPojo = getUsersRes;
				genResp.setResponseCode(getUsersRes.getResponseCode());
				genResp.setErrorCode(getUsersRes.getErrorCode());
			} else if (pojo instanceof GetUsersByIdReq) {
				LOG.info("GetUsersByIdReq class is called.....");
				int UserId = ((GetUsersByIdReq) pojo).getUserId();
				GetUsersByIdRes getUsersByIdRes = new GetUsersDetailsById().getDetailsById(UserId);
				rtnPojo = getUsersByIdRes;
				genResp.setResponseCode(getUsersByIdRes.getResponseCode());
				genResp.setErrorCode(getUsersByIdRes.getErrorCode());
			} else if (pojo instanceof UpdateUsers) {
				LOG.info("UpdateUsersReq class is called.....");
				UpdateUsersDetails updateUsersDetails = ((UpdateUsers) pojo).getUpdateUsersList();
				String transcationId1 = ((UpdateUsers) pojo).getTransactionId();
				String userName1 = updateUsersDetails.getUserName();
				System.out.println("UserName=" + userName1);
				int userId=updateUsersDetails.getUserId();
				String firstName = updateUsersDetails.getFirstName();
				String lastName = updateUsersDetails.getLastName();
				String primaryEmail = updateUsersDetails.getPrimaryEmail();
				String secondaryEmail = updateUsersDetails.getSecondaryEmail();
				String mobileNumber = updateUsersDetails.getMobileNumber();
				String Designation = updateUsersDetails.getDesignation();
				UpdateUsersRes updateUsersRes;
				updateUsersRes = new UpdateUser().updateUser(firstName, lastName, userName1, primaryEmail, secondaryEmail, mobileNumber, Designation, transcationId1, userId, updateUsersDetails);
				rtnPojo = updateUsersRes;
				genResp.setResponseCode(updateUsersRes.getResponseCode());
				genResp.setErrorCode(updateUsersRes.getErrorCode());
			}else if (pojo instanceof LogoutReq) {
	            LogoutRes logoutRes = new Logout().logout((LogoutReq) pojo, null, request);
	            rtnPojo = logoutRes;
	            genResp.setResponseCode(logoutRes.getResponseCode());
	            genResp.setErrorCode(logoutRes.getErrorCode());
	        }else if (true) {
				LOG.error("delegate method in else statement:" + pojo);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js = om.writeValueAsString(rtnPojo);
		return js;
	}

}
