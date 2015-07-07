package com.epam.gm.services;

import com.epam.gm.model.CommentUser;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 05.07.2015.
 */
public class CommentUserServiceTest {

    private CommentUserService commentUserService = new CommentUserService();

    @Test
    @Ignore
    public void testGelAll() {
        System.out.println("test get all user comments");
        try {
            List<CommentUser> comments = commentUserService.getByUserId(8);
            for (CommentUser comment : comments) {
                System.out.println(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSaveComment() {
        CommentUser commentUser = new CommentUser();
        commentUser.setUserId(8);
        commentUser.setCommentatorId(2);
        commentUser.setComment("A trip was good, thanks! :)");
        try {
            commentUserService.save(commentUser);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
