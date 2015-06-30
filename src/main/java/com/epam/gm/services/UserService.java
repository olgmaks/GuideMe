package com.epam.gm.services;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class UserService {

    public enum SearchRole {
        guide,
        user,
        all
    }

    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }
    
    public Boolean isUserPresent (Integer id) throws SQLException {
    	return userDao.isUserPresent(id);
    }

    public void saveUser(User user) throws IllegalArgumentException, IllegalAccessException, SQLException {
        userDao.saveUser(user);
    }

    public static UserService serve() {
        return new UserService();
    }

    public Set<User> searchUsers(Integer searcherId, String nameFilterInput, String cityName,
                                 String tags, Integer tagsMatches, SearchRole searchRole) throws SQLException {

        System.out.println("****user search started****");
        long time = System.currentTimeMillis();
        System.out.println("searcherId : " + searcherId);
        System.out.println("nameFilterInput : " + nameFilterInput);
        System.out.println("cityName : " + cityName);
        System.out.println("tags : " + tags);
        System.out.println("tagsMatches : " + tagsMatches);
        System.out.println("searchRole : " + searchRole);


        String tagWrapper = "'";
        if (!tags.contains(tagWrapper) && !tags.isEmpty()) {
            String[] tagList = tags.split(",");
            if (tagList.length == 1) {
                tags = "'" + tagList + "'";
            } else {
                StringBuilder builder = new StringBuilder();
                String prefix = "";
                for (String tag : tagList) {
                    builder.append(prefix).append(tagWrapper).append(tag).append(tagWrapper);
                    prefix = ",";
                }
                tags = builder.toString();
            }
        }

        Set<User> results = new LinkedHashSet<>();

        List<User> resultsByName = null;
        List<User> resultsByCity = null;
        List<User> resultsByTags = null;
        List<User> resultsNonFriend = null;
        List<User> resultsByGuide = null;

        if (nameFilterInput != null) {
            resultsByName = userDao.searchUserByName(nameFilterInput);
            System.out.println("results by name : " + resultsByName.size());
        }
        if (cityName != null) {
            resultsByCity = userDao.searchUserByCityName(cityName);
            System.out.println("results by city : " + resultsByCity.size());
        }

        if (tags != null && tags.isEmpty()) {
            tags = null;
        }

        if (tags != null) {

            if (tagsMatches == null) {

                if (tags.contains(",")) {
                    tagsMatches = tags.split(",").length;
                } else {
                    tagsMatches = 0;
                }

            }
            resultsByTags = userDao.searchUserByTags(tags, tagsMatches);
//            System.out.println("results by tags : " +resultsByTags);
            System.out.println("results by tags : " + resultsByTags.size());
        }

        if (searchRole == null) {
            searchRole = SearchRole.all;
        }

        if (searchRole == SearchRole.guide) {
            resultsByGuide = userDao.searchUserGuide();
        }

        if (searchRole == SearchRole.user) {
            resultsByGuide = userDao.searchUserNonGuide();
        }

        if (searchRole == SearchRole.all) {
            resultsByGuide = userDao.searchUserGuide();
            resultsByGuide.addAll(userDao.searchUserNonGuide());
        }


        if (searcherId != null) {
            resultsNonFriend = userDao.searchNonFriendsUsers(searcherId);
            System.out.println("results by resultsNonFriend : " + resultsNonFriend.size());
        }

        System.out.println("Union");
        /*
            Union operations
         */
        if (resultsByName != null && !resultsByName.isEmpty()) {
            results.addAll(resultsByName);
            System.out.print("name : +" + results.size());
        }

        if (resultsByCity != null && !resultsByCity.isEmpty()) {
            results.addAll(resultsByCity);
            System.out.print(" | city : +" + results.size());
        }

        if (resultsByTags != null && !resultsByTags.isEmpty()) {
            results.addAll(resultsByTags);
            System.out.print(" | tags : +" + results.size());
        }

        if (resultsByGuide != null && !resultsByGuide.isEmpty()) {
            results.addAll(resultsByGuide);
            System.out.print(" | guide : +" + results.size());
        }

        if (resultsNonFriend != null && !resultsNonFriend.isEmpty()) {
            results.addAll(resultsNonFriend);
            System.out.println(" | nonFriend : +" + results.size());
        }
        System.out.println("Intersection");
        /*
            Intersection operations
         */
        if (resultsByName != null && !nameFilterInput.isEmpty()) {
            results.retainAll(resultsByName);
            System.out.println("name : -" + results.size() + " nameFilterInput: " + nameFilterInput);
        }

        if (resultsByCity != null && !cityName.isEmpty()) {
            results.retainAll(resultsByCity);
            System.out.println(" | city: -" + results.size() + " cityName: " + cityName);
        }

        if (resultsByTags != null && !tags.isEmpty()) {
            results.retainAll(resultsByTags);
            System.out.println(" | tags: -" + results.size() + " tags: " + tags);
        }

        if (resultsByGuide != null && !resultsByGuide.isEmpty()) {
            results.retainAll(resultsByGuide);
            System.out.println(" | guide: -" + results.size() + " searchRole: " + searchRole);
        }

        if (resultsNonFriend != null) {
            results.retainAll(resultsNonFriend);
            System.out.println(" | nonFriends: -" + results.size());
        }

        System.out.println("****user search ended**** with time = " + TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - time));
        return results;
    }


    public List<User> searchNonFriendsUsers(Integer searcherId) throws SQLException {
        return userDao.searchNonFriendsUsers(searcherId);
    }

    public User getUserByEmail(String email) throws SQLException {
        return userDao.getUserByEmail(email);
    }

    public List<User> getUsersByCityName(String cityName) throws SQLException {
        return userDao.getUsersByCityName(cityName);
    }

    public User getUserById(int id) throws SQLException {
        return userDao.getUserById(id);
    }

    public List<User> getAll() throws SQLException {
        return userDao.getAll();
    }

    public List<User> getByUserType(String userTypeId) throws SQLException {
        return userDao.getUsersByUserType(userTypeId);
    }

    public void deleteById(int userId) throws IllegalAccessException, SQLException {
        userDao.deleteById(userId);
    }

    public User getUserById(Integer id) throws SQLException {
        return userDao.getUserById(id);
    }

    public void updateWithCustomQuery(Map<String, Object> updates, String joined, String where) throws SQLException {
        userDao.updateWithCustomQuery(updates, joined, where);
    }

    public void updateUserAvatar (Integer userId, Integer avatarId) throws SQLException {
        Map <String, Object> map = new HashMap<>();
        map.put("avatar_id",avatarId);
        userDao.updateById(userId,map);
    }

    //gryn
    public List<User> getActiveUsersAndGuidesInTheCountry(Integer countryId) throws SQLException {
        return userDao.getActiveUsersAndGuidesInTheCountry(countryId);
    }

}
