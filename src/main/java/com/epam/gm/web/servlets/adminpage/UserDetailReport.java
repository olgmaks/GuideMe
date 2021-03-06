package com.epam.gm.web.servlets.adminpage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;

/**
 * Servlet implementation class UserDetailReport
 */
@WebServlet("/UserDetailReport")
public class UserDetailReport extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserDetailReport() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		printReport(request, response, "/report/report4.jasper",
				new HashMap<String, Object>());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", Integer.parseInt(request.getParameter("userId")));
		printReport(request, response, "/report/userDetailById.jasper",
				map);
	}

	public void printReport(HttpServletRequest request,
			HttpServletResponse response, String reportPath,
			Map<String, Object> map) throws IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/pdf;charset=utf-8");
		ServletOutputStream servletOutputStream = response.getOutputStream();
		File reportFile = new File(getServletConfig().getServletContext()
				.getRealPath(reportPath));
		byte[] bytes = null;

		try {
			bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), map,
					ConnectionManager.getConnection());

			response.setContentLength(bytes.length);
			servletOutputStream.write(bytes, 0, bytes.length);

			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (JRException e) {
			// display stack trace in the browser
			StringWriter stringWriter = new StringWriter();
			PrintWriter printWriter = new PrintWriter(stringWriter);
			e.printStackTrace(printWriter);
			response.setContentType("text/plain");
			response.getOutputStream().print(stringWriter.toString());
		}
	}

}
