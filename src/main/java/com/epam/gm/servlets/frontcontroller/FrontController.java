package com.epam.gm.servlets.frontcontroller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private Map<String, HttpRequestHandler> handlers;
       

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		String props = getServletContext().getRealPath("/WEB-INF/frontcontroller.properties");
		
		System.out.println("Prop = " + props);
		

		try {
			handlers = FrontUtil.buildHandlers(props);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Front controller");
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		HttpRequestHandler handler = null;
		String path = request.getServletPath();
		
		String key = null;
		
		if(path.contains(".do")) {
			key = path.substring(1, path.lastIndexOf("."));
			
		} else if(path.trim().length() == 0) {
			key = "home";
		}
		
		handler = handlers.get(key);
		
		if(handler != null){
			
			handler.handle(request, response);
		
		}else{
		
			handlers.get("404").handle(request, response);
			//throw new ServletException("No Matching Handler");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
