package com.epam.gm.daolayer;


import com.epam.gm.model.City;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CityDao extends AbstractDao<City> {
	  private static final String GET_CITY_BY_PURE_AND_LOCALIZED = "c JOIN language l ON l.id = c.local_id "
		   		+ "WHERE c.pure_id = '%S' "
		   		+ "AND l.localized = 1  "
		   		+ "AND c.deleted = FALSE";
	  private String deleteByPureId = "update city set deleted = true where pure_id = ?";
    public CityDao() {
        //gryn
        //super(ConnectionManager.getConnection(), City.class);
        super(City.class);
    }

    // gryn
    public List<City> getCitiesByCountryId(Integer country_id) throws SQLException {


        List<City> result = getByField("country_id", country_id);
        return result;
    }

    public List<City> getCitiesByLocalId(int localId) throws SQLException {
        //return super.getByField("local_id", localId);
        return super.getWithCustomQuery("where local_id =" +localId+ " and deleted = false");
    }

    //gryn
    public City getCityById(Integer id) throws SQLException {

        List<City> list = getByField("id", id);

        if (list.isEmpty())
            return null;

        return list.get(0);
    }

    //gryn
    //�������� ���� ������� � ����� �����:
    //Lviv - ���� - �����
    public List<City> getCitiesByPureId(Integer pureId) throws SQLException {

        List<City> list = getByField("pure_id", pureId);


        return list;
    }

    //gryn
    //must be optimized later
    public Integer getLastPureId() throws SQLException{
    	Integer result=null;
    	String select = "select pure_id from city order by pure_id  DESC LIMIT 1";
    	Connection connection = ConnectionManager.getConnection();
    	PreparedStatement stmt = connection.prepareStatement(select);
    	ResultSet rs = stmt.executeQuery();
    	if(rs.next()){
    		result = rs.getInt(1);
    	}
    	rs.close();
		stmt.close();
		ConnectionManager.closeConnection(connection);
		return result;
    }


    public List<City> getAllActive() throws SQLException{
    	return super.getByField("deleted", "false");
    }
    public List<City> getCityByPureLocalized(int pureId) throws SQLException {
        return getWithCustomQuery(String.format(GET_CITY_BY_PURE_AND_LOCALIZED,
                pureId));
    }
	public void deleteByPureId(int pureId)
			throws SQLException {
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement stmt = connection
				.prepareStatement(deleteByPureId);
		stmt.setInt(1, pureId);
		stmt.executeUpdate();
		stmt.close();
		ConnectionManager.closeConnection(connection);
	}
    

}
