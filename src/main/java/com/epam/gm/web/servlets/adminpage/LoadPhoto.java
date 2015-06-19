package com.epam.gm.web.servlets.adminpage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
/**
 * Servlet implementation class LoadPhoto
 */
@WebServlet("/LoadPhoto")
public class LoadPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoadPhoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	       out.print("Request content length is " + request.getContentLength() + "<br/>"); 
	       out.print("Request content type is " + request.getHeader("Content-Type") + "<br/>");
	       boolean isMultipart = ServletFileUpload.isMultipartContent(request);
	       if(isMultipart){
	                  ServletFileUpload upload = new ServletFileUpload();
	           try{
	               FileItemIterator iter = upload.getItemIterator(request);
	               FileItemStream item = null;
	               String name = "";
	               InputStream stream = null;
	               while (iter.hasNext()){
	                                     item = iter.next();
	                                     name = item.getFieldName();
	                                     stream = item.openStream();
	                  if(item.isFormField()){out.write("Form field " + name + ": " 
	                                           + Streams.asString(stream) + "<br/>");}
	                  else {
	                      name = item.getName();
	                      System.out.println("name==" + name);
	                      if(name != null && !"".equals(name)){
	                         String fileName = new File(item.getName()).getName();
	                         out.write("Client file: " + item.getName() + " <br/>with file name "
	                                                    + fileName + " was uploaded.<br/>");
	                         File file = new File(getServletContext().getRealPath("/img/userphotos/" + fileName));
	                         FileOutputStream fos = new FileOutputStream(file);
	                         long fileSize = Streams.copy(stream, fos, true);
	                         out.write("Size was " + fileSize + " bytes <br/>");
	                         out.write("File Path is " + file.getPath() + "<br/>");
	                      }
	                  }
	               }
	           } catch(FileUploadException fue) {out.write("fue!!!!!!!!!");}
	       } 
	}
		// TODO Auto-generated method stub
	}


