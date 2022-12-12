USE bd_videojuegos;

CREATE TABLE videojuegos(
	id INT PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(40),
    precio INT NOT NULL,
    consola VARCHAR(40) NOT NULL,
    pegi INT NOT NULL
);

SELECT * FROM videojuegos;

