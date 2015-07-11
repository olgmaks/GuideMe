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
	private static final String LAST_MONTH_EARNINGS = "SELECT SUM(s.price) AS allsum FROM paid_service ps JOIN event e ON ps.event_id = e.id JOIN service_in_event sie ON ps.service_in_event_id = sie.id JOIN service s ON sie.service_id = s.id where MONTH(ps.paid_date) = MONTH(CURDATE())-1 AND YEAR(ps.paid_date) = YEAR(CURDATE()) AND e.moderator_id=";
	private static final String THIS_MONTH_EARNINGS = "SELECT SUM(s.price) AS allsum FROM paid_service ps JOIN event e ON ps.event_id = e.id JOIN service_in_event sie ON ps.service_in_event_id = sie.id JOIN service s ON sie.service_id = s.id where MONTH(ps.paid_date) = MONTH(CURDATE()) AND YEAR(ps.paid_date) = YEAR(CURDATE()) AND e.moderator_id=";
	private static final String ALL_EARNINGS = "SELECT SUM(s.price) AS allsum FROM paid_service ps JOIN event e ON ps.event_id = e.id JOIN service_in_event sie ON ps.service_in_event_id = sie.id JOIN service s ON sie.service_id = s.id where  e.moderator_id=";
	private static final String ALL_NOT_WITHDRAW_MONEY_1 = "SELECT SUM(s.price)-IFNULL((SELECT SUM(money_amount) FROM withdrawmoney WHERE user_id=";
	private static final String ALL_NOT_WITHDRAW_MONEY_2 = "),0) AS allsum FROM paid_service ps JOIN event e ON ps.event_id = e.id JOIN service_in_event sie ON ps.service_in_event_id = sie.id JOIN service s ON sie.service_id = s.id where e.moderator_id=";

	public Double getTodayEarningByModeratorId(int id) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(TODAY_EARNINGS + id + ";");
		Double sum = null;
		if (rs.next()) {
			sum = rs.getDouble(1);
		}
		return sum;

	}

	public Double getYesterdayEarningsByModeratorId(int id) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(YESTERDAY_EARNINGS + id + ";");
		Double sum = null;
		if (rs.next()) {
			sum = rs.getDouble(1);
		}
		return sum;
	}

	public Double getLastMonthEarningsByModeratorId(int id) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(LAST_MONTH_EARNINGS + id + ";");
		Double sum = null;
		if (rs.next()) {
			sum = rs.getDouble(1);
		}
		return sum;
	}

	public Double getThisMonthEarningsByModeratorId(int id) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(THIS_MONTH_EARNINGS + id + ";");
		Double sum = null;
		if (rs.next()) {
			sum = rs.getDouble(1);
		}
		return sum;
	}

	public Double getAllNotWithDrawEarnings(int id) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(ALL_NOT_WITHDRAW_MONEY_1 + id
				+ ALL_NOT_WITHDRAW_MONEY_2 + id + ";");
		Double sum = null;
		if (rs.next()) {
			sum = rs.getDouble(1);
		}
		return sum;
	}

	public static void main(String[] args) throws SQLException {
		System.out.println(new EarningsService().getAllNotWithDrawEarnings(2));
	}
}
