package com.epam.gm.calculators;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.EventDao;
import com.epam.gm.model.Event;

public class EventCalculatorTest {
	
	private static EventCalculator eventCalc;
	private static Event event;
	private static EventDao dao = new EventDao();
	
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		event = dao.getByField("id", 1).get(0);
		System.out.println("Our event: " + event);
		System.out.println("+++++++++++++++++++++++++++++++++++++");
		
		//eventCalc = new EventCalculator(1);
	}

	@Test
	public void testIsActiveAndNotOutOfDate() throws SQLException {
		System.out.println("isActive = " + eventCalc.isActiveAndNotOutOfDate());
		System.out.println("ModeratorGuide = " + eventCalc.isModeratorGuide());
		System.out.println("ModeratorUser = " + eventCalc.isModeratorUser());
		System.out.println("Particip = " + eventCalc.countOfParticipants());
	}

}
