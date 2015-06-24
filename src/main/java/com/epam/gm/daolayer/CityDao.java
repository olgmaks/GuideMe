package com.epam.gm.daolayer;


import com.epam.gm.model.City;
import com.epam.gm.olgmaks.absractdao.general.AbstractDao;

import java.sql.SQLException;
import java.util.List;

public class CityDao extends AbstractDao<City> {

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
        return super.getByField("local_id", localId);
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
    public Integer getLastPureId() throws SQLException {

        List<City> list = getAll();

        Integer max = 0;
        for (City c : list) {
            if (c.getPureId() > max)
                max = c.getPureId();
        }

        return max;
    }


}
