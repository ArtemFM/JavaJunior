-- ################################################################################################################################
-- Написать запрос получение всех продуктов с типом "СЫР";
SELECT p.product_name AS "ИМЯ ПРОДУКТА", UPPER(t.type_name) AS "ТИП ПРОДУКТА" FROM
    products p INNER JOIN typeproduct t USING(id_type) WHERE t.type_name = LOWER('СЫР');

-- ################################################################################################################################
-- Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"  
SELECT p.product_name AS "ИМЯ ПРОДУКТА", UPPER(t.type_name) AS "ТИП ПРОДУКТА" FROM
    products p INNER JOIN typeproduct t USING(id_type) WHERE LOWER(p.product_name) LIKE '%мороженное%';

-- ################################################################################################################################
-- Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT p.product_name "ИМЯ ПРОДУКТА", UPPER(t.type_name) "ТИП ПРОДУКТА", p.product_expired_date "СРОК ГОДНОСТИ ДО" FROM 
    products p INNER JOIN typeproduct t USING(id_type) WHERE EXTRACT(MONTH FROM p.product_expired_date) = EXTRACT(MONTH FROM now() + INTERVAL '1' MONTH);

-- ################################################################################################################################
-- Написать запрос, который вывод самый дорогой продукт.
-- 1-й вариант
SELECT p.product_name "ИМЯ ПРОДУКТА", UPPER(t.type_name) "ТИП ПРОДУКТА", p.product_price "ЦЕНА ПРОДУКТА" FROM 
    products p INNER JOIN typeproduct t USING(id_type) WHERE p.product_price = (SELECT MAX(p.product_price) FROM products p);
    
-- 2-й вариант    
SELECT p.product_name "ИМЯ ПРОДУКТА", UPPER(t.type_name) "ТИП ПРОДУКТА", p.product_price "ЦЕНА ПРОДУКТА" FROM 
    products p INNER JOIN typeproduct t USING(id_type) ORDER BY product_price DESC LIMIT 1;  
    
-- ################################################################################################################################
-- Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT UPPER(t.type_name) "ТИП ПРОДУКТА", COUNT(t.type_name) "КОЛ-ВО" FROM 
    products p INNER JOIN typeproduct t USING(id_type) GROUP BY (t.type_name);

-- ################################################################################################################################
-- Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
SELECT p.product_name "НАЗВАНИЕ", UPPER(t.type_name) "ТИП ПРОДУКТА" FROM 
    products p INNER JOIN typeproduct t USING(id_type) WHERE UPPER(t.type_name) = 'СЫР' OR UPPER(t.type_name) = 'МОЛОКО';

-- ################################################################################################################################
-- Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT UPPER(t.type_name) "ТИП ПРОДУКТА", COUNT(t.type_name) "КОЛ-ВО" FROM 
    products p INNER JOIN typeproduct t USING(id_type) GROUP BY (t.type_name) HAVING COUNT(t.type_name) < 10;

-- ################################################################################################################################
-- Вывести все продукты и их тип.
SELECT p.product_name, p.product_price, p.product_expired_date, t.type_name FROM products p INNER JOIN typeproduct t USING(id_type);

