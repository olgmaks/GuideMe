package com.epam.gm.olgmaks.absractdao.crudoperation;

import com.epam.gm.olgmaks.absractdao.general.IDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by OLEG on 07.06.2015.
 */
public class GetHelper<T> extends AbstractHelper<T> {

	// private T t;

	public GetHelper(Connection connection, Class<T> clazz) {
		super(connection, clazz);
	}

	public PreparedStatement getByFieldName(String fieldName, Object fieldValue)
			throws SQLException {
		String sql = String.format(IDao.SELECT + " where %S=?", tableName,
				fieldName);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setObject(1, fieldValue);
		return statement;
	}

	// Query should start with "join ... on ... where ... " statement
	public PreparedStatement customQuery(String sqlWithRestrictions)
			throws SQLException {
		String baseSelect = IDao.SELECT;
		String sql = String.format(baseSelect,
				(tableName + " " + sqlWithRestrictions));
		System.out.println(sql);
		PreparedStatement statement = connection.prepareStatement(sql);
		return statement;
	}

}
