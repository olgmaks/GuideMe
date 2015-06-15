package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.AddressDao;
import com.epam.gm.model.Address;

public class AddressService {
	
	private AddressDao dao = new AddressDao();

	// gryn
	public Integer getLastPureId() throws SQLException {
		return dao.getLastPureId();
	}

	// gryn
	public void save(Address address) throws SQLException {

		try {
			dao.save(address);
		} catch (IllegalArgumentException | IllegalAccessException
				| SQLException e) {

			e.printStackTrace();

			throw new SQLException(e);
		}
	}
	
	//gryn
	public List<Address> getAddressByPureId(Integer pureId) throws SQLException {
		return dao.getAddressByPureId(pureId);
	}
}
