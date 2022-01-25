package com.cisco.test.services;

import com.cisco.test.model.Case;
import com.cisco.test.repositories.CaseRepository;
import com.cisco.test.repositories.NoteRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CaseServiceTest {

    private final CaseRepository caseRepository = mock(CaseRepository.class);
    private final NoteRepository noteRepository = mock(NoteRepository.class);

    private final CaseService testObject = new CaseService(caseRepository, noteRepository);

    @Test
    void create() {
        final Case toCreate = Case.builder().title("title").description("desc").build();
        final Case created = Case.builder().caseId(1).title("title").description("desc").build();
        when(caseRepository.create(any(Case.class))).thenReturn(created);

        final Case result = testObject.create(toCreate);

        assertEquals(1, result.getCaseId());

        verify(caseRepository, atMostOnce()).create(any(Case.class));
    }
}