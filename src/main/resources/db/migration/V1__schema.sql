CREATE TABLE vet (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    specialty VARCHAR(255),
    email VARCHAR(255)
);

CREATE TABLE pet (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    specie VARCHAR(50) NOT NULL,
    breed VARCHAR(255),
    age INTEGER,
    owner_name VARCHAR(255),
    owner_id VARCHAR(255),
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE diagnosis (
    id BIGSERIAL PRIMARY KEY,
    description TEXT,
    suggested_treatment TEXT,
    recommendations TEXT
);

CREATE TABLE appointment (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    time TIME NOT NULL,
    reason VARCHAR(255),
    state_appointment VARCHAR(50),
    pet_id BIGINT NOT NULL,
    vet_id BIGINT NOT NULL,
    diagnosis_id BIGINT,

    CONSTRAINT fk_appointment_pet
        FOREIGN KEY (pet_id) REFERENCES pet(id),
    CONSTRAINT fk_appointment_vet
        FOREIGN KEY (vet_id) REFERENCES vet(id),
    CONSTRAINT uk_appointment_diagnosis_id
        UNIQUE (diagnosis_id),
    CONSTRAINT fk_appointment_diagnosis
        FOREIGN KEY (diagnosis_id) REFERENCES diagnosis(id)
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);