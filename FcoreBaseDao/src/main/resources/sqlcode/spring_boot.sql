/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.14-log : Database - spring_boot
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`spring_boot` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `spring_boot`;

/*Table structure for table `sys_child_dict` */

DROP TABLE IF EXISTS `sys_child_dict`;

CREATE TABLE `sys_child_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sysDictId` int(11) NOT NULL COMMENT '上级ID',
  `name` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `key` varchar(100) COLLATE utf8_bin NOT NULL COMMENT 'key值',
  `value` varchar(100) COLLATE utf8_bin NOT NULL COMMENT 'value值',
  `isDelete` int(3) NOT NULL COMMENT '是否删除',
  `createUserId` int(11) DEFAULT NULL COMMENT '创建人',
  `createTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updateUserId` int(11) DEFAULT NULL COMMENT '修改人',
  `updateTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_child_dict` */

insert  into `sys_child_dict`(`id`,`sysDictId`,`name`,`key`,`value`,`isDelete`,`createUserId`,`createTime`,`updateUserId`,`updateTime`) values (1,1,'男','sex_man','1',1,NULL,NULL,NULL,NULL),(2,1,'女','sex_men','2',1,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '名称',
  `key` varchar(32) COLLATE utf8_bin NOT NULL COMMENT 'key值',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'value值',
  `isMoreLevel` int(3) NOT NULL COMMENT '是否多级 1是 2否',
  `remark` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '说明',
  `isDelete` int(3) NOT NULL COMMENT '是否删除',
  `createUserId` int(11) DEFAULT NULL COMMENT '创建人',
  `createTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updateTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改时间',
  `updateUserId` int(11) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`name`,`key`,`value`,`isMoreLevel`,`remark`,`isDelete`,`createUserId`,`createTime`,`updateTime`,`updateUserId`) values (1,'性别','sex',NULL,1,NULL,1,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '权限名称',
  `value` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '权限值',
  `href` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'URL',
  `parentId` int(11) DEFAULT NULL COMMENT '父ID',
  `parentIds` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '父ID集合',
  `isLast` int(3) NOT NULL COMMENT '最后一级',
  `createUserId` int(11) DEFAULT NULL COMMENT '创建人',
  `createTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updateUserId` int(11) DEFAULT NULL COMMENT '更新人',
  `updateTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '更新时间',
  `isDelete` int(3) NOT NULL COMMENT '删除标记',
  `levelCode` int(3) NOT NULL COMMENT '层级编号',
  `orderCode` int(3) NOT NULL COMMENT '排序编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_permission` */

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `roleName` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `description` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '角色描述',
  `createUserId` int(11) DEFAULT NULL COMMENT '创建人',
  `createTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updateUserId` int(11) DEFAULT NULL COMMENT '修改人',
  `updateTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改时间',
  `isDelete` int(3) NOT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_role` */

/*Table structure for table `sys_role_per` */

DROP TABLE IF EXISTS `sys_role_per`;

CREATE TABLE `sys_role_per` (
  `sysRoleId` int(11) NOT NULL,
  `sysPerId` int(11) NOT NULL,
  PRIMARY KEY (`sysRoleId`,`sysPerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_role_per` */

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `userName` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '用户名称',
  `loginName` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '登录名称',
  `mobile` int(11) DEFAULT NULL COMMENT '手机号码',
  `salt` varchar(36) COLLATE utf8_bin NOT NULL COMMENT '加密盐',
  `password` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '登录秘密',
  `lastLoginIp` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '最后登录IP',
  `lastLoginTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '最后登录时间',
  `createUserId` int(11) DEFAULT NULL COMMENT '创建人',
  `createTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updateUserId` int(11) DEFAULT NULL COMMENT '修改人',
  `updateTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改时间',
  `isDelete` int(11) NOT NULL COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`userName`,`loginName`,`mobile`,`salt`,`password`,`lastLoginIp`,`lastLoginTime`,`createUserId`,`createTime`,`updateUserId`,`updateTime`,`isDelete`) values (1,'张居开','zjk',NULL,'b48690cd-78c8-4fcc-9df5-412308e607e0','a149a73da75fb4c9cfbd54ff2a1ea2d2','0:0:0:0:0:0:0:1','2016-09-28 16:36:06',0,'2016-09-22 14:25:07',0,NULL,1);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `sysUserId` int(11) NOT NULL,
  `sysRoleId` int(11) NOT NULL,
  PRIMARY KEY (`sysUserId`,`sysRoleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_user_role` */

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`role`) values ('zjk','123456','ROLE_USER');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
