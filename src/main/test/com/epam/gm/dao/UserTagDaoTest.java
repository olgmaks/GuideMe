package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.epam.gm.daolayer.UserTagDao;
import com.epam.gm.model.UserTag;

public class UserTagDaoTest {

	private static UserTagDao userTagDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		userTagDao = new UserTagDao();
	}

	@Ignore
	@Test
	public void test() throws SQLException {
		List<UserTag> userTags = userTagDao.getAll();
		for (UserTag userTag : userTags) {
			System.out.println(userTag);
		}
	}
	
	@Test
	public void test2()  {
		
		for(int i = 0; i<10; i++) {
		
			
			try {
				userTagDao.deleteAllUserTags(8);
			} catch (IllegalAccessException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("done");
		
		}
	}

}
