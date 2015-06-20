package com.epam.gm.daolayer;

import com.epam.gm.model.Photo;
import com.epam.gm.model.User;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.services.UserService;

import java.sql.SQLException;
import java.util.List;

public class PhotoDao extends AbstractDao<Photo> {

    private static final String GET_PHOTO_USER = "where owner_type='user' and owner_id = %S";
    private static final String GET_PHOTO_EVENT = "where owner_type='event' and owner_id = %S";

    private static final Integer DEFAULT_USER_PHOTO_OWNER_ID = -1;
    private static final Integer DEFAULT_USER_AVATAR_ID = 10;

    private UserService userService;


    public PhotoDao() {
        //gryn
        //super(ConnectionManager.getConnection(), Photo.class);
        super(Photo.class);
        userService = new UserService();
    }


    public Photo getUserPhoto(int userId) throws SQLException {

        User user = userService.getUserById(userId);
        Photo result = null;

        if (user.getAvatarId() == null) {
            System.out.println("avatar == null");
            throw new RuntimeException();
        }
            if (user.getAvatarId().equals(DEFAULT_USER_AVATAR_ID)) {
                result = super.getWithCustomQuery(String.format(
                        GET_PHOTO_USER, DEFAULT_USER_PHOTO_OWNER_ID)).get(0);
            } else {
                result = super.getWithCustomQuery(String.format(GET_PHOTO_USER, userId)).get(0);
            }

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
    
    public List<Photo> getUserPhotos(int userId) throws SQLException {
        List<Photo> result = super.getWithCustomQuery(String.format(GET_PHOTO_USER, userId));
        return result;
    }

}
