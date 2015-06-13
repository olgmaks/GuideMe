package com.epam.gm.alltest;

import com.epam.gm.dao.*;
import org.junit.Test;
import org.junit.runner.JUnitCore;


public class AllDaoTest {

    @Test
    public void test() {
        JUnitCore jUnit = new JUnitCore();
        jUnit.run(getAll());

    }

    public static Class<?>[] getAll() {
        Class<?>[] allTest = {
                AddressDaoTest.class,
                CityDaoTest.class,
                CommentEventDaoTest.class,
                CommentUserDaoTest.class,
                CountryDaoTest.class,
                EventDaoTest.class,
                EventTagDaoTest.class,
                LanguageDaoTest.class,
                MessageEventTest.class,
                MessageUserDaoTest.class,
                PhotoDaoTest.class,
                RatingEventTest.class,
                RatingUserTest.class,
                ServiceDaoTest.class,
                ServiceInEventDaoTest.class,
                TagDaoTest.class,
                UserDaoTest.class,
                UserInEventDaoTest.class,
                UserLanguageDaoTest.class,
                UserTagDaoTest.class,
                UserTypeTest.class
        };
        return allTest;

    }

}
