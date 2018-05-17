/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : chatroomsystem

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2018-05-17 19:14:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for chatcontent
-- ----------------------------
DROP TABLE IF EXISTS `chatcontent`;
CREATE TABLE `chatcontent` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) NOT NULL,
  `roomid` int(4) NOT NULL,
  `userid` int(4) DEFAULT NULL,
  `username` varchar(50) NOT NULL,
  `date` datetime NOT NULL,
  `roomname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chatcontent
-- ----------------------------
INSERT INTO `chatcontent` VALUES ('1', '12', '1', '1', '王宝强', '2018-05-17 14:31:48', 'groud');
INSERT INTO `chatcontent` VALUES ('2', '1', '1', '1', '王宝强', '2018-05-17 14:35:19', 'groud');
INSERT INTO `chatcontent` VALUES ('3', '你是不是傻', '1', '1', '王宝强', '2018-05-17 14:35:34', 'groud');
INSERT INTO `chatcontent` VALUES ('4', '不是哇', '2', '1', '王宝强', '2018-05-17 14:35:40', '1');
INSERT INTO `chatcontent` VALUES ('5', '我跟你无话可说', '1', '3', '1', '2018-05-17 15:23:14', 'groud');
INSERT INTO `chatcontent` VALUES ('6', '1', '1', '1', '王宝强', '2018-05-17 15:25:38', 'groud');
INSERT INTO `chatcontent` VALUES ('7', '```', '2', '3', '1', '2018-05-17 15:26:17', '1');
INSERT INTO `chatcontent` VALUES ('8', '1', '1', null, '游客9113', '2018-05-17 18:22:40', 'groud');
INSERT INTO `chatcontent` VALUES ('9', '123123', '1', null, '游客9113', '2018-05-17 18:22:42', 'groud');
INSERT INTO `chatcontent` VALUES ('10', 'sdsd', '1', null, '游客9113', '2018-05-17 18:22:47', 'groud');
INSERT INTO `chatcontent` VALUES ('11', '12', '1', null, '游客909', '2018-05-17 18:44:16', 'groud');
INSERT INTO `chatcontent` VALUES ('12', '23', '1', null, '游客909', '2018-05-17 18:44:18', 'groud');
INSERT INTO `chatcontent` VALUES ('13', '1', '1', null, '王宝强', '2018-05-17 19:01:48', 'groud');
INSERT INTO `chatcontent` VALUES ('14', '1', '1', null, '王宝强', '2018-05-17 19:01:53', 'groud');
INSERT INTO `chatcontent` VALUES ('15', '1', '1', null, '王宝强', '2018-05-17 19:03:41', 'groud');
INSERT INTO `chatcontent` VALUES ('16', '1', '1', null, '王宝强', '2018-05-17 19:12:03', 'groud');

-- ----------------------------
-- Table structure for chatroom
-- ----------------------------
DROP TABLE IF EXISTS `chatroom`;
CREATE TABLE `chatroom` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `roomname` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of chatroom
-- ----------------------------
INSERT INTO `chatroom` VALUES ('1', 'groud');
INSERT INTO `chatroom` VALUES ('2', '1');
INSERT INTO `chatroom` VALUES ('3', '21');
INSERT INTO `chatroom` VALUES ('4', '王宝强');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'visitor');
INSERT INTO `role` VALUES ('2', 'user');
INSERT INTO `role` VALUES ('3', 'administrator');
INSERT INTO `role` VALUES ('4', 'super_admin');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `nickname` varchar(50) NOT NULL,
  `lastlogintime` datetime DEFAULT NULL,
  `lastchatroom` varchar(50) DEFAULT NULL,
  `mail` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '123', '202cb962ac59075b964b07152d234b70', '王宝强', null, null, '1213018820@qq.com');
INSERT INTO `user` VALUES ('2', '12c1', 'c4ca4238a0b923820dcc509a6f75849b', '1', null, null, '1');
INSERT INTO `user` VALUES ('3', '121', 'c4ca4238a0b923820dcc509a6f75849b', '1', null, null, '1');
INSERT INTO `user` VALUES ('4', '12', 'c20ad4d76fe97759aa27a0c99bff6710', '21', null, null, '12');

-- ----------------------------
-- Table structure for user_friend
-- ----------------------------
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
  `userid` int(4) DEFAULT NULL,
  `friendid` int(4) DEFAULT NULL,
  `remark` varchar(50) DEFAULT NULL,
  KEY `user` (`userid`) USING BTREE,
  KEY `friend` (`friendid`) USING BTREE,
  CONSTRAINT `friend` FOREIGN KEY (`friendid`) REFERENCES `user` (`id`) ON DELETE SET NULL,
  CONSTRAINT `user` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_friend
-- ----------------------------
INSERT INTO `user_friend` VALUES ('1', '3', '');
INSERT INTO `user_friend` VALUES ('1', '1', '');
INSERT INTO `user_friend` VALUES ('1', '4', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `userid` int(4) DEFAULT NULL,
  `roleid` int(4) DEFAULT NULL,
  KEY `usersecond` (`userid`) USING BTREE,
  KEY `role` (`roleid`) USING BTREE,
  CONSTRAINT `role` FOREIGN KEY (`roleid`) REFERENCES `role` (`id`) ON DELETE SET NULL,
  CONSTRAINT `usersecond` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------

-- ----------------------------
-- Table structure for user_room
-- ----------------------------
DROP TABLE IF EXISTS `user_room`;
CREATE TABLE `user_room` (
  `userid` int(4) DEFAULT NULL,
  `roomid` int(4) DEFAULT NULL,
  KEY `userthird` (`userid`),
  KEY `roomid` (`roomid`),
  CONSTRAINT `roomid` FOREIGN KEY (`roomid`) REFERENCES `chatroom` (`id`) ON DELETE SET NULL,
  CONSTRAINT `userthird` FOREIGN KEY (`userid`) REFERENCES `user` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_room
-- ----------------------------
INSERT INTO `user_room` VALUES ('1', '1');
INSERT INTO `user_room` VALUES ('2', '1');
INSERT INTO `user_room` VALUES ('3', '1');
INSERT INTO `user_room` VALUES ('4', '1');
INSERT INTO `user_room` VALUES ('1', '2');
INSERT INTO `user_room` VALUES ('3', '2');
INSERT INTO `user_room` VALUES ('1', '3');
INSERT INTO `user_room` VALUES ('4', '3');
INSERT INTO `user_room` VALUES ('1', '4');
