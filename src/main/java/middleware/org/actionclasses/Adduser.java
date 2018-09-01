package middleware.org.actionclasses;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.Handle;
import middleware.org.connectionpool.ConnectionPool;
import middleware.org.constants.ApplicationConstants;
import middleware.org.models.AddUserDetails;
import middleware.org.models.AddUsers;
import middleware.org.models.AddUsersRes;

public class Adduser {
	private static final Logger LOG = LogManager.getLogger(Adduser.class);

	Handle handle = null;
	AddUsers addUsers = null;
	AddUsersRes addUsersRes = null;

	@SuppressWarnings("finally")
	public AddUsersRes addUser(String firstName, String lastName, String userName, String primaryEmail,
			String secondaryEmail, String mobileNumber, String Designation, String transactionId,
			AddUserDetails addUsersDetailsRes) throws ClassNotFoundException {
		System.out.println("AddUsersRes method is called......");
		
		try {
			handle = ConnectionPool.getConnection();
			System.out.println("Handle Connection is created in AddUsers..");
			int i = handle.insert(ApplicationConstants.ADD_USERS_DETAILS, firstName, lastName, primaryEmail,
					mobileNumber, userName, Designation, secondaryEmail);
			addUsers = new AddUsers();
			addUsersRes = new AddUsersRes();
			transactionId = addUsers.getTransactionId();
			System.out.println("TranscationId in addUsers=" + transactionId);
			addUsersRes.setModelId(ApplicationConstants.ADD_USER_DETAILS_RESPONSE_MODELID);
			addUsersRes.setTransactionId(transactionId);
			addUsersRes.setAdditionalProperty("AddUsersDetails", addUsersDetailsRes);
			System.out.println(i + "Row inserted...");

		} catch (Exception sq) {
			System.out.println("Exception occurs in database");
			LOG.error("Exception occurs in database" + sq.getMessage(), sq);
		} finally {
			if (handle != null) {
				handle.close();
			}
			return addUsersRes;
		}
	}
}
