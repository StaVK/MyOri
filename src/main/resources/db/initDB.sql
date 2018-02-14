DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS order_products CASCADE;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 100000;

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR                  NOT NULL,
  email      VARCHAR                  NOT NULL,
  password   VARCHAR                  NOT NULL,
  registered TIMESTAMP DEFAULT now()  NOT NULL,
  enabled    BOOL DEFAULT TRUE        NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE products (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  article     INTEGER          NOT NULL,
  description TEXT             NOT NULL,
  price       DOUBLE PRECISION NOT NULL
);
-- CREATE UNIQUE INDEX meals_unique_user_datetime_idx ON meals (user_id, date_time)

CREATE TABLE orders (
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id    INTEGER NOT NULL,
  foruser_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (foruser_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE order_products (
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  order_id   INTEGER NOT NULL,
  product_id INTEGER NOT NULL,
  volume     INTEGER NOT NULL,
  FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
  FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);
