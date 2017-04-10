start transaction;

create database `Acme-Chorbies`;

use `Acme-Chorbies`;

create user 'acme-user'@'%' identified by password '*4F10007AADA9EE3DBB2CC36575DFC6F4FDE27577';

create user 'acme-manager'@'%' identified by password '*FDB8CD304EB2317D10C95D797A4BD7492560F55F';

grant select, insert, update, delete 
on `Acme-Chorbies`.* to 'acme-user'@'%';

grant select, insert, update, delete, create, drop, references, index, alter, 
create temporary tables, lock tables, create view, create routine,
 alter routine, execute, trigger, show view 
on `Acme-Chorbies`.* to 'acme-manager'@'%';

-- MySQL dump 10.13  Distrib 5.5.29, for Win64 (x86)
--
-- Host: localhost    Database: Acme-Chorbies
-- ------------------------------------------------------
-- Server version	5.5.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrator`
--

DROP TABLE IF EXISTS `administrator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrator` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_idt4b4u259p6vs4pyr9lax4eg` (`userAccount_id`),
  CONSTRAINT `FK_idt4b4u259p6vs4pyr9lax4eg` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrator`
--

LOCK TABLES `administrator` WRITE;
/*!40000 ALTER TABLE `administrator` DISABLE KEYS */;
INSERT INTO `administrator` VALUES (1038,0,'admin@acme.com','administrator1','+34666666666','administrator1',1029);
/*!40000 ALTER TABLE `administrator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attachment`
--

DROP TABLE IF EXISTS `attachment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `attachment` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `chirp_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_bnyhwxm7ara2q688wlq0928va` (`chirp_id`),
  CONSTRAINT `FK_bnyhwxm7ara2q688wlq0928va` FOREIGN KEY (`chirp_id`) REFERENCES `chirp` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attachment`
--

LOCK TABLES `attachment` WRITE;
/*!40000 ALTER TABLE `attachment` DISABLE KEYS */;
INSERT INTO `attachment` VALUES (1058,0,'AttachmentName1','http://www.attachmentName1.com',1039),(1059,0,'AttachmentName1','http://www.attachmentName2.com',1039),(1060,0,'AttachmentName1','http://www.attachmentName3.com',1039),(1061,0,'AttachmentName1','http://www.attachmentName4.com',1039),(1062,0,'AttachmentName1','http://www.attachmentName5.com',1039),(1063,0,'AttachmentName1','http://www.attachmentName6.com',1039),(1064,0,'AttachmentName1','http://www.attachmentName1.com',1040),(1065,0,'AttachmentName1','http://www.attachmentName2.com',1040),(1066,0,'AttachmentName1','http://www.attachmentName3.com',1040),(1067,0,'AttachmentName1','http://www.attachmentName4.com',1040),(1068,0,'AttachmentName1','http://www.attachmentName5.com',1040),(1069,0,'AttachmentName1','http://www.attachmentName6.com',1040),(1070,0,'AttachmentName1','http://www.attachmentName1.com',1042),(1071,0,'AttachmentName1','http://www.attachmentName1.com',1042);
/*!40000 ALTER TABLE `attachment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `banner`
--

DROP TABLE IF EXISTS `banner`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `link` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `banner`
--

