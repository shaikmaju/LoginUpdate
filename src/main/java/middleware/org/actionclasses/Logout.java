package middleware.org.actionclasses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import middleware.org.constants.ApplicationConstants;
import middleware.org.models.LogoutReq;
import middleware.org.models.LogoutRes;

public class Logout {
	private static final Logger LOG = LogManager.getLogger(Logout.class);

	public LogoutRes logout(LogoutReq logoutReq, LogoutRes logoutRes, HttpServletRequest request) {
		LOG.info("{} , {} : main - Process Logout Request..." + logoutReq.getTransactionId());
		HttpSession session = request.getSession();
		if (logoutRes == null)
			logoutRes = new LogoutRes();
		logoutRes.setModelId(ApplicationConstants.LOGIN_REQUEST_MODELID);
		logoutRes.setTransactionId(logoutReq.getTransactionId());
		LOG.info("The Email and Password coming from session object is:email:{},password:{}"+session.getAttribute("email")+session.getAttribute("password"));
		session.invalidate();
		logoutRes.setAdditionalProperty("respMsg",
				"You are successfully logged out.....{ \" Please login : http://localhost:8888/LoginUpdateGetAddDetails/index.html\"}");
		return logoutRes;
	}
}
