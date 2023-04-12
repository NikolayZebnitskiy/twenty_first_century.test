INSERT INTO orders(client, date, address)
    VALUES
    ('Ivan', '2003-01-03', 'Rostov'),
    ('Marya', '2011-05-29', 'Moscow'),
    ('Oleg', '2023-09-03', 'Sochi'),
    ('Misha', '2020-10-03', 'Minsk'),
    ('Anna', '2008-12-12', 'Moscow');

INSERT INTO goods(name, price)
    VALUES
    ('apple', 70.00),
    ('potato', 21.00),
    ('orange', 120.50);

INSERT INTO order_line(order_id, goods_id, count)
    VALUES
    (1, 1, 5),
    (2, 1, 3),
    (5, 2, 4),
    (5, 1, 20),
    (1, 1, 10),
    (4, 2, 1),
    (5, 2, 1);