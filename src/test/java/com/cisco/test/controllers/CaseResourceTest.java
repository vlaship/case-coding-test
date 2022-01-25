package com.cisco.test.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CaseResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getCase() throws Exception {
        mockMvc
                .perform(get("/cases/"+ 1))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.caseId").value(1))
                .andExpect(jsonPath("$.title").value("title1"))
                .andExpect(jsonPath("$.description").value("desc1"))
                .andExpect(jsonPath("$.severity").value(1))
                .andExpect(jsonPath("$.status").value("OPEN"))
                .andExpect(jsonPath("$.user.userId").value(1))

                .andExpect(jsonPath("$.notes").isArray())
                .andExpect(jsonPath("$.notes").isNotEmpty())
                .andExpect(jsonPath("$.notes.length()").value(2))
                .andExpect(jsonPath("$.notes[0].noteId").value(1))
                .andExpect(jsonPath("$.notes[0].caseId").value(1))
                .andExpect(jsonPath("$.notes[1].noteId").value(4))
                ;
    }
}