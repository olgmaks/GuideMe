package com.epam.gm.services;

import com.epam.gm.daolayer.UserDao;
import com.epam.gm.model.Event;
import com.epam.gm.model.User;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class UserService {

	public enum SearchRole {
		guide, user, all
	}

	private UserDao userDao;

	public UserService() {
		userDao = new UserDao();
	}

	public Boolean isUserPresent(Integer id) throws SQLException {
		return userDao.isUserPresent(id);
	}

	public void saveUser(User user) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		userDao.saveUser(user);
	}

	public Integer saveUserAndRerutnId(User user)
			throws IllegalArgumentException, IllegalAccessException,
			SQLException {
		return userDao.saveUserAndRerutnId(user);
	}

	public static UserService serve() {
		return new UserService();
	}

	public boolean isEmailExist(String email) {
		boolean newBoolean = false;
		try {
			if (new UserDao().getUserByEmail(email) != null) {
				newBoolean = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

//Maks
	public List<User> searchUsers(Integer searcherId, String firstName, String lastName, String countryName,
								  String cityName, String tags, SearchRole searchRole) throws SQLException {

		System.out.println("****user search started****");
		long time = System.currentTimeMillis();
		System.out.print("searcherId : '" + searcherId + "', ");
		System.out.print("firstName : '" + firstName +"', ");
		System.out.print("lastName : '" + lastName+"', ");
        System.out.print("countryName : '" + countryName+"', ");
		System.out.print("cityName : '" + cityName+"', ");
		System.out.print("tags : '" + tags+"', ");
		System.out.println("searchRole : '" + searchRole + "'");

        if (searcherId==null) {
            System.out.println("searcher id was not specified");
            throw  new RuntimeException("searcher id was not specified");
        }

		if (firstName==null) {firstName="";}
		if (lastName==null) {lastName="";}
		if (countryName==null){countryName="";}
		if (cityName==null) {cityName="";}
		if (searchRole==null) {searchRole=SearchRole.all;}
        if (tags==null) {tags ="";}

		String tagWrapper = "'";
		if (!tags.contains(tagWrapper) && !tags.isEmpty()) {
			String[] tagList = tags.split(",");

			if (tagList.length == 1) {
                tags = new String("'" + tags + "'");
			} else {
				StringBuilder builder = new StringBuilder();
				String prefix = "";
				for (String tag : tagList) {
					builder.append(prefix).append(tagWrapper).append(tag)
							.append(tagWrapper);
					prefix = ",";
				}
				tags = builder.toString();
			}
		}
        System.out.println("***tags: " + tags);

        List<User> results = userDao.callSearchUser(searcherId,firstName,lastName,countryName,cityName,tags,searchRole.name());

        Iterator<User> iter = results.iterator();

        while (iter.hasNext()){
            User user = iter.next();
            user.setPoints(getUserAvgMark(user.getId()));
        }

		System.out.println("****user search ended**** with time = "
				+ TimeUnit.MILLISECONDS.toMillis(System.currentTimeMillis() - time));
		return results;
	}

    public Double getUserAvgMark (Integer userId) throws SQLException {
        return userDao.getUserAvgMark(userId);
    }

	public List<User> searchNonFriendsUsers(Integer searcherId)
			throws SQLException {
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

	public void deleteById(int userId) throws IllegalAccessException,
			SQLException {
		userDao.deleteById(userId);
	}

	public User getUserById(Integer id) throws SQLException {
		return userDao.getUserById(id);
	}

	public void updateWithCustomQuery(Map<String, Object> updates,
			String joined, String where) throws SQLException {
		userDao.updateWithCustomQuery(updates, joined, where);
	}

	public void updateUserAvatar(Integer userId, Integer avatarId)
			throws SQLException {
		Map<String, Object> map = new HashMap<>();
		map.put("avatar_id", avatarId);
		userDao.updateById(userId, map);
	}

	// gryn
	public List<User> getActiveUsersAndGuidesInTheCountry(Integer countryId)
			throws SQLException {
		return userDao.getActiveUsersAndGuidesInTheCountry(countryId);
	}

	// gryn
	public void buildTagString(List<User> list) throws SQLException {
		userDao.buildTagString(list);
	}

	// gryn
	public void buildTagString(User user) throws SQLException {
		List<User> list = new ArrayList<User>(1);
		list.add(user);

		buildTagString(list);
	}

	public void saveUserLang(Integer userId, Integer langId)
			throws SQLException {
		userDao.saveUserLang(userId, langId);
	}
	
	public void updateUserProfile(User user) throws SQLException {
		userDao.updateUserProfile(user);
	}
	
	public boolean isFbVkUser(User user) {
		boolean fbVkUser = false;
		
		if(user.getFacebookId() != null && user.getFacebookId().trim().length() > 0) {
			fbVkUser = true;
		} else if(user.getVkId() != null && user.getVkId().trim().length() > 0) {
			fbVkUser = true;
		}
		
		return fbVkUser;
	}
}
