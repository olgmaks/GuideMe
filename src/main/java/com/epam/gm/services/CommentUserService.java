package com.epam.gm.services;

import com.epam.gm.daolayer.CommentUserDao;
import com.epam.gm.model.CommentUser;

import java.sql.SQLException;
import java.util.List;

public class CommentUserService {

    private CommentUserDao cuDao;

    public CommentUserService() {
        cuDao = new CommentUserDao();
    }

    public List<CommentUser> getByUserId(int userId) throws SQLException {
        return cuDao.getByUserId(userId);
    }

    public void save(CommentUser comment) throws IllegalArgumentException, IllegalAccessException, SQLException {
        cuDao.save(comment);
    }

    public void deleteById(Integer id) throws IllegalAccessException, SQLException {
        cuDao.deleteByField("id", id);
    }

}
