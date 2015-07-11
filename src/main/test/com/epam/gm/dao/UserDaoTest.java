package com.epam.gm.dao;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.daolayer.UserTypeDao;
import com.epam.gm.model.User;
import com.epam.gm.model.UserType;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoTest {

    private static UserDao userDao;
    private static UserType roleUser;

    @BeforeClass
    public static void bef() throws SQLException {
        userDao = new UserDao();
        roleUser = new UserTypeDao().getByField("name", "user").get(0);
    }

    @Test
    public void callableSeach () {
        try {

            String tags = "'music','cinema','hiking','camping','tourism'";

            long t1 = System.currentTimeMillis();
            List<User> users = userDao.callSearchUser(8,"r","d","","","","all");
            long t2 = System.currentTimeMillis();

            System.out.println("time = "+ (t2-t1));

            for (User user : users) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test@Ignore
    public void averageTimeCallableSearch () {
        try {

            String tags = "'music','cinema','hiking','camping','tourism'";


            int index=0;
            double avg = 0;
            long totalTime = 0;

            while (index<20) {
                long t1 = System.currentTimeMillis();
                List<User> users = userDao.callSearchUser(8, "", "","" ,"", "", "all");
                long t2 = System.currentTimeMillis();

                totalTime += (t2 - t1);

                index++;
            }

            avg=totalTime/index;

//            long t1 = System.currentTimeMillis();
//            List<User> users = userDao.callSearchUser(8,"","","","","all");
//            long t2 = System.currentTimeMillis();

            System.out.println("avg time = "+ avg + " sec, during = " + totalTime +" sec, after " + index +" times");

//            for (User user : users) {
//                System.out.println(user);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test@Ignore
    public void getByTags() throws SQLException {
        String tags = "'music'";
        List<User> users = userDao.searchUserByTags(tags,0);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Ignore
    @Test
    public void testGetUserById() throws SQLException {
        System.out.println(userDao.getByField("id", 8));
    }

    @Ignore
    @Test
    public void searchUserByNameTest() throws SQLException {
        List<User> users = userDao.searchUserByName("тарас");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Ignore
    @Test
    public void searchUserByCity() throws SQLException {
        System.out.println("test start");
        List<User> users = userDao.searchUserByCityName("варшава");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("test end");
    }

    @Ignore
    @Test
    public void getUserByVkId() throws SQLException {
        System.out.println(userDao.getUserByVkId("xxx"));
    }

    @Ignore
    @Test
    public void testGetUsers() throws SQLException {
        System.out.println("userRole = " + roleUser);
        List<User> users = userDao.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Ignore
    @Test
    public void saveUser() throws IllegalArgumentException, IllegalAccessException, SQLException {
        User user = new User();
        user.setFirstName("�����");
        user.setLastName("�����");
        user.setEmail("serg@gmail.com");
        user.setUserTypeId(roleUser.getId());
        user.setSex("male");
        user.setLangId(3);
        user.setCellNumber("+38097585454");
        user.setIsActive(true);
        user.setAddressId(1);
        user.setPassword("qwerty");

        userDao.save(user);
    }

    @Ignore
    @Test
    public void testCustomQuery() throws SQLException {
        List<User> users = userDao.getUsersByCityName("kyiv");
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Ignore
    @Test
    public void testUpdateUserById() throws SQLException {
        Map<String, Object> updates = new HashMap<>();
        updates.put("first_name", "Bogdan");
        updates.put("facebook_id", 265);
        userDao.updateById(8, updates);
    }

    @Ignore
    @Test
    public void testUpdateWithCustomQuery() throws SQLException {
        Map<String, Object> updates = new HashMap<>();
        String joined = "JOIN address a ON user.address_id = a.id JOIN city c ON a.city_id = c.id";
        String where = "WHERE a.city_id = 1";
        updates.put("first_name", "Bogdan");
        updates.put("facebook_id", 265);
        userDao.updateWithCustomQuery(updates, joined, where);
    }

    @Ignore
    @Test
    public void test() throws SQLException {
        User user = userDao.getUserById(8);

        System.out.println("hjhjhjhj");

        System.out.println(user.getAddress().getCity());
    }


}
