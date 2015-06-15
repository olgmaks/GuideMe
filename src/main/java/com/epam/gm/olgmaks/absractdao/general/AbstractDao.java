package com.epam.gm.olgmaks.absractdao.general;

import com.epam.gm.olgmaks.absractdao.annotation.Entity;
import com.epam.gm.olgmaks.absractdao.crudoperation.DeleteHelper;
import com.epam.gm.olgmaks.absractdao.crudoperation.GetHelper;
import com.epam.gm.olgmaks.absractdao.crudoperation.SaveHelper;
import com.epam.gm.olgmaks.absractdao.crudoperation.UpdateHelper;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.transformer.ResultTransformer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AbstractDao<T> {

	public static final String SELECT = "SELECT * FROM %S";
	public static final String INSERT = "INSERT INTO %S (%S) VALUES (%S)";
	public static final String UPDATE = "UPDATE %S SET %S WHERE %S";
	public static final String DELETE = "DELETE FROM %S WHERE %S";

	private String dataBaseTableName;
	private ResultTransformer<T> transformer;

	// gryn
	// private Connection connection;

	private Class<T> clazz;
	private DeleteHelper<T> deleteHelper;
	private GetHelper<T> getHelper;
	private UpdateHelper<T> updateHelper;

	// gryn
	// public AbstractDao(Connection connection, Class<T> clazz) {
	public AbstractDao(Class<T> clazz) {
		// gryn
		// this.connection = connection;

		this.clazz = clazz;

		// gryn - removing connection from constructors

		// transformer = new ResultTransformer<T>(connection, clazz);
		// dataBaseTableName = clazz.getAnnotation(Entity.class).value();
		// deleteHelper = new DeleteHelper<T>(connection, clazz);
		// getHelper = new GetHelper<>(connection, clazz);
		// updateHelper = new UpdateHelper<>(connection, clazz);

		transformer = new ResultTransformer<T>(clazz);
		dataBaseTableName = clazz.getAnnotation(Entity.class).value();
		deleteHelper = new DeleteHelper<T>(clazz);
		getHelper = new GetHelper<>(clazz);
		updateHelper = new UpdateHelper<>(clazz);

	}

	public void save(T t) throws IllegalArgumentException,
			IllegalAccessException, SQLException {
		// gryn
		Connection connection = ConnectionManager.getConnection();

		// new SaveHelper<T>(connection, clazz).prepareSave(t).executeUpdate();
		new SaveHelper<T>(clazz).prepareSave(connection, t).executeUpdate();

		// gryn
		ConnectionManager.closeConnection(connection);
	}

	public void update(T t, String... updateConditions)
			throws IllegalAccessException, SQLException {
		// gryn
		Connection connection = ConnectionManager.getConnection();

		System.out.println("abstract dao update");
		// updateHelper.update(t, updateConditions).executeUpdate();
		updateHelper.update(connection, t, updateConditions).executeUpdate();

		// gryn
		ConnectionManager.closeConnection(connection);
	}

	public void updateById(Integer id, Map<String, Object> updates)
			throws SQLException {
		// gryn
		Connection connection = ConnectionManager.getConnection();

		// updateHelper.update(id, updates).executeUpdate();
		updateHelper.update(connection, id, updates).executeUpdate();

		// gryn
		ConnectionManager.closeConnection(connection);
	}

	public void updateWithCustomQuery(Map<String, Object> updates,
			String joined, String where) throws SQLException {
		System.out.println("update with custom query");
		// gryn
		Connection connection = ConnectionManager.getConnection();

		// updateHelper.update(updates, joined, where).executeUpdate();
		updateHelper.update(connection, updates, joined, where).executeUpdate();

		// gryn
		ConnectionManager.closeConnection(connection);
	}

	public void delete(T t) throws IllegalAccessException, SQLException {
		// gryn
		Connection connection = ConnectionManager.getConnection();

		// deleteHelper.delete(t).executeUpdate();
		deleteHelper.delete(connection, t).executeUpdate();

		// gryn
		ConnectionManager.closeConnection(connection);
	}

	public void deleteByField(String fieldName, Object fieldValue)
			throws IllegalAccessException, SQLException {
		// gryn
		Connection connection = ConnectionManager.getConnection();

		// deleteHelper.delete(fieldName, fieldValue).executeUpdate();
		deleteHelper.delete(connection, fieldName, fieldValue).executeUpdate();

		// gryn
		ConnectionManager.closeConnection(connection);
	}

	public List<T> getAll() throws SQLException {
		// gryn
		Connection connection = ConnectionManager.getConnection();

		List<T> result = new ArrayList<T>();
		String baseSelect = SELECT;
		String concreteTableSelect = String.format(baseSelect,
				dataBaseTableName);
		ResultSet rs = connection.prepareStatement(concreteTableSelect)
				.executeQuery();
		// result = transformer.getAllInstances(rs);
		result = transformer.getAllInstances(connection, rs);

		// gryn
		ConnectionManager.closeConnection(connection);

		return result;
	}

	// Query should start with "join"

	public List<T> getWithCustomQuery(String sqlWithRestrictions)
			throws SQLException {
		// gryn
		Connection connection = ConnectionManager.getConnection();

		List<T> result = new ArrayList<T>();
		//
		// ResultSet resultSet =
		// getHelper.customQuery(sqlWithRestrictions).executeQuery();
		ResultSet resultSet = getHelper.customQuery(connection,
				sqlWithRestrictions).executeQuery();

		// ResultTransformer<T> transformer = new
		// ResultTransformer<>(connection, clazz);
		ResultTransformer<T> transformer = new ResultTransformer<>(clazz);

		// result = transformer.getAllInstances(resultSet);
		result = transformer.getAllInstances(connection, resultSet);

		ConnectionManager.closeConnection(connection);

		return result;
	}

	// gryn - adding connection
	// public List<T> getByField(String fieldName, Object fieldValue) throws
	// SQLException {
	public List<T> getByField(String fieldName, Object fieldValue)
			throws SQLException {
		// gryn
		Connection connection = ConnectionManager.getConnection();

		// ResultSet resultSet = getHelper.getByFieldName(fieldName,
		// fieldValue).executeQuery();
		ResultSet resultSet = getHelper.getByFieldName(connection, fieldName,
				fieldValue).executeQuery();

		// ResultTransformer<T> transformer = new
		// ResultTransformer<>(connection, clazz);
		// return transformer.getAllInstances(resultSet);

		ResultTransformer<T> transformer = new ResultTransformer<>(clazz);

		List<T> result = transformer.getAllInstances(connection, resultSet);
		ConnectionManager.closeConnection(connection);
		return result;
	}

	// gryn - adding connection
	// public void callStoredProcedure(String procedureName, String...
	// parameters) throws SQLException {
	// CallableStatement cs = connection.prepareCall(procedureName);
	// cs.setInt(1, Integer.parseInt(parameters[0]));
	// cs.execute();
	// }

	public void callStoredProcedure(String procedureName, String... parameters)
			throws SQLException {
		Connection connection = ConnectionManager.getConnection();

		CallableStatement cs = connection.prepareCall(procedureName);
		cs.setInt(1, Integer.parseInt(parameters[0]));
		cs.execute();

		ConnectionManager.closeConnection(connection);
	}
}
