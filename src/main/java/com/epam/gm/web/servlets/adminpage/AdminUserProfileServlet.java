package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.StringJoiner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.calculators.EventCalculator;
import com.epam.gm.calculators.UserCalculator;
import com.epam.gm.daolayer.RatingUserDao;
import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.FriendUser;
import com.epam.gm.model.Language;
import com.epam.gm.model.Tag;
import com.epam.gm.model.User;
import com.epam.gm.services.CommentUserService;
import com.epam.gm.services.EventService;
import com.epam.gm.services.FriendUserService;
import com.epam.gm.services.LanguageService;
import com.epam.gm.services.PhotoService;
import com.epam.gm.services.TagService;
import com.epam.gm.services.UserService;
import com.epam.gm.services.UserTypeService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;


public class AdminUserProfileServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		
		System.out.println("AdminUserProfileServlet");
		
		FriendUserService friendUserService = new FriendUserService();
		User user = new User();
		RatingUserDao ruDao = new RatingUserDao();
		user = SessionRepository.getSessionUser(request);
		
		if(user == null) {
			response.sendRedirect("401.do");
			return;
		}
		
		if(!ValidateHelper.isNumber(request.getParameter("id"))) {
			response.sendRedirect("404.do");
			return;
		}
		int id = Integer.parseInt(request.getParameter("id"));
		UserService userService = new UserService();
		User profile = userService.getUserById(id);
		if(profile == null) {
			response.sendRedirect("404.do");
			return;
		}
		userService.buildTagString(profile);
		

		if(profile.getId().equals(user.getId())) {
			response.sendRedirect("userCabinet.do");
			return;
		}
		
		List<FriendUser> friends = friendUserService.getUserFriends(user.getId());
		UserCalculator.sortFriendsByPoints(friends, user.getId());
		
		Integer friendId =  isInTheListFriend(friends, id);
		boolean isFriend =  friendId != null;
		Integer requestedId = isInTheList(friendUserService.getUserRequestsFromFriends(user.getId()) , id);
		Integer requesterId = isInTheListFriend(friendUserService.getUserRequestsToFriends(user.getId()), profile.getId());
		boolean noRelation = (!isFriend) && (requestedId == null) && (requesterId == null);

		friendUserService.getUserRequestsToFriends(user.getId()).forEach(x -> System.out.println(x.getUserId() + " " + x.getFriendId()));;
		
		
		UserCalculator calc = new UserCalculator(profile.getId(), user.getId());
		long averMark = Math.round(calc.getAverageRate());
		long total = Math.round(calc.calculate());
		
		List<Language> langs = new LanguageService().getAllUserLangs(id);
		StringJoiner joiner = new StringJoiner(", ", "", "");
		joiner.setEmptyValue("not_specified");
		for(Language l : langs) 
			joiner.add(l.getName());
		
		
		
		UserDao userDao = new UserDao();
		
		
		EventService es = new EventService();
		List<Event> moderatorEvents = es.getAllActiveEventsWhereUserModerator(id);
		EventCalculator.sortEventsByPoints(moderatorEvents, user.getId());

		List<Event> memberEvents = es.getAllActiveEventsWhereUserNotModerator(id);
		EventCalculator.sortEventsByPoints(memberEvents, user.getId());		
		
		try {
			request.setAttribute("userLogined", SessionRepository.getSessionUser(request));
			request.setAttribute("commentUser", new CommentUserService().getByUserId(id));
			request.setAttribute("userActivity",userDao.userActivity(id));
			request.setAttribute("friends", friends);
			request.setAttribute("isFriend", isFriend);
			request.setAttribute("isAdmin", SessionRepository.isAdmin(request));
			request.setAttribute("averMark", averMark);
			request.setAttribute("total", total);
			request.setAttribute("profileId", profile.getId());
			request.setAttribute("profile", profile);
			
			request.setAttribute("friendId", friendId);
			request.setAttribute("requestedId", requestedId);
			request.setAttribute("requesterId", requesterId);
			request.setAttribute("noRelation", noRelation);
			request.setAttribute("langs", joiner.toString());
			request.setAttribute("moderatorEvents", moderatorEvents);
			request.setAttribute("memberEvents", memberEvents);
			
			request.setAttribute("photos",
					new PhotoService().getUserPhotos(id));
			
			
			Integer mark = null;
			if (user != null){
				if (ruDao.getMarkByEvent(id, user.getId()) != null){
					mark =  ruDao.getMarkByEvent(id, user.getId()).getMark();
				}
			}
			request.setAttribute("mark", mark);
			System.out.println("friends " + friendUserService.getUserFriends(id));
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		}
		request.setAttribute("user", profile);
		request.setAttribute("userType", new UserTypeService().getAll());
		request.setAttribute("centralContent", "adminUserDetail");
			
			request.getRequestDispatcher("pages/admin/adminPanel.jsp").forward(request, response);
		//request.getRequestDispatcher("pages/admin/adminUserDetail.jsp").forward(request, response);
	}
	
	
	private Integer isInTheListFriend(List<FriendUser> list,  Integer id) {
		if(list == null || id == null) return null;
		
		for(FriendUser f: list) 
			if(f.getFriendId().equals(id)) 
				return f.getId();
		return null;
	}
	
	private Integer isInTheList(List<FriendUser> list,  Integer id) {
		if(list == null || id == null) return null;
		
		for(FriendUser f: list) 
			if(f.getUserId().equals(id))  
				return f.getId();
				
		return null;
	}
}