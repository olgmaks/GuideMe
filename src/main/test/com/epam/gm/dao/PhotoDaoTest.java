package com.epam.gm.dao;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.gm.daolayer.PhotoDao;
import com.epam.gm.model.Photo;

public class PhotoDaoTest {
    
    private static PhotoDao photoDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
	photoDao = new PhotoDao();
    }
 

    @Test
    public void test() {
	List<Photo> photos = photoDao.getAll();
	for (Photo photo : photos) {
	    System.out.println(photo);
	}
    }

}
