package com.epam.gm.web.servlets.userpage;

import com.epam.gm.model.Country;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.User;
import com.epam.gm.services.CountryService;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.UserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by OLEG on 20.06.2015.
 */
public class SearchUserServlet implements HttpRequestHandler {

    private UserService userService;
    private CountryService countryService;

    public SearchUserServlet(){
        userService = new UserService();
        countryService = new CountryService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, IllegalAccessException {
        System.out.println("search user servlet ...");

        Integer sessionUserId = SessionRepository.getSessionUser(request).getId();

        List<User> users = UserService.serve().searchNonFriendsUsers(SessionRepository.
                getSessionUser(request).getId());

        Iterator<User> iter = users.iterator();

        while (iter.hasNext()){
            User user = iter.next();
            user.setPoints(userService.getUserAvgMark(user.getId()));
        }

        List<Country> countries = countryService
                .getCountriesByLocalId(SessionRepository.getSessionLanguage(request).getId());

        request.setAttribute("countryItems", countries);
        request.setAttribute("users", users);
        request.setAttribute("centralContent", "searchuser");
        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
    }
}
