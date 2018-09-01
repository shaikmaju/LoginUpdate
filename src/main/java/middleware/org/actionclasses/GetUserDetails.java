package middleware.org.actionclasses;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;
import middleware.org.connectionpool.ConnectionPool;
import middleware.org.constants.ApplicationConstants;
import middleware.org.models.GetAllUsersDetailsReq;
import middleware.org.models.GetAllUsersDetailsRes;

public class GetUserDetails {
	private static final Logger LOG = LogManager.getLogger(GetUserDetails.class);

	Handle handle = null;
	GetAllUsersDetailsReq getUsersDetailsReq = null;
	GetAllUsersDetailsRes getUsersDetailsRes = null;
	String transactionId = null;

	public GetAllUsersDetailsRes getUserDetails() {
		try {
			getUsersDetailsReq = new GetAllUsersDetailsReq();
			transactionId = getUsersDetailsReq.getTransactionId();
			if (getUsersDetailsRes == null)
				handle = ConnectionPool.getConnection();
			getUsersDetailsRes = new GetAllUsersDetailsRes();
			getUsersDetailsRes.setModelId(ApplicationConstants.GET_ALL_USER_DETAILS_RESPONSE_MODELID);
			getUsersDetailsRes.setTransactionId(transactionId);
			Query<Map<String, Object>> q = handle.createQuery(ApplicationConstants.GET_USERS_DETAILS_QUERY);
			List<Map<String, Object>> listOfUserDetails = q.list();
			getUsersDetailsRes.setUsersList(listOfUserDetails);
		} catch (Exception se) {

			LOG.error(se.getMessage() + "while something happens in dataabase while inserting", se);
			se.printStackTrace();
		} finally {
			if (handle != null) {
				handle.close();
			}
		}

		return getUsersDetailsRes;
	}

}
