INSERT INTO typeproduct(type_name)
    VALUES('молоко'),
          ('сыр'),
          ('мясо');
          
INSERT INTO products(product_name, product_price, product_expired_date, id_type)
    VALUES('молоко 2.5%', 64.20, '2018-01-28', 1),
          ('молоко 3%', 67.40, '2018-01-27', 1),
          ('сметана 12%', 38.70, '2018-02-12', 1),
          ('сметана 20%', 42.80, '2018-02-23', 1),
          ('кефир 5%', 47.20, '2018-02-04', 1),
          ('Ванильное мороженное', 25.20, '2018-03-12', 1),
          ('Шоколадное мороженное', 25.20, '2018-03-12', 1),
          ('Мороженное Пломбир', 57.40, '2018-03-20', 1),
          ('Мороженное Эскимо', 33.50, '2018-02-18', 1),
          ('ряженка', 64.20, '2018-02-07', 1),
          ('снежок', 64.20, '2018-02-14', 1),
          ('Пармезан', 135.80, '2018-02-27', 2),
          ('Эдем', 162.60, '2018-02-18', 2),
          ('Голандский', 120.30, '2018-03-04', 2),
          ('Кура филе', 245.50, '2018-05-12', 3),
          ('Говядина филе', 350.20, '2018-04-22', 3),
          ('Свинина шейка', 305.40, '2018-06-07', 3),
          ('Кура ножки', 180.70, '2018-03-14', 3),
          ('Замороженное бедро куры', 145.60, '2018-09-12', 3);