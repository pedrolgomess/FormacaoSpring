ALTER TABLE medicos ADD COLUMN ativo boolean;
UPDATE medicos SET ativo = true;

ALTER TABLE pacientes ADD COLUMN ativo boolean;
UPDATE pacientes SET ativo = true;