-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 23 mai 2024 à 21:30
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

--
-- Déchargement des données de la table `facture`
--

INSERT INTO `facture` (`num_facture`, `details`, `montant`, `date`, `status`) VALUES
('FACT240523203236', 'Matricule Médecin Infos\nMAlI\nPatient infos \nNom:Keita\nPrénom:Badra\nCNI:YDKO5', 350.00, '2024-05-23', 'Attent'),
('FACT240523193156', 'Matricule Médecin Infos\nALQ2\nPatient infos \nNom:Modi\nPrénom:sd\nCNI:654', 280.00, '2024-05-23', 'payé');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `facturespayees`
-- (Voir ci-dessous la vue réelle)
--
DROP VIEW IF EXISTS `facturespayees`;
CREATE TABLE IF NOT EXISTS `facturespayees` (
`num_facture` varchar(50)
,`details` varchar(255)
,`montant` decimal(10,2)
,`date` date
,`status` varchar(15)
);

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `traitement`
--

INSERT INTO `traitement` (`codeTraitement`, `type`, `nom`, `description`, `prix`) VALUES
(1, 'Consultation générale', 'Consultation médicale générale', 'Examen médical de routine pour évaluer la santé globale du patient', 50.00),
(2, 'Consultation spécialisée', 'Consultation en neurologie', 'Examen médical avec un spécialiste des troubles neurologiques', 80.00),
(3, 'Consultation générale', 'Consultation pédiatrique', 'Examen médical pour les enfants', 60.00),
(4, 'Consultation spécialisée', 'Consultation en cardiologie', 'Examen médical avec un spécialiste des problèmes cardiaques', 90.00),
(5, 'Consultation générale', 'Consultation en gynécologie', 'Examen médical pour les problèmes de santé des femmes', 70.00),
(6, 'Consultation spécialisée', 'Consultation en dermatologie', 'Examen médical avec un spécialiste des problèmes de peau', 75.00),
(7, 'Consultation générale', 'Consultation en médecine interne', 'Examen médical pour les problèmes de santé internes', 65.00),
(8, 'Consultation spécialisée', 'Consultation en orthopédie', 'Examen médical avec un spécialiste des problèmes musculo-squelettiques', 85.00),
(9, 'Consultation générale', 'Consultation en médecine familiale', 'Examen médical pour les membres de la famille', 55.00),
(10, 'Consultation spécialisée', 'Consultation en ophtalmologie', 'Examen médical avec un spécialiste des problèmes de vision', 80.00),
(11, 'Consultation générale', 'Consultation en médecine sportive', 'Examen médical pour les athlètes et les amateurs de sport', 75.00),
(12, 'Consultation spécialisée', 'Consultation en gastro-entérologie', 'Examen médical avec un spécialiste des troubles gastro-intestinaux', 95.00),
(13, 'Consultation générale', 'Consultation en santé mentale', 'Examen médical pour les problèmes de santé mentale', 60.00),
(14, 'Consultation spécialisée', 'Consultation en endocrinologie', 'Examen médical avec un spécialiste des troubles hormonaux', 85.00),
(15, 'Consultation générale', 'Consultation en soins primaires', 'Examen médical de routine pour les soins de santé de base', 50.00),
(16, 'Consultation spécialisée', 'Consultation en urologie', 'Examen médical avec un spécialiste des troubles urinaires et reproductifs', 90.00);

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

-- --------------------------------------------------------

--
-- Structure de la vue `facturespayees`
--
DROP TABLE IF EXISTS `facturespayees`;

DROP VIEW IF EXISTS `facturespayees`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `facturespayees`  AS SELECT `facture`.`num_facture` AS `num_facture`, `facture`.`details` AS `details`, `facture`.`montant` AS `montant`, `facture`.`date` AS `date`, `facture`.`status` AS `status` FROM `facture` WHERE (`facture`.`status` = 'payé') ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
