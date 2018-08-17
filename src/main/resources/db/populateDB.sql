DELETE FROM user_roles;
DELETE FROM customers;
DELETE FROM storage_products;
DELETE FROM storage;
DELETE FROM products;
DELETE FROM users;
DELETE FROM orders;
DELETE FROM reserved_products;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');


INSERT INTO products (article, description, price) VALUES
  ('111', 'Pomada1', 500),
  ('222', 'Помада2', 1000),
  ('333', 'Туалетная вода', 500),
  ('444', 'Тушь', 500),
  ('555', 'Пена для бритья', 1000),
  ('666', 'Лак для ногтей', 510),
  ('777', 'Туалетная вода 2', 510),
  ('888', 'Дезодорант', 1500);

INSERT INTO orders (orderId, user_id, foruser_id, status) VALUES
  (nextval('global_seq'), '100000', '100001', '0');

INSERT INTO order_products (opid, orderid, prodid, volume, executedVolume, status) VALUES
  ('100011', '100010', '100002', '2', '0', '0');

INSERT INTO users (name, email, password)
VALUES ('User2', 'user2@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('User11', 'user11@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('User21', 'user21@yandex.ru', 'password');

INSERT INTO customers (user_id, customerid)
VALUES (100000, 100012);

INSERT INTO customers (user_id, customerid)
VALUES (100011, 100013);

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001),
  ('ROLE_USER', 100011),
  ('ROLE_USER', 100012),
  ('ROLE_USER', 100013);

INSERT INTO storage (storageid, name, userid)
VALUES (nextval('global_seq'), 'Главный склад', 100000);

INSERT INTO storage_products (spid, volume, price, prodid, storageid)
VALUES (nextval('global_seq'), 5, 1.1, 100002, 100014);

INSERT INTO storage_products (spid, volume, price, prodid, storageid)
VALUES (nextval('global_seq'), 1, 1.1, 100003, 100014);

INSERT INTO order_products (opid, orderid, prodid, volume, executedVolume, status) VALUES
  (nextval('global_seq'), '100010', '100003', '2', '0', '0');


INSERT INTO orders (orderId, user_id, foruser_id, status) VALUES
  (nextval('global_seq'), '100000', '100012', '0');

INSERT INTO order_products (opid, orderid, prodid, volume, executedVolume, status) VALUES
  (nextval('global_seq'), '100018', '100003', '3', '0', '0');

INSERT INTO reserved_products (rpId, spId, opId, reserveVolume, userId) VALUES
  (nextval('global_seq'), 100015, 100011, 1, 100000);

