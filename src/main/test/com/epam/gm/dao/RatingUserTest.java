package com.epam.gm.dao;

import com.epam.gm.daolayer.RatingUserDao;
import com.epam.gm.model.RatingUser;
import com.epam.gm.model.User;
import com.epam.gm.services.RatingUserService;
import com.epam.gm.services.UserService;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

public class RatingUserTest {

    private static RatingUserDao ratingUserDao;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        ratingUserDao = new RatingUserDao();
    }

    @Test
    public void fillTable() {

        try {
            UserService userService = new UserService();
            RatingUserService ratingUserService = new RatingUserService();
            List<User> users = userService.getAll();

            int step = 0;

            while (step < 100) {

                Integer randomEstimatorId = users.get(new Random().nextInt(users.size())).getId();
                Integer userId = users.get(new Random().nextInt(users.size())).getId();
                Integer mark = new Random().nextInt(3)+2;

                if (randomEstimatorId==userId) {
                    continue;
                }

                if (ratingUserService.verifyMarkExisting(randomEstimatorId, userId)) {
                    continue;
                }

                ratingUserService.saveRatingUser(randomEstimatorId,userId,mark);
                step++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    @Test
    @Ignore
    public void test() throws SQLException {
        List<RatingUser> ratingUsers = ratingUserDao.getAll();
        for (RatingUser ratingUser : ratingUsers) {
            System.out.println(ratingUser);
        }
    }

}
