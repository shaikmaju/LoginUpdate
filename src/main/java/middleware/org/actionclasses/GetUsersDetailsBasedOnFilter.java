package middleware.org.actionclasses;

import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.Handle;
import org.skife.jdbi.v2.Query;

import middleware.org.connectionpool.ConnectionPool;
import middleware.org.constants.ApplicationConstants;
import middleware.org.models.GetUsersDetailsByFilterReq;
import middleware.org.models.GetUsersDetailsByFilterRes;

public class GetUsersDetailsBasedOnFilter {
	private static final Logger LOG = LogManager.getLogger(GetUsersDetailsById.class);

	Handle handle = null;
	GetUsersDetailsByFilterRes getUsersByFilterRes = null;
	GetUsersDetailsByFilterReq getUsersByFilterReq = null;
	String transactionId = null;

	public GetUsersDetailsByFilterRes getDetailsByFilter(List<?> userId) {
		try {
			getUsersByFilterReq = new GetUsersDetailsByFilterReq();
			// BigMsgGenReq us = new BigMsgGenReq();
			transactionId = getUsersByFilterReq.getTransactionId();
			if (getUsersByFilterRes == null)
				getUsersByFilterRes = new GetUsersDetailsByFilterRes();
			getUsersByFilterRes.setModelId(ApplicationConstants.GET_USER_FILTER_BY_ID_RESPONSE_MODELID);
			getUsersByFilterRes.setTransactionId(transactionId);
			handle = ConnectionPool.getConnection();
			Query<Map<String, Object>> q = handle.createQuery(ApplicationConstants.GET_USER_FILTER_BY_ID_QUERY);
			List<Map<String, Object>> listOfUserDetailsById = q.bind(0, userId).list();

			getUsersByFilterRes.setUsersList(listOfUserDetailsById);

		} catch (Exception se) {

			LOG.error(se.getMessage() + "while something happens in dataabase while inserting", se);
			se.printStackTrace();
		} finally {
			if (handle != null) {
				handle.close();
			}
		}

		return getUsersByFilterRes;
	}

}
