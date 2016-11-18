create table app_user (
   id BIGSERIAL NOT NULL,
   username VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   name VARCHAR NOT NULL,
   email VARCHAR NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (username)
);

create table user_profile(
   id BIGSERIAL NOT NULL,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
 
CREATE TABLE user_has_user_profile (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_USER FOREIGN KEY (user_id) REFERENCES app_user (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES user_profile (id)
);

INSERT INTO user_profile(type)
VALUES ('Aluno');
  
INSERT INTO user_profile(type)
VALUES ('Bibliotecaria');
  
INSERT INTO user_profile(type)
VALUES ('Professor');

INSERT INTO app_user(username, password, name, email)
VALUES ('bibliotecaria','$2a$10$KdqmwdnzxB1225G.V8ca9OkcbqGqAe7Ug16DJZAnEtAeR3oU77I5G', 'Bibliotecaria','smartlibrary@yahoo.com.br');

INSERT INTO user_has_user_profile (user_id, user_profile_id)
  SELECT u.id, profile.id FROM app_user u, user_profile profile
  where u.username='bibliotecaria' and profile.type='Bibliotecaria';
 
CREATE TABLE PERSISTENT_LOGINS (
    username VARCHAR(64) NOT NULL,
    series VARCHAR(64) NOT NULL,
    token VARCHAR(64) NOT NULL,
    last_used TIMESTAMP NOT NULL,
    PRIMARY KEY (series)
);

CREATE TABLE disciplina (
  id SERIAL   NOT NULL ,
  name VARCHAR   NOT NULL ,
  ementa TEXT   NOT NULL   ,
PRIMARY KEY(id));

CREATE TABLE editora (
  id SERIAL   NOT NULL ,
  name VARCHAR   NOT NULL   ,
PRIMARY KEY(id));

CREATE TABLE autor (
  id SERIAL   NOT NULL ,
  name VARCHAR   NOT NULL   ,
PRIMARY KEY(id));

CREATE TABLE livro (
  id SERIAL   NOT NULL ,
  editora_id INTEGER   NOT NULL ,
  isbn VARCHAR   NOT NULL ,
  nome VARCHAR   NOT NULL ,
  ano INTEGER   NOT NULL ,
  edicao INTEGER   NOT NULL ,
  sumario VARCHAR   NOT NULL ,
  imagem VARCHAR    ,
  quantidade INTEGER   NOT NULL ,
  classificacao NUMERIC      ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(editora_id)
    REFERENCES editora(id));

CREATE INDEX livro_FKIndex1 ON livro (editora_id);

CREATE INDEX IFK_Rel_07 ON livro (editora_id);

CREATE TABLE reserva (
  id SERIAL   NOT NULL ,
  user_id INTEGER   NOT NULL ,
  livro_id INTEGER   NOT NULL   ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(livro_id)
    REFERENCES livro(id),
  FOREIGN KEY(user_id)
    REFERENCES app_user(id));

CREATE INDEX reserva_FKIndex2 ON reserva (livro_id);
CREATE INDEX reserva_FKIndex3 ON reserva (user_id);

CREATE INDEX IFK_Rel_13 ON reserva (livro_id);
CREATE INDEX IFK_Rel_25 ON reserva (user_id);

CREATE TABLE livros_indicados (
  livro_id INTEGER   NOT NULL ,
  user_id INTEGER   NOT NULL   ,
PRIMARY KEY(livro_id, user_id)    ,
  FOREIGN KEY(livro_id)
    REFERENCES livro(id),
  FOREIGN KEY(user_id)
    REFERENCES app_user(id));

CREATE INDEX FK_livro ON livros_indicados (livro_id);
CREATE INDEX Fk_app_user ON livros_indicados (user_id);

CREATE INDEX IFK_Rel_27 ON livros_indicados (livro_id);
CREATE INDEX IFK_Rel_22 ON livros_indicados (user_id);

CREATE TABLE comentario (
  user_id INTEGER   NOT NULL ,
  livro_id INTEGER   NOT NULL ,
  comentario VARCHAR   NOT NULL ,
  classificacao INTEGER   NOT NULL   ,
PRIMARY KEY(user_id, livro_id)    ,
  FOREIGN KEY(user_id)
    REFERENCES app_user(id),
  FOREIGN KEY(livro_id)
    REFERENCES livro(id));

CREATE INDEX comentario_FKIndex1 ON comentario (user_id);
CREATE INDEX comentario_FKIndex2 ON comentario (livro_id);

CREATE INDEX IFK_Rel_28 ON comentario (user_id);
CREATE INDEX IFK_Rel_29 ON comentario (livro_id);

CREATE TABLE autor_has_livro (
  autor_id INTEGER   NOT NULL ,
  livro_id INTEGER   NOT NULL   ,
PRIMARY KEY(autor_id, livro_id)    ,
  FOREIGN KEY(autor_id)
    REFERENCES autor(id),
  FOREIGN KEY(livro_id)
    REFERENCES livro(id));

CREATE INDEX autor_has_livro_FKIndex1 ON autor_has_livro (autor_id);
CREATE INDEX autor_has_livro_FKIndex2 ON autor_has_livro (livro_id);

CREATE INDEX IFK_Rel_08 ON autor_has_livro (autor_id);
CREATE INDEX IFK_Rel_09 ON autor_has_livro (livro_id);

CREATE TABLE emprestimo (
  id SERIAL   NOT NULL ,
  user_id INTEGER   NOT NULL ,
  livro_id INTEGER   NOT NULL ,
  inicio DATE   NOT NULL ,
  fim DATE      ,
PRIMARY KEY(id)    ,
  FOREIGN KEY(livro_id)
    REFERENCES livro(id),
  FOREIGN KEY(user_id)
    REFERENCES app_user(id));

CREATE INDEX emprestimo_FKIndex2 ON emprestimo (livro_id);
CREATE INDEX emprestimo_FKIndex3 ON emprestimo (user_id);

CREATE INDEX IFK_Rel_17 ON emprestimo (livro_id);
CREATE INDEX IFK_Rel_24 ON emprestimo (user_id);

CREATE TABLE disciplinas_matriculadas (
  disciplina_id INTEGER   NOT NULL ,
  periodo VARCHAR   NOT NULL ,
  user_id INTEGER   NOT NULL   ,
PRIMARY KEY(disciplina_id, periodo, user_id)    ,
  FOREIGN KEY(disciplina_id)
    REFERENCES disciplina(id),
  FOREIGN KEY(user_id)
    REFERENCES app_user(id));

CREATE INDEX disciplinas_matriculadas_FKIndex2 ON disciplinas_matriculadas (disciplina_id);
CREATE INDEX disciplinas_matriculadas_FKIndex3 ON disciplinas_matriculadas (user_id);

CREATE INDEX IFK_Rel_30 ON disciplinas_matriculadas (disciplina_id);
CREATE INDEX IFK_Rel_31 ON disciplinas_matriculadas (user_id);

CREATE TABLE informe (
  id SERIAL   NOT NULL ,
  user_id INTEGER   NOT NULL ,
  texto VARCHAR NOT NULL     ,
PRIMARY KEY(id)  ,
  FOREIGN KEY(user_id)
    REFERENCES app_user(id));

CREATE INDEX informe_FKIndex1 ON informe (user_id);

CREATE INDEX IFK_Rel_19 ON informe (user_id);