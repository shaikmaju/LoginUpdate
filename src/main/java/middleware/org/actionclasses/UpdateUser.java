package middleware.org.actionclasses;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.Handle;

import middleware.org.connectionpool.ConnectionPool;
import middleware.org.constants.ApplicationConstants;
import middleware.org.models.UpdateUsers;
import middleware.org.models.UpdateUsersDetails;
import middleware.org.models.UpdateUsersRes;

public class UpdateUser {
	private static final Logger LOG = LogManager.getLogger(UpdateUser.class);
	Handle handle = null;
	UpdateUsers updateUsers = null;
	UpdateUsersRes updateUsersRes = null;

	@SuppressWarnings("finally")
	public UpdateUsersRes updateUser(String firstName, String lastName, String userName, String primaryEmail,
			String secondaryEmail, String mobileNumber, String Designation, String transactionId,int userId,
			UpdateUsersDetails updateUsersDetailsRes) throws ClassNotFoundException {
		System.out.println("UpdateUsersRes method is called......");
		
		try {
			handle = ConnectionPool.getConnection();
			System.out.println("Handle Connection is created in UpdateUsers..");
			int i = handle.update(ApplicationConstants.UPDATE_DETAILS_QUERY, firstName,lastName,primaryEmail,mobileNumber,userName,Designation,secondaryEmail,userId);
			updateUsers = new UpdateUsers();
			updateUsersRes = new UpdateUsersRes();
			transactionId = updateUsers.getTransactionId();
			System.out.println("TranscationId in UpdateUsers=" + transactionId);
			updateUsersRes.setModelId(ApplicationConstants.UPDATE_USER_RESPONSE_MODELID);
			updateUsersRes.setTransactionId(transactionId);
			updateUsersRes.setAdditionalProperty("UpdateUsersDetails", updateUsersDetailsRes);
			System.out.println(i + "Row updated...");

		} catch (Exception sq) {
			System.out.println("Exception occurs in database");
			LOG.error("Exception occurs in database" + sq.getMessage(), sq);
		} finally {
			if (handle != null) {
				handle.close();
			}
			return updateUsersRes;
		}
	}

}
