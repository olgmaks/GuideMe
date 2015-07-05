package com.epam.gm.daolayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.gm.model.Country;
import com.epam.gm.olgmaks.absractdao.dbcontrol.ConnectionManager;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;
import com.epam.gm.services.UserService;

public class CountryDao extends AbstractDao<Country> {

    public CountryDao() {
    	//gryn
    	//super(ConnectionManager.getConnection(), Country.class);
    	super(Country.class);
    }
    
    //gryn
    public Country getCountryById(Integer id) throws SQLException {
        List<Country> list =  getByField("id", id);
        
        if(list.isEmpty()) 
        	return null;
        
        return list.get(0);
    }
    
    //gryn
    //�������� ����� ������� � ����� �����:
    //Ukraine - ������ - �������
    public List<Country> getCountriesByPureId(Integer pureId) throws SQLException {
    	return getByField("pure_id", pureId);
    }	
    public List<Country> getCountriesByLocalId(Integer localId) throws SQLException {
    	return getByField("local_id", localId);
    }	
    
    public static void main(String[] args) throws SQLException {
		System.out.println(new CountryDao().getCountriesByLocalId(2));
	}
    public Integer getLastPureId() throws SQLException{
    	Integer result=null;
    	String select = "select pure_id from country order by pure_id  DESC LIMIT 1";
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
    

}
