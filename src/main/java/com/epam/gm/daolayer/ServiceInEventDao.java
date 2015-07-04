package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.epam.gm.model.ServiceInEvent;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class ServiceInEventDao extends AbstractDao<ServiceInEvent> {

	public ServiceInEventDao() {
		// gryn
		// super(ConnectionManager.getConnection(), ServiceInEvent.class);
		super(ServiceInEvent.class);
	}

	public void saveService(ServiceInEvent serviceInEvent)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		super.save(serviceInEvent);
	}

	public static void main(String[] args) throws IllegalArgumentException,
			IllegalAccessException, SQLException, ParseException {
		ServiceInEvent serviceInEvent = new ServiceInEvent();
		String date = "2015-07-04";
		String time = "22:02";
		String fulldate = date + "-" + time;
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
		Date d = format.parse(fulldate);
		serviceInEvent.setEventId(22);
		serviceInEvent.setServiceId(3);
		serviceInEvent.setDateFrom(d);

		new ServiceInEventDao().saveService(serviceInEvent);

	}

}
