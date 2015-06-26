package com.epam.gm.web.servlets.userpage;

import com.epam.gm.model.FriendUser;
import com.epam.gm.model.User;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.UserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by OLEG on 21.06.2015.
 */
public class SearchUserFilterServlet implements HttpRequestHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, IllegalAccessException {
        System.out.println("SearchUserFilterServlet servlet");
        System.out.println(request);
        Integer searcherId = SessionRepository.getSessionUser(request).getId();

        String nameFilterInput = request.getParameter("userNameInput");

        String cityName = request.getParameter("cityName");

        String tags = request.getParameter("tags");

//        Integer tagsMatches = Integer.valueOf(request.getParameter(""));
        Integer tagsMatches = 0;

        String searchRoleString = request.getParameter("searchRole");

        if (searchRoleString == null || searchRoleString.isEmpty()) {
            searchRoleString = "all";
        }
//        System.out.print("/ searchRole : " + searchRoleString);
        UserService.SearchRole searchRole = UserService.SearchRole.valueOf(searchRoleString);


        Collection<User> users = UserService.serve().searchUsers(
                searcherId, nameFilterInput, cityName, tags, tagsMatches, searchRole);

        System.out.print(" /searcher id : " + searcherId);
        System.out.print(" / nameFilterInput : " + nameFilterInput);
        System.out.print(" / cityName : " + cityName);
        System.out.print(" / tags : " + tags);
        System.out.println(" / tagsMatches : " + tagsMatches);
        System.out.print("/ searchRole : " + searchRole);


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(users));
    }
}
