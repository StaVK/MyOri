DELETE FROM user_roles;
DELETE FROM customers;
DELETE FROM storage_products;
DELETE FROM storage;
DELETE FROM products;
DELETE FROM users;
DELETE FROM orders;
DELETE FROM reserved_products;
ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO people (name, surname, patronymic, phoneNumber)
VALUES ('peopleName1', 'peopleSurame1', 'peoplePatronymic1', 123456789012);
INSERT INTO people (name, surname, patronymic, phoneNumber)
VALUES ('peopleNameAdmin', 'peopleSurameAdmin', 'peoplePatronymicAdmin', 123456789013);

-- 1
INSERT INTO users (name, email, password, peopleid)
VALUES ('User', 'user@yandex.ru', 'password', 100000);

INSERT INTO users (name, email, password, peopleid)
VALUES ('Admin', 'admin@gmail.com', 'admin', 100000);

INSERT INTO products (article, description, price) VALUES
  ('111', 'Pomada1', 500),
  ('222', 'Помада2', 1000),
  ('333', 'Туалетная вода', 500),
  ('444', 'Тушь', 500),
  ('555', 'Пена для бритья', 1000),
  ('666', 'Лак для ногтей', 510),
  ('777', 'Туалетная вода 2', 510),
  ('888', 'Дезодорант', 1500);
-- 11

INSERT INTO customers (customerId, userId, peopleid)
VALUES (nextval('global_seq'), 100002, 100001);

INSERT INTO orders (orderId, user_id, customerid, status) VALUES
  (nextval('global_seq'), '100002', '100012', '0');

INSERT INTO order_products (opid, orderid, prodid, volume, executedVolume, status) VALUES
  (nextval('global_seq'), 100013, 100004, '2', '0', '0');

-- 14
INSERT INTO users (name, email, password, peopleid)
VALUES ('User2', 'user2@yandex.ru', 'password', 100000);

INSERT INTO users (name, email, password, peopleid)
VALUES ('User11', 'user11@yandex.ru', 'password', 100001);

INSERT INTO users (name, email, password, peopleid)
VALUES ('User21', 'user21@yandex.ru', 'password', 100000);

INSERT INTO people (name, surname, patronymic, phoneNumber)
VALUES ('People1Name3', 'People1Surname3', 'People1Patronymic3', 123456789014);

-- 18
INSERT INTO people (name, surname, patronymic, phoneNumber)
VALUES ('People1Name4', 'People1Surname4', 'People1Patronymic4', 123456789015);



INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100002),
  ('ROLE_ADMIN', 100003),
  ('ROLE_USER', 100003),
  ('ROLE_USER', 100015),
  ('ROLE_USER', 100016),
  ('ROLE_USER', 100017);

-- 19
INSERT INTO storage (storageid, name, userid)
VALUES (nextval('global_seq'), 'Главный склад', 100002);


INSERT INTO storage_products (spid, volume, price, prodid, storageid)
VALUES (nextval('global_seq'), 5, 1.1, 100004, 100020);

INSERT INTO storage_products (spid, volume, price, prodid, storageid)
VALUES (nextval('global_seq'), 1, 1.1, 100005, 100020);

INSERT INTO order_products (opid, orderid, prodid, volume, executedVolume, status) VALUES
  (nextval('global_seq'), 100013, 100005, '2', '0', '0');

-- 23

INSERT INTO orders (orderId, user_id, customerid, status) VALUES
  (nextval('global_seq'), 100002, 100012, '0');

INSERT INTO order_products (opid, orderid, prodid, volume, executedVolume, status) VALUES
  (nextval('global_seq'), 100024, 100005, '3', '0', '0');

INSERT INTO reserved_products (rpId, spId, opId, reserveVolume, userId) VALUES
  (nextval('global_seq'), 100021, 100014, 1, 100002);

