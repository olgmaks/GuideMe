package com.epam.gm.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.TagDao;
import com.epam.gm.model.Tag;

public class TagDaoTest {

    private static TagDao tagDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	tagDao = new TagDao();
    }

    @Test
    public void test() {
	List<Tag> tags = tagDao.getAll();

	for (Tag tag : tags) {
	    System.out.println(tag);
	}
    }

}
