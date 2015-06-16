package com.epam.gm.dao;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.epam.gm.daolayer.TagDao;
import com.epam.gm.model.Tag;

public class TagDaoTest {

	private static TagDao tagDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tagDao = new TagDao();
	}

	@Ignore
	@Test
	public void test() throws SQLException {
		List<Tag> tags = tagDao.getAll();

		for (Tag tag : tags) {
			System.out.println(tag);
		}
	}
	
	@Ignore
	@Test
	public void test2() throws SQLException {
		List<Tag> tags = tagDao.getAllActiveTags() ;

		for (Tag tag : tags) {
			System.out.println(tag);
		}
	}
	
	@Test
	public void test3() throws SQLException {
		List<Tag> tags = tagDao.getAllUserTags(8) ;

		for (Tag tag : tags) {
			System.out.println(tag);
		}
	}

}
