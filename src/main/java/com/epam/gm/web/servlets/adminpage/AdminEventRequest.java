package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.model.Address;
import com.epam.gm.model.Event;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class AdminEventRequest  implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

EventDao dao;
	public AdminEventRequest() {
		dao = new EventDao();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String action = request.getParameter("action");
		System.out.println(action);
		List<Event> studentList = new ArrayList<Event>();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		response.setContentType("application/json");

		if (action != null) {
			try {
				if (action.equals("list")) {
					// Fetch Data from Student Table
					studentList = dao.getAllEvents();

					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					System.out.println(studentList);
					JSONROOT.put("Records", studentList);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					//request.setAttribute("",jsonArray);
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(jsonArray);
				} else if (action.equals("create") || action.equals("update")) {
				
					Event event = new Event();
					
					if (request.getParameter("id") != null) {
						int id = Integer.parseInt(request.getParameter("id"));
						event.setId(id);
					}

					if (request.getParameter("name") != null) {
						String name = request.getParameter("name");
						event.setName(name);
					}

					if (request.getParameter("description") != null) {
						String description = request.getParameter("description");
						event.setDescription(description); 
					}

					if (request.getParameter("dateFrom") != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss a");
						String dateFrom = request.getParameter("dateFrom");
						System.out.println("dATE FROM" + dateFrom) ;
						event.setDateFrom(new Date(dateFrom));
					}
					
					if (request.getParameter("dateTo") != null) {
						String dateTo = request.getParameter("dateTo");
						event.setDateTo(new Date(dateTo));
					}
					
					if (request.getParameter("status") != null) {
						String statusId = request.getParameter("status");
						event.setStatus(statusId);
					}
					if (request.getParameter("addressId") != null) {
						String addressId = request.getParameter("addressId");
						event.setAddressId(Integer.parseInt(addressId));
					}
					if (request.getParameter("moderatorId") != null) {
						int moderatorId = Integer.parseInt(request.getParameter("moderatorId"));
						event.setModeratorId(moderatorId);
					}

					if (action.equals("create")) {
						// Create new record
						dao.save(event);
					} else if (action.equals("update")) {
						// Update existing record
						System.out.println(event);
						dao.update(event, "id");
					}

					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", event);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					response.setCharacterEncoding("UTF-8");
					response.getWriter().print(jsonArray);
				} else if (action.equals("delete")) {
					// Delete record
					if (request.getParameter("id") != null) {
						int eventId = Integer.parseInt(request.getParameter("id"));

						System.out.println("event id " +eventId);

						dao.deleteById(eventId);

						// Return in the format required by jTable plugin
						JSONROOT.put("Result", "OK");

						// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.setCharacterEncoding("UTF-8");
						response.getWriter().print(jsonArray);
					}
				}
			} catch (Exception ex) {
				JSONROOT.put("Result", "ERROR");
				JSONROOT.put("Message", ex.getMessage());
				String error = gson.toJson(JSONROOT);
				ex.printStackTrace();
				response.setCharacterEncoding("UTF-8");
				response.getWriter().print(error);
			}
		}
	}
	}
