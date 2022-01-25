package com.cisco.test.repositories;

import com.cisco.test.exceptions.NotFoundException;
import com.cisco.test.model.Case;
import com.cisco.test.repositories.mappers.CaseRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CaseRepository {

    private final CaseRowMapper caseRowMapper;
    private final JdbcTemplate jdbcTemplate;

    public List<Case> findAllByStatus(Case.Status status) {
        final PreparedStatementSetter pss = ps -> {
            ps.setString(1, status.name());
        };
        return jdbcTemplate.query(
                "select case_id,title,description,severity,status,user_id from cases where status = ?",
                pss,
                caseRowMapper
        );
    }

    public List<Case> findAllByStatusAndUserId(Case.Status status, Integer userId) {
        final PreparedStatementSetter pss = ps -> {
            ps.setInt(1, userId);
            ps.setString(2, status.name());
        };
        return jdbcTemplate.query(
                "select case_id,title,description,severity,status,user_id from cases where user_id = ? and status = ?",
                pss,
                caseRowMapper
        );
    }


    public Case findById(Integer caseId) {
        final PreparedStatementSetter pss = ps -> {
            ps.setInt(1, caseId);
        };
        return jdbcTemplate.query(
                        "select case_id,title,description,severity,status,user_id from cases where case_id = ?",
                        pss,
                        caseRowMapper
                ).stream()
                .findFirst()
                .orElseThrow(() -> new NotFoundException(Case.class, caseId));
    }

    public Case create(Case toCreate) {
        final var keyHolder = new GeneratedKeyHolder();

        final PreparedStatementSetter pss = ps -> {
            ps.setString(1, toCreate.getTitle());
            ps.setString(1, toCreate.getDescription());
            ps.setInt(1, toCreate.getSeverity());
            ps.setString(1, toCreate.getStatus().name());
            ps.setInt(1, toCreate.getUser().getUserId());
        };

        jdbcTemplate.update(
                "insert into cases (title,description,severity,status,user_id) values(?,?,?,?,?)",
                pss,
                keyHolder
        );
        toCreate.setCaseId((Integer) keyHolder.getKey());
        return toCreate;
    }
}
