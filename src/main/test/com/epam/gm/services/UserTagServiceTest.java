package com.epam.gm.services;

import com.epam.gm.model.UserTag;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;

/**
 * Created by OLEG on 03.07.2015.
 */
public class UserTagServiceTest {

    private static UserTagService userTagService = new UserTagService();
@Ignore
    @Test
    public void testSaveUserTag() throws SQLException, IllegalAccessException {
        UserTag userTag = new UserTag();
        userTag.setTagId(6);
        userTag.setUserId(8);
        userTagService.save(userTag);
    }

    @Test
    public void testDeleteUserTag () {
        try {
            userTagService.deleteUserTag(8,4);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
