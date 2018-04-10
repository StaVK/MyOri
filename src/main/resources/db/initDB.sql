DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS storage_products;
DROP TABLE IF EXISTS storage CASCADE;
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

CREATE TABLE storage
(
  storageid INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name VARCHAR NOT NULL,
  userid integer NOT NULL,
  CONSTRAINT fkd9mujigmn3o07ld2m1osdam3y FOREIGN KEY (userid)
  REFERENCES users (id) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION
);

CREATE TABLE products (
  prodId      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  article     INTEGER          NOT NULL,
  description TEXT             NOT NULL,
  price       DOUBLE PRECISION NOT NULL
);

CREATE TABLE storage_products
(
  spid INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  volume integer NOT NULL,
  price float NOT NULL,
  prodid integer NOT NULL,
  storageid integer NOT NULL,
  CONSTRAINT fk2jf7oqjn70dtpbkjpxqkb1jgi FOREIGN KEY (storageid)
  REFERENCES storage (storageid) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk2v4acakr0x6tj13kiru0sf6y5 FOREIGN KEY (prodid)
  REFERENCES products (prodid) MATCH SIMPLE
  ON UPDATE NO ACTION ON DELETE CASCADE
);


CREATE TABLE customers
(
  user_id    INTEGER NOT NULL,
  customerId INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (customerId) REFERENCES users (id) ON DELETE CASCADE
);



-- CREATE UNIQUE INDEX meals_unique_user_datetime_idx ON meals (user_id, date_time)

CREATE TABLE orders (
  orderId    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id    INTEGER NOT NULL,
  foruser_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (foruser_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE order_products (
  opId    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  orderId INTEGER NOT NULL,
  prodId  INTEGER NOT NULL,
  volume  INTEGER NOT NULL,
  FOREIGN KEY (orderId) REFERENCES orders (orderId) ON DELETE CASCADE,
  FOREIGN KEY (prodId) REFERENCES products (prodId) ON DELETE CASCADE
);
