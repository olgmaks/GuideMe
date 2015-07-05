package com.epam.gm.web.servlets.userpage;

import com.epam.gm.services.CommentUserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by OLEG on 05.07.2015.
 */
public class UserCabinetCommentsServlet implements HttpRequestHandler{

    private CommentUserService commentUserService;

    public UserCabinetCommentsServlet(){
        commentUserService = new CommentUserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {

        Integer sessionUserId = SessionRepository.getSessionUser(request).getId();

        request.setAttribute("userComments",commentUserService.getByUserId(sessionUserId));

        request.setAttribute("centralContent","userCabinetComments");
        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
    }

}
