DROP TABLE PW_valoraciones;
DROP TABLE PW_sesiones;
DROP TABLE PW_criticas;
DROP TABLE PW_espectaculos;
DROP TABLE PW_espectadores;
DROP TABLE PW_administradores;

CREATE TABLE PW_administradores (
    correo     VARCHAR(100) NOT NULL,
    nombre     VARCHAR(25) NOT NULL,
    apellidos  VARCHAR(50) NOT NULL,
    nick       VARCHAR(25) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    fechaRegistro DATETIME NOT NULL,
   	ultimaConexion DATETIME NULL
);

ALTER TABLE PW_administradores ADD CONSTRAINT administradores_pk PRIMARY KEY ( correo );

CREATE TABLE PW_criticas (
    titulo              VARCHAR(50) NOT NULL,
    puntuacion          INTEGER NOT NULL,
    resena              VARCHAR(250) NOT NULL,
    propietario         VARCHAR(100) NOT NULL,
    espectaculos_titulo VARCHAR(50) NOT NULL
);

ALTER TABLE PW_criticas
    ADD CONSTRAINT puntuacionvalores CHECK ( puntuacion BETWEEN 1 AND 10 );

ALTER TABLE PW_criticas ADD CONSTRAINT criticas_pk PRIMARY KEY ( titulo );

CREATE TABLE PW_espectaculos (
    titulo      VARCHAR(50) NOT NULL,
    descripcion VARCHAR(250) NOT NULL,
    categoria   VARCHAR(20) NOT NULL,
    tipo        VARCHAR(25) NOT NULL,
    propietario VARCHAR(100) NOT NULL
);

ALTER TABLE PW_espectaculos
    ADD CONSTRAINT categorias CHECK ( categoria IN ( 'CONCIERTO', 'MONOLOGO', 'TEATRO' ) );

ALTER TABLE PW_espectaculos
    ADD CONSTRAINT tipoespectaculo CHECK ( tipo IN ( 'PASEMULTIPLE', 'PUNTUAL', 'TEMPORADA' ) );

ALTER TABLE PW_espectaculos ADD CONSTRAINT espectaculos_pk PRIMARY KEY ( titulo );

CREATE TABLE PW_espectadores (
    correo     VARCHAR(100) NOT NULL,
    nombre     VARCHAR(25) NOT NULL,
    apellidos  VARCHAR(50) NOT NULL,
    nick       VARCHAR(25) NOT NULL,
    contrasena VARCHAR(50) NOT NULL,
    fechaRegistro DATETIME NOT NULL,
   	ultimaConexion DATETIME NULL
);

ALTER TABLE PW_espectadores ADD CONSTRAINT espectadores_pk PRIMARY KEY ( correo );

CREATE TABLE PW_sesiones (
    id                  INTEGER NOT NULL,
    fecha               DATE DEFAULT '0001-01-01' NOT NULL,
    hora                TIME DEFAULT '00:00' NOT NULL,
    espectaculos_titulo VARCHAR(50) NOT NULL,
    totalEntradas		INTEGER NOT NULL,
    entradasVendidas	INTEGER NOT NULL,
    entradasDisponibles INTEGER NOT NULL
);

ALTER TABLE PW_sesiones ADD CONSTRAINT sesiones_pk PRIMARY KEY ( id );

ALTER TABLE PW_sesiones MODIFY COLUMN id INT auto_increment;

CREATE TABLE PW_valoraciones (
    propietario     VARCHAR(100) NOT NULL,
    puntuacion      INTEGER NOT NULL,
    criticas_titulo VARCHAR(50) NOT NULL
);

ALTER TABLE PW_valoraciones
    ADD CONSTRAINT valoracionesrango CHECK ( puntuacion BETWEEN 1 AND 5 );

ALTER TABLE PW_valoraciones ADD CONSTRAINT valoraciones_pk PRIMARY KEY ( propietario );

ALTER TABLE PW_criticas
    ADD CONSTRAINT criticas_espectaculos_fk FOREIGN KEY ( espectaculos_titulo )
        REFERENCES PW_espectaculos ( titulo );

ALTER TABLE PW_criticas
    ADD CONSTRAINT criticas_espectadores_fk FOREIGN KEY ( propietario )
        REFERENCES PW_espectadores ( correo );

ALTER TABLE PW_espectaculos
    ADD CONSTRAINT espectaculos_admin_fk FOREIGN KEY ( propietario )
        REFERENCES PW_administradores ( correo );

ALTER TABLE PW_sesiones
    ADD CONSTRAINT sesiones_espectaculos_fk FOREIGN KEY ( espectaculos_titulo )
        REFERENCES PW_espectaculos ( titulo );

ALTER TABLE PW_valoraciones
    ADD CONSTRAINT valoraciones_criticas_fk FOREIGN KEY ( criticas_titulo )
        REFERENCES PW_criticas ( titulo );

ALTER TABLE PW_valoraciones
    ADD CONSTRAINT valoraciones_espectadores_fk FOREIGN KEY ( propietario )
        REFERENCES PW_espectadores ( correo );
