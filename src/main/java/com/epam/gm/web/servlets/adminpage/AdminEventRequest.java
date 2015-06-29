package com.epam.gm.web.servlets.adminpage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.daolayer.RatingEventDao;
import com.epam.gm.daolayer.UserDao;
import com.epam.gm.daolayer.UserInEventDao;
import com.epam.gm.dateparser.DateParser;
import com.epam.gm.model.Address;
import com.epam.gm.model.City;
import com.epam.gm.model.CommentEvent;
import com.epam.gm.model.Event;
import com.epam.gm.model.RatingEvent;
import com.epam.gm.model.User;
import com.epam.gm.model.UserInEvent;
import com.epam.gm.services.AddressService;
import com.epam.gm.services.CityService;
import com.epam.gm.services.CommentEventService;
import com.epam.gm.services.UserInEventService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.utf8uncoder.StringHelper;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AdminEventRequest implements HttpRequestHandler {
	private static final long serialVersionUID = 1L;
	private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

	EventDao dao;

	public AdminEventRequest() {
		dao = new EventDao();
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String action = request.getParameter("action");
		request.setCharacterEncoding("UTF-8");
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
					// request.setAttribute("",jsonArray);
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
						String description = request
								.getParameter("description");
						event.setDescription(description);
					}

					if (request.getParameter("dateFrom") != null) {
						SimpleDateFormat sdf = new SimpleDateFormat(
								"dd/MM/yyyy HH:mm:ss a");
						String dateFrom = request.getParameter("dateFrom");
						System.out.println("dATE FROM" + dateFrom);
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
						int moderatorId = Integer.parseInt(request
								.getParameter("moderatorId"));
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
				}

				if (action.equalsIgnoreCase("edit")) {
					editEvent(request, response);
				} else if (action.equals("delete")) {
					// Delete record
					if (request.getParameter("id") != null) {
						int eventId = Integer.parseInt(request
								.getParameter("id"));

						System.out.println("event id " + eventId);

						dao.deleteById(eventId);

						// Return in the format required by jTable plugin
						JSONROOT.put("Result", "OK");

						// Convert Java Object to Json
						String jsonArray = gson.toJson(JSONROOT);
						response.setCharacterEncoding("UTF-8");
						response.getWriter().print(jsonArray);
					}
				} else if (action.equals("commentEvent")) {
					commentEvent(request, response);
				} else if (action.equals("saveFile")) {
					ratingEvent(request, response);
				} else if (action.equals("ratingEvent")) {
					ratingEvent(request, response);
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

	private void ratingEvent(HttpServletRequest request,
			HttpServletResponse response) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		RatingEvent re = new RatingEvent();
		User user = SessionRepository.getSessionUser(request);
		Integer eventId = Integer.parseInt(request.getParameter("eventId"));
		re.setEstimatorId(user.getId());
		re.setEventId(eventId);
		re.setMark(Integer.parseInt(request.getParameter("mark")));
		RatingEventDao reDao = new RatingEventDao();
		RatingEvent reFromDB = reDao.getMarkByEvent(eventId, user.getId());

		if (reFromDB == null) {
			reDao.save(re);
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("mark", Integer.parseInt(request.getParameter("mark")));
			reDao.updateById(reFromDB.getId(), map);
		}
	}

	public void editEvent(HttpServletRequest request,
			HttpServletResponse response) throws SQLException {
		boolean ok = true;

		Map<Integer, String> addressMap = new HashMap<>();
		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			if (entry.getKey() == null)
				continue;

			if (entry.getKey().startsWith("addressByLang_")) {

				String key = entry.getKey().replace("addressByLang_", "")
						.trim();
				if (ValidateHelper.isNumber(key)) {
					Integer langId = Integer.parseInt(key);

					if (entry.getValue() != null && entry.getValue().length > 0) {
						String value = entry.getValue()[0];

						String result = ValidateHelper.validateField("address",
								value, User.class);

						System.out.println("**********************");
						System.out.println("param = " + entry.getKey());
						System.out.println("value = " + value);
						System.out.println("result = " + result);

						addressMap.put(langId, value);

						if (!"".equals(result))
							if (!result.endsWith(".ok"))
								ok = false;
					}

				}

			} else {

				String param = entry.getKey().trim();
				String value = "";
				if (entry.getValue() != null && entry.getValue().length > 0)
					value = entry.getValue()[0];

				String result = ValidateHelper.validateField(param, value,
						User.class);

				System.out.println("**********************");
				System.out.println("param = " + param);
				System.out.println("value = " + value);
				System.out.println("result = " + result);

				System.out.println("**********************AdressMap");
				System.out.println(addressMap);

				if (!"".equals(result))
					if (!result.endsWith(".ok"))
						ok = false;

			}
		}

		Map<String, Object> map = new HashMap<>();
		Integer eventId = Integer.parseInt(request.getParameter("eventId"));
		map.put("name",
				StringHelper.convertFromUTF8(request.getParameter("name")));

		StringBuilder date_frombuilder = new StringBuilder();
		date_frombuilder.append(request.getParameter("dateFrom"));
		date_frombuilder.append(" ");
		date_frombuilder.append(request.getParameter("hourFrom"));
		date_frombuilder.append(":");
		date_frombuilder.append(request.getParameter("minuteFrom"));
		date_frombuilder.append(":");
		date_frombuilder.append("00");
		String date_from = date_frombuilder.toString();
		Date dateFrom = DateParser.StringToSqlDate(date_from);

		StringBuilder date_tobuilder = new StringBuilder();
		date_tobuilder.append(request.getParameter("dateTo"));
		date_tobuilder.append(" ");
		date_tobuilder.append(request.getParameter("hourTo"));
		date_tobuilder.append(":");
		date_tobuilder.append(request.getParameter("minuteTo"));
		date_tobuilder.append(":");
		date_tobuilder.append("00");
		String date_to = date_tobuilder.toString();
		Date dateTo = DateParser.StringToSqlDate(date_to);

		map.put("date_from", dateFrom);
		map.put("date_to", dateTo);

		map.put("description", request.getParameter("description"));

		if (request.getParameter("partisipant_limit") != "") {

			map.put("participants_limit",
					request.getParameter("partisipant_limit"));
		}

		if (request.getParameter("videoLink") != "") {
			map.put("video_link", request.getParameter("videoLink"));
		}

		UserInEventService userInEventService = new UserInEventService();
		User user = new User();
		user = SessionRepository.getSessionUser(request);
		List<UserInEvent> userInEvent = userInEventService.getByEventAndUser(
				eventId, user.getId());
		if (!userInEvent.isEmpty()) {
			Map<String, Object> map1 = new HashMap<>();
			map1.put("status", request.getParameter("status"));

			if (request.getParameter("bad_count") != "") {
				if (request.getParameter("bedCountSelect").equalsIgnoreCase(
						"need")) {
					map1.put(
							"bed_count",
							Integer.parseInt("-"
									+ request.getParameter("bad_count")));

				} else if (request.getParameter("bedCountSelect")
						.equalsIgnoreCase("accept")) {
					map1.put("bed_count",
							Integer.parseInt(request.getParameter("bad_count")));
				}
			}
			UserInEventDao userInEventDao = new UserInEventDao();
			try {

				userInEventDao.updateById(userInEvent.get(0).getId(), map1);

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (ok) {
			Integer cityId = Integer.parseInt(request.getParameter("cityId")
					.trim());

			// get our city
			CityService cityService = new CityService();
			City city = cityService.getCityById(cityId);

			Map<Integer, Integer> cityMap = new HashMap<>();
			// get analogs
			List<City> cities = cityService.getCitiesByPureId(city.getPureId());
			for (City c : cities) {
				cityMap.put(c.getLocalId(), c.getId());
			}
			System.out
					.println("saving adress ================================================================");
			AddressService addressService = new AddressService();
			Integer newPureId = addressService.getLastPureId() + 1;

			for (City c : cities) {

				Address address = new Address();
				address.setCityId(c.getId());
				address.setLocalId(c.getLocalId());
				address.setPureId(newPureId);
				address.setAddress(addressMap.get(c.getLocalId()));

				addressService.save(address);
			}

			Address newAddress = addressService.getAddressByPureId(newPureId)
					.get(0);

			map.put("address_id", newAddress.getId());

		}
		// map.put("user_type_id", request.getParameter("userTypeId"));
		EventDao eventDao = new EventDao();
		try {
			eventDao.updateById(eventId, map);


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response.sendRedirect("eventDetail.do?id=" + eventId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void commentEvent(HttpServletRequest request,
			HttpServletResponse response) {
		CommentEventService ceService = new CommentEventService();
		int eventId = Integer.parseInt(request.getParameter("eventId"));
		CommentEvent cu = new CommentEvent();
		cu.setComment(StringHelper.convertFromUTF8(request
				.getParameter("comment")));
		cu.setCommentatorId(SessionRepository.getSessionUser(request).getId());
		cu.setEventId(eventId);
		try {
			ceService.save(cu);
			response.sendRedirect("eventDetail.do?id=" + eventId);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}