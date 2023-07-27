-- Remove foreign key checks to allow data deletion
SET FOREIGN_KEY_CHECKS = 0;

-- Clear existing data
DELETE FROM aluno;
DELETE FROM professor;
DELETE FROM materia;

-- Reset auto-increment values
ALTER TABLE aluno AUTO_INCREMENT = 1;
ALTER TABLE professor AUTO_INCREMENT = 1;
ALTER TABLE materia AUTO_INCREMENT = 1;

-- Repopulate data
INSERT INTO materia(id, nome, horas) VALUES (1, 'Matematica', 30);
INSERT INTO materia(id, nome, horas) VALUES (2, 'Historia', 10);
-- Add more INSERT statements as needed for other rows

INSERT INTO professor (id, nome) VALUES (1, 'Marcela Maia');
INSERT INTO professor (id, nome) VALUES (2, 'Douglas Carlos');

INSERT INTO aluno (id, nome) VALUES (1, 'Maicon Frascio');
INSERT INTO aluno (id, nome) VALUES (2, 'Gilson Silva');

-- Restore foreign key checks
SET FOREIGN_KEY_CHECKS = 1;
