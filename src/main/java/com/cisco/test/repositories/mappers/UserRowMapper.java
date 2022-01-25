package com.cisco.test.repositories.mappers;

import com.cisco.test.model.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return User.builder()
                .userId(rs.getInt(1))
                .firstName(rs.getString(2))
                .lastName(rs.getString(3))
                .email(rs.getString(4))
                .build();
    }
}
