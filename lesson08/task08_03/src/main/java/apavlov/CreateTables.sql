-- Create table "transmission"
CREATE TABLE IF NOT EXISTS transmission(
    id_transmission SERIAL PRIMARY KEY,
    brand VARCHAR(50) DEFAULT 'unknown' NOT NULL,
    type VARCHAR(100) DEFAULT 'mechanical' NOT NULL
);

-- Create table "engine"
CREATE TABLE IF NOT EXISTS engine(
    id_engine SERIAL PRIMARY KEY,
    brand VARCHAR(50) DEFAULT 'unknown' NOT NULL,
    type VARCHAR(100) DEFAULT 'gasoline' NOT NULL
);

-- Create table "gearbox"
CREATE TABLE IF NOT EXISTS gearbox(
    id_gearbox SERIAL PRIMARY KEY,
    brand VARCHAR(50) DEFAULT 'unknown' NOT NULL,
    type VARCHAR(100) DEFAULT 'mechanical' NOT NULL
);

-- Create table "car"
CREATE TABLE IF NOT EXISTS car(
    id SERIAL PRIMARY KEY,
    brand VARCHAR(50) DEFAULT 'unknown' NOT NULL,
    id_transmission INTEGER REFERENCES transmission(id_transmission) NOT NULL,
    id_engine INTEGER REFERENCES engine(id_engine) NOT NULL,
    id_gearbox INTEGER REFERENCES gearbox(id_gearbox) NOT NULL
);

