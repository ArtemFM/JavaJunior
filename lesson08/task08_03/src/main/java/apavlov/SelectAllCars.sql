SELECT c.brand AS "МАРКА", e.type AS "ДВИГАТЕЛЬ", t.type AS "ТРАНСМИССИЯ", g.type AS "КПП" 
    FROM car AS c
    INNER JOIN engine AS e USING(id_engine)
    INNER JOIN transmission AS t USING(id_transmission)
    INNER JOIN gearbox AS g USING(id_gearbox);