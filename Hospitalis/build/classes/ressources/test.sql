-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 23 mai 2024 à 15:38
-- Version du serveur : 8.2.0
-- Version de PHP : 8.3.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `test`
--

-- --------------------------------------------------------

--
-- Structure de la table `facture`
--

DROP TABLE IF EXISTS `facture`;
CREATE TABLE IF NOT EXISTS `facture` (
  `num_facture` varchar(50) NOT NULL,
  `details` varchar(255) DEFAULT NULL,
  `montant` decimal(10,2) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`num_facture`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `code_service` int NOT NULL AUTO_INCREMENT,
  `type_service` varchar(50) DEFAULT NULL,
  `description` text,
  PRIMARY KEY (`code_service`)
) ENGINE=MyISAM AUTO_INCREMENT=126 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `service`
--

INSERT INTO `service` (`code_service`, `type_service`, `description`) VALUES
(123, 'Urgance', 'je vais change'),
(124, 'Administratifs', 'pour le accueil et autre ');

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `id_article` int NOT NULL AUTO_INCREMENT,
  `article` varchar(50) DEFAULT NULL,
  `type` varchar(100) DEFAULT NULL,
  `quantite` mediumtext,
  `date_entre` date DEFAULT NULL,
  PRIMARY KEY (`id_article`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `stock`
--

INSERT INTO `stock` (`id_article`, `article`, `type`, `quantite`, `date_entre`) VALUES
(1, 'A1', 'Médicament', '-109', '2024-05-10'),
(2, 'A2', 'Médicament', '-27', '2024-05-10'),
(3, '', 'Médicament', '47', '2024-05-21'),
(4, '', 'Médicament', '47', '2024-05-21'),
(5, 'ng', 'Matriel', '0', '2024-05-23'),
(6, '', 'Matriel', '47', '2024-05-21'),
(7, 'hj', 'Matriel', '88', '2024-05-21'),
(8, 'MZ', 'Matriel', '14', '2024-05-15'),
(9, 'A1', 'Médicament', '784', '2024-05-01'),
(10, 'hj', 'Médicament', '784', '2024-05-09'),
(11, 'A1', NULL, '144', '2024-05-14'),
(12, 'A1', NULL, '4', '2024-05-14'),
(13, 'er', 'Matriel', '1455', '2024-05-08');

-- --------------------------------------------------------

--
-- Structure de la table `stock_livre`
--

DROP TABLE IF EXISTS `stock_livre`;
CREATE TABLE IF NOT EXISTS `stock_livre` (
  `code_service` int DEFAULT NULL,
  `id_article` int DEFAULT NULL,
  `quantite` mediumtext,
  `date` date DEFAULT NULL,
  KEY `code_service` (`code_service`),
  KEY `id_article` (`id_article`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `stock_livre`
--

INSERT INTO `stock_livre` (`code_service`, `id_article`, `quantite`, `date`) VALUES
(0, 2, '14', '2024-05-08'),
(0, 2, '1', '2024-05-09'),
(0, 1, '4', '2024-05-07'),
(0, 1, '14', '2024-05-15'),
(0, 5, '14', '2024-05-01'),
(124, 1, '14', '2024-05-07'),
(124, 1, '14', '2024-05-14'),
(124, 1, '47', '2024-05-01'),
(123, 1, '1', '2024-05-03'),
(123, 2, '12', '2024-05-14');

-- --------------------------------------------------------

--
-- Structure de la table `traitement`
--

DROP TABLE IF EXISTS `traitement`;
CREATE TABLE IF NOT EXISTS `traitement` (
  `codeTraitement` int NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `description` text,
  `prix` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`codeTraitement`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `matricule` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `nom` varchar(100) DEFAULT NULL,
  `prenom` varchar(100) DEFAULT NULL,
  `role` varchar(100) DEFAULT NULL,
  `date_naissance` date DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `matricule` (`matricule`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`id`, `matricule`, `password`, `nom`, `prenom`, `role`, `date_naissance`, `contact`) VALUES
(1, '12345', 'admin', 'Doe', 'John', 'admin', '1990-01-01', '123456789'),
(2, '54321', 'medecin', 'Smith', 'Jane', 'medecin', '1985-05-15', '987654321'),
(3, '98765', 'finance', 'Johnson', 'Michael', 'finance', '1978-10-20', '555555555'),
(4, '45678', 'stocke', 'Williams', 'Emily', 'stocke', '1995-03-12', '111111111'),
(5, '11111', 'accueil', 'Brown', 'Sarah', 'accueil', '1980-07-08', '999999999');

-- --------------------------------------------------------

--
-- Structure de la table `utilisateurs`
--

DROP TABLE IF EXISTS `utilisateurs`;
CREATE TABLE IF NOT EXISTS `utilisateurs` (
  `Matricule` varchar(50) NOT NULL,
  `Nom` varchar(100) DEFAULT NULL,
  `Prenom` varchar(100) DEFAULT NULL,
  `DateNaissance` date DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `Telephone` varchar(15) DEFAULT NULL,
  `Specialite` varchar(100) DEFAULT NULL,
  `Email` varchar(100) DEFAULT NULL,
  `Role` varchar(50) DEFAULT NULL,
  `Sexe` enum('Homme','Femme') DEFAULT NULL,
  PRIMARY KEY (`Matricule`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `utilisateurs`
--

INSERT INTO `utilisateurs` (`Matricule`, `Nom`, `Prenom`, `DateNaissance`, `Password`, `Telephone`, `Specialite`, `Email`, `Role`, `Sexe`) VALUES
('12345', 'aliou', 'ke', '2020-05-16', '145', '894648935', 'medecin', 'g@gmail.com', 'Administrateur', 'Homme'),
('12564', 'Ali', 'efz', '2024-05-11', '124', '451361894', '78efkfe', ',l,ke', 'Stocke', 'Homme'),
('56884', 'bas', 'zez', '2004-05-14', '123', '118853547', 'medecin', 'ba@gmail.com', 'Finance', 'Homme'),
('75788', 'efz', 'fezf', '2024-05-17', '123', '48965', 'zfef', 'feef', 'Accueil', 'Homme'),
('12454', 'wahab', 'jk', '2009-05-15', '123', '789545415', 'doctor', 'g@gmail.com', 'Médecin', 'Homme');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
