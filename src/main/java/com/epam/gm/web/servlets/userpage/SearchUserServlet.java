package com.epam.gm.web.servlets.userpage;

import com.epam.gm.model.FriendUser;
import com.epam.gm.model.User;
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

    public SearchUserServlet(){
        userService = new UserService();
    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, IllegalAccessException {
        System.out.println("search user servlet ...");

        Integer sessionUserId = SessionRepository.getSessionUser(request).getId();
//
//        FriendUserService friendUserService =  FriendUserService.serve();
//
//        Set<User> sentRequestUsers = new HashSet<>();
//        for (FriendUser fu : friendUserService.getUserRequestsToFriends(sessionUserId)){
//            sentRequestUsers.add(fu.getFriend().setFriendCriteria(User.FriendCriteria.sentRequest));
//        }
//
//
//        Set<User> receivedFriendUsers =  new HashSet<>();
//        for (FriendUser fu : friendUserService.getUserRequestsFromFriends(sessionUserId)){
//            receivedFriendUsers.add(fu.getUser().setFriendCriteria(User.FriendCriteria.receivedRequest));
//        }
//
//        Set <User> results = new HashSet<>();
//        results.addAll(sentRequestUsers);
//        results.addAll(sentRequestUsers);

        List<User> users = UserService.serve().searchNonFriendsUsers(SessionRepository.
                getSessionUser(request).getId());
//
//        for (User user : users) {
//            user.setFriendCriteria(User.FriendCriteria.notFriend);
//        }
//
//        results.addAll(users);



        request.setAttribute("users", users);
        request.setAttribute("centralContent", "searchuser");
        request.getRequestDispatcher("pages/user/usercabinet.jsp").forward(request, response);
    }
}
