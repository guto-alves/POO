DROP TABLE IF EXISTS cursos;

CREATE TABLE cursos (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome VARCHAR (255) NOT NULL UNIQUE,
    descricao VARCHAR (255),
    ativo INTEGER,
    inicio DATE,
    termino DATE
);
