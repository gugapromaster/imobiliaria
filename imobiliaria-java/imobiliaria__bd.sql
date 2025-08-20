-- Criação do banco de dados (se não existir)
CREATE DATABASE IF NOT EXISTS imobiliaria_db;

-- Seleciona o banco de dados para uso
USE imobiliaria_db;

-- Tabela de Clientes
CREATE TABLE clientes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    telefone VARCHAR(20),
    email VARCHAR(255)
);

-- Tabela de Imóveis
CREATE TABLE imoveis (
    id INT AUTO_INCREMENT PRIMARY KEY,
    descricao VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL,
    tipo_imovel VARCHAR(50),
    valor_aluguel DECIMAL(10, 2) NOT NULL,
    disponivel BOOLEAN DEFAULT TRUE
);

-- Tabela de Contratos de Aluguel
CREATE TABLE contratos (
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
-- Contrato para a kitnet (imovel 3) com a cliente Maria (cliente 2)
INSERT INTO contratos (id_cliente, id_imovel, data_inicio, data_fim, valor_aluguel_contrato, ativo) VALUES
(2, 3, '2025-01-15', '2026-01-14', 950.00, TRUE);

-- Contrato que vai expirar em breve (para teste do relatório)
-- Supondo que a data atual é ~20/08/2025
INSERT INTO contratos (id_cliente, id_imovel, data_inicio, data_fim, valor_aluguel_contrato, ativo) VALUES
(1, 1, '2024-09-10', '2025-09-09', 1500.00, TRUE);

-- Atualiza o status dos imóveis alugados
UPDATE imoveis SET disponivel = FALSE WHERE id = 1;
UPDATE imoveis SET disponivel = FALSE WHERE id = 3;