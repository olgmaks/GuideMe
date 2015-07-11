package com.epam.gm.web.servlets.userpage;

import com.epam.gm.model.User;
import com.epam.gm.services.CountryService;
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

/**
 * Created by OLEG on 21.06.2015.
 */
public class SearchUserFilterServlet implements HttpRequestHandler {

    private UserService userService;
    private CountryService countryService;

    public SearchUserFilterServlet() {
        userService = new UserService();
        countryService = new CountryService();
    }


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, IllegalAccessException {
        System.out.println("SearchUserFilterServlet servlet");
        System.out.println(request);

        Integer searcherId = SessionRepository.getSessionUser(request).getId();

        //Maks. getting input and spliting on two parts like fname-lname
        String nameFilterInput = request.getParameter("userNameInput");
        String firstName = "";
        String lastName = "";
        String[] strings = nameFilterInput.split(" ");

        if (strings.length == 1) {
            firstName = strings[0];
        }

        if (strings.length >= 2) {
            firstName = strings[0];
            lastName = strings[1];
        }

        //Maks. getting country name from request
        String countryName = request.getParameter("countryName");

        if (countryName != null) {
            if (countryName.equals("any") || countryName.equals("all")) {
                countryName = "";
            }
        } else {
            countryName = "";
        }

        //Maks. getting city name from request
        String cityName = request.getParameter("cityName");
        if (cityName != null) {
            if (cityName.equals("any") || cityName.equals("all")) {
                cityName = "";
            }
        }else {
            cityName = "";
        }

        //Maks. getting country name from request
        String tags = request.getParameter("tags");


        //Maks. getting searchrole from request
        String searchRoleString = request.getParameter("searchRole");

        if (searchRoleString == null || searchRoleString.isEmpty()) {
            searchRoleString = "all";
        }

        UserService.SearchRole searchRole = UserService.SearchRole.valueOf(searchRoleString);


        Collection<User> users = userService.searchUsers(
                searcherId, firstName, lastName, countryName, cityName, tags, searchRole);

        System.out.print(" /searcher id : " + searcherId);
        System.out.print(" / nameFilterInput : " + nameFilterInput);
        System.out.print(" / firstName : " + firstName);
        System.out.print(" / lastName : " + lastName);
        System.out.print(" / countryName : " + countryName);
        System.out.print(" / cityName : " + cityName);
        System.out.print(" / tags : " + tags);
        System.out.print("/ searchRole : " + searchRole);


        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(new Gson().toJson(users));
    }
}
