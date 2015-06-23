package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.util.List;

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
		return super.getByField(GUIDE_ID, guideId);
	}

}
