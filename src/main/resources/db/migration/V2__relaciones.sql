---- Relación Cita -> Mascota
--ALTER TABLE appointment
--ADD CONSTRAINT fk_appointment_pet
--FOREIGN KEY (pet_id) REFERENCES pet(id);
--
-- Relación Cita -> Veterinario
ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_vet
FOREIGN KEY (vet_id) REFERENCES vet(id);

-- Relación Cita -> Diagnóstico (1 a 1)
-- Agregamos la columna diagnostico_id a citas si no la pusimos antes, o creamos la FK
ALTER TABLE appointment
ADD COLUMN diagnosis_id BIGINT UNIQUE;

ALTER TABLE appointment
ADD CONSTRAINT fk_appointment_diagnosis
FOREIGN KEY (diagnosis_id) REFERENCES diagnosis(id);