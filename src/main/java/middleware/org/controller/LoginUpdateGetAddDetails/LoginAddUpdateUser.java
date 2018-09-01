package middleware.org.controller.LoginUpdateGetAddDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import middleware.org.actiondelegate.ActionDelegate;
import middleware.org.jsonpojo.JsontoPojoConventor;

/**
 * Servlet implementation class LoginAddUpdateUser
 */
public class LoginAddUpdateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LogManager.getLogger(LoginAddUpdateUser.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginAddUpdateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		LOG.info("doPost method of entry servlet has been called.");
		response.setContentType("application/json");
		String resp = "";
		String responseOutput = "";
		PrintWriter out = response.getWriter();
		StringBuffer jb = new StringBuffer();
		ActionDelegate actionDelegate = new ActionDelegate();
		System.out.println("The Request Object=" + request);
		String line = null;
		BufferedReader reader = request.getReader();
		reader = request.getReader();
		while ((line = reader.readLine()) != null)
			jb.append(line);
		String json = new String(jb.toString().getBytes("UTF-8"));
		Object[] pojo = new JsontoPojoConventor().convert(json);
		System.out.println("Json Object=" + json);
		responseOutput = actionDelegate.main(json,pojo[1],pojo[2],request);
		System.out.println("The Action Delegate=" + actionDelegate);
		System.out.println("The Action Delegate of the response=" + responseOutput);
		resp = responseOutput;
		System.out.println("The Output to Json="+resp);
		out.write(resp);
	}

}
