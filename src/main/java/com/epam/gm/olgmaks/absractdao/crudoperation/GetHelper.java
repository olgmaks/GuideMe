package com.epam.gm.olgmaks.absractdao.crudoperation;

import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by OLEG on 07.06.2015.
 */
public class GetHelper<T> extends AbstractHelper<T> {

	// private T t;

	//gryn
	//public GetHelper(Connection connection, Class<T> clazz) {
	public GetHelper(Class<T> clazz) {
		//super(connection, clazz);
		super(clazz);
	}

	//gryn - adding Connection connection to signature
	public PreparedStatement getByFieldName(Connection connection, String fieldName, Object fieldValue)
			throws SQLException {
		String sql = String.format(AbstractDao.SELECT + " where %S=?", tableName,
				fieldName);
//		System.out.println(sql);
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setObject(1, fieldValue);
		return statement;
	}

	// Query should start with "join ... on ... where ... " statement
	
	//gryn - adding Connection connection to signature
	public PreparedStatement customQuery(Connection connection, String sqlWithRestrictions)
			throws SQLException {
		String baseSelect = AbstractDao.SELECT;
		String sql = String.format(baseSelect,
				(tableName + " " + sqlWithRestrictions));
		System.out.println(sql);
		PreparedStatement statement = connection.prepareStatement(sql);
		return statement;
	}

}
