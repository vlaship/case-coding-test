package com.cisco.test.repositories;

import com.cisco.test.model.Note;
import com.cisco.test.repositories.mappers.NoteRowMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class NoteRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NoteRowMapper noteRowMapper;

    public Note create(Note toCreate) {
        final var keyHolder = new GeneratedKeyHolder();

        final PreparedStatementSetter pss = ps -> {
            ps.setInt(1, toCreate.getCaseId());
            ps.setString(1, toCreate.getDetails());
        };

        jdbcTemplate.update("insert into notes (case_id, details) values(?,?)", pss, keyHolder);
        toCreate.setNoteId((Integer) keyHolder.getKey());
        return toCreate;
    }

    public List<Note> findAllByCaseId(int caseId) {
        final PreparedStatementSetter pss = ps -> {
            ps.setInt(1, caseId);
        };
        return jdbcTemplate
                .query("select note_id,case_id,details from notes where case_id = ?", pss, noteRowMapper);
    }
}
