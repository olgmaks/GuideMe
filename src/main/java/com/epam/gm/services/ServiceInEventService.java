package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.ServiceInEventDao;
import com.epam.gm.model.ServiceInEvent;

public class ServiceInEventService {

	public Integer getPriceOfAllNecessaryServicesByEventId(int id, int userId)
			throws SQLException {
		List<ServiceInEvent> list = new ServiceInEventDao()
				.getAllNecessaryServicesByEventId(id, userId);
		int sum = 0;
		for (ServiceInEvent serviceInEvent : list) {
			sum += serviceInEvent.getService().getPrice();
		}
		return sum;
	}

	public static void main(String[] args) throws SQLException {

	}
}
