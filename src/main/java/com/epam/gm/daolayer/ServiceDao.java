package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.gm.model.Service;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class ServiceDao extends AbstractDao<Service> {
	private static final String GUIDE_ID = "guide_id";

	public ServiceDao() {
		// gryn
		// super(ConnectionManager.getConnection(), Service.class);
		super(Service.class);
	}

	public List<Service> getServicesByGuideId(int guideId) throws SQLException {
		List<Service> newList = new ArrayList<Service>();
		for (Service s : super.getByField(GUIDE_ID, guideId)) {
			if (!s.isDeleted()) {
				newList.add(s);
			}
		}

		return newList;
	}

	public void deleteServiceById(int id) throws IllegalAccessException,
			SQLException {
		super.deleteByField("id", id);
	}

	public void addService(Service s) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		super.save(s);
	}

	public void updateServiceToDeletedById(int id) {
		Map<String, Object> updates = new HashMap<String, Object>();
		updates.put("deleted", 1);
		try {
			updateById(id, updates);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
