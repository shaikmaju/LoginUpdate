package middleware.org.jsonpojo;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import middleware.org.constants.ApplicationConstants;
import middleware.org.models.AddUsers;
import middleware.org.models.BigMsgGenReq;
import middleware.org.models.BigMsgGenRes;
import middleware.org.models.GetAllUsersDetailsReq;
import middleware.org.models.GetUsersByIdReq;
import middleware.org.models.GetUsersDetailsByFilterReq;
import middleware.org.models.LoginReq;
import middleware.org.models.LogoutReq;
import middleware.org.models.UpdateUsers;

public class JsontoPojoConventor {

	private static final Logger LOG = LogManager.getLogger(JsontoPojoConventor.class);

	public Object[] convert(String json) {
		LOG.info("convert method in JsonToPOJOConverter called with json: " + json);
		ObjectMapper mapper = new ObjectMapper();
		BigMsgGenReq genericPojo = null;
		BigMsgGenRes genResp = new BigMsgGenRes();
		System.out.println("BigMsgRes="+genResp);
		Object[] rtnPojo = new Object[3];
		Object mappedPojo = null;
		try {
			genericPojo = mapper.readValue(json, BigMsgGenReq.class);
			genResp.setTransactionId(genericPojo.getTransactionId());
			rtnPojo[0] = genericPojo;
			if (genericPojo.getModelId() == ApplicationConstants.LOGIN_REQUEST_MODELID) {
				LoginReq loginReq = mapper.readValue(json, LoginReq.class);
				genResp.setModelId(ApplicationConstants.LOGIN_RESPONSE_MODELID);
				mappedPojo = loginReq;
			} else if (genericPojo.getModelId() == ApplicationConstants.ADD_USER_DETAILS_REQUEST_MODELID) {
				AddUsers addUsersReq = mapper.readValue(json, AddUsers.class);
				genResp.setModelId(ApplicationConstants.ADD_USER_DETAILS_RESPONSE_MODELID);
				mappedPojo = addUsersReq;
			} else if (genericPojo.getModelId() == ApplicationConstants.GET_ALL_USER_DETAILS_REQUEST_MODELID) {
				GetAllUsersDetailsReq getUsersReq = mapper.readValue(json, GetAllUsersDetailsReq.class);
				genResp.setModelId(ApplicationConstants.GET_ALL_USER_DETAILS_RESPONSE_MODELID);
				mappedPojo = getUsersReq;
			} else if (genericPojo.getModelId() == ApplicationConstants.GET_USER_DETAILS_BY_ID_REQUEST_MODELID) {
				GetUsersByIdReq getUsersByIdReq = mapper.readValue(json, GetUsersByIdReq.class);
				genResp.setModelId(ApplicationConstants.GET_USER_FILTER_BY_ID_RESPONSE_MODELID);
				mappedPojo = getUsersByIdReq;
			} else if (genericPojo.getModelId() == ApplicationConstants.UPDATE_USER_REQUEST_MODELID) {
				UpdateUsers updateUsersReq = mapper.readValue(json, UpdateUsers.class);
				genResp.setModelId(ApplicationConstants.UPDATE_USER_RESPONSE_MODELID);
				mappedPojo = updateUsersReq;
			} else if (genericPojo.getModelId() == ApplicationConstants.GET_USER_FILTER_BY_ID_REQUEST_MODELID) {
				GetUsersDetailsByFilterReq getUsersDetailsByFilterReq = mapper.readValue(json, GetUsersDetailsByFilterReq.class);
				genResp.setModelId(ApplicationConstants.GET_USER_FILTER_BY_ID_RESPONSE_MODELID);
				mappedPojo = getUsersDetailsByFilterReq;
			} else if (genericPojo.getModelId() == ApplicationConstants.LOGOUT_REQUEST_MODELID) {
				LogoutReq logoutReq = mapper.readValue(json, LogoutReq.class);
				genResp.setModelId(ApplicationConstants.LOGOUT_RESPONSE_MODELID);
				mappedPojo = logoutReq;
			} else if (true) {
				LOG.error("convert method in else statement.Pojo is:  json is:" + genericPojo);
			}

			if (LOG.isTraceEnabled())
				LOG.trace("For json: " + json + " Generic pojo is: " + genericPojo);

		} catch (JsonGenerationException e) {
			LOG.error(" json is:" + json + " Exception message is: " + e.getMessage());
			e.printStackTrace(System.out);
		} catch (JsonMappingException e) {
			LOG.error(" json is:" + json + " Exception message is: " + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			LOG.error(" json is:" + json + " Exception message is: " + e.getMessage());
			e.printStackTrace();
		}
		LOG.trace("For json: {} Returning pojo: {}" + mappedPojo);
		rtnPojo[1] = mappedPojo;
		rtnPojo [2]= genResp;

		return rtnPojo;

	}

}
