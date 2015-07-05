package com.epam.gm.services;

import com.epam.gm.model.CommentUser;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 05.07.2015.
 */
public class CommentUserServiceTest {

    private CommentUserService commentUserService = new CommentUserService();

    @Test
    public void testGelAll (){
        System.out.println("test get all user comments");
        try {
            List<CommentUser> comments = commentUserService.getByUserId(8);
            for (CommentUser comment : comments) {
                System.out.println( comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
