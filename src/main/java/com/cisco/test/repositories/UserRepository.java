package com.cisco.test.repositories;

import com.cisco.test.exceptions.NotFoundException;
import com.cisco.test.model.User;
import com.cisco.test.repositories.mappers.UserRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final UserRowMapper userRowMapper;
    private final JdbcTemplate jdbcTemplate;

    public User findById(int userId) {
        final PreparedStatementSetter pss = ps -> {
            ps.setInt(1, userId);
        };
        return jdbcTemplate
                .query("select user_id,firstname,lastname,email from users where user_id = ?", pss, userRowMapper)
                .stream().findFirst()
                .orElseThrow(() -> new NotFoundException(User.class, userId));
    }
}
