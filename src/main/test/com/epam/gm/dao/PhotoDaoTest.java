package com.epam.gm.dao;

import com.epam.gm.daolayer.PhotoDao;
import com.epam.gm.model.Photo;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class PhotoDaoTest {

    private static PhotoDao photoDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        photoDao = new PhotoDao();
    }

    @Ignore
    @Test
    public void test() throws SQLException {
        List<Photo> photos = photoDao.getAll();
        for (Photo photo : photos) {
            System.out.println(photo);
        }
    }

    @Ignore
    @Test
    public void testGet() throws SQLException {
        Photo photo = photoDao.getUserPhoto(2);
        System.out.println(photo);
    }

    @Test
    public void testDeleteById () throws SQLException, IllegalAccessException {
        photoDao.deleteByField("id",10);
    }

}
