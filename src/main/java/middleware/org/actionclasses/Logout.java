package middleware.org.actionclasses;

import java.time.Duration;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import middleware.org.config.PropertyValuesFromFile;
import middleware.org.constants.ApplicationConstants;
import middleware.org.models.LogoutReq;
import middleware.org.models.LogoutRes;
import middleware.org.util.RedisConnectionPool;
import redis.clients.jedis.ShardedJedis;


public class Logout {
	private static final Logger LOG = LogManager.getLogger(Logout.class);
	public LogoutRes logout(LogoutReq logoutReq, LogoutRes logoutRes,HttpServletRequest request) {
		 LOG.info("{} , {} : main - Process Logout Request..."+logoutReq.getTransactionId());
	        Instant starttime = Instant.now();

	        if (logoutRes == null)
	            logoutRes = new LogoutRes();
	        logoutRes.setModelId(ApplicationConstants.LOGIN_REQUEST_MODELID);
	        logoutRes.setTransactionId(logoutReq.getTransactionId());

	        try (ShardedJedis shardedJedis = RedisConnectionPool.shardedJedisPool.getResource()) {
	            String loggedInChannel = null;
	            String loggedInDeviceId = null;
	            if(request.getAttribute("userId") != null){
	                loggedInChannel = shardedJedis.get(request.getAttribute("userId").toString()+"~loginChannel");
	                LOG.debug("{} , {} : main - loggedIn Channel is: {}"+logoutReq.getTransactionId()+""+ loggedInChannel);
	                if(loggedInChannel != null){
	                    loggedInDeviceId = shardedJedis.get(request.getAttribute("userId").toString()+"~loginDevice");
	                    LOG.info("{} , {} : main - loggedIn deviceId is: {}"+ logoutReq.getTransactionId()+""+loggedInDeviceId);
	                }
	               
	            }else{
	                LOG.warn("{} , {} : main - userId missing in jwt token !!"+logoutReq.getTransactionId());
	            }
	            logoutRes.setResponseCode(ApplicationConstants.SUCCESS_RESPONSECODE);
	        }
	        
	        Instant endtime = Instant.now();
	        LOG.info("{} : Total time taken to respond: {} milli seconds."+Duration.between(starttime, endtime).toMillis());
	        if (Duration.between(starttime, endtime).toMillis() > Long.parseLong(PropertyValuesFromFile.getInstance().getConfiguration("WARN_TIMELIMIT")))
	            LOG.warn("{} : Total time taken is longer than expected: {} milli seconds."+Duration.between(starttime, endtime).toMillis());

		return logoutRes;
	}
}
