# --- !Ups

CREATE TABLE users (
  id BIGINT NOT NULL UNIQUE AUTO_INCREMENT,
  username VARCHAR(50) NOT NULL UNIQUE,
  password VARCHAR(50),
  email VARCHAR(100),
  create_date timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_date datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (id)
) ENGINE=InnoDB;

# --- !Downs

DROP TABLE IF EXISTS users;
