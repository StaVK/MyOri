DELETE FROM user_roles;
DELETE FROM products;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', 'password');

INSERT INTO users (name, email, password)
VALUES ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO products (article, description, price) VALUES
  ('111', 'Помада1', 500),
  ('222', 'Помада2', 1000),
  ('333', 'Туалетная вода', 500),
  ('444', 'Тушь', 500),
  ('555', 'Пена для бритья', 1000),
  ('6666', 'Лак для ногтей', 510),
  ('777', 'Туалетная вода 2', 510),
  ('888', 'Дезодорант', 1500);