LOCK TABLES `banner` WRITE;
/*!40000 ALTER TABLE `banner` DISABLE KEYS */;
INSERT INTO `banner` VALUES (1088,0,'http://i.imgur.com/HfkbICf.png','http://www.Acme-Chorbies.com'),(1089,0,'http://i.imgur.com/pA52wSC.png','http://www.acme-bnb.com'),(1090,0,'http://i.imgur.com/GgsILAX.png','http://www.acme-cng.com'),(1091,0,'http://i.imgur.com/HfkbICf.png','http://www.Acme-Chorbies.com'),(1092,0,'http://i.imgur.com/pA52wSC.png','http://www.acme-bnb.com'),(1093,0,'http://i.imgur.com/GgsILAX.png','http://www.acme-cng.com');
/*!40000 ALTER TABLE `banner` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chirp`
--

DROP TABLE IF EXISTS `chirp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chirp` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `isSender` bit(1) NOT NULL,
  `recipientName` varchar(255) DEFAULT NULL,
  `senderName` varchar(255) DEFAULT NULL,
  `sendingMoment` datetime DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  `recipient_id` int(11) DEFAULT NULL,
  `sender_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_a5xyhin5jhq9cbuafsy55t6mb` (`isSender`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chirp`
--

LOCK TABLES `chirp` WRITE;
/*!40000 ALTER TABLE `chirp` DISABLE KEYS */;
INSERT INTO `chirp` VALUES (1039,1,'','Chorbi2 Chorbi2','Chorbi1 Chorbi1','2016-04-04 11:45:00','subject Chirp','text Chirp',1096,1095),(1040,1,'\0','Chorbi2 Chorbi2','Chorbi1 Chorbi1','2016-04-04 11:45:00','subject Chirp','text Chirp',1096,1095),(1041,1,'','Chorbi2 Chorbi2','Chorbi1 Chorbi1','2016-04-04 11:45:00','subject Chirp','text Chirp',1096,1095),(1042,1,'\0','Chorbi2 Chorbi2','Chorbi1 Chorbi1','2016-04-04 11:45:00','subject Chirp','text Chirp',1096,1095),(1043,1,'','Chorbi2 Chorbi2','Chorbi1 Chorbi1','2016-04-04 11:45:00','subject Chirp','text Chirp',1096,1095),(1044,1,'','Chorbi2 Chorbi2','Chorbi1 Chorbi1','2016-04-04 11:45:00','subject Chirp','text Chirp',1096,1095),(1045,1,'','Chorbi2 Chorbi2','Chorbi1 Chorbi1','2016-04-04 11:45:00','subject Chirp','text Chirp',1096,1095),(1046,1,'','Chorbi2 Chorbi2','Chorbi1 Chorbi1','2016-04-04 11:45:00','subject Chirp','text Chirp',1096,1095),(1047,1,'\0','Chorbi1 Chorbi1','Chorbi2 Chorbi2','2016-04-04 11:45:00','subject Chirp','text Chirp',1095,1096),(1048,1,'\0','Chorbi1 Chorbi1','Chorbi2 Chorbi2','2016-04-04 11:45:00','subject Chirp','text Chirp',1095,1096),(1049,1,'\0','Chorbi1 Chorbi1','Chorbi2 Chorbi2','2016-04-04 11:45:00','subject Chirp','text Chirp',1095,1096),(1050,1,'\0','Chorbi1 Chorbi1','Chorbi2 Chorbi2','2016-04-04 11:45:00','subject Chirp','text Chirp',1095,1096),(1051,1,'\0','Chorbi1 Chorbi1','Chorbi2 Chorbi2','2016-04-04 11:45:00','subject Chirp','text Chirp',1095,1096),(1052,1,'\0','Chorbi1 Chorbi1','Chorbi2 Chorbi2','2016-04-04 11:45:00','subject Chirp','text Chirp',1095,1096),(1053,1,'','Chorbi3 Chorbi3','Chorbi2 Chorbi2','2016-04-04 11:45:00','subject Chirp','text Chirp',1097,1096),(1054,1,'','Chorbi4 Chorbi4','Chorbi3 Chorbi3','2016-04-04 11:45:00','subject Chirp','text Chirp',1098,1097),(1055,1,'\0','Chorbi3 Chorbi3','Chorbi4 Chorbi4','2016-04-04 11:45:00','subject Chirp','text Chirp',1097,1098),(1056,1,'\0','Chorbi3 Chorbi3','Chorbi- Chorbi-','2016-04-04 11:45:00','subject Chirp','text Chirp',1097,NULL),(1057,1,'\0','Chorbi- Chorbi-','Chorbi3 Chorbi3','2016-04-04 11:45:00','subject Chirp','text Chirp',NULL,1097);
/*!40000 ALTER TABLE `chirp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chorbi`
--

DROP TABLE IF EXISTS `chorbi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chorbi` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `userAccount_id` int(11) NOT NULL,
  `birthDate` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `desiredRelationship` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `coordinates_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hjmrn8vvm3631n2jvyojlilsj` (`coordinates_id`),
  UNIQUE KEY `UK_qrvmwkp25xc5exr6m3jgaxu4x` (`userAccount_id`),
  CONSTRAINT `FK_qrvmwkp25xc5exr6m3jgaxu4x` FOREIGN KEY (`userAccount_id`) REFERENCES `useraccount` (`id`),
  CONSTRAINT `FK_hjmrn8vvm3631n2jvyojlilsj` FOREIGN KEY (`coordinates_id`) REFERENCES `coordinates` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chorbi`
--

LOCK TABLES `chorbi` WRITE;
/*!40000 ALTER TABLE `chorbi` DISABLE KEYS */;
INSERT INTO `chorbi` VALUES (1095,0,'chorbi1@acme.com','Chorbi1','+34000000000','Chorbi1',1030,'1990-06-20','description chorbi1','love','man','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',1072),(1096,0,'chorbi2@acme.com','Chorbi2','+34111111111','Chorbi2',1031,'1970-06-20','description chorbi2','friendship','man','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',1074),(1097,0,'chorbi3@acme.com','Chorbi3','+34222222222','Chorbi3',1032,'1988-06-20','description chorbi3','activities','man','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',1076),(1098,0,'chorbi4@acme.com','Chorbi4','+34333333333','Chorbi4',1033,'1979-06-20','description chorbi4, call me at +34333333333','love','woman','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',1078),(1099,0,'chorbi5@acme.com','Chorbi5','+34444444444','Chorbi5',1034,'1995-06-20','description chorbi5, add me chorbi5@acme.com','love','woman','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',1080),(1100,0,'chorbi6@acme.com','Chorbi6','+34555555555','Chorbi6',1035,'1980-04-21','description chorbi6','friendship','woman','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',1082),(1101,0,'chorbi6@acme.com','Chorbi7','+34555555555','Chorbi7',1036,'1980-04-21','description chorbi7','friendship','woman','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',1084),(1102,0,'chorbi8@acme.com','Chorbi8','+34555555555','Chorbi8',1037,'1980-04-21','description chorbi8','friendship','woman','https://image.freepik.com/iconos-gratis/avatar-de-usuario-de-esquema_318-34741.jpg',1086);
/*!40000 ALTER TABLE `chorbi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `configuration`
--

DROP TABLE IF EXISTS `configuration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `configuration` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `cachedTime` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `configuration`
--

LOCK TABLES `configuration` WRITE;
/*!40000 ALTER TABLE `configuration` DISABLE KEYS */;
INSERT INTO `configuration` VALUES (1094,0,43200000);
/*!40000 ALTER TABLE `configuration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordinates`
--

DROP TABLE IF EXISTS `coordinates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coordinates` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordinates`
--

LOCK TABLES `coordinates` WRITE;
/*!40000 ALTER TABLE `coordinates` DISABLE KEYS */;
INSERT INTO `coordinates` VALUES (1072,0,'Seville','Spain','Seville',''),(1073,0,'Seville','','',''),(1074,0,'Madrid','','',''),(1075,0,'','','',''),(1076,0,'Austin','','','Texas'),(1077,0,'','','',''),(1078,0,'Paris','France','',''),(1079,0,'','','',''),(1080,0,'Seville','','',''),(1081,0,'','','',''),(1082,0,'Seville','','',''),(1083,0,'','','',''),(1084,0,'Seville','','',''),(1085,0,'','','',''),(1086,0,'Seville','','',''),(1087,0,'','','','');
/*!40000 ALTER TABLE `coordinates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `creditcard`
--

DROP TABLE IF EXISTS `creditcard`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `creditcard` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `brandName` varchar(255) DEFAULT NULL,
  `cvvCode` int(11) NOT NULL,
  `expirationMonth` int(11) NOT NULL,
  `expirationYear` int(11) NOT NULL,
  `holderName` varchar(255) DEFAULT NULL,
  `number` varchar(255) DEFAULT NULL,
  `chorbi_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_584svqyk53nkaci4v621ae3kk` (`chorbi_id`),
  CONSTRAINT `FK_584svqyk53nkaci4v621ae3kk` FOREIGN KEY (`chorbi_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `creditcard`
--

LOCK TABLES `creditcard` WRITE;
/*!40000 ALTER TABLE `creditcard` DISABLE KEYS */;
INSERT INTO `creditcard` VALUES (1118,0,'VISA',500,10,2020,'Chorbi1','4485733078654607',1095),(1119,0,'MASTERCARD',500,10,2020,'Chorbi2','4485733078654607',1096),(1120,0,'AMEX',500,10,2020,'Chorbi3','4485733078654607',1097);
/*!40000 ALTER TABLE `creditcard` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequences`
--

DROP TABLE IF EXISTS `hibernate_sequences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequences` (
  `sequence_name` varchar(255) DEFAULT NULL,
  `sequence_next_hi_value` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequences`
--

LOCK TABLES `hibernate_sequences` WRITE;
/*!40000 ALTER TABLE `hibernate_sequences` DISABLE KEYS */;
INSERT INTO `hibernate_sequences` VALUES ('DomainEntity',1);
/*!40000 ALTER TABLE `hibernate_sequences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `moment` datetime DEFAULT NULL,
  `liked_id` int(11) NOT NULL,
  `liker_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_5f2yipvghjdnd82byd8c8au33` (`liked_id`),
  KEY `FK_4se8l0bnwm6trfmo1jsn3rln6` (`liker_id`),
  CONSTRAINT `FK_4se8l0bnwm6trfmo1jsn3rln6` FOREIGN KEY (`liker_id`) REFERENCES `chorbi` (`id`),
  CONSTRAINT `FK_5f2yipvghjdnd82byd8c8au33` FOREIGN KEY (`liked_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1103,0,'nice','2016-10-10 20:30:00',1096,1095),(1104,0,'niiiice','2016-10-10 20:33:00',1097,1095),(1105,0,'','2016-10-10 20:34:00',1098,1095),(1106,0,'','2016-10-10 20:35:00',1099,1095),(1107,0,'','2016-10-10 20:36:00',1100,1095),(1108,0,'','2016-10-10 20:37:00',1101,1095),(1109,0,'','2016-10-10 20:38:00',1097,1096);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searchtemplate`
--

DROP TABLE IF EXISTS `searchtemplate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchtemplate` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `cacheMoment` datetime DEFAULT NULL,
  `desiredRelationship` varchar(255) DEFAULT NULL,
  `genre` varchar(255) DEFAULT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `chorbi_id` int(11) NOT NULL,
  `coordinates_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_n6tillyj5veb60uuot1b24lac` (`chorbi_id`),
  KEY `FK_sacit3a66xps0g3utxay4cuj6` (`coordinates_id`),
  CONSTRAINT `FK_sacit3a66xps0g3utxay4cuj6` FOREIGN KEY (`coordinates_id`) REFERENCES `coordinates` (`id`),
  CONSTRAINT `FK_n6tillyj5veb60uuot1b24lac` FOREIGN KEY (`chorbi_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchtemplate`
--

LOCK TABLES `searchtemplate` WRITE;
/*!40000 ALTER TABLE `searchtemplate` DISABLE KEYS */;
INSERT INTO `searchtemplate` VALUES (1110,0,20,'2016-10-10 22:22:00','love','man','',1095,1073),(1111,0,0,'2016-10-10 22:22:00','','','chorbi3',1096,1075),(1112,0,0,'2016-10-10 22:22:00','friendship','','',1097,1077),(1113,0,0,'2016-10-10 22:22:00','','woman','',1098,1079),(1114,0,40,'2016-10-10 22:22:00','','','',1099,1081),(1115,0,0,'2016-10-10 22:22:00','','','',1100,1083),(1116,0,0,'2016-10-10 22:22:00','','','',1101,1085),(1117,0,0,'2016-10-10 22:22:00','','','',1102,1087);
/*!40000 ALTER TABLE `searchtemplate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searchtemplate_chorbi`
--

DROP TABLE IF EXISTS `searchtemplate_chorbi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `searchtemplate_chorbi` (
  `SearchTemplate_id` int(11) NOT NULL,
  `chorbies_id` int(11) NOT NULL,
  KEY `FK_3t03s45pt13r22kb7y510060k` (`chorbies_id`),
  KEY `FK_8qqojnmpd0r5ur1bo34ymb973` (`SearchTemplate_id`),
  CONSTRAINT `FK_8qqojnmpd0r5ur1bo34ymb973` FOREIGN KEY (`SearchTemplate_id`) REFERENCES `searchtemplate` (`id`),
  CONSTRAINT `FK_3t03s45pt13r22kb7y510060k` FOREIGN KEY (`chorbies_id`) REFERENCES `chorbi` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searchtemplate_chorbi`
--

LOCK TABLES `searchtemplate_chorbi` WRITE;
/*!40000 ALTER TABLE `searchtemplate_chorbi` DISABLE KEYS */;
INSERT INTO `searchtemplate_chorbi` VALUES (1110,1096),(1110,1097),(1110,1098),(1110,1099),(1110,1100),(1110,1101),(1111,1097);
/*!40000 ALTER TABLE `searchtemplate_chorbi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount`
--

DROP TABLE IF EXISTS `useraccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount` (
  `id` int(11) NOT NULL,
  `version` int(11) NOT NULL,
  `enabled` bit(1) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_csivo9yqa08nrbkog71ycilh5` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount`
--

LOCK TABLES `useraccount` WRITE;
/*!40000 ALTER TABLE `useraccount` DISABLE KEYS */;
INSERT INTO `useraccount` VALUES (1029,0,'','21232f297a57a5a743894a0e4a801fc3','admin'),(1030,0,'','3daa859a294cdefb20a84840240a76f5','chorbi1'),(1031,0,'','0c8746de81268518ff83490301db8652','chorbi2'),(1032,0,'','a2da05a88eead7e64593826cafc6a7a7','chorbi3'),(1033,0,'','a09dd233386632e297a7f4f461989563','chorbi4'),(1034,0,'','7e062f6f2a4c0f7ec5abacef2917e033','chorbi5'),(1035,0,'\0','0b41c51bd4b755c5b639498f55058fd3','chorbi6'),(1036,0,'\0','cd33d975ad65688dc4d54b67ed48fd1a','chorbi7'),(1037,0,'\0','cf0216b73314f84c64fd88f5adf4dfb2','chorbi8');
/*!40000 ALTER TABLE `useraccount` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `useraccount_authorities`
--

DROP TABLE IF EXISTS `useraccount_authorities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `useraccount_authorities` (
  `UserAccount_id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  KEY `FK_b63ua47r0u1m7ccc9lte2ui4r` (`UserAccount_id`),
  CONSTRAINT `FK_b63ua47r0u1m7ccc9lte2ui4r` FOREIGN KEY (`UserAccount_id`) REFERENCES `useraccount` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `useraccount_authorities`
--

LOCK TABLES `useraccount_authorities` WRITE;
/*!40000 ALTER TABLE `useraccount_authorities` DISABLE KEYS */;
INSERT INTO `useraccount_authorities` VALUES (1029,'ADMINISTRATOR'),(1030,'CHORBI'),(1031,'CHORBI'),(1032,'CHORBI'),(1033,'CHORBI'),(1034,'CHORBI'),(1035,'CHORBI'),(1036,'CHORBI'),(1037,'CHORBI');
/*!40000 ALTER TABLE `useraccount_authorities` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-10 13:58:22

commit;
