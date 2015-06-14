package com.epam.gm.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


public class AccessFilter implements Filter {
//	private String encoding = "utf-8";
	

	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		
//		request.setCharacterEncoding(encoding);
//		
//		HttpServletRequest req = (HttpServletRequest) request;
//		String uri = req.getRequestURI().toLowerCase().trim();
//		
//		String path = req.getServletPath();
//		
//		boolean error = false;
//		if (uri.endsWith(".html") || uri.endsWith(".jsp")
//				|| uri.endsWith(".md") || uri.endsWith("licence"))
//			error = true;
//		
//		if(path.trim().length() > 0)
//			if(!path.contains(".do"))
//				error = true;
//		
// 
//		if (error) {
//			System.out.println("Filter not pass: ");
//			System.out.println(uri);
//			System.out.println(path);
//			
//			
//			HttpServletResponse resp = (HttpServletResponse)response; 
//			req.getRequestDispatcher("404.do").forward(request, response);
//			
//			
//			
//			return;
//		}
//		
//		((HttpServletRequest)request).getSession(true);
		filterChain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String encodingParam = filterConfig.getInitParameter("encoding");
		if (encodingParam != null) {
//			encoding = encodingParam;
		}
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}
}
