package com.teamcore.manageapp.service.extractors;

import com.teamcore.manageapp.service.domain.Role;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleExtractor implements ResultSetExtractor<Role> {

    @Override
    public Role extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        resultSet.next();

        Role role = new Role();
        role.setId(resultSet.getLong("id"));
        role.setRoleId(resultSet.getInt("c_group_id"));
        role.setName(resultSet.getString("c_name"));

        return role;
    }
}
