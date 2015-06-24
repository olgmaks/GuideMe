package com.epam.gm.services;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.User;

import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public void saveUser(User user) throws IllegalArgumentException, IllegalAccessException, SQLException {
        userDao.saveUser(user);
    }

    public static UserService serve() {
        return new UserService();
    }

    public Set<User> searchUsers(Integer searcherId, String nameFilterInput, String cityName, String tags, Integer tagsMatches,
                                 SearchRole searchRole) throws SQLException {

        System.out.println("****user search started****");
        System.out.println("searcherId : " + searcherId);
        System.out.println("nameFilterInput : " + nameFilterInput);
        System.out.println("cityName : " + cityName);
        System.out.println("tags : " + tags);
        System.out.println("tagsMatches : " + tagsMatches);
        System.out.println("searchRole : " + searchRole);


        String tagWrapper = "'";
        if (!tags.contains(tagWrapper)) {
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


        /*
            Union operations
         */
        if (resultsByName != null && !resultsByName.isEmpty()) {
            results.addAll(resultsByName);
            System.out.println("results was not produced added by name : " + results.size());
        }

        if (resultsByCity != null && !resultsByCity.isEmpty()) {
            results.addAll(resultsByCity);
            System.out.println("results was produced added by city : " + results.size());
        }

        if (resultsByTags != null && !resultsByTags.isEmpty()) {
            results.addAll(resultsByTags);
            System.out.println("results was produced added by tags : " + results.size());
        }

        if (resultsByGuide != null && !resultsByGuide.isEmpty()) {
            results.addAll(resultsByGuide);
            System.out.println("results was produced added by guide : " + results.size());
        }

        if (resultsNonFriend != null && !resultsNonFriend.isEmpty()) {
            results.addAll(resultsNonFriend);
            System.out.println("results was produced added by resultsNonFriend : " + results.size());
        }

        /*
            Intersection operations
         */
        if (resultsByName != null && !resultsByName.isEmpty()) {
            results.retainAll(resultsByName);
            System.out.println("intersection with name reduced to : " + results.size());
        }

        if (resultsByCity != null && !resultsByCity.isEmpty()) {
            results.retainAll(resultsByCity);
            System.out.println("intersection with city reduced to : " + results.size());
        }

        if (resultsByTags != null && !resultsByTags.isEmpty()) {
            results.retainAll(resultsByTags);
            System.out.println("intersection with tags reduced to : " + results.size());
        }

        if (resultsByGuide != null && !resultsByGuide.isEmpty()) {
            results.retainAll(resultsByGuide);
            System.out.println("intersection with guide reduced to : " + results.size());
        }

        if (resultsNonFriend != null && !resultsNonFriend.isEmpty()) {
            results.retainAll(resultsNonFriend);
            System.out.println("intersection with non friends reduced to : " + results.size());
        }

        System.out.println("****user search ended****");
        return results;
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
    
    //gryn
    public List<User> getActiveUsersAndGuidesInTheCountry(Integer countryId) throws SQLException {
    	return userDao.getActiveUsersAndGuidesInTheCountry(countryId); 
    }

}
