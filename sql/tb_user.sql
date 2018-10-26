/*
SQLyog Enterprise - MySQL GUI v8.12 
MySQL - 5.6.27 : Database - sino_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`sino_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sino_db`;

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `pid` bigint(20) DEFAULT NULL,
  `method` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`id`,`name`,`description`,`url`,`pid`,`method`) values (1,'Admission','Admission','/admission',NULL,NULL),(2,'Patronage','Patronage','/patronage',NULL,NULL),(3,'SalesKit','SalesKit','/salesKit',NULL,NULL),(4,'Card','Card','/card',NULL,NULL),(5,'Service Plan','Service Plan','/servicePlan',1,NULL),(6,'Day Pass','Day Pass','/dayPass',1,NULL),(7,'Temporary Pass','Temporary Pass','/temporaryPass',1,NULL),(8,'Patron Overview','Patron Overview','/patronOverview',2,NULL),(9,'Patronage Due','Patronage Due','/patronageDue',2,NULL),(10,'Top up Settlement','Top up Settlement','/topup',2,NULL),(11,'Presentation Archive','Presentation Archive','/presentationArchive',3,NULL),(12,'Sales Kit Login','Sales Kit Login','/salesKitLogin',3,NULL),(13,'UserManager','usermanager','/user/*',1,NULL);

/*Table structure for table `sys_permission_role` */

DROP TABLE IF EXISTS `sys_permission_role`;

CREATE TABLE `sys_permission_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) unsigned NOT NULL,
  `permission_id` bigint(20) unsigned NOT NULL,
  `access_right` char(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_permission_role` */

insert  into `sys_permission_role`(`id`,`role_id`,`permission_id`,`access_right`) values (1,1,1,'*'),(2,1,2,'R'),(3,2,1,'*'),(4,1,13,'*');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`) values (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_NORMAL');

/*Table structure for table `sys_role_user` */

DROP TABLE IF EXISTS `sys_role_user`;

CREATE TABLE `sys_role_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `sys_user_id` bigint(20) unsigned NOT NULL,
  `sys_role_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_role_user` */

insert  into `sys_role_user`(`id`,`sys_user_id`,`sys_role_id`) values (1,1,1),(2,2,2),(3,4,1),(4,4,2);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`) values (1,'admin','admin'),(2,'abel','abel'),(3,'string','$2a$10$yaHKAkjN6UFzQur0686VQ.vIgGy92aW1Fo8frjY9dFz9KFbvA1Mxy'),(4,'christ','$2a$10$EMR6tqRQj2iEVe0mCsmW0ODRADdZoINQxkYfFpQqZGS7555oLLimG');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
