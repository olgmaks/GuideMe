package com.epam.gm.daolayer;

import com.epam.gm.model.Photo;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.SQLException;
import java.util.List;

public class PhotoDao extends AbstractDao<Photo> {

    private static final String GET_PHOTO_USER = "where owner_type='user' and owner_id = %S";
    private static final String GET_PHOTO_EVENT = "where owner_type='event' and owner_id = %S";

    public PhotoDao() {
        //gryn
    	//super(ConnectionManager.getConnection(), Photo.class);
    	super(Photo.class);
    }

    public Photo getUserPhoto(int userId) throws SQLException {
        Photo result = super.getWithCustomQuery(String.format(GET_PHOTO_USER, userId)).get(0);
        return result;
    }

    public Photo getEventPhoto(int eventId) throws SQLException {
        Photo result = super.getWithCustomQuery(String.format(GET_PHOTO_EVENT, eventId)).get(0);
        return result;
    }
    public List<Photo> getEventPhotos(int eventId) throws SQLException {
        List<Photo> result = super.getWithCustomQuery(String.format(GET_PHOTO_EVENT, eventId));
        return result;
    }

}
