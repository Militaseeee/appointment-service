package com.vettrack_CAV.appointment_service.infrastructure.adapters.in.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vettrack_CAV.appointment_service.domain.model.Role;
import com.vettrack_CAV.appointment_service.domain.model.UserModel;
import com.vettrack_CAV.appointment_service.infrastructure.adapters.in.web.dto.request.AppointmentRequest;
import com.vettrack_CAV.appointment_service.infrastructure.config.SecurityConfig;
import com.vettrack_CAV.appointment_service.infrastructure.security.JwtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AppointmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper =
            new ObjectMapper().findAndRegisterModules();

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldReturnCreated_WhenCreatingAppointment() throws Exception {
        AppointmentRequest request = new AppointmentRequest();
        request.setPetId(1L);
        request.setVetId(1L);
        request.setDate(LocalDate.now().plusDays(5));
        request.setTime(LocalTime.of(9, 0));
        request.setReason("Control general");

        mockMvc.perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnForbidden_WhenNoAuth() throws Exception {
        mockMvc.perform(post("/appointment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isForbidden());
    }
}