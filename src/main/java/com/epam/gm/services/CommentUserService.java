package com.epam.gm.services;

import java.sql.SQLException;
import java.util.List;

import com.epam.gm.daolayer.CommentUserDao;
import com.epam.gm.model.CommentUser;

public class CommentUserService {
		private CommentUserDao cudao ;
		
		public CommentUserService(){
			cudao = new CommentUserDao();
		}
		public List<CommentUser> getByUserId(int userId) throws SQLException{
			return cudao.getByUserId(userId);
		}
		
}
