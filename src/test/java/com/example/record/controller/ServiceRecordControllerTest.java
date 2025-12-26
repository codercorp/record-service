package com.example.record.controller;

import com.example.record.model.ServiceRecord;
import com.example.record.repo.ServiceRecordRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ServiceRecordController.class)
class ServiceRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceRecordRepository repo;

    @Autowired
    private ObjectMapper objectMapper;

    private ServiceRecord mockRecord() {
        ServiceRecord r = new ServiceRecord();
        r.setId("1");
        r.setDescription("First Service");
        r.setCost(5000);
        return r;
    }

    // -------- GET ALL --------
    @Test
    void all_success() throws Exception {

        Mockito.when(repo.findAll())
                .thenReturn(List.of(mockRecord()));

        mockMvc.perform(get("/api/servicerecords"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$[0].description").value("First Service"));
    }

    // -------- GET BY ID --------
    @Test
    void get_success() throws Exception {

        Mockito.when(repo.findById("1"))
                .thenReturn(Optional.of(mockRecord()));

        mockMvc.perform(get("/api/servicerecords/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    // -------- CREATE --------
    @Test
    void create_success() throws Exception {

        Mockito.when(repo.save(Mockito.any(ServiceRecord.class)))
                .thenReturn(mockRecord());

        mockMvc.perform(post("/api/servicerecord")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockRecord())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"));
    }

    // -------- UPDATE --------
    @Test
    void update_success() throws Exception {

        ServiceRecord updated = mockRecord();
        updated.setDescription("Updated Service");

        Mockito.when(repo.save(Mockito.any(ServiceRecord.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/servicerecord/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updated)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Updated Service"));
    }

    // -------- DELETE --------
    @Test
    void delete_success() throws Exception {

        Mockito.doNothing().when(repo).deleteById("1");

        mockMvc.perform(delete("/api/servicerecord/1"))
                .andExpect(status().isOk());

        Mockito.verify(repo, Mockito.times(1)).deleteById("1");
    }
}
