package com.epam.gm.services;

import com.epam.gm.model.Tag;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by OLEG on 03.07.2015.
 */
public class TagServiceTest {

    private static final TagService TAG_SERVICE = new TagService();

    @Test
    public void testGetAllUserTags() throws SQLException {
        List<Tag> tags = TAG_SERVICE.getAllUserTags(8);
        for (Tag tag : tags) {
            System.out.println(tag);
        }
    }
}
