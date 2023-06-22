-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 19/06/2023 às 20:32
-- Versão do servidor: 8.0.31
-- Versão do PHP: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `gerenciador`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `hospedagem`
--

DROP TABLE IF EXISTS `hospedagem`;
CREATE TABLE IF NOT EXISTS `hospedagem` (
  `idHospedagem` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `local` varchar(100) NOT NULL,
  `checkin` date DEFAULT NULL,
  PRIMARY KEY (`idHospedagem`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Despejando dados para a tabela `hospedagem`
--

INSERT INTO `hospedagem` (`idHospedagem`, `nome`, `tipo`, `local`, `checkin`) VALUES
(1, 'Cabana Verde', 'hotel', 'Conservatória, centro', '2023-07-06');

-- --------------------------------------------------------

--
-- Estrutura para tabela `pessoa`
--

DROP TABLE IF EXISTS `pessoa`;
CREATE TABLE IF NOT EXISTS `pessoa` (
  `idPessoa` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cpf` varchar(11) NOT NULL,
  `email` varchar(100) NOT NULL,
  `telefone` varchar(11) NOT NULL,
  PRIMARY KEY (`idPessoa`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Despejando dados para a tabela `pessoa`
--

INSERT INTO `pessoa` (`idPessoa`, `nome`, `cpf`, `email`, `telefone`) VALUES
(1, 'Ricardo Augusto Franco', '06535244989', 'ricardoaugustofranco@hotmail.com', '49999628027'),
(2, 'Nícolas Matheus Beckert dos Santos Ramos', '09628625926', 'nicolasmatheusbecekert@gmail.com', '49989205587'),
(3, 'Rafael Dalmagro', '07116398929', 'rafa.dalmagro@hotmail.com', '49999995673');

-- --------------------------------------------------------

--
-- Estrutura para tabela `transporte`
--

DROP TABLE IF EXISTS `transporte`;
CREATE TABLE IF NOT EXISTS `transporte` (
  `idTransporte` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `local` varchar(100) NOT NULL,
  `diaViagem` date NOT NULL,
  PRIMARY KEY (`idTransporte`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Despejando dados para a tabela `transporte`
--

INSERT INTO `transporte` (`idTransporte`, `nome`, `tipo`, `local`, `diaViagem`) VALUES
(1, 'Voo Gol', 'aviao', 'XAP', '2023-07-17'),
(2, 'OnibusSP-VR', 'Onibus', 'Terminal Tiête', '2023-07-03');

-- --------------------------------------------------------

--
-- Estrutura para tabela `viagem`
--

DROP TABLE IF EXISTS `viagem`;
CREATE TABLE IF NOT EXISTS `viagem` (
  `idViagem` int NOT NULL AUTO_INCREMENT,
  `pessoa` int NOT NULL,
  `transporte` int NOT NULL,
  `hospedagem` int NOT NULL,
  PRIMARY KEY (`idViagem`),
  KEY `pessoa_idx` (`pessoa`),
  KEY `transporte_idx` (`transporte`),
  KEY `hospedagem_idx` (`hospedagem`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Despejando dados para a tabela `viagem`
--

INSERT INTO `viagem` (`idViagem`, `pessoa`, `transporte`, `hospedagem`) VALUES
(1, 1, 1, 1);

--
-- Restrições para tabelas despejadas
--

--
-- Restrições para tabelas `viagem`
--
ALTER TABLE `viagem`
  ADD CONSTRAINT `hospedagem` FOREIGN KEY (`hospedagem`) REFERENCES `hospedagem` (`idHospedagem`),
  ADD CONSTRAINT `pessoa` FOREIGN KEY (`pessoa`) REFERENCES `pessoa` (`idPessoa`),
  ADD CONSTRAINT `transporte` FOREIGN KEY (`transporte`) REFERENCES `transporte` (`idTransporte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
