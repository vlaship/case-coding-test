package com.cisco.test.repositories.mappers;

import com.cisco.test.model.Note;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class NoteRowMapper implements RowMapper<Note> {

    @Override
    public Note mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Note.builder()
                .noteId(rs.getInt(1))
                .caseId(rs.getInt(2))
                .details(rs.getString(3))
                .build();
    }
}
