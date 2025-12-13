package com.vettrack_CAV.appointment_service.infrastructure.adapters.out.external;

import com.vettrack_CAV.appointment_service.domain.ports.out.VetAvailabilityExternalPort;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class VetAvailabilityAdapter implements VetAvailabilityExternalPort {

    private final RestTemplate restTemplate;

    // Leemos la URL desde application.properties (lo configuraremos en el siguiente paso)
    @Value("${services.vet-availability.url}")
    private String availabilityServiceUrl;

    @Override
    public boolean isVeterinarianAvailable(Long vetId, LocalDate date, LocalTime time) {
        // Construir la URL completa
        String url = availabilityServiceUrl + "/availability";

        //  Preparar el cuerpo de la petición (JSON)
        // Body: { "veterinarioId": 22, "fecha": "2025-01-15", "hora": "10:30" }
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("vetId", vetId);
        requestBody.put("date", date.toString());
        requestBody.put("time", time.toString());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            // Hacer la llamada POST
            // Esperamos una respuesta como: { "disponible": true, ... }
            AvailabilityResponse response = restTemplate.postForObject(url, requestEntity, AvailabilityResponse.class);

            return response != null && response.isAvailable();

        } catch (Exception e) {
            // Si el servicio externo falla o está caído, asumimos que NO está disponible por seguridad
            // O podrías lanzar una BusinessException("No se pudo verificar disponibilidad")
            System.err.println("Error consultando disponibilidad: " + e.getMessage());
            return false;
        }
    }

    // Clase interna auxiliar para mapear la respuesta del Mock
    // Respuesta esperada: { "veterinarioId": 22, "disponible": true, "motivo": "..." }
    private static class AvailabilityResponse {
        private boolean available;

        public boolean isAvailable() {
            return available;
        }
        public void setAvailable(boolean available) {
            this.available = available;
        }
    }
}
