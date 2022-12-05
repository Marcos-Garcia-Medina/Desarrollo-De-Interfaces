USE bd_videojuegos;

CREATE TABLE videojuegos(
	nombre VARCHAR(40) PRIMARY KEY,
    precio INT NOT NULL,
    consola VARCHAR(40) NOT NULL,
    pegi INT NOT NULL
);

