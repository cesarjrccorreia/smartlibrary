/*All User's gets stored in APP_USER table*/
create table APP_USER (
   id BIGSERIAL NOT NULL,
   username VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   name VARCHAR NOT NULL,
   email VARCHAR NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (username)
);

/* USER_PROFILE table contains all possible roles */ 
create table USER_PROFILE(
   id BIGSERIAL NOT NULL,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);

/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE USER_HAS_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);

/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('Aluno');
  
INSERT INTO USER_PROFILE(type)
VALUES ('Bibliotecaria');
  
INSERT INTO USER_PROFILE(type)
VALUES ('Professor');

INSERT INTO APP_USER(username, password, name, email)
VALUES ('cj','3xt77TPKsWZAYUvJ4Nnk5OBxwTWJOtJP9loLjbtgaxZlNTLhI2QE', 'Cesar Junior','cesarjrcorreia@gmail.com');

/* Populate JOIN Table */
INSERT INTO USER_HAS_USER_PROFILE (user_id, user_profile_id)
  SELECT u.id, profile.id FROM APP_USER u, USER_PROFILE profile
  where u.username='cj' and profile.type='Bibliotecaria';
 
/* Create persistent_logins Table used to store rememberme related stuff*/
CREATE TABLE PERSISTENT_LOGINS (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);  