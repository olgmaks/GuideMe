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


public class AdminEventRequest  extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

EventDao dao;
	public AdminEventRequest() {
		dao = new EventDao();
	}

	public void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
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
					response.getWriter().print(jsonArray);
				} else if (action.equals("create") || action.equals("update")) {
					System.out.println("create");
					Event event = new Event();
					if (request.getParameter("studentId") != null) {
						int id = Integer.parseInt(request.getParameter("eventId"));
						event.setId(id);
					}
//
//					if (request.getParameter("name") != null) {
//						String name = request.getParameter("name");
//						student.setName(name);
//					}
//
//					if (request.getParameter("department") != null) {
//						String department = request.getParameter("department");
//						student.setDepartment(department);
//					}
//
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
					response.getWriter().print(jsonArray);
				} else if (action.equals("delete")) {
					// Delete record
					if (request.getParameter("eventId") != null) {
						int eventId = Integer.parseInt(request.getParameter("studentId"));
						//dao.delete(studentId);

						// Return in the format required by jTable plugin
						JSONROOT.put("Result", "OK");

						// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.getWriter().print(jsonArray);
					}
				}
			} catch (Exception ex) {
				System.out.println("EROR");
				JSONROOT.put("Result", "ERROR");
				JSONROOT.put("Message", ex.getMessage());
				String error = gson.toJson(JSONROOT);
				ex.printStackTrace();
				response.getWriter().print(error);
			}
		}
	}
	}

