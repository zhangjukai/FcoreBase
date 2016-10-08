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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`name`,`key`,`value`,`isMoreLevel`,`remark`,`isDelete`,`createUserId`,`createTime`,`updateTime`,`updateUserId`) values (1,'性别','sex',NULL,1,NULL,1,NULL,NULL,NULL,NULL),(2,'允许上传的文件类型','UPLOAD_FILE_TYPE','.png,.gif,.jpg,.jpeg,.bmp,.PNG,.GIF,.JPG,.JPEG,.BMP,.doc,.docx,.xlsx,.xls,.pdf,.rar,.zip,.mp4',2,NULL,1,NULL,NULL,NULL,NULL),(3,'文件上传根目录','UPLOAD_FILE_ROOT_PATH','E:/',2,NULL,1,NULL,NULL,NULL,NULL);

/*Table structure for table `sys_file` */

DROP TABLE IF EXISTS `sys_file`;

CREATE TABLE `sys_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `fileName` varchar(100) COLLATE utf8_bin NOT NULL COMMENT '文件名',
  `fileSize` int(11) NOT NULL COMMENT '文件大小',
  `fileType` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '文件类型',
  `suffix` varchar(32) COLLATE utf8_bin NOT NULL COMMENT '后缀名',
  `filePath` varchar(200) COLLATE utf8_bin NOT NULL COMMENT '文件路径',
  `isDelete` int(3) NOT NULL COMMENT '是否删除',
  `createUserId` int(11) DEFAULT NULL COMMENT '创建人',
  `createTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '创建时间',
  `updateUserId` int(11) DEFAULT NULL COMMENT '修改人',
  `updateTime` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_file` */

insert  into `sys_file`(`id`,`fileName`,`fileSize`,`fileType`,`suffix`,`filePath`,`isDelete`,`createUserId`,`createTime`,`updateUserId`,`updateTime`) values (1,'73014493435b19c56cab3f00309617e7.gif',123339,'image','.gif','E:/2016/9/8\\fb7c75a5-47dd-43bc-bc67-bb91b7c796fc.gif',1,NULL,NULL,NULL,NULL),(2,'13a88226cffc1e17a0053f954890f603728de9d9.jpg',35248,'image','.jpg','E:/2016/9/8\\819f2bb1-eb03-44ed-b9df-d7ec358e90c7.jpg',1,NULL,NULL,NULL,NULL),(3,'f78eda3fe4fbf95e2c68be5e5cdc7d09.gif',13712,'image','.gif','E:/2016/9/8\\378f88ba-e22a-4c2b-8207-2ed82ab68c2c.gif',1,NULL,NULL,NULL,NULL),(4,'f78eda3fe4fbf95e2c68be5e5cdc7d09.gif',13712,'image','.gif','E:/2016/9/8\\9db40805-f46b-4fed-aa50-90791ea62fe7.gif',1,NULL,NULL,NULL,NULL),(5,'fine-uploader.zip',1233457,'notype','.zip','E:/2016/9/8\\483603f4-22a2-435c-ba0d-53130aeefe9a.zip',1,NULL,NULL,NULL,NULL),(6,'湖州crm定制版第三次迭代工作总结会议纪要_20160826.doc',34304,'word','.doc','E:/2016/9/8\\2e5d97b1-cc34-4d32-8522-a4274df57360.doc',1,NULL,NULL,NULL,NULL),(7,'fine-uploader.zip',1233457,'notype','.zip','E:/2016/9/8\\de80c7a3-817d-454d-bbe1-9daf15913c81.zip',1,NULL,NULL,NULL,NULL),(8,'湖州crm定制版第三次迭代工作总结会议纪要_20160826.doc',34304,'word','.doc','E:/2016/9/8\\547c3db2-4293-4ff1-b812-765b2a00c682.doc',1,NULL,NULL,NULL,NULL),(9,'张居开-满月跟进表.xlsx',11071,'notype','.xlsx','E:/2016/9/8\\ce3dded5-0d9a-4d35-a739-3f066c43b650.xlsx',1,NULL,NULL,NULL,NULL),(10,'张居开-满月跟进表.xlsx',11071,'notype','.xlsx','E:/2016/9/8\\755295e7-1ebe-4ec5-afeb-c14bf2d61221.xlsx',1,NULL,NULL,NULL,NULL),(11,'张居开-满月跟进表.xlsx',11071,'notype','.xlsx','E:/2016/9/8\\059be1a2-8893-4ba6-aae1-018bad85c0ee.xlsx',1,NULL,NULL,NULL,NULL),(12,'新建 Microsoft Word 文档.docx',0,'word','.docx','E:/2016/9/8\\f1a68a6d-b345-42f7-8e26-d3290a6af000.docx',1,NULL,NULL,NULL,NULL),(13,'张居开-满月跟进表.xlsx',11071,'excel','.xlsx','E:/2016/9/8\\07075d5f-2b67-422c-8815-3ecec0159376.xlsx',1,NULL,NULL,NULL,NULL),(14,'fine-uploader.zip',1233457,'notype','.zip','E:/2016/9/8\\01267752-4969-4739-a8f6-b673681ee78c.zip',1,NULL,NULL,NULL,NULL),(15,'d44de9504dcd9ec906e3ecdbaafa0041.gif',1722,'image','.gif','E:/2016/9/8\\7f3e8eb6-499e-4edb-995d-d0e66941a7b9.gif',1,NULL,NULL,NULL,NULL),(16,'签到数据20160920103901.xls',4608,'excel','.xls','E:/2016/9/8\\77f46e98-55a8-44e9-9cd5-453d5b55be87.xls',1,NULL,NULL,NULL,NULL),(17,'java并发编程实战pdf及源码.rar',9196965,'notype','.rar','E:/2016/9/8\\270dd681-ca43-4910-aee7-70e56728da32.rar',1,NULL,NULL,NULL,NULL),(18,'d439b6003af33a875f6c9278c65c10385343b54b.jpg',176846,'image','.jpg','E:/2016/9/8\\05384560-ebd7-4ee5-bb62-c80ec289308b.jpg',1,NULL,NULL,NULL,NULL),(19,'73014493435b19c56cab3f00309617e7.gif',123339,'image','.gif','E:/2016/9/8\\64658790-ec81-4a93-b705-d88ae6ec7d19.gif',1,NULL,NULL,NULL,NULL),(20,'13a88226cffc1e17a0053f954890f603728de9d9.jpg',35248,'image','.jpg','E:/2016/9/8\\d5a3409c-0de3-4be1-b476-47658a7f4c80.jpg',1,NULL,NULL,NULL,NULL),(21,'201609071045471745.gif',83498,'image','.gif','E:/2016/9/8\\597efdbc-15b8-4028-8ad4-eb8004f4b4ad.gif',1,NULL,NULL,NULL,NULL),(22,'d439b6003af33a875f6c9278c65c10385343b54b.jpg',176846,'image','.jpg','E:/2016/9/8\\e26dfe6e-9fad-4176-ba26-bdb9dfb88b8e.jpg',1,NULL,NULL,NULL,NULL),(23,'ppt.png',2118,'image','.png','E:/2016/9/8\\b9065dfe-ee6f-4415-898e-b70b588f0c79.png',1,NULL,NULL,NULL,NULL),(24,'张居开-满月跟进表.xlsx',11071,'excel','.xlsx','E:/2016/9/8\\3045e6ae-7631-4e1c-9125-8242f3474f6e.xlsx',1,NULL,NULL,NULL,NULL),(25,'bui-master.zip',21609062,'notype','.zip','E:/2016/9/8\\8a4ab504-8814-4340-9572-f25909cf1fce.zip',1,NULL,NULL,NULL,NULL);

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
  `mobile` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机号码',
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`userName`,`loginName`,`mobile`,`salt`,`password`,`lastLoginIp`,`lastLoginTime`,`createUserId`,`createTime`,`updateUserId`,`updateTime`,`isDelete`) values (1,'张居开','zjk','13880394348','b48690cd-78c8-4fcc-9df5-412308e607e0','a149a73da75fb4c9cfbd54ff2a1ea2d2','127.0.0.1','2016-09-30 11:34:30',0,'2016-09-22 14:25:07',1,'2016-09-30 11:04:02',1),(2,'张三','zhangsan·','13388370901','353e965e-c482-4fd3-842d-0cc584e64e12','a0a4d4ae95bbfb05a4173ff382cd7112',NULL,NULL,1,'2016-09-30 11:11:54',1,'2016-09-30 11:13:49',2),(3,'张三','zhangsan','13880394348','ae8e59f3-25fa-4b96-8993-467176e7f52e','1ba07f6b007e215114f24b450d614b56',NULL,NULL,1,'2016-09-30 11:23:16',1,'2016-09-30 11:28:02',1);

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
