package com.epam.gm.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;

public class EarningsService {

	private Connection connection = ConnectionManager.getConnection();
	private static final String TODAY_EARNINGS = "SELECT SUM(s.price) FROM paid_service ps JOIN event e ON ps.event_id = e.id JOIN service_in_event sie ON ps.service_in_event_id = sie.id JOIN service s ON sie.service_id = s.id where DATE_FORMAT(CURDATE(),'%d.%m.%Y')=DATE_FORMAT(paid_date,'%d.%m.%Y' ) AND e.moderator_id=";
	private static final String YESTERDAY_EARNINGS = "SELECT SUM(s.price) AS allsum FROM paid_service ps JOIN event e ON ps.event_id = e.id JOIN service_in_event sie ON ps.service_in_event_id = sie.id JOIN service s ON sie.service_id = s.id where DATE_FORMAT(DATE_ADD(CURDATE(), INTERVAL -1 DAY),'%d.%m.%Y')=DATE_FORMAT(paid_date,'%d.%m.%Y' ) AND e.moderator_id=";

	public Integer getTodayEarningByModeratorId(int id) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(TODAY_EARNINGS + id + ";");
		Integer sum = null;
		if (rs.next()) {
			sum = rs.getInt(1);
		}
		return sum;

	}

	public Integer getYesterdayEarningsByModeratorId(int id)
			throws SQLException {
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(YESTERDAY_EARNINGS + id + ";");
		Integer sum = null;
		if (rs.next()) {
			sum = rs.getInt(1);
		}
		return sum;

	}

	public static void main(String[] args) throws SQLException {
		System.out.println(new EarningsService()
				.getTodayEarningByModeratorId(2));
	}
}
