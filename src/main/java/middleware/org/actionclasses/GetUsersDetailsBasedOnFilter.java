package middleware.org.actionclasses;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;

import middleware.org.connectionpool.ConnectionPool;
import middleware.org.constants.ApplicationConstants;
import middleware.org.models.GetUsersByIdReq;
import middleware.org.models.GetUsersByIdRes;

public class GetUsersDetailsBasedOnFilter {
	private static final Logger LOG = LogManager.getLogger(GetUsersDetailsById.class);

	Handle handle = null;
	GetUsersByIdRes getUsersByIdRes = null;
	GetUsersByIdReq getUsersByIdReq = null;
	String transactionId = null;

	public GetUsersByIdRes getDetailsById(int userId) {
		try {
			getUsersByIdReq = new GetUsersByIdReq();
			// BigMsgGenReq us = new BigMsgGenReq();
			transactionId = getUsersByIdReq.getTransactionId();
			if (getUsersByIdRes == null)
				getUsersByIdRes = new GetUsersByIdRes();
			getUsersByIdRes.setModelId(ApplicationConstants.GET_USER_FILTER_BY_ID_RESPONSE_MODELID);
			getUsersByIdRes.setTransactionId(transactionId);
			handle = ConnectionPool.getConnection();
			Query<Map<String, Object>> q = handle.createQuery(ApplicationConstants.GET_DETAILS_BY_ID_QUERY);
			List<Map<String, Object>> listOfUserDetailsById = q.bind(0, userId).list();

			getUsersByIdRes.setUsersList(listOfUserDetailsById);

		} catch (Exception se) {

			LOG.error(se.getMessage() + "while something happens in dataabase while inserting", se);
			se.printStackTrace();
		} finally {
			if (handle != null) {
				handle.close();
			}
		}

		return getUsersByIdRes;
	}

}
