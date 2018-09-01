package middleware.org.connectionpool;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

public class ConnectionPool {
	private static final Logger LOG = LogManager.getLogger(ConnectionPool.class);

	public static MysqlDataSource getSqlDataSource() {

		Properties props = new Properties();
		FileInputStream fis = null;
		MysqlDataSource ds = null;
		try {
			fis = new FileInputStream(
					 "F:\\MrcubesMainProject\\LoginUpdateGetAddDetails\\src\\main\\resources\\db.properties");
			props.load(fis);

			ds = new MysqlConnectionPoolDataSource();
			ds.setURL(props.getProperty("mysql.url"));
			  ds.setUser(props.getProperty("mysql.username"));
			  ds.setPassword(props.getProperty("mysql.password"));
			System.out.println("datasource :" + ds);
		} catch (IOException io) {
			System.out.println("error while creating datasource" + io.getMessage());
			LOG.info("error in properties file :" + io.getMessage());
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					LOG.error("error while closing file :" + e.getMessage());
				}
			}

		}
		return ds;
	}

	public static Handle getConnection() {
		Handle handle = null;
		MysqlDataSource ds = getSqlDataSource();
		System.out.println("data source in connection method : " + ds);
		DBI dbi = new DBI(ds);
		System.out.println("class dbi contain connection :" + dbi);

		try {
			System.out.println("datasource having null data value ");
			handle = dbi.open();
			System.out.println("open a connection with handle : " + handle);
			LOG.info("open a connection with handle : " + handle);

		} catch (Exception fe) {
			fe.printStackTrace();
			System.out.println("Handle Exception"+fe.getMessage());
			LOG.info("error in getconnection :" + fe.getMessage());
		}

		return handle;
	}

	/*
	 * static MysqlDataSource ds = null; static { Properties props = new
	 * Properties(); FileInputStream fis = null; try { fis = new FileInputStream(
	 *
	 * ); props.load(fis); ds = new MysqlConnectionPoolDataSource();
	 * ds.setURL(props.getProperty("mysql.url"));
	 * ds.setUser(props.getProperty("mysql.username"));
	 * ds.setPassword(props.getProperty("mysql.password"));
	 * ds.setMaxReconnects(100); } catch (FileNotFoundException fe) {
	 * LOG.error("File is not found in project" + fe.getMessage(), fe);
	 * fe.printStackTrace(); } catch (IOException io) {
	 * LOG.error("Something went wrong in files" + io.getMessage(), io);
	 * io.printStackTrace();
	 * 
	 * } catch (Exception e) { LOG.error("Exception can occurs on DataSource" +
	 * e.getMessage(), e); e.printStackTrace();
	 * 
	 * }
	 * 
	 * } public static Handle connection() throws SQLException {
	 * LOG.info("open  method is called....");
	 * System.out.println("open methosd is called......"); DBI dbi = new DBI(ds);
	 * System.out.println("DBI"+dbi); System.out.println("DataSource="+ds); Handle
	 * handle = dbi.open(); System.out.println("Handle is="+handle); return handle;
	 * }
	 */
}
