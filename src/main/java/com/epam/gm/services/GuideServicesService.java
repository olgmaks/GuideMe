package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.ServiceDao;
import com.epam.gm.model.Service;

public class GuideServicesService {
	private ServiceDao serviceDao = new ServiceDao();

	public Service getServiceById(int id) throws SQLException {
		List<Service> result = serviceDao.getByField("id", id);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}

	}
}
