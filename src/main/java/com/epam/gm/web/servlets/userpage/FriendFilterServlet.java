package com.epam.gm.web.servlets.userpage;

import com.epam.gm.model.FriendUser;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by OLEG on 20.06.2015.
 */
public class FriendFilterServlet implements HttpRequestHandler {

    private FriendUserService friendUserService;

    public  FriendFilterServlet(){
        friendUserService = new FriendUserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, IllegalAccessException {

        System.out.println("friend filter servlet");
        request.setCharacterEncoding("UTF-8");
        int userId = SessionRepository.getSessionUser(request).getId();
        String filterInput = request.getParameter("friendfilter");
        System.out.println(String.format("UserID = %s ... Filter Input = %s", userId, filterInput));


        Collection<FriendUser> results = friendUserService.filterFriends(userId, filterInput);
        boolean isEmpty = false;

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        Map<String, Object> responseMap = new HashMap<>();

        responseMap.put("isEmpty", isEmpty);
        responseMap.put("value", filterInput);
        responseMap.put("results", results);
        response.getWriter().write(new Gson().toJson(responseMap));

    }

}
