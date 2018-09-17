package middleware.org.actionclasses;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.Handle;

import com.fasterxml.jackson.databind.ObjectMapper;

import middleware.org.connectionpool.ConnectionPool;
import middleware.org.constants.ApplicationConstants;
import middleware.org.models.LoginReq;
import middleware.org.models.LoginRes;

public class LoginDetails {
	private static final Logger LOG = LogManager.getLogger(LoginDetails.class);
	Handle handle = null;
	LoginRes loginRes = null;
	ObjectMapper om = new ObjectMapper();
	Object getBySingle = null;

	public LoginRes login(String userName, String password, String transactionId, List<?> status,
			HttpServletRequest request) {
		try {
			LOG.info("The LoginDetails class is called....");
			LoginReq loginReq = new LoginReq();
			System.out.println("TranscationId in Login=" + transactionId);
			handle = ConnectionPool.getConnection();
			System.out.println("The Handle =" + handle);
			List<Map<String, Object>> q = handle.createQuery(ApplicationConstants.LOGIN_DETAILS_QUERY).list();
			System.out.println("The Select Operation values=" + q);
			/*
			 * for (Map<String, Object> out : q) { for (Map.Entry<String, Object> entry :
			 * out.entrySet()) { System.out.println(entry.getKey() + " - " +
			 * entry.getValue()); } }
			 */
			System.out.println("The List values:" + q);
			for (Map<String, Object> out : q) {
				System.out.println("The Particular List value=" + out);
				Object pass1 = out.get("password");
				Object email1 = out.get("username");
				if ((userName.equals(email1)) && (password.equals(pass1))) {
					HttpSession session = request.getSession();
					session.setAttribute("email", email1);
					session.setAttribute("password", pass1);
					System.out.println("Session is=" + session);
					if (loginRes == null)
						loginRes = new LoginRes();
					loginRes.setModelId(ApplicationConstants.LOGIN_RESPONSE_MODELID);
					loginRes.setUserName(userName);
					loginRes.setTransactionId(transactionId);
					LOG.info("UserName in LOgin" + loginReq.getUserName());
					LOG.info("Status :" + status);
					loginRes.setAdditionalProperty("Response", "Login Success");
					loginRes.setResponseCode(ApplicationConstants.SUCCESS_RESPONSECODE);

				} else {
					LOG.info("UserName in Login is incorrect" + loginReq.getUserName());
					loginRes.setErrorCode(ApplicationConstants.UNKNOWN_ERRORCODE);
					loginRes.setResponseCode(ApplicationConstants.FUNCTIONAL_ERROR_RESPONSECODE);

				}
			}
		} catch (Exception e) {
			LOG.error("Something went wrong in database:" + ApplicationConstants.DB_EXCEPTION_ERRORCODE);
		} finally {
			if (handle != null) {
				handle.close();
			}

		}
		System.out.println("The Login Response=" + loginRes);
		return loginRes;

	}

}
