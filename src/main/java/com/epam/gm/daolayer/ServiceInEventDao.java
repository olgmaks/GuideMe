package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.gm.model.ServiceInEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class ServiceInEventDao extends AbstractDao<ServiceInEvent> {
	private static final String GET_ALL_NOT_DELETED = "e WHERE e.deleted = 0 and e.event_id = %s";
	private static final String GET_ALL_NECESSARY_NOT_PAID = "e WHERE e.deleted = 0 and e.event_id = %s AND e.is_necessary_to_pay=1 AND e.id NOT IN (SELECT ps1.service_in_event_id FROM paid_service ps1 WHERE ps1.user_id =%s);";
	private static final String GET_ALL_NOT_NECESSARY_NOT_PAID = "e WHERE e.deleted = 0 and e.event_id = %s AND e.is_necessary_to_pay=0 AND e.id NOT IN (SELECT ps1.service_in_event_id FROM paid_service ps1 WHERE ps1.user_id =%s);";
	private static final String GET_ALL_PAID = "e WHERE e.deleted = 0 and e.event_id = %s AND e.id IN (SELECT ps1.service_in_event_id FROM paid_service ps1 WHERE ps1.user_id =%s);";

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

	public List<ServiceInEvent> getAllPaidByEventIdAndUserId(int eventId,
			int userId) throws SQLException {
		return super.getWithCustomQuery(String.format(GET_ALL_PAID, eventId,
				userId));
	}

	public List<ServiceInEvent> getAllServicesByEventId(int eventId)
			throws SQLException {
		return super.getWithCustomQuery(String.format(GET_ALL_NOT_DELETED,
				eventId));
	}

	public List<ServiceInEvent> getAllNecessaryServicesByEventId(int eventId,
			int userId) throws SQLException {
		return super.getWithCustomQuery(String.format(
				GET_ALL_NECESSARY_NOT_PAID, eventId, userId));
	}

	public List<ServiceInEvent> getAllNotNecessaryServicesByEventId(
			int eventId, int userId) throws SQLException {
		return super.getWithCustomQuery(String.format(
				GET_ALL_NOT_NECESSARY_NOT_PAID, eventId, userId));
	}

	public void updateServiceInEventToDeletedById(int id) throws SQLException {
		Map<String, Object> updates = new HashMap<String, Object>();
		updates.put("deleted", 1);
		super.updateById(id, updates);

	}

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, SQLException, ParseException {
		ServiceInEvent inEvent = new ServiceInEvent();
		inEvent.setServiceId(3);
		inEvent.setEventId(42);
		inEvent.setDateTo(new Date());
		inEvent.setIsNecessaryToPay(0);
		new ServiceInEventDao().save(inEvent);
	}

}
