package com.cisco.test.services;

import com.cisco.test.model.Case;
import com.cisco.test.model.Note;
import com.cisco.test.repositories.CaseRepository;
import com.cisco.test.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CaseService {

    private final CaseRepository caseRepository;
    private final NoteRepository noteRepository;

    public List<Case> findAllByStatus(Case.Status status) {
        return caseRepository.findAllByStatus(status);
    }

    public List<Case> findAllByStatusAnsUserId(Case.Status status, Integer userId) {
        return caseRepository.findAllByStatusAndUserId(status, userId);
    }

    public Case findById(Integer caseId) {
        return caseRepository.findById(caseId);
    }

    public Case create(Case toCreate) {
        return caseRepository.create(toCreate);
    }

    public Note addNote(Integer caseId, String detail) {
        final Note toCreate = Note.builder()
                .caseId(caseId)
                .details(detail)
                .build();
        return noteRepository.create(toCreate);
    }
}

