use bd_backroomsdi;

CREATE TABLE backrooms (
	/*Numero de Backroom*/
    levelNumber         INT PRIMARY KEY,
    /*Nombre de Backroom*/
	backroomName 	 VARCHAR(40) UNIQUE NOT NULL,
    /*Numero de entidades en la backroom*/
    entitysNumber      INT NOT NULL,
    /*Dificultad de la backroom*/
    difficulty		 VARCHAR(40) NOT NULL
);