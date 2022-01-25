package com.cisco.test.controllers;

import com.cisco.test.model.Case;
import com.cisco.test.model.Note;
import com.cisco.test.services.CaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CaseResource {

    private final CaseService caseService;

    @GetMapping("/cases/status/{status}")
    List<Case> getCasesWithStatus(@PathVariable Case.Status status) {
        return caseService.findAllByStatus(status);
    }

    @GetMapping("/cases/user/{userId}")
    List<Case> getOpenCases(@PathVariable Integer userId) {
        return caseService.findAllByStatusAnsUserId(Case.Status.OPEN, userId);
    }

    @GetMapping("/cases/user/{userId}/status/{status}")
    List<Case> getCasesByUserAndStatus(@PathVariable Integer userId, @PathVariable Case.Status status) {
        return caseService.findAllByStatusAnsUserId(status, userId);
    }

    @GetMapping("/cases/{caseId}")
    Case getCase(@PathVariable Integer caseId) {
        return caseService.findById(caseId);
    }

    @PostMapping(value = "/cases/create", consumes = "application/json")
    Case createCase(@RequestBody Case toCreate) {
        return caseService.create(toCreate);
    }

    @PostMapping(value = "/cases/{caseId}/addNote", consumes = "text/plain")
    Note addNote(@PathVariable Integer caseId, @RequestBody String detail) {
        return caseService.addNote(caseId, detail);
    }

}
