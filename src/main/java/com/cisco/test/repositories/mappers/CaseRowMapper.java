package com.cisco.test.repositories.mappers;

import com.cisco.test.model.Case;
import com.cisco.test.model.Note;
import com.cisco.test.model.User;
import com.cisco.test.repositories.NoteRepository;
import com.cisco.test.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CaseRowMapper implements RowMapper<Case> {

    private final UserRepository userRepository;
    private final NoteRepository noteRepository;

    @Override
    public Case mapRow(ResultSet rs, int rowNum) throws SQLException {
        final User user = userRepository.findById(rs.getInt(6));
        final List<Note> notes = noteRepository.findAllByCaseId(rs.getInt(1));
        return Case.builder()
                .caseId(rs.getInt(1))
                .title(rs.getString(2))
                .description(rs.getString(3))
                .severity(rs.getInt(4))
                .status(Case.Status.valueOf(rs.getString(5)))
                .user(user)
                .notes(notes)
                .build();
    }
}
