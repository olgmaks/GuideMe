package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.EventDao;
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
		if (action  == null) action =  "list";
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
					System.out.println("create");
					Event event = new Event();
					if (request.getParameter("id") != null) {
						int id = Integer.parseInt(request.getParameter("eventId"));
						event.setId(id);
					}

					if (request.getParameter("name") != null) {
						String name = request.getParameter("name");
						event.setName(name);
					}

					if (request.getParameter("description") != null) {
						String description = request.getParameter("department");
						event.setDescription(description); 
					}

//					if (request.getParameter("emailId") != null) {
//						String emailId = request.getParameter("emailId");
//						student.setEmailId(emailId);
//					}
//
//					if (action.equals("create")) {
//						// Create new record
//						dao.addStudent(student);
//					} else if (action.equals("update")) {
//						// Update existing record
//						dao.updateStudent(student);
//					}

					// Return in the format required by jTable plugin
					JSONROOT.put("Result", "OK");
					JSONROOT.put("Record", event);

					// Convert Java Object to Json
					String jsonArray = gson.toJson(JSONROOT);
					 response.setCharacterEncoding("UTF-8");
					response.getWriter().print(jsonArray);
				} else if (action.equals("delete")) {
					// Delete record
					System.out.println( "delete id "+request.getParameter("id"));
					if (request.getParameter("id") != null) {
						int eventId = Integer.parseInt(request.getParameter("id"));
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
				System.out.println("EROR");
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

