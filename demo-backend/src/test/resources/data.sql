DROP TABLE IF EXISTS DEMO_ENCUESTA;
create table DEMO_ENCUESTA
(
    PK_ENCUESTA    int primary key auto_increment,
    EMAIL          varchar(150) unique,
    FK_MUSICA_TIPO int
);

DROP TABLE IF EXISTS DEMO_MUSICA_TIPO;
create table DEMO_MUSICA_TIPO
(
    PK_MUSICA_TIPO int primary key,
    NAME           varchar(50) unique
);

ALTER TABLE DEMO_ENCUESTA
    ADD FOREIGN KEY (FK_MUSICA_TIPO)
        REFERENCES DEMO_MUSICA_TIPO (PK_MUSICA_TIPO);

INSERT INTO DEMO_MUSICA_TIPO (PK_MUSICA_TIPO, NAME)
VALUES (1, 'Rock');
INSERT INTO DEMO_MUSICA_TIPO (PK_MUSICA_TIPO, NAME)
VALUES (2, 'Pop');


INSERT INTO DEMO_ENCUESTA (PK_ENCUESTA, EMAIL, FK_MUSICA_TIPO)
VALUES (1,'fernando@gmail.com',1);
INSERT INTO DEMO_ENCUESTA (PK_ENCUESTA, EMAIL, FK_MUSICA_TIPO)
VALUES (2,'adriana@gmail.com',1);
--
-- DROP TABLE IF EXISTS GRTA_COMPENDIO_GENERAL;
--
-- create table DEMO_USUARIO
-- (
--     PK_USUARIO int primary key,
--     EMAIL      varchar(150) unique,
-- );
--
--
-- create table DEMO_ENCUESTA
-- (
--     PK_ENCUESTA    int primary key,
--     FK_USUARIO     int,
--     FK_TIPO_MUSICA int
-- );
--
-- create table DEMO_MUSICA_TIPO
-- (
--     PK_MUSICA_TIPO int primary key,
--     NAME           varchar(50) unique,
-- );
--
-- ALTER TABLE DEMO_ENCUESTA
--     ADD FOREIGN KEY (FK_TIPO_MUSICA)
--         REFERENCES DEMO_MUSICA_TIPO (PK_MUSICA_TIPO);
--
-- INSERT INTO DEMO_MUSICA_TIPO (name)
-- VALUES ('Rock');
-- INSERT INTO DEMO_MUSICA_TIPO (name)
-- VALUES ('Pop');
-- INSERT INTO DEMO_MUSICA_TIPO (name)
-- VALUES ('Jazz');
-- INSERT INTO DEMO_MUSICA_TIPO (name)
-- VALUES ('Classic');
-- INSERT INTO DEMO_MUSICA_TIPO (name)
-- VALUES ('Blues');
-- INSERT INTO DEMO_MUSICA_TIPO (name)
-- VALUES ('Metal');
--
-- INSERT INTO DEMO_USUARIO (EMAIL)
-- VALUES ('nandollc@gmail.com');
--
-- INSERT INTO DEMO_ENCUESTA (PK_ENCUESTA, FK_USUARIO, FK_TIPO_MUSICA)
-- VALUES (1,1,1);
