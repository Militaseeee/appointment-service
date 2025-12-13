-- Insertar algunos veterinarios de prueba
INSERT INTO vet (name, specialty, email) VALUES
('Dr. Juan Perez', 'Cirujano', 'juan@vettrack.com'),
('Dra. Ana Gomez', 'Dermatóloga', 'ana@vettrack.com'),
('Dr. Carlos Ruiz', 'General', 'carlos@vettrack.com');

-- Insertar una mascota de prueba
INSERT INTO pet (name, specie, breed, age, owner_name, owner_id, active) VALUES
('Firulais', 'DOG', 'Labrador', 5, 'Pedro Picapiedra', '1234567890', true);