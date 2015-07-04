package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.epam.gm.model.Service;
import com.epam.gm.model.ServiceInEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class ServiceInEventDao extends AbstractDao<ServiceInEvent> {

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
		return super.getByField("event_id", eventId);
	}

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, SQLException, ParseException {
List<ServiceInEvent>list = new ServiceInEventDao().getAllServicesByEventId(42);
for (ServiceInEvent s:list){
	System.out.println(s.getService().getName());
}
	}

}
