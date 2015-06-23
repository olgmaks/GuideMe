package com.epam.gm.web.servlets.eventpage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.gm.dateparser.DateParser;
import com.epam.gm.model.Address;
import com.epam.gm.model.City;
import com.epam.gm.model.Event;
import com.epam.gm.model.Photo;
import com.epam.gm.model.User;
import com.epam.gm.services.AddressService;
import com.epam.gm.services.CityService;
import com.epam.gm.services.EventService;
import com.epam.gm.services.UserService;
import com.epam.gm.sessionrepository.SessionRepository;
import com.epam.gm.util.ValidateHelper;
import com.epam.gm.web.servlets.frontcontroller.HttpRequestHandler;

public class SubmitAddEventServlet implements HttpRequestHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, SQLException {
		User user = SessionRepository.getSessionUser(request);

		System.out.println("SubmitAddEventServlet");
		// String email = request.getParameter("email");
		// String password = request.getParameter("password");
		// System.out.println(email);
		// System.out.println(password);

		boolean ok = true;

		Map<Integer, String> addressMap = new HashMap<>();

		Map<String, String[]> params = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : params.entrySet()) {
			if (entry.getKey() == null)
				continue;

			// System.out.println("key = " + entry.getKey());
			// System.out.println("value = " + Arrays.toString(entry.getValue())
			// );

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

		System.out.println("ok=" + ok);

		if (ok) {
			Event event = new Event();

			String name = request.getParameter("eventName");

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

			String description = request.getParameter("description");
			String participants_limit = request.getParameter("partisipant_limit");

			event.setName(name);
			event.setDescription(description);
			event.setParticipants_limit(Integer.parseInt(participants_limit));
			event.setDateFrom(dateFrom);
			event.setDateTo(dateTo);

			event.setModeratorId(user.getId());

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

			System.out
					.println("saving event ================================================================");
			System.out.println("name = " + name);
			System.out.println("dateFrom = " + dateFrom);
			System.out.println("dateTo = " + dateTo);

			System.out.println("description = " + description);
			System.out.println("participants_limit = " + participants_limit);

			System.out.println("adressId = " + newAddress.getId());

			event.setAddressId(newAddress.getId());

			EventService eventService = new EventService();

			try {
				eventService.saveEvent(event);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			request.getRequestDispatcher("home.do").forward(request, response);

		}

	}

}