package com.example.drama.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DramaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldListSeedDramas() throws Exception {
        mockMvc.perform(get("/api/dramas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").exists());
    }

    @Test
    void shouldCreateDrama() throws Exception {
        String body = """
                {
                  "title":"打工人逆袭记",
                  "category":"URBAN",
                  "description":"普通打工人逆袭创业。",
                  "status":"DRAFT"
                }
                """;

        mockMvc.perform(post("/api/dramas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("打工人逆袭记"));
    }
}
