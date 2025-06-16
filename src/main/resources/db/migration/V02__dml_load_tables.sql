-- Usar o schema correto
SET search_path TO ms_cliente;

-- Inserir endereços
INSERT INTO endereco (id, rua, numero, cep, complemento, bairro, cidade, data_criacao, data_ultima_alteracao) VALUES
    (uuid_generate_v4(), 'Av. Paulista', 1000, '01311000', 'Apto 101', 'Bela Vista', 'São Paulo', CURRENT_TIMESTAMP, NULL),
    (uuid_generate_v4(), 'Rua das Palmeiras', 200, '04045000', NULL, 'Vila Mariana', 'São Paulo', CURRENT_TIMESTAMP, NULL),
    (uuid_generate_v4(), 'Rua XV de Novembro', 300, '80020010', 'Casa 2', 'Centro', 'Curitiba', CURRENT_TIMESTAMP, NULL),
    (uuid_generate_v4(), 'Rua 7 de Setembro', 400, '70000000', NULL, 'Asa Sul', 'Brasília', CURRENT_TIMESTAMP, NULL),
    (uuid_generate_v4(), 'Rua Amazonas', 150, '69000000', 'Casa Verde', 'Centro', 'Manaus', CURRENT_TIMESTAMP, NULL);

-- Inserir clientes
INSERT INTO cliente (id, nome, cpf, data_nascimento, endereco, data_criacao, data_ultima_alteracao) VALUES
    (uuid_generate_v4(), 'João Pereira', '12345678901', '1985-03-25', (SELECT id FROM endereco WHERE rua = 'Av. Paulista'), CURRENT_TIMESTAMP, NULL),
    (uuid_generate_v4(), 'Maria Oliveira', '12345678902', '1992-11-10', (SELECT id FROM endereco WHERE rua = 'Rua das Palmeiras'), CURRENT_TIMESTAMP, NULL),
    (uuid_generate_v4(), 'Carlos Souza', '12345678903', '1978-06-15', (SELECT id FROM endereco WHERE rua = 'Rua XV de Novembro'), CURRENT_TIMESTAMP, NULL),
    (uuid_generate_v4(), 'Ana Lima', '12345678904', '1990-02-20', (SELECT id FROM endereco WHERE rua = 'Rua 7 de Setembro'), CURRENT_TIMESTAMP, NULL),
    (uuid_generate_v4(), 'Pedro Almeida', '12345678905', '1983-09-12', (SELECT id FROM endereco WHERE rua = 'Rua Amazonas'), CURRENT_TIMESTAMP, NULL);
