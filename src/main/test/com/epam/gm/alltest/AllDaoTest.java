package com.epam.gm.alltest;

import org.junit.Test;
import org.junit.runner.JUnitCore;

import com.epam.gm.dao.AddressDaoTest;
import com.epam.gm.dao.CityDaoTest;
import com.epam.gm.dao.CommentEventDaoTest;
import com.epam.gm.dao.CommentUserDaoTest;
import com.epam.gm.dao.CountryDaoTest;
import com.epam.gm.dao.EventTagDaoTest;
import com.epam.gm.dao.LanguageDaoTest;
import com.epam.gm.dao.MessageEventTest;
import com.epam.gm.dao.MessageUserDaoTest;
import com.epam.gm.dao.PhotoDaoTest;
import com.epam.gm.dao.RatingEventTest;
import com.epam.gm.dao.RatingUserTest;
import com.epam.gm.dao.ServiceDaoTest;
import com.epam.gm.dao.ServiceInEventDaoTest;
import com.epam.gm.dao.TagDaoTest;
import com.epam.gm.dao.UserDaoTest;
import com.epam.gm.dao.UserInEventDaoTest;
import com.epam.gm.dao.UserLanguageDaoTest;
import com.epam.gm.dao.UserTagDaoTest;
import com.epam.gm.dao.UserTypeTest;


public class AllDaoTest {
    
    @Test
    public void test (){
	JUnitCore jUnit =  new JUnitCore();
	jUnit.run(getAll());
	
    }
    
    public static Class<?> [] getAll() {
	Class<?> [] allTest = {
		AddressDaoTest.class,
		CityDaoTest.class,
		CommentEventDaoTest.class,
		CommentUserDaoTest.class,
		CountryDaoTest.class,
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
