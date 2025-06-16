-- Criação do schema
CREATE SCHEMA IF NOT EXISTS ms_cliente;

-- Garante que tudo será criado dentro do schema ms-cliente
SET search_path TO ms_cliente;

-- Criação da extensão para UUID
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Tabela de endereços
CREATE TABLE endereco (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    rua VARCHAR(255) NOT NULL,
    numero INTEGER NOT NULL,
    cep VARCHAR(8) NOT NULL,
    complemento VARCHAR(255),
    bairro VARCHAR(255) NOT NULL,
    cidade VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_ultima_alteracao TIMESTAMP
);

-- Tabela de clientes
CREATE TABLE cliente (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    nome VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL,
    data_nascimento DATE NOT NULL,
    endereco_id UUID NOT NULL,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_ultima_alteracao TIMESTAMP,

    CONSTRAINT uk_cliente_cpf UNIQUE (cpf),
    CONSTRAINT fk_cliente_endereco FOREIGN KEY (endereco_id) REFERENCES endereco(id)
);

-- Índice adicional no CPF para facilitar buscas
CREATE INDEX idx_cliente_cpf ON cliente (cpf);
