package com.epam.gm.web.servlets.userpage;

import com.epam.gm.model.CommentUser;
import com.epam.gm.services.CommentUserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by OLEG on 05.07.2015.
 */
public class UserCabinetCommentControllerServlet implements HttpRequestHandler {

    private CommentUserService commentUserService;

    public UserCabinetCommentControllerServlet(){
        commentUserService = new CommentUserService();
    }


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {

        System.out.println("User Cabinet Comment Controller Servlet ");

        String respondOnComment = "respondOnComment";
        String deleteComment = "deleteComment";

        String command = request.getParameter("command");

        Integer sessionUserId = SessionRepository.getSessionUser(request).getId();


        //ajax request with 'respondOnComment' command
        if (command.equals(respondOnComment)) {
            System.out.println("server request On Comments call");
            String respondText = request.getParameter("respondText");

            Integer generatedId = commentUserService.saveAndReturnId(new CommentUser(sessionUserId, sessionUserId, respondText));

            CommentUser commentUser = commentUserService.getCommentUserById(generatedId);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson(commentUser));
        }

        //ajax request 'deleteComment' command
        if (command.equals(deleteComment)) {
            Integer commentId =  Integer.parseInt(request.getParameter("commentId"));
            System.out.println("server delete comment request commentId = " + commentId);

            commentUserService.deleteById(commentId);

            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(new Gson().toJson("comment has been successfully deleted"));
        }
    }


}
