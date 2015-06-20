package com.epam.gm.services;

import com.epam.gm.daolayer.PhotoDao;
import com.epam.gm.model.Photo;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 15.06.2015.
 */
public class PhotoService {

    private PhotoDao photoDao;

    public PhotoService() {
        photoDao = new PhotoDao();
    }

    public Photo getUserPhoto (int userId) throws SQLException {
        return  photoDao.getUserPhoto(userId);
    }

    public Photo getEventPhoto(int eventId) throws SQLException {
        return  photoDao.getEventPhoto(eventId);
    }
    public List<Photo> getEventPhotos(int eventId) throws SQLException {
        return  photoDao.getEventPhotos(eventId);
    }
    
    public List<Photo> getUserPhotos(int userId) throws SQLException {
    	return photoDao.getUserPhotos(userId);
    }
}
