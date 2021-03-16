-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 12, 2019 at 08:21 PM
-- Server version: 5.7.19
-- PHP Version: 5.6.31

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `codebreakers_integration`
--

-- --------------------------------------------------------

--
-- Table structure for table `activite`
--

DROP TABLE IF EXISTS `activite`;
CREATE TABLE IF NOT EXISTS `activite` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `passion_id` int(11) DEFAULT NULL,
  `nom_atelier` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `difficulty` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbre_pts` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B8755515D7E406D0` (`passion_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `activite`
--

INSERT INTO `activite` (`id`, `passion_id`, `nom_atelier`, `difficulty`, `nbre_pts`) VALUES
(9, 1, 'Music activity1', 'Facile', 0),
(10, 1, 'Music activity2', 'Moyen', 0),
(11, 1, 'Music activity3', 'Difficile', 0);

-- --------------------------------------------------------

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
CREATE TABLE IF NOT EXISTS `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `body` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `nom_image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `User_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_23A0E6668D3EA09` (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `article`
--

INSERT INTO `article` (`id`, `title`, `description`, `body`, `created_at`, `nom_image`, `User_id`) VALUES
(1, 'afef', 'bella afef', 'a7la foufa', '2019-02-19 21:19:01', '38725337_2217232588512166_2820723310701051904_n.jpg', 6),
(3, 'xxx', 'xxx', 'xxxmodiiiif', '2019-02-19 21:46:23', '42846872_295831014353070_3815648009463529472_n.jpg', 6),
(4, 'afef', 'bien etre', 'manger lugume', '2019-02-25 22:09:52', '43663940_1993166160730209_4995076746551230464_n.jpg', 3),
(5, 'afef2', 'bella afef', 'a7la foufa', '2019-02-26 10:54:21', '42846872_295831014353070_3815648009463529472_n.jpg', 15),
(6, 'afef2', 'bella afefazerty', 'a7la foufa', '2019-02-26 10:56:10', 'AhaShare.com.txt', 15),
(7, 'afef2', 'bella afefazerty', 'a7la foufa', '2019-02-26 11:05:30', 'AhaShare.com.txt', 15),
(8, 'afef2', 'bella afefazerty', 'a7la foufa', '2019-02-26 11:06:15', 'AhaShare.com.txt', 15),
(9, 'afef3', 'bien etre', 'a7la foufa', '2019-02-26 11:06:31', 'article.txt', 15),
(10, 'afef3', 'bien etre', 'a7la foufa', '2019-02-26 11:07:50', 'article.txt', 15),
(11, 'afef3', 'bien etre', 'a7la foufa', '2019-02-26 11:08:08', 'article.txt', 15),
(12, 'afef3', 'bien etre', 'a7la foufa', '2019-02-26 11:08:31', 'article.txt', 15),
(13, 'azze', 'bella afef', 'manger lugume', '2019-02-26 11:08:57', 'AhaShare.com.txt', 15),
(14, 'azze', 'bella afef', 'manger lugume', '2019-02-26 11:10:18', 'AhaShare.com.txt', 15),
(15, 'azze', 'bella afef', 'manger lugume', '2019-02-26 11:11:22', 'AhaShare.com.txt', 15),
(16, 'azerty12', 'bella afef', 'a7la foufa', '2019-02-26 11:11:52', 'csharp.txt', 15),
(17, 'afef2zzz', 'bien etre', 'a7la foufa', '2019-02-26 11:12:19', 'borjinterieur.jpg', 15),
(18, 'afef2zzz', 'bien etre', 'a7la foufa', '2019-02-26 11:20:42', 'borjinterieur.jpg', 15),
(19, 'afef3', 'bien etre', 'manger lugume', '2019-02-26 11:21:25', 'AhaShare.com.txt', 15),
(20, 'afef3', 'bien etre', 'manger lugume', '2019-02-26 11:30:20', 'AhaShare.com.txt', 15),
(21, 'afef3', 'bien etre', 'manger lugume', '2019-02-26 11:30:56', 'AhaShare.com.txt', 15),
(22, 'afef3', 'bien etre', 'manger lugume', '2019-02-26 11:32:06', 'AhaShare.com.txt', 15),
(23, 'acjref', 'chouchou', 'ta77fouun', '2019-02-26 14:29:59', '32756127_1758463414214772_4014169249084866560_n.jpg', 15);

-- --------------------------------------------------------

--
-- Table structure for table `association`
--

DROP TABLE IF EXISTS `association`;
CREATE TABLE IF NOT EXISTS `association` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_assoc_id` int(11) DEFAULT NULL,
  `name_assoc` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nbre_membre` int(11) NOT NULL,
  `description_assoc` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type_assoc` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_FD8521CCAC425E03` (`client_assoc_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `association`
--

INSERT INTO `association` (`id`, `client_assoc_id`, `name_assoc`, `nbre_membre`, `description_assoc`, `type_assoc`) VALUES
(3, 5, 'kdnajdz', 25, 'skk', 'ss\r\n'),
(4, 8, 'Make Animals Happy', 55, 'help animals', 'Animaux'),
(5, 8, 'hhhh', 12, 'hh', 'Nature');

-- --------------------------------------------------------

--
-- Table structure for table `besoin_argent`
--

DROP TABLE IF EXISTS `besoin_argent`;
CREATE TABLE IF NOT EXISTS `besoin_argent` (
  `idBesoin` int(11) NOT NULL AUTO_INCREMENT,
  `titreBesoinArgent` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idUserInNeed` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `idMaisonRetraite` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `disponibiliteBesoin` tinyint(1) NOT NULL,
  `montantBesoin` double NOT NULL,
  PRIMARY KEY (`idBesoin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `besoin_objet`
--

DROP TABLE IF EXISTS `besoin_objet`;
CREATE TABLE IF NOT EXISTS `besoin_objet` (
  `idBesoin` int(11) NOT NULL AUTO_INCREMENT,
  `titreBesoinObjet` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idUserInNeed` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `idMaisonRetraite` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `disponibiliteBesoin` tinyint(1) NOT NULL,
  `typeBesoinObjet` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`idBesoin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
CREATE TABLE IF NOT EXISTS `booking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `begin_at` datetime NOT NULL,
  `end_at` datetime NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `bricolage`
--

DROP TABLE IF EXISTS `bricolage`;
CREATE TABLE IF NOT EXISTS `bricolage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_bri_id` int(11) DEFAULT NULL,
  `url_bri` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tire_bri` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description_bri` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_DE443E24FD645345` (`client_bri_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `bricolage`
--

INSERT INTO `bricolage` (`id`, `client_bri_id`, `url_bri`, `tire_bri`, `description_bri`) VALUES
(4, 2, 'br2.mp4', 'Bri1', 'je souhaite que vous aimez ma creation de jeens\r\n'),
(5, 2, 'br1.mp4', 'BRi2', 'hhh'),
(6, 2, 'br3.mp4', 'bri3', 'xDD'),
(7, 2, 'br3.mp4', 'Bri4', 'you'),
(8, 2, 'br1.mp4', 'Bri5', 'hello'),
(9, 2, 'br1.mp4', 'Bri6', 'uu'),
(26, 24, 'br3.mp4', 'oooo', 'ooooo');

-- --------------------------------------------------------

--
-- Table structure for table `cat_consoma`
--

DROP TABLE IF EXISTS `cat_consoma`;
CREATE TABLE IF NOT EXISTS `cat_consoma` (
  `idcat` int(11) NOT NULL AUTO_INCREMENT,
  `type_consoma` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nom_image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idcat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
CREATE TABLE IF NOT EXISTS `commande` (
  `id_commande_p` int(11) NOT NULL,
  `date_commande_p` date NOT NULL,
  `nb_total_p` int(11) NOT NULL,
  PRIMARY KEY (`id_commande_p`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `comment_bri`
--

DROP TABLE IF EXISTS `comment_bri`;
CREATE TABLE IF NOT EXISTS `comment_bri` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bricolage_id` int(11) DEFAULT NULL,
  `Comment` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date` date NOT NULL,
  `userid_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_72E744E46971B526` (`bricolage_id`),
  KEY `IDX_72E744E458E0A285` (`userid_id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `comment_bri`
--

INSERT INTO `comment_bri` (`id`, `bricolage_id`, `Comment`, `date`, `userid_id`) VALUES
(12, 5, 'hhhhhh', '2019-02-24', 2),
(13, 5, 'xDDDDDDDDDDDDDDDDD', '2019-02-24', 2),
(19, 8, 'uuuuuuuuu', '2019-02-26', 2),
(21, 4, 'jjj\r\n', '2019-02-26', 2),
(22, 5, 'dadaad', '2019-02-27', 2),
(24, 4, ',n;znzlkf', '2019-02-28', 8);

-- --------------------------------------------------------

--
-- Table structure for table `compagne_don`
--

DROP TABLE IF EXISTS `compagne_don`;
CREATE TABLE IF NOT EXISTS `compagne_don` (
  `idCompagneDon` int(11) NOT NULL AUTO_INCREMENT,
  `titreCompagneDon` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateFinCompagneDon` date DEFAULT NULL,
  `descriptionCompagneDon` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nombreParticipantsCompagneDon` int(11) NOT NULL,
  PRIMARY KEY (`idCompagneDon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `consommation`
--

DROP TABLE IF EXISTS `consommation`;
CREATE TABLE IF NOT EXISTS `consommation` (
  `idconsoma` int(11) NOT NULL AUTO_INCREMENT,
  `cat_consoma_id` int(11) DEFAULT NULL,
  `nom_consoma` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adress_consoma` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `email_consoma` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `num_consoma` int(11) NOT NULL,
  `prix_r` double NOT NULL,
  `nom_image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idconsoma`),
  KEY `IDX_F993F0A21D194889` (`cat_consoma_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `consommation`
--

INSERT INTO `consommation` (`idconsoma`, `cat_consoma_id`, `nom_consoma`, `adress_consoma`, `email_consoma`, `num_consoma`, `prix_r`, `nom_image`) VALUES
(1, NULL, '465', '4', '5456', 4, 5645, 'thumb-1920-562108.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `demandeservice`
--

DROP TABLE IF EXISTS `demandeservice`;
CREATE TABLE IF NOT EXISTS `demandeservice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_demande` date NOT NULL,
  `Societe_id` int(11) DEFAULT NULL,
  `User_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_95570222B3AA76D3` (`Societe_id`),
  KEY `IDX_9557022268D3EA09` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `don_besoin`
--

DROP TABLE IF EXISTS `don_besoin`;
CREATE TABLE IF NOT EXISTS `don_besoin` (
  `id_don_besoin` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `id_maison_retraite` int(11) NOT NULL,
  `disponibilite` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_don_besoin`),
  KEY `IDX_987AE06CA76ED395` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `don_besoin_argent`
--

DROP TABLE IF EXISTS `don_besoin_argent`;
CREATE TABLE IF NOT EXISTS `don_besoin_argent` (
  `id_don_besoin` int(11) NOT NULL AUTO_INCREMENT,
  `id_maison_retraite` int(11) NOT NULL,
  `disponibilite` tinyint(1) NOT NULL,
  `montant_argent` double NOT NULL,
  PRIMARY KEY (`id_don_besoin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `don_besoin_objet`
--

DROP TABLE IF EXISTS `don_besoin_objet`;
CREATE TABLE IF NOT EXISTS `don_besoin_objet` (
  `id_don_besoin` int(11) NOT NULL AUTO_INCREMENT,
  `id_maison_retraite` int(11) NOT NULL,
  `disponibilite` tinyint(1) NOT NULL,
  `type_objet` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_don_besoin`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `don_pot_compagne`
--

DROP TABLE IF EXISTS `don_pot_compagne`;
CREATE TABLE IF NOT EXISTS `don_pot_compagne` (
  `id_pot_compagne` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `date_fin_pot` date NOT NULL,
  `ddescription_pot` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id_pot_compagne`),
  KEY `IDX_AD6885ABA76ED395` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `evenement`
--

DROP TABLE IF EXISTS `evenement`;
CREATE TABLE IF NOT EXISTS `evenement` (
  `id_event` int(11) NOT NULL AUTO_INCREMENT,
  `event_categorie_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `nom_event` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description_event` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_debut_event` date NOT NULL,
  `date_fin_event` date NOT NULL,
  `heure_debut_event` time NOT NULL,
  `heure_fin_event` time NOT NULL,
  `adresse_event` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `privacy_event` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `image_event` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prix_event` double NOT NULL,
  PRIMARY KEY (`id_event`),
  KEY `IDX_B26681E51E6EDDE` (`event_categorie_id`),
  KEY `IDX_B26681EA76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `evenement`
--

INSERT INTO `evenement` (`id_event`, `event_categorie_id`, `user_id`, `nom_event`, `description_event`, `date_debut_event`, `date_fin_event`, `heure_debut_event`, `heure_fin_event`, `adresse_event`, `privacy_event`, `image_event`, `prix_event`) VALUES
(1, 3, 5, 'Spectacle Hadhra', 'spectacle hadhra tunis', '2019-02-26', '2019-02-26', '18:00:00', '21:00:00', 'Tunis', 'Privé', 'bf2f1a1bf57e21707bdc995fdbbaae48.jpeg', 25),
(2, 6, 5, 'Spectacle Hadhra', 'spectacle hadhra tunis', '2019-02-06', '2019-02-07', '18:00:00', '21:00:00', 'Tunis', 'Privé', 'bf2f1a1bf57e21707bdc995fdbbaae48.jpeg', 25),
(3, 6, 5, 'Spectacle Hadhra', 'spectacle hadhra tunis', '2019-02-11', '2019-02-12', '18:00:00', '21:00:00', 'Tunis', 'Privé', 'bf2f1a1bf57e21707bdc995fdbbaae48.jpeg', 25),
(4, 4, 5, 'Film', 'film', '2019-07-29', '2019-07-29', '14:00:00', '15:00:00', 'Tunis', 'Privé', '806afcb023a0bc50942033755f82d16b.jpeg', 6);

-- --------------------------------------------------------

--
-- Table structure for table `event_categorie`
--

DROP TABLE IF EXISTS `event_categorie`;
CREATE TABLE IF NOT EXISTS `event_categorie` (
  `id_event_categorie` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `nom_event_categorie` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  `image_event_categorie` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_event_categorie`),
  KEY `IDX_CFE8E809A76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `event_categorie`
--

INSERT INTO `event_categorie` (`id_event_categorie`, `user_id`, `nom_event_categorie`, `image_event_categorie`) VALUES
(2, 1, 'Théâtre', '28a2e270ecf0a70187a9a96414c95dc6.jpeg'),
(3, 1, 'Musique', '773a05582afe6eb555f6d948b40eff78.jpeg'),
(4, 1, 'Cinéma', '85dc0da77888ad0ec3a805add853e185.jpeg'),
(5, 1, 'Excursion', 'eaa03c314a36bf4661ca5370fa6190db.jpeg'),
(6, 1, 'Littérature', '6c796e57c6675b87060ae3d006fe08a3.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `event_invitation`
--

DROP TABLE IF EXISTS `event_invitation`;
CREATE TABLE IF NOT EXISTS `event_invitation` (
  `id_event_invitation` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `user_invite_id` int(11) DEFAULT NULL,
  `evenement_id` int(11) DEFAULT NULL,
  `reponse_invitation` tinyint(1) DEFAULT NULL,
  `date_invitation` datetime NOT NULL,
  PRIMARY KEY (`id_event_invitation`),
  KEY `IDX_A9F3B88DA76ED395` (`user_id`),
  KEY `IDX_A9F3B88DEAA1FAA3` (`user_invite_id`),
  KEY `IDX_A9F3B88DFD02F13` (`evenement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `event_invitation`
--

INSERT INTO `event_invitation` (`id_event_invitation`, `user_id`, `user_invite_id`, `evenement_id`, `reponse_invitation`, `date_invitation`) VALUES
(1, 5, 6, 4, 1, '2019-02-26 15:39:08');

-- --------------------------------------------------------

--
-- Table structure for table `event_participation`
--

DROP TABLE IF EXISTS `event_participation`;
CREATE TABLE IF NOT EXISTS `event_participation` (
  `id_event_participation` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `evenement_id` int(11) DEFAULT NULL,
  `date_participation` datetime NOT NULL,
  PRIMARY KEY (`id_event_participation`),
  KEY `IDX_8F0C52E3A76ED395` (`user_id`),
  KEY `IDX_8F0C52E3FD02F13` (`evenement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `event_participation`
--

INSERT INTO `event_participation` (`id_event_participation`, `user_id`, `evenement_id`, `date_participation`) VALUES
(2, 5, 1, '2019-02-26 15:40:50'),
(3, 6, 4, '2019-02-26 15:43:07');

-- --------------------------------------------------------

--
-- Table structure for table `event_publication`
--

DROP TABLE IF EXISTS `event_publication`;
CREATE TABLE IF NOT EXISTS `event_publication` (
  `id_publication` int(11) NOT NULL AUTO_INCREMENT,
  `evenement_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `contenu_publication` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `date_publication` datetime NOT NULL,
  PRIMARY KEY (`id_publication`),
  KEY `IDX_4B5B4F9FD02F13` (`evenement_id`),
  KEY `IDX_4B5B4F9A76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `event_publication`
--

INSERT INTO `event_publication` (`id_publication`, `evenement_id`, `user_id`, `contenu_publication`, `date_publication`) VALUES
(2, 4, 6, 'hello', '2019-02-26 15:43:20');

-- --------------------------------------------------------

--
-- Table structure for table `like__societe`
--

DROP TABLE IF EXISTS `like__societe`;
CREATE TABLE IF NOT EXISTS `like__societe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_user` int(11) DEFAULT NULL,
  `id_Societe` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_CF1920F86B3CA4B` (`id_user`),
  KEY `IDX_CF1920F8845AD51` (`id_Societe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `livraison`
--

DROP TABLE IF EXISTS `livraison`;
CREATE TABLE IF NOT EXISTS `livraison` (
  `id_livration` int(11) NOT NULL,
  `commande_id` int(11) DEFAULT NULL,
  `date_liv` date NOT NULL,
  PRIMARY KEY (`id_livration`),
  KEY `IDX_A60C9F1F82EA2E54` (`commande_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  `body` longtext COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_B6BD307FE2904019` (`thread_id`),
  KEY `IDX_B6BD307FF624B39D` (`sender_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `message`
--

INSERT INTO `message` (`id`, `thread_id`, `sender_id`, `body`, `created_at`) VALUES
(3, 1, 2, 'lkdjzjz', '2019-02-12 00:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `message_metadata`
--

DROP TABLE IF EXISTS `message_metadata`;
CREATE TABLE IF NOT EXISTS `message_metadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `message_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_read` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_4632F005537A1329` (`message_id`),
  KEY `IDX_4632F0059D1C3019` (`participant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `participation`
--

DROP TABLE IF EXISTS `participation`;
CREATE TABLE IF NOT EXISTS `participation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `compagne_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_AB55E24F8EB43C7` (`compagne_id`),
  KEY `IDX_AB55E24FA76ED395` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `passion`
--

DROP TABLE IF EXISTS `passion`;
CREATE TABLE IF NOT EXISTS `passion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type_passion` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `description_carriere` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `client_pass_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_7A3D8D5FBE691E98` (`client_pass_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `passion`
--

INSERT INTO `passion` (`id`, `type_passion`, `description_carriere`, `client_pass_id`) VALUES
(1, 'Music', 'ahahahhaha', 4),
(13, 'Dessin', 'ff', 2),
(14, 'Science', 'hhhhh', 2),
(15, 'Music', 'j\'aime la musique\r\n', 2),
(16, 'Sport', 'jdkadhkjadz', 8);

-- --------------------------------------------------------

--
-- Table structure for table `planning`
--

DROP TABLE IF EXISTS `planning`;
CREATE TABLE IF NOT EXISTS `planning` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `begin_at` datetime NOT NULL,
  `end_at` datetime NOT NULL,
  `title` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `plat`
--

DROP TABLE IF EXISTS `plat`;
CREATE TABLE IF NOT EXISTS `plat` (
  `idplat` int(11) NOT NULL AUTO_INCREMENT,
  `consommation_id` int(11) DEFAULT NULL,
  `nomplat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prixplat` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `nom_image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`idplat`),
  KEY `IDX_2038A207C1076F84` (`consommation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `prefere`
--

DROP TABLE IF EXISTS `prefere`;
CREATE TABLE IF NOT EXISTS `prefere` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `client_pref_id` int(11) DEFAULT NULL,
  `bri_pref_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_16BC7415B34CAB22` (`client_pref_id`),
  KEY `IDX_16BC741591582D04` (`bri_pref_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `produit`
--

DROP TABLE IF EXISTS `produit`;
CREATE TABLE IF NOT EXISTS `produit` (
  `id_produit` int(11) NOT NULL AUTO_INCREMENT,
  `cat_consoma_id` int(11) DEFAULT NULL,
  `nom_produit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type_produit` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prix_produit` double NOT NULL,
  `quantite_produit` int(11) NOT NULL,
  `nom_image` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id_produit`),
  KEY `IDX_29A5EC271D194889` (`cat_consoma_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

DROP TABLE IF EXISTS `rate`;
CREATE TABLE IF NOT EXISTS `rate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rate` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
CREATE TABLE IF NOT EXISTS `reservation` (
  `id_reserv` int(11) NOT NULL AUTO_INCREMENT,
  `consommation_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date_r` date NOT NULL,
  PRIMARY KEY (`id_reserv`),
  KEY `IDX_42C84955C1076F84` (`consommation_id`),
  KEY `IDX_42C84955A76ED395` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`id_reserv`, `consommation_id`, `user_id`, `date_r`) VALUES
(1, 1, NULL, '2014-01-01'),
(2, 1, NULL, '2014-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
CREATE TABLE IF NOT EXISTS `service` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nomService` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `societe`
--

DROP TABLE IF EXISTS `societe`;
CREATE TABLE IF NOT EXISTS `societe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `tel` int(11) NOT NULL,
  `adress` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `localisation` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `Service_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_19653DBDA201AA36` (`Service_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `thread`
--

DROP TABLE IF EXISTS `thread`;
CREATE TABLE IF NOT EXISTS `thread` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_by_id` int(11) DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `created_at` datetime NOT NULL,
  `is_spam` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_31204C83B03A8386` (`created_by_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `thread`
--

INSERT INTO `thread` (`id`, `created_by_id`, `subject`, `created_at`, `is_spam`) VALUES
(1, 3, '22', '2019-02-19 00:00:00', 22);

-- --------------------------------------------------------

--
-- Table structure for table `thread_metadata`
--

DROP TABLE IF EXISTS `thread_metadata`;
CREATE TABLE IF NOT EXISTS `thread_metadata` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `thread_id` int(11) DEFAULT NULL,
  `participant_id` int(11) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL,
  `last_participant_message_date` datetime DEFAULT NULL,
  `last_message_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `IDX_40A577C8E2904019` (`thread_id`),
  KEY `IDX_40A577C89D1C3019` (`participant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `username_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `email_canonical` varchar(180) COLLATE utf8_unicode_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `salt` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `last_login` datetime DEFAULT NULL,
  `confirmation_token` varchar(180) COLLATE utf8_unicode_ci DEFAULT NULL,
  `password_requested_at` datetime DEFAULT NULL,
  `roles` longtext COLLATE utf8_unicode_ci NOT NULL COMMENT '(DC2Type:array)',
  `nom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `prenom` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `adress` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `sold` double NOT NULL,
  `password_plain` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UNIQ_8D93D64992FC23A8` (`username_canonical`),
  UNIQUE KEY `UNIQ_8D93D649A0D96FBF` (`email_canonical`),
  UNIQUE KEY `UNIQ_8D93D649C05FB297` (`confirmation_token`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`, `nom`, `prenom`, `type`, `adress`, `sold`, `password_plain`, `role`) VALUES
(1, 'root2', 'root2', 'lhadzkjhazdk@gmail.com', 'lhadzkjhazdk@gmail.com', 1, NULL, '$2y$13$XVwK1wbiGRRCS5dexyx.reD6LRUCh90NIZYuM/IOvbcDX4lpsZW0a', '2019-02-26 15:16:14', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'root2', 'root2', 'ROLE_ADMIN', '', 0, NULL, NULL),
(2, 'xdd', 'xdd', 'hahahah@gmail.com', 'hahahah@gmail.com', 1, NULL, '$2y$13$./fih8SjN09Hqmi/egY53OFNhkEWEwIDkG7ymuwqM0uBbGG7xwsPO', '2019-02-27 08:31:36', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'xD', 'xD', 'ROLE_CLIENT', 'adkjhazkjhadzjk', 24, NULL, NULL),
(3, 'root', 'root', 'root@gmail.com', 'root@gmail.com', 1, NULL, '$2y$13$7WMkrCMautGCZtHb0yPzM.dHFvrCW1atI8GJdV.WOMrTDTxV8Pyom', '2019-03-03 12:21:05', NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}', 'root', 'root', 'ROLE_ADMIN', '', 0, NULL, NULL),
(4, 'pirateos', 'pirateos', 'derbalios.mohamed@gmail.com', 'derbalios.mohamed@gmail.com', 1, NULL, '$2y$13$z6dAHFG.z7bFd6PbdVEqdOo/llrX/5keh.civVfhEqkJuIc/tKJUa', '2019-02-21 16:03:02', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'derbai', 'mohamed', 'ROLE_CLIENT', '', 0, NULL, NULL),
(5, 'nada', 'nada', 'nada@esprit.tn', 'nada@esprit.tn', 1, NULL, '$2y$13$DdJx2jwKqfRyudoYzSAkhOspjv9yEAZ6YM/zi3Tma9GbgfGpDpVK.', '2019-02-26 15:43:55', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'Baklouti', 'Nada', 'ROLE_CLIENT', '', 0, NULL, NULL),
(6, 'ameni', 'ameni', 'ameni@esprit.tn', 'ameni@esprit.tn', 1, NULL, '$2y$13$qbGh1oMq19OhGQy7pVdlb.QLWb5z2jf08BWPyeF9ed6jwO0aruMbW', '2019-02-26 15:42:34', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'Ayadi', 'Ameni', 'ROLE_CLIENT', '', 0, NULL, NULL),
(7, 'afef', 'afef', 'afef.baccouche@esprit.tn', 'afef.baccouche@esprit.tn', 1, NULL, '$2y$13$CgUTUtk5XGl.EBnbluK/FuzoiYp1dDVpMjHtfLYUUCcKFyh.MMkUu', '2019-02-26 19:22:50', NULL, NULL, 'a:1:{i:0;s:16:\"ROLE_RESPONSABLE\";}', 'Afef', 'baccouche', 'ROLE_CLIENT', 'fjhfkjahkjhfazkj', 545, NULL, NULL),
(8, 'Hsan', 'hsan', 'Hsan.boughaleb@gmail.com', 'hsan.boughaleb@gmail.com', 1, NULL, '$2y$13$pZLMFL/zRTRKh8xxP0UpBOZVnIT3L.jcKd6YlDHTp3fqZaSEivjNu', '2019-02-28 17:37:17', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'Hsan', 'Boughaleb', 'ROLE_CLIENT', 'tunis', 5000, NULL, NULL),
(21, 'llll', 'llll', 'llllll', 'llllll', 0, NULL, 'll', NULL, NULL, NULL, '', 'llll', 'llllll', 'type', 'address', 0, 'll', 'Doctor'),
(22, ',kn,k,', ',kn,k,', 'pollm;;', 'pollm;;', 0, NULL, 'lll', NULL, NULL, NULL, '', ',kn,k,', ',lk,pmù;k', 'type', 'address', 0, 'lll', 'Doctor'),
(23, 'kjn,', 'kjn,', 'k,', 'k,', 0, NULL, 'mm', NULL, NULL, NULL, '', 'kjn,', ',kl', 'type', 'address', 0, 'mm', 'Doctor'),
(24, 'derbali', 'derbali', 'derbali@gmail.com', 'derbali@gmail.com', 1, NULL, 'hama', '2019-03-01 00:00:00', NULL, NULL, 'a:1:{i:0;s:11:\"ROLE_CLIENT\";}', 'derbali', 'hhhhh', 'Client', '555', 55, 'derbali', 'Client');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activite`
--
ALTER TABLE `activite`
  ADD CONSTRAINT `FK_B8755515D7E406D0` FOREIGN KEY (`passion_id`) REFERENCES `passion` (`id`);

--
-- Constraints for table `association`
--
ALTER TABLE `association`
  ADD CONSTRAINT `FK_FD8521CCAC425E03` FOREIGN KEY (`client_assoc_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `bricolage`
--
ALTER TABLE `bricolage`
  ADD CONSTRAINT `FK_DE443E24FD645345` FOREIGN KEY (`client_bri_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `comment_bri`
--
ALTER TABLE `comment_bri`
  ADD CONSTRAINT `FK_72E744E458E0A285` FOREIGN KEY (`userid_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_72E744E46971B526` FOREIGN KEY (`bricolage_id`) REFERENCES `bricolage` (`id`);

--
-- Constraints for table `consommation`
--
ALTER TABLE `consommation`
  ADD CONSTRAINT `FK_F993F0A21D194889` FOREIGN KEY (`cat_consoma_id`) REFERENCES `cat_consoma` (`idcat`) ON DELETE CASCADE;

--
-- Constraints for table `demandeservice`
--
ALTER TABLE `demandeservice`
  ADD CONSTRAINT `FK_9557022268D3EA09` FOREIGN KEY (`User_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_95570222B3AA76D3` FOREIGN KEY (`Societe_id`) REFERENCES `societe` (`id`);

--
-- Constraints for table `don_besoin`
--
ALTER TABLE `don_besoin`
  ADD CONSTRAINT `FK_987AE06CA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `don_pot_compagne`
--
ALTER TABLE `don_pot_compagne`
  ADD CONSTRAINT `FK_AD6885ABA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `evenement`
--
ALTER TABLE `evenement`
  ADD CONSTRAINT `FK_B26681E51E6EDDE` FOREIGN KEY (`event_categorie_id`) REFERENCES `event_categorie` (`id_event_categorie`) ON DELETE SET NULL,
  ADD CONSTRAINT `FK_B26681EA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `event_categorie`
--
ALTER TABLE `event_categorie`
  ADD CONSTRAINT `FK_CFE8E809A76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `event_invitation`
--
ALTER TABLE `event_invitation`
  ADD CONSTRAINT `FK_A9F3B88DA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_A9F3B88DEAA1FAA3` FOREIGN KEY (`user_invite_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_A9F3B88DFD02F13` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id_event`) ON DELETE CASCADE;

--
-- Constraints for table `event_participation`
--
ALTER TABLE `event_participation`
  ADD CONSTRAINT `FK_8F0C52E3A76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_8F0C52E3FD02F13` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id_event`) ON DELETE CASCADE;

--
-- Constraints for table `event_publication`
--
ALTER TABLE `event_publication`
  ADD CONSTRAINT `FK_4B5B4F9A76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_4B5B4F9FD02F13` FOREIGN KEY (`evenement_id`) REFERENCES `evenement` (`id_event`) ON DELETE CASCADE;

--
-- Constraints for table `like__societe`
--
ALTER TABLE `like__societe`
  ADD CONSTRAINT `FK_CF1920F86B3CA4B` FOREIGN KEY (`id_user`) REFERENCES `user` (`id`) ON DELETE CASCADE,
  ADD CONSTRAINT `FK_CF1920F8845AD51` FOREIGN KEY (`id_Societe`) REFERENCES `societe` (`id`) ON DELETE CASCADE;

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `FK_B6BD307FE2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`),
  ADD CONSTRAINT `FK_B6BD307FF624B39D` FOREIGN KEY (`sender_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `message_metadata`
--
ALTER TABLE `message_metadata`
  ADD CONSTRAINT `FK_4632F005537A1329` FOREIGN KEY (`message_id`) REFERENCES `message` (`id`),
  ADD CONSTRAINT `FK_4632F0059D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `participation`
--
ALTER TABLE `participation`
  ADD CONSTRAINT `FK_AB55E24F8EB43C7` FOREIGN KEY (`compagne_id`) REFERENCES `compagne_don` (`idCompagneDon`),
  ADD CONSTRAINT `FK_AB55E24FA76ED395` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `passion`
--
ALTER TABLE `passion`
  ADD CONSTRAINT `FK_7A3D8D5FBE691E98` FOREIGN KEY (`client_pass_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `prefere`
--
ALTER TABLE `prefere`
  ADD CONSTRAINT `FK_16BC741591582D04` FOREIGN KEY (`bri_pref_id`) REFERENCES `bricolage` (`id`),
  ADD CONSTRAINT `FK_16BC7415B34CAB22` FOREIGN KEY (`client_pref_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `societe`
--
ALTER TABLE `societe`
  ADD CONSTRAINT `FK_19653DBDA201AA36` FOREIGN KEY (`Service_id`) REFERENCES `service` (`id`);

--
-- Constraints for table `thread`
--
ALTER TABLE `thread`
  ADD CONSTRAINT `FK_31204C83B03A8386` FOREIGN KEY (`created_by_id`) REFERENCES `user` (`id`);

--
-- Constraints for table `thread_metadata`
--
ALTER TABLE `thread_metadata`
  ADD CONSTRAINT `FK_40A577C89D1C3019` FOREIGN KEY (`participant_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FK_40A577C8E2904019` FOREIGN KEY (`thread_id`) REFERENCES `thread` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
