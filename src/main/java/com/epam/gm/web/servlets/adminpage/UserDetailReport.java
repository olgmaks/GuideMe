package com.epam.gm.web.servlets.adminpage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.view.JasperViewer;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		 response.setCharacterEncoding("UTF-8");
		 response.setContentType("application/pdf;charset=utf-8");
		ServletOutputStream servletOutputStream = response.getOutputStream(); 
 	    File reportFile = new File(getServletConfig().getServletContext()
 	        .getRealPath("/report/report4.jasper"));
 	    byte[] bytes = null;

 	    try
 	    {
 	      bytes = JasperRunManager.runReportToPdf(reportFile.getPath(),
 	          new HashMap(), ConnectionManager.getConnection());
 	    
 	     response.setContentLength(bytes.length);
 	      servletOutputStream.write(bytes, 0, bytes.length);
 	  
 	      servletOutputStream.flush();
 	      servletOutputStream.close();
 	    }
 	    catch (JRException e)
 	    {
 	      // display stack trace in the browser
 	      StringWriter stringWriter = new StringWriter();
 	      PrintWriter printWriter = new PrintWriter(stringWriter);
 	      e.printStackTrace(printWriter);
 	      response.setContentType("text/plain");
 	      response.getOutputStream().print(stringWriter.toString());
 	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection = ConnectionManager.getConnection() ;
		   // System.out.println("Usage: ReportGenerator ....");

		  try {
		    System.out.println("Start ....");
		    // Get jasper report
		    String jrxmlFileName = "C:/report4.jrxml";
		    String jasperFileName = "D:report4.jasper";
		    String pdfFileName = "D:/report4.pdf";

		   JasperCompileManager.compileReportToFile(jrxmlFileName, jasperFileName);


		   // Generate jasper print
		    JasperPrint jprint = (JasperPrint) JasperFillManager.fillReport(jasperFileName, null, connection);
		    JasperViewer.viewReport(jprint,true);
		   // Export pdf file
		    JasperExportManager.exportReportToPdfFile(jprint, pdfFileName);
		    
		    
		    System.out.println("Done exporting reports to pdf");
		    
		   } catch (Exception e) {
		    System.out.print("Exceptiion" + e);
		   }
	}

}
