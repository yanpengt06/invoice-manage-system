/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : invoicing

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2021-12-08 10:20:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_order
-- ----------------------------
DROP TABLE IF EXISTS `a_order`;
CREATE TABLE `a_order` (
  `order_id` bigint(20) NOT NULL,
  `state` varchar(255) default NULL,
  `time` datetime default NULL,
  `customer_id` bigint(20) default NULL,
  `profit` double(10,2) default NULL,
  `total` double(10,2) default NULL,
  PRIMARY KEY  (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_order
-- ----------------------------
INSERT INTO `a_order` VALUES ('1', '待提交', '2021-12-01 16:47:26', '12', '12312.00', null);

-- ----------------------------
-- Table structure for a_user
-- ----------------------------
DROP TABLE IF EXISTS `a_user`;
CREATE TABLE `a_user` (
  `account` bigint(20) NOT NULL,
  `pwd` varchar(255) default NULL,
  PRIMARY KEY  (`account`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_user
-- ----------------------------
INSERT INTO `a_user` VALUES ('1', 'sd');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `classification` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) NOT NULL,
  `account` bigint(20) default NULL,
  `position` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `age` int(11) default NULL,
  `sex` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `input_price` double(10,2) default NULL,
  `retail_price` double(10,2) default NULL,
  `wholesale_price` double(10,2) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES ('1', 'sad', 'asd', '12.00', '13.00', '13.00');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `item_id` bigint(20) NOT NULL,
  `order_id` bigint(20) default NULL,
  `good_id` bigint(20) default NULL,
  `num` bigint(20) default NULL,
  PRIMARY KEY  (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------

-- ----------------------------
-- Table structure for repository
-- ----------------------------
DROP TABLE IF EXISTS `repository`;
CREATE TABLE `repository` (
  `repository_id` bigint(20) NOT NULL,
  `classification` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  PRIMARY KEY  (`repository_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repository
-- ----------------------------

-- ----------------------------
-- Table structure for repository_item
-- ----------------------------
DROP TABLE IF EXISTS `repository_item`;
CREATE TABLE `repository_item` (
  `item_id` bigint(20) NOT NULL,
  `repository_id` bigint(20) default NULL,
  `good_id` bigint(20) default NULL,
  `num` bigint(20) default NULL,
  PRIMARY KEY  (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repository_item
-- ----------------------------
