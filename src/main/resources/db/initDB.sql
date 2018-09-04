DROP TABLE IF EXISTS user_roles;

DROP TABLE IF EXISTS storage_products CASCADE;
DROP TABLE IF EXISTS storage CASCADE;
DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS people CASCADE;
DROP TABLE IF EXISTS customers CASCADE;
DROP TABLE IF EXISTS orders CASCADE;
DROP TABLE IF EXISTS order_products CASCADE;
DROP TABLE IF EXISTS boxes CASCADE;
DROP TABLE IF EXISTS box_products CASCADE;
DROP TABLE IF EXISTS reserved_products CASCADE;
DROP TABLE IF EXISTS customer_products CASCADE;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq
  START 100000;

CREATE TABLE people (
  peopleId    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name        VARCHAR NOT NULL,
  surname     VARCHAR NOT NULL,
  patronymic  VARCHAR NOT NULL,
  phoneNumber VARCHAR NOT NULL
);

CREATE UNIQUE INDEX peoople_unique_phoneNumber_idx
  ON people (phoneNumber);

CREATE TABLE users
(
  id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR                  NOT NULL,
  email      VARCHAR                  NOT NULL,
  password   VARCHAR                  NOT NULL,
  registered TIMESTAMP DEFAULT now()  NOT NULL,
  enabled    BOOL DEFAULT TRUE        NOT NULL,
  peopleId   INTEGER                  NOT NULL,
  FOREIGN KEY (peopleId) REFERENCES people (peopleId) ON DELETE CASCADE
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
  name      VARCHAR NOT NULL,
  userid    integer NOT NULL,
  CONSTRAINT fkd9mujigmn3o07ld2m1osdam3y
  FOREIGN KEY (userid) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE products (
  prodId      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  article     INTEGER          NOT NULL,
  description TEXT             NOT NULL,
  price       DOUBLE PRECISION NOT NULL
);

CREATE TABLE customers
(
  customerId INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  userId     INTEGER NOT NULL,
  peopleId   INTEGER NOT NULL,
  FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (peopleId) REFERENCES people (peopleId)
);

CREATE TABLE orders (
  orderId    INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id    INTEGER NOT NULL,
  customerId INTEGER NOT NULL,
  status     INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (customerId) REFERENCES customers (customerId) ON DELETE CASCADE
);

CREATE TABLE order_products (
  opId           INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  orderId        INTEGER NOT NULL,
  prodId         INTEGER NOT NULL,
  volume         INTEGER NOT NULL,
  executedVolume INTEGER NOT NULL,
  money          BYTEA,
  status         INTEGER NOT NULL,
  FOREIGN KEY (orderId) REFERENCES orders (orderId) ON DELETE CASCADE,
  FOREIGN KEY (prodId) REFERENCES products (prodId) ON DELETE CASCADE
);

CREATE TABLE storage_products
(
  spid      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  volume    integer NOT NULL,
  price     float   NOT NULL,
  prodid    integer NOT NULL,
  storageid integer NOT NULL,
  --   rpId INTEGER,
  FOREIGN KEY (storageid) REFERENCES storage (storageid) ON DELETE CASCADE,
  FOREIGN KEY (prodid) REFERENCES products (prodid) ON DELETE CASCADE
  --   FOREIGN KEY (rpId) REFERENCES reserved_products (rpId) ON DELETE NO ACTION
);

CREATE TABLE reserved_products (
  rpId          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  spId          INTEGER NOT NULL,
  opId          INTEGER NOT NULL,
  userId        INTEGER NOT NULL,
  reserveVolume INTEGER,
  FOREIGN KEY (userId) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (opId) REFERENCES order_products (opId) ON DELETE CASCADE,
  FOREIGN KEY (spId) REFERENCES storage_products (spid) ON DELETE CASCADE
);

-- CREATE UNIQUE INDEX meals_unique_user_datetime_idx ON meals (user_id, date_time)


CREATE TABLE boxes (
  boxId      INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  user_id    INTEGER NOT NULL,
  customerId INTEGER NOT NULL,
  storageId  INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (customerId) REFERENCES customers (customerId) ON DELETE CASCADE,
  FOREIGN KEY (storageId) REFERENCES storage (storageid) ON DELETE CASCADE
);

CREATE TABLE box_products (
  bpId   INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  boxId  INTEGER NOT NULL,
  prodId INTEGER NOT NULL,
  volume INTEGER NOT NULL,
  FOREIGN KEY (boxId) REFERENCES boxes (boxId) ON DELETE CASCADE,
  FOREIGN KEY (prodId) REFERENCES products (prodId) ON DELETE CASCADE
);

CREATE TABLE customer_products
(
  cpid       INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  volume     integer,
  customerid integer,
  prodid     integer,
  FOREIGN KEY (customerid) REFERENCES customers (customerId) ON DELETE CASCADE,
  FOREIGN KEY (prodid) REFERENCES products (prodId) ON DELETE CASCADE
);

/*create table money
(
  moneyid  INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  amount   bigint,
  currency varchar(255)
);*/

