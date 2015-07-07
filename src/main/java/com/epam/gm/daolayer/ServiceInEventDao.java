package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.gm.model.Service;
import com.epam.gm.model.ServiceInEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class ServiceInEventDao extends AbstractDao<ServiceInEvent> {
	private static final String GET_ALL_NOT_DELETED = "e WHERE e.deleted = 0 and e.event_id = %s";

	public ServiceInEventDao() {
		// gryn
		// super(ConnectionManager.getConnection(), ServiceInEvent.class);
		super(ServiceInEvent.class);
	}

	public void saveServiceInEvent(ServiceInEvent serviceInEvent)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		super.save(serviceInEvent);
	}

	public List<ServiceInEvent> getAllServicesByEventId(int eventId)
			throws SQLException {
		return super.getWithCustomQuery(String.format(GET_ALL_NOT_DELETED,
				eventId));

	}

	public void updateServiceInEventToDeletedById(int id) throws SQLException {
		Map<String, Object> updates = new HashMap<String, Object>();
		updates.put("deleted", 1);
		super.updateById(id, updates);

	}

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, SQLException, ParseException {
		List<ServiceInEvent> list = new ServiceInEventDao()
				.getAllServicesByEventId(42);
		for (ServiceInEvent s : list) {
			System.out.println(s.getService().getName());
		}
	}

}
