-- Insert table "gearbox"
INSERT INTO gearbox(brand, type)
    VALUES('BMW', 'automatic'),
           ('BMW', 'mechanical'),
           ('Lexus', 'automatic'),
           ('Lexus', 'mechanical'),
           ('Audi', 'automatic'),
           ('Audi', 'mechanical');

-- Insert table "gearbox"
INSERT INTO transmission(brand, type)
    VALUES('BMW', 'mechanical'),
           ('BMW', 'electric'),
           ('Lexus', 'mechanical'),
           ('Lexus', 'combine'),
           ('Audi', 'mechanical'),
           ('Audi', 'hydrovolume');
           
-- Insert table "engine"
INSERT INTO engine(brand, type)
    VALUES('BMW', 'gasoline'),
           ('BMW', 'diesel'),
           ('Lexus', 'gasoline'),
           ('Lexus', 'combine'),
           ('Audi', 'gasoline'),
           ('Audi', 'diesel');       
           
-- Insert table "car"
INSERT INTO car(brand, id_engine, id_transmission, id_gearbox)
    VALUES('BMW',   1, 2, 2),   
           ('Lexus', 4, 3, 4),           
           ('Audi',  6, 6, 5);              