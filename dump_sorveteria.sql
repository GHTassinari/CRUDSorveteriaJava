CREATE DATABASE sorveteria;
use sorveteria;
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `CPF` varchar(14) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `CPF` (`CPF`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `fornecedor`
--

DROP TABLE IF EXISTS `fornecedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fornecedor` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `contato` varchar(255) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fornecedor`
--

LOCK TABLES `fornecedor` WRITE;
/*!40000 ALTER TABLE `fornecedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `fornecedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funcionario`
--

DROP TABLE IF EXISTS `funcionario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `funcionario` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `cargo` varchar(255) DEFAULT NULL,
  `salario` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funcionario`
--

LOCK TABLES `funcionario` WRITE;
/*!40000 ALTER TABLE `funcionario` DISABLE KEYS */;
/*!40000 ALTER TABLE `funcionario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loja`
--

DROP TABLE IF EXISTS `loja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loja` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loja`
--

LOCK TABLES `loja` WRITE;
/*!40000 ALTER TABLE `loja` DISABLE KEYS */;
/*!40000 ALTER TABLE `loja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `maquinadesorvete`
--

DROP TABLE IF EXISTS `maquinadesorvete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `maquinadesorvete` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `modelo` varchar(255) NOT NULL,
  `capacidade` double NOT NULL,
  `data_aquisicao` date NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `maquinadesorvete`
--

LOCK TABLES `maquinadesorvete` WRITE;
/*!40000 ALTER TABLE `maquinadesorvete` DISABLE KEYS */;
/*!40000 ALTER TABLE `maquinadesorvete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pagamento` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `valor` double NOT NULL,
  `metodo` varchar(255) NOT NULL,
  `data_pagamento` datetime NOT NULL,
  `tipo` varchar(255) NOT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagamento`
--

LOCK TABLES `pagamento` WRITE;
/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sorvete`
--

DROP TABLE IF EXISTS `sorvete`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sorvete` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sabor` varchar(255) NOT NULL,
  `tamanho` double NOT NULL,
  `preco` double NOT NULL,
  `quantidade` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sorvete`
--

LOCK TABLES `sorvete` WRITE;
/*!40000 ALTER TABLE `sorvete` DISABLE KEYS */;
/*!40000 ALTER TABLE `sorvete` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `data_criacao` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venda`
--

DROP TABLE IF EXISTS `venda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venda` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `id_cliente` bigint(20) unsigned NOT NULL,
  `id_funcionario` bigint(20) unsigned NOT NULL,
  `id_fornecedor` bigint(20) unsigned NOT NULL,
  `id_sorvete` bigint(20) unsigned NOT NULL,
  `id_maquinaDeSorvete` bigint(20) unsigned NOT NULL,
  `id_pagamento` bigint(20) unsigned NOT NULL,
  `id_loja` bigint(20) unsigned NOT NULL,
  `data_venda` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cliente` (`id_cliente`),
  KEY `id_funcionario` (`id_funcionario`),
  KEY `id_fornecedor` (`id_fornecedor`),
  KEY `id_sorvete` (`id_sorvete`),
  KEY `id_maquinaDeSorvete` (`id_maquinaDeSorvete`),
  KEY `id_pagamento` (`id_pagamento`),
  KEY `id_loja` (`id_loja`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`),
  CONSTRAINT `venda_ibfk_2` FOREIGN KEY (`id_funcionario`) REFERENCES `funcionario` (`id`),
  CONSTRAINT `venda_ibfk_3` FOREIGN KEY (`id_fornecedor`) REFERENCES `fornecedor` (`id`),
  CONSTRAINT `venda_ibfk_4` FOREIGN KEY (`id_sorvete`) REFERENCES `sorvete` (`id`),
  CONSTRAINT `venda_ibfk_5` FOREIGN KEY (`id_maquinaDeSorvete`) REFERENCES `maquinadesorvete` (`id`),
  CONSTRAINT `venda_ibfk_6` FOREIGN KEY (`id_pagamento`) REFERENCES `pagamento` (`id`),
  CONSTRAINT `venda_ibfk_7` FOREIGN KEY (`id_loja`) REFERENCES `loja` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venda`
--

LOCK TABLES `venda` WRITE;


/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;
UNLOCK TABLES;

DROP TABLE IF EXISTS avaliacao;

CREATE TABLE avaliacao (
  id bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  venda_id bigint(20) unsigned NOT NULL,
  descricao text DEFAULT NULL,
  nota int(11) NOT NULL,
  PRIMARY KEY (id),
  KEY venda_id (venda_id),
  CONSTRAINT avaliacao_ibfk_1 FOREIGN KEY (venda_id) REFERENCES venda (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `avaliacao`
--

LOCK TABLES `avaliacao` WRITE;
/*!40000 ALTER TABLE `avaliacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `avaliacao` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

INSERT INTO cliente (nome, CPF, telefone, email) VALUES
('Ana Silva', '123.456.789-00', '123456789', 'ana.silva@email.com'),
('João Santos', '987.654.321-00', '987654321', 'joao.santos@email.com'),
('Maria Oliveira', '111.222.333-44', '111222333', 'maria.oliveira@email.com'),
('Pedro Costa', '555.666.777-88', '555666777', 'pedro.costa@email.com'),
('Lucas Pereira', '444.555.666-77', '444555666', 'lucas.pereira@email.com'),
('Fernanda Souza', '222.333.444-55', '222333444', 'fernanda.souza@email.com'),
('Mariana Lima', '333.444.555-66', '333444555', 'mariana.lima@email.com'),
('Ricardo Almeida', '666.777.888-99', '666777888', 'ricardo.almeida@email.com'),
('Juliana Ferreira', '888.999.000-11', '888999000', 'juliana.ferreira@email.com'),
('Rafael Martins', '999.000.111-22', '999000111', 'rafael.martins@email.com');

INSERT INTO fornecedor (nome, contato, endereco) VALUES
('Fornecedor A', 'Contato A', 'Rua A, 123'),
('Fornecedor B', 'Contato B', 'Rua B, 456'),
('Fornecedor C', 'Contato C', 'Rua C, 789'),
('Fornecedor D', 'Contato D', 'Rua D, 101'),
('Fornecedor E', 'Contato E', 'Rua E, 202'),
('Fornecedor F', 'Contato F', 'Rua F, 303'),
('Fornecedor G', 'Contato G', 'Rua G, 404'),
('Fornecedor H', 'Contato H', 'Rua H, 505'),
('Fornecedor I', 'Contato I', 'Rua I, 606'),
('Fornecedor J', 'Contato J', 'Rua J, 707');

INSERT INTO funcionario (nome, cargo, salario) VALUES
('Carlos Mendes', 'Vendedor', 2500.00),
('Fernanda Lima', 'Caixa', 2200.00),
('Roberto Almeida', 'Gerente', 3500.00),
('Camila Silva', 'Atendente', 2100.00),
('Eduardo Costa', 'Supervisor', 3000.00),
('Juliana Rocha', 'Estoquista', 2300.00),
('Bruno Pereira', 'Assistente', 2000.00),
('Tatiane Santos', 'Recepcionista', 2400.00),
('Leonardo Fernandes', 'Analista', 2800.00),
('Sofia Oliveira', 'Coordenadora', 3200.00);

INSERT INTO loja (nome, endereco, telefone) VALUES
('Loja Central', 'Avenida Principal, 1000', '111111111'),
('Loja Norte', 'Avenida Norte, 200', '222222222'),
('Loja Sul', 'Avenida Sul, 300', '333333333'),
('Loja Leste', 'Rua Leste, 400', '444444444'),
('Loja Oeste', 'Rua Oeste, 500', '555555555'),
('Loja Jardim', 'Rua Jardim, 600', '666666666'),
('Loja Campo', 'Avenida Campo, 700', '777777777'),
('Loja Praia', 'Avenida Praia, 800', '888888888'),
('Loja Vila', 'Rua Vila, 900', '999999999'),
('Loja Estação', 'Avenida Estação, 100', '000000000');

INSERT INTO maquinadesorvete (modelo, capacidade, data_aquisicao, status) VALUES
('Modelo A', 100.0, '2022-01-01', 'Operacional'),
('Modelo B', 150.0, '2022-02-01', 'Manutenção'),
('Modelo C', 200.0, '2022-03-01', 'Operacional'),
('Modelo D', 250.0, '2022-04-01', 'Inativo'),
('Modelo E', 300.0, '2022-05-01', 'Operacional'),
('Modelo F', 350.0, '2022-06-01', 'Manutenção'),
('Modelo G', 400.0, '2022-07-01', 'Operacional'),
('Modelo H', 450.0, '2022-08-01', 'Inativo'),
('Modelo I', 500.0, '2022-09-01', 'Operacional'),
('Modelo J', 550.0, '2022-10-01', 'Manutenção');

INSERT INTO pagamento (valor, metodo, data_pagamento, tipo, descricao) VALUES
(100.00, 'Cartão de Crédito', '2024-01-01 10:00:00', 'Venda', 'Pagamento de sorvetes'),
(150.00, 'Débito', '2024-01-02 11:00:00', 'Venda', 'Pagamento de sorvetes'),
(200.00, 'Dinheiro', '2024-01-03 12:00:00', 'Venda', 'Pagamento de sorvetes'),
(250.00, 'PIX', '2024-01-04 13:00:00', 'Venda', 'Pagamento de sorvetes'),
(300.00, 'Cartão de Crédito', '2024-01-05 14:00:00', 'Venda', 'Pagamento de sorvetes'),
(350.00, 'Débito', '2024-01-06 15:00:00', 'Venda', 'Pagamento de sorvetes'),
(400.00, 'Dinheiro', '2024-01-07 16:00:00', 'Venda', 'Pagamento de sorvetes'),
(450.00, 'PIX', '2024-01-08 17:00:00', 'Venda', 'Pagamento de sorvetes'),
(500.00, 'Cartão de Crédito', '2024-01-09 18:00:00', 'Venda', 'Pagamento de sorvetes'),
(550.00, 'Débito', '2024-01-10 19:00:00', 'Venda', 'Pagamento de sorvetes');

INSERT INTO sorvete (sabor, tamanho, preco, quantidade) VALUES
('Chocolate', 200.0, 10.00, 50),
('Baunilha', 250.0, 12.00, 60),
('Morango', 150.0, 11.00, 70),
('Manga', 300.0, 15.00, 40),
('Maracujá', 350.0, 14.00, 45),
('Café', 200.0, 13.00, 55),
('Doce de Leite', 250.0, 16.00, 50),
('Pistache', 300.0, 18.00, 35),
('Menta', 150.0, 12.00, 65),
('Limão', 250.0, 11.00, 60);

INSERT INTO venda (id_cliente, id_funcionario, id_fornecedor, id_sorvete, id_maquinaDeSorvete, id_pagamento, id_loja, data_venda) VALUES
(17, 1, 1, 1, 1, 1, 1, '2024-01-01 10:00:00'),
(18, 2, 2, 2, 2, 2, 2, '2024-01-02 11:00:00'),
(19, 3, 3, 3, 3, 3, 3, '2024-01-03 12:00:00'),
(20, 4, 4, 4, 4, 4, 4, '2024-01-04 13:00:00'),
(21, 5, 5, 5, 5, 5, 5, '2024-01-05 14:00:00'),
(22, 6, 6, 6, 6, 6, 6, '2024-01-06 15:00:00'),
(23, 7, 7, 7, 7, 7, 7, '2024-01-07 16:00:00'),
(24, 8, 8, 8, 8, 8, 8, '2024-01-08 17:00:00'),
(25, 9, 9, 9, 9, 9, 9, '2024-01-09 18:00:00'),
(26, 10, 10, 10, 10, 10, 10, '2024-01-10 19:00:00');

INSERT INTO avaliacao (venda_id, descricao, nota) VALUES
(1, 'Ótimo atendimento e sabor excelente!', 5),
(2, 'O sorvete estava um pouco derretido.', 3),
(3, 'Produto de alta qualidade, recomendo!', 4),
(4, 'Serviço rápido, mas o sabor estava abaixo do esperado.', 2),
(5, 'Muito bom, vou comprar novamente.', 4),
(6, 'Gostei muito, principalmente do sabor de morango.', 5),
(7, 'O atendimento poderia ser mais atencioso.', 3),
(8, 'Excelente sorvete e bom atendimento.', 5),
(9, 'O preço está um pouco alto, mas a qualidade é boa.', 4),
(10, 'Não gostei do sabor, achei muito doce.', 2);
