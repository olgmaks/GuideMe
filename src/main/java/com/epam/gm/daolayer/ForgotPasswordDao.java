package com.epam.gm.daolayer;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.epam.gm.model.ForgotPassword;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

public class ForgotPasswordDao extends AbstractDao<ForgotPassword> {
	private static final String CODE_FIELD = "code";

	public ForgotPasswordDao() {
		super(ForgotPassword.class);
	}

	public void saveForgotPassword(ForgotPassword forgotPassword)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		super.save(forgotPassword);
	}

	
	public static void main(String[] args) {
		ForgotPassword forgotPassword = new ForgotPassword();
		forgotPassword.setCode("ss");
		forgotPassword.setIsAvailable(true);
		try {
			new ForgotPasswordDao().saveForgotPassword(forgotPassword);
		} catch (IllegalArgumentException | IllegalAccessException
				| SQLException e) {
			e.printStackTrace();
		}
	}
	public List<ForgotPassword> getAllForgotPassword() throws SQLException {
		return getAll();
	}

	@SuppressWarnings("deprecation")
	public boolean isAvailableCode(String code) {
		boolean newBoolean = false;
		ForgotPassword newForgotPassword = null;
		try {
			newForgotPassword = getForgotPasswordByCode(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (newForgotPassword != null && newForgotPassword.getIsAvailable()) {
			Timestamp timestamp = newForgotPassword.getTimestamp();
			timestamp.setHours(timestamp.getHours() + 1);
			Date newDate = new Date();
			int comparison = timestamp.compareTo(newDate);
			if (comparison == 1) {
				newBoolean = true;
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("is_available", false);
				try {
					updateById(newForgotPassword.getId(), map);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return newBoolean;
	}

	public ForgotPassword getForgotPasswordByCode(String code)
			throws SQLException {
		List<ForgotPassword> result = getByField(CODE_FIELD, code);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0);
		}
	}

	public void updateAvailability(int id, boolean availability) {
		int a = 0;
		if (availability == true) {
			a = 1;
		}
		Map<String, Object> value = new HashMap<String, Object>();
		value.put("is_available", a);
		try {
			updateById(id, value);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
