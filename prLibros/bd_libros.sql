use bd_libros;

CREATE TABLE libros (
	id INT PRIMARY KEY AUTO_INCREMENT,
	titulo VARCHAR(40) NOT NULL,
    editorial VARCHAR(30),
    autor VARCHAR(30) NOT NULL,
    paginas INT NOT NULL
);