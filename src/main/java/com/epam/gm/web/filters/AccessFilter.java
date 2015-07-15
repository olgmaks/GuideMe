package com.epam.gm.web.filters;

import com.epam.gm.model.User;
import com.epam.gm.sessionrepository.SessionRepository;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {
    // private String encoding = "utf-8";

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain filterChain) throws IOException, ServletException {

        System.out.println("filter works ... ");

        HttpServletRequest servletRequest = ((HttpServletRequest) request);
        HttpServletResponse servletResponse = ((HttpServletResponse) response);

        HttpSession session = servletRequest.getSession(false);
        System.out.println("session : " + session);
        User user = null;

        if (session != null) {
            user = SessionRepository.getSessionUser(servletRequest);
        }

        String URI = servletRequest.getRequestURI().trim();

        System.out.println(URI);

        System.out.println(session);
        
        if(URI.contains("//")) {
        	servletResponse.sendRedirect("404.do");
        	return;
        }
        

        //Allowing image
        if (URI.endsWith("css")
                || URI.endsWith("js")
                || URI.endsWith("png")
                || URI.endsWith("jpg")
                || URI.endsWith("jpeg")
                || URI.endsWith("ttf")
                || URI.endsWith("woff")
                || URI.endsWith("ttf")
                || URI.endsWith("woff2")
                ) {

            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        
        
//        if (URI.endsWith(".html") || URI.endsWith(".jsp")) {
//       	 servletResponse.sendRedirect("http://localhost:8080/GuideMe/404.do");
//       	 return;
//        }        
        
        if (!URI.contains(".do") && (
				!URI.equals("/GuideMe/") &&
				!URI.contains("chat") &&
				!(URI.contains("Report"))
				)) {
			System.out.println("uri not contains");
			System.out.println(URI);
			servletResponse.sendRedirect("404.do");
			return;
		}
		

		
        //Verifying does session null
        if (session == null && (
                        !URI.contains("home.do") &&
                        !URI.contains("loginPage.do") &&
                        !URI.contains("login.do") &&
                        !URI.contains("loginfb.do") &&
                        !URI.contains("loginvk.do") &&
                        !URI.contains("register.do") &&
                        !URI.contains("registervalidator.do") &&
                        !URI.contains("getCitiesByCountry.do") &&
                        !URI.contains("getLocalCountryAnalogs.do") &&
                        !URI.contains("getLocalCityAnalogs.do") &&
                        !URI.contains("registerAddressValidator.do") &&
                        !URI.contains("confirmValidator.do") &&
                        !URI.contains("submitRegister.do") &&
                        !URI.contains("userforgotpassword.do") &&
                        !URI.contains("sendlinktoresetpass.do") &&
                        !URI.contains("searchindexpage.do") &&
                        !URI.contains("404.do") &&
                        !URI.equals("/GuideMe/")
         )) {
            System.out.println("session null - redirecting in login ");
//			request.getRequestDispatcher("loginPage.do").forward(servletRequest,servletResponse);
            servletResponse.sendRedirect("loginPage.do");
//			filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
//
//        Verifying does user null
        if (session != null &&
                user == null && (
                        !URI.contains("home.do") &&
                        !URI.contains("loginPage.do") &&
                        !URI.contains("login.do") &&
                        !URI.contains("loginfb.do") &&
                        !URI.contains("loginvk.do") &&
                        !URI.contains("register.do") &&
                        !URI.contains("registervalidator.do") &&
                        !URI.contains("getCitiesByCountry.do") &&
                        !URI.contains("getLocalCountryAnalogs.do") &&
                        !URI.contains("getLocalCityAnalogs.do") &&
                        !URI.contains("registerAddressValidator.do") &&
                        !URI.contains("confirmValidator.do") &&
                        !URI.contains("submitRegister.do") &&
                        !URI.contains("userforgotpassword.do") &&
                        !URI.contains("sendlinktoresetpass.do") &&
                        !URI.contains("searchindexpage.do") &&
                        !URI.contains("404.do") &&
                        !URI.equals("/GuideMe/")
        )) {

            System.out.println("session user null - redirecting in login ");
//			request.getRequestDispatcher("loginPage.do").forward(servletRequest,servletResponse);
            servletResponse.sendRedirect("loginPage.do");
//			filterChain.doFilter(servletRequest, servletResponse);
            return;
        }


        //Filter for admin
        if (session != null
                && user != null
                && (
                URI.contains("admin.do")
        )) {

            if (user.getUserType().getName().equals("admin")) {
                filterChain.doFilter(request, response);
                return;
            } else {
                servletResponse.sendRedirect("home.do");
                return;
            }
        }

//        if(SessionRepository.getSessionUser(servletRequest).getUserType().getName())

        // request.setCharacterEncoding(encoding);
        //
        // HttpServletRequest req = (HttpServletRequest) request;
        // String uri = req.getRequestURI().toLowerCase().trim();
        //
        // String path = req.getServletPath();
        //
        // boolean error = false;
        // if (uri.endsWith(".html") || uri.endsWith(".jsp")
        // || uri.endsWith(".md") || uri.endsWith("licence"))
        // error = true;
        //
        // if(path.trim().length() > 0)
        // if(!path.contains(".do"))
        // error = true;
        //
        //
        // if (error) {
        // System.out.println("Filter not pass: ");
        // System.out.println(uri);
        // System.out.println(path);
        //
        //
        // HttpServletResponse resp = (HttpServletResponse)response;
        // req.getRequestDispatcher("404.do").forward(request, response);
        //
        //
        //
        // return;
        // }
        //
        // ((HttpServletRequest)request).getSession(true);
        filterChain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String encodingParam = filterConfig.getInitParameter("encoding");
        if (encodingParam != null) {
            // encoding = encodingParam;
        }

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }
}
