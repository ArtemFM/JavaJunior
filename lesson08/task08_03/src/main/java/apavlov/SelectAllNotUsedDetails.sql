-- Print all not used engines
SELECT e.brand AS "МАРКА", e.type AS "ТИП" FROM engine AS e
    LEFT OUTER JOIN car AS c USING(id_engine) WHERE c.id_engine ISNULL;

-- Print all not used transmissions
SELECT t.brand AS "МАРКА", t.type AS "ТИП" FROM transmission AS t
    LEFT OUTER JOIN car AS c USING(id_transmission) WHERE c.id_transmission ISNULL; 
    
-- Print all not used gearboxes
SELECT g.brand AS "МАРКА", g.type AS "ТИП" FROM car AS c
    RIGHT OUTER JOIN gearbox AS g USING(id_gearbox) WHERE c.id_gearbox ISNULL;    