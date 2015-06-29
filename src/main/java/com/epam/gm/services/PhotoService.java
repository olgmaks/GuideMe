package com.epam.gm.services;

import com.epam.gm.daolayer.PhotoDao;
import com.epam.gm.model.Photo;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 15.06.2015.
 */
public class PhotoService {
    
    public static final String PATH_TO_EVENT_PHOTOS = "/img/eventphotos/";
    public static final String PATH_TO_USER_PHOTOS="/img/userphotos/";
    public static final String SAVE_EVENT_PHOTO_URL = "uploadeventphoto.do";
    public static final String SAVE_USER_PHOTO_URL="uploaduserphoto.do";

    private PhotoDao photoDao;
    
    public static PhotoService serve(){
	return new PhotoService();
    }

    public PhotoService() {
        photoDao = new PhotoDao();
    }
    
    public Integer savePhotoReturnId (Photo photo) throws IllegalArgumentException, IllegalAccessException, SQLException {
	return  photoDao.saveAndReturnId(photo);
    }
    
    public void deletePhoto(Integer photoId) throws IllegalAccessException, SQLException{
	photoDao.deleteByField("id", photoId);
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
    	return photoDao.getAllUserPhotos(userId);
    }
    
    public List<Photo> getAllEventPhotos(int eventId) throws SQLException{
	return photoDao.getAllEventPhotos(eventId);
    }
}
