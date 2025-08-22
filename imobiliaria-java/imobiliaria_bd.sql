-- Criação do banco de dados (se não existir)
CREATE DATABASE IF NOT EXISTS imobiliaria_db;

-- Seleciona o banco de dados para uso
USE imobiliaria_db;

-- Tabela de Clientes
CREATE TABLE IF NOT EXISTS clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(255)
);

-- Tabela de Imóveis
CREATE TABLE IF NOT EXISTS imoveis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    tipo_imovel VARCHAR(50),
    valor_aluguel DECIMAL(10, 2) NOT NULL,
    disponivel BOOLEAN DEFAULT TRUE
);

-- Tabela de Contratos de Aluguel
CREATE TABLE IF NOT EXISTS contratos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT,
    id_imovel INT,
    data_inicio DATE NOT NULL,
    data_fim DATE NOT NULL,
    valor_aluguel_contrato DECIMAL(10, 2) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    FOREIGN KEY (id_cliente) REFERENCES clientes(id),
    FOREIGN KEY (id_imovel) REFERENCES imoveis(id)
);

-- Inserção de dados de exemplo
-- Clientes
INSERT INTO clientes (nome, cpf, telefone, email) VALUES
('João da Silva', '111.222.333-44', '(47) 99999-1111', 'joao.silva@email.com'),
('Maria Oliveira', '555.666.777-88', '(47) 98888-2222', 'maria.o@email.com'),
('Pedro Souza', '999.888.777-66', '(47) 97777-3333', 'pedro.souza@email.com');

-- Imóveis
INSERT INTO imoveis (descricao, endereco, tipo_imovel, valor_aluguel, disponivel) VALUES
('Apartamento 2 quartos no centro', 'Rua das Flores, 123, Centro, Joinville', 'Apartamento', 1500.00, TRUE),
('Casa com 3 quartos e piscina', 'Avenida Brasil, 1000, América, Joinville', 'Casa', 3200.00, TRUE),
('Kitnet mobiliada perto da faculdade', 'Rua dos Universitários, 50, Universitário, Joinville', 'Kitnet', 950.00, FALSE),
('Apartamento com sacada e churrasqueira', 'Rua XV de Novembro, 800, Glória, Joinville', 'Apartamento', 1800.00, TRUE);

-- Contratos (um ativo e um expirando em breve)
INSERT INTO contratos (id_cliente, id_imovel, data_inicio, data_fim, valor_aluguel_contrato, ativo) VALUES
(2, 3, '2025-01-15', '2026-01-14', 950.00, TRUE),
(1, 1, '2024-09-10', '2025-09-09', 1500.00, TRUE);

-- Atualiza o status dos imóveis alugados
UPDATE imoveis SET disponivel = FALSE WHERE id = 1;
UPDATE imoveis SET disponivel = FALSE WHERE id = 3;

-- ==============================
-- Consulta de contratos ativos
-- ==============================
SELECT con.id AS contrato_id,
       cli.nome AS cliente,
       cli.cpf,
       imo.descricao AS imovel,
       con.data_inicio,
       con.data_fim,
       con.valor_aluguel_contrato
FROM contratos con
JOIN clientes cli ON con.id_cliente = cli.id
JOIN imoveis imo ON con.id_imovel = imo.id
WHERE con.ativo = TRUE;
