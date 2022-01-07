/*
Navicat MySQL Data Transfer

Source Server         : test1
Source Server Version : 50729
Source Host           : localhost:3306
Source Database       : invoice

Target Server Type    : MYSQL
Target Server Version : 50729
File Encoding         : 65001

Date: 2022-01-07 09:53:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for a_order
-- ----------------------------
DROP TABLE IF EXISTS `a_order`;
CREATE TABLE `a_order` (
  `order_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `state` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,
  `profit` double(10,2) DEFAULT NULL,
  `total` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_order
-- ----------------------------
INSERT INTO `a_order` VALUES ('6', '待支付', '2021-12-09 10:38:30', '6', '5.00', '10.00');
INSERT INTO `a_order` VALUES ('7', '待支付', '2021-12-09 10:51:15', '6', '5.00', '10.00');
INSERT INTO `a_order` VALUES ('8', '已保存', '2021-12-09 10:51:22', '6', '5.00', '10.00');
INSERT INTO `a_order` VALUES ('19', '已完成', '2021-12-09 19:54:53', '8', '61.00', '70.00');
INSERT INTO `a_order` VALUES ('20', '已保存', '2021-12-09 19:57:33', '4', '55.00', '60.00');
INSERT INTO `a_order` VALUES ('22', '待审核', '2021-12-09 20:03:32', '4', '55.00', '60.00');
INSERT INTO `a_order` VALUES ('23', '已完成', '2021-12-09 20:03:37', '4', '55.00', '60.00');
INSERT INTO `a_order` VALUES ('24', '已保存', '2021-12-09 20:06:33', '8', '61.00', '70.00');
INSERT INTO `a_order` VALUES ('28', '已保存', '2021-12-09 21:10:11', '5', '2.00', '7.00');
INSERT INTO `a_order` VALUES ('31', '已保存', '2022-01-03 17:17:43', '8', '-5.00', '5.00');
INSERT INTO `a_order` VALUES ('34', '待审核', '2022-01-04 19:54:40', '9', '-5.00', '5.00');
INSERT INTO `a_order` VALUES ('35', '已保存', '2022-01-04 19:54:59', '9', '-5.00', '5.00');
INSERT INTO `a_order` VALUES ('38', '已退货', '2022-01-05 16:43:45', '6', '-31.00', '0.00');
INSERT INTO `a_order` VALUES ('40', '已退货', '2022-01-06 11:25:56', '4', '-50.00', '50.00');
INSERT INTO `a_order` VALUES ('41', '已完成', '2022-01-06 11:30:34', '6', '100.00', '200.00');
INSERT INTO `a_order` VALUES ('42', '待审核', '2022-01-06 12:20:40', '6', '50.00', '150.00');
INSERT INTO `a_order` VALUES ('43', '待支付', '2022-01-06 12:21:48', '0', '5.00', '15.00');
INSERT INTO `a_order` VALUES ('44', '待支付', '2022-01-06 12:24:30', '0', '100.00', '200.00');
INSERT INTO `a_order` VALUES ('45', '已完成', '2022-01-06 12:28:26', '0', '100.00', '200.00');
INSERT INTO `a_order` VALUES ('46', '已退货', '2022-01-06 12:30:12', '0', '100.00', '200.00');
INSERT INTO `a_order` VALUES ('47', '待出库', '2022-01-06 12:30:37', '0', '100.00', '200.00');
INSERT INTO `a_order` VALUES ('48', '已完成', '2022-01-06 12:31:27', '6', '50.00', '150.00');
INSERT INTO `a_order` VALUES ('49', '待出库', '2022-01-06 16:33:05', '6', '-83.00', '30.00');

-- ----------------------------
-- Table structure for a_user
-- ----------------------------
DROP TABLE IF EXISTS `a_user`;
CREATE TABLE `a_user` (
  `account` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `pwd` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=12147 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_user
-- ----------------------------
INSERT INTO `a_user` VALUES ('1', '111111');
INSERT INTO `a_user` VALUES ('12138', '111111');
INSERT INTO `a_user` VALUES ('12139', '111111');
INSERT INTO `a_user` VALUES ('12140', 'a123456');
INSERT INTO `a_user` VALUES ('12141', '111111');
INSERT INTO `a_user` VALUES ('12142', '111111');
INSERT INTO `a_user` VALUES ('12143', '111111');
INSERT INTO `a_user` VALUES ('12144', '111111');
INSERT INTO `a_user` VALUES ('12145', '111111');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('0', '零售客户', null, null);
INSERT INTO `customer` VALUES ('4', '张三', '18912345678', '西大直街92号');
INSERT INTO `customer` VALUES ('5', '小刚', '18912345678', '西大直街92号');
INSERT INTO `customer` VALUES ('6', '小白', '18912345678', '西大直街92号');
INSERT INTO `customer` VALUES ('7', '李四', '18912345678', '西大直街92号');
INSERT INTO `customer` VALUES ('8', '王五', '18912345678', '西大直街92号');
INSERT INTO `customer` VALUES ('9', '小明', '18912345678', '西大直街92号');
INSERT INTO `customer` VALUES ('13', '张三疯', '18959064444', '西大直街92号');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `account` bigint(20) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '12140', '店长', '赵四', '13859091234', '24', '男');
INSERT INTO `employee` VALUES ('5', '12138', '仓库管理员', '张三', '18912345678', '0', '男');
INSERT INTO `employee` VALUES ('6', '12139', '销售员', '李四', '18912345678', '44', '男');
INSERT INTO `employee` VALUES ('8', '12141', '收银员', '王五', '18912345678', '45', '男');
INSERT INTO `employee` VALUES ('11', '12144', '销售员', '张四', '18912345678', '45', '男');
INSERT INTO `employee` VALUES ('12', '12145', '销售员', '张大炮', '12345678945', '25', '男');

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `input_price` double(10,2) DEFAULT NULL,
  `retail_price` double(10,2) DEFAULT NULL,
  `wholesale_price` double(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES ('1', 'sad', 'asd', '20.00', '20.10', '30.10');
INSERT INTO `good` VALUES ('8', '铅笔', '这是另一根铅笔', '5.00', '7.00', '8.00');
INSERT INTO `good` VALUES ('9', '铅笔', '这是一根铅笔', '4.00', '10.00', '8.00');
INSERT INTO `good` VALUES ('10', '笔记本-蓝', 'x1234', '10.00', '20.00', '15.00');
INSERT INTO `good` VALUES ('11', '笔记本-蓝', 'x12345', '31.00', '40.00', '35.00');
INSERT INTO `good` VALUES ('12', '尺子-长', '长尺子', '2.00', '0.00', '0.00');
INSERT INTO `good` VALUES ('13', '尺子-短', '短尺子', '1.00', '0.00', '0.00');
INSERT INTO `good` VALUES ('14', '钢笔', '这是一根钢笔', '50.00', '0.00', '0.00');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `item_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL,
  `good_id` bigint(20) DEFAULT NULL,
  `num` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item` VALUES ('12', '6', '8', '1');
INSERT INTO `order_item` VALUES ('14', '7', '8', '9');
INSERT INTO `order_item` VALUES ('15', '8', '8', '9');
INSERT INTO `order_item` VALUES ('36', '19', '8', '1');
INSERT INTO `order_item` VALUES ('37', '19', '9', '2');
INSERT INTO `order_item` VALUES ('38', '20', '8', '2');
INSERT INTO `order_item` VALUES ('39', '22', '8', '2');
INSERT INTO `order_item` VALUES ('40', '23', '8', '5');
INSERT INTO `order_item` VALUES ('41', '24', '8', '1');
INSERT INTO `order_item` VALUES ('43', '24', '9', '2');
INSERT INTO `order_item` VALUES ('50', '28', '8', '3');
INSERT INTO `order_item` VALUES ('56', '31', '10', '2');
INSERT INTO `order_item` VALUES ('60', '34', '10', '10');
INSERT INTO `order_item` VALUES ('61', '35', '10', '2');
INSERT INTO `order_item` VALUES ('66', '38', '11', '6');
INSERT INTO `order_item` VALUES ('69', '40', '10', '10');
INSERT INTO `order_item` VALUES ('70', '41', '10', '10');
INSERT INTO `order_item` VALUES ('71', '42', '10', '10');
INSERT INTO `order_item` VALUES ('72', '43', '10', '10');
INSERT INTO `order_item` VALUES ('73', '44', '10', '10');
INSERT INTO `order_item` VALUES ('74', '45', '10', '10');
INSERT INTO `order_item` VALUES ('75', '46', '10', '10');
INSERT INTO `order_item` VALUES ('76', '47', '10', '10');
INSERT INTO `order_item` VALUES ('77', '48', '10', '10');
INSERT INTO `order_item` VALUES ('78', '49', '10', '2');
INSERT INTO `order_item` VALUES ('79', '49', '11', '3');

-- ----------------------------
-- Table structure for repository
-- ----------------------------
DROP TABLE IF EXISTS `repository`;
CREATE TABLE `repository` (
  `repository_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `classification` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`repository_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repository
-- ----------------------------
INSERT INTO `repository` VALUES ('1', '大库', '黄河路90号', '13066666666');
INSERT INTO `repository` VALUES ('2', '门店', '西大直街91#', '1234567');
INSERT INTO `repository` VALUES ('4', '大库', '西大直街92号', '18912345678');
INSERT INTO `repository` VALUES ('6', '门店', '西大直街92号', '18912345678');
INSERT INTO `repository` VALUES ('7', '门店', '西大直街98号', '18912345678');

-- ----------------------------
-- Table structure for repository_item
-- ----------------------------
DROP TABLE IF EXISTS `repository_item`;
CREATE TABLE `repository_item` (
  `item_id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `repository_id` bigint(20) DEFAULT NULL,
  `good_id` bigint(20) DEFAULT NULL,
  `num` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of repository_item
-- ----------------------------
INSERT INTO `repository_item` VALUES ('4', '4', '10', '105');
INSERT INTO `repository_item` VALUES ('5', '5', '10', '26');
INSERT INTO `repository_item` VALUES ('6', '2', '8', '140');
INSERT INTO `repository_item` VALUES ('7', '2', '9', '147');
INSERT INTO `repository_item` VALUES ('11', '1', '11', '465');
INSERT INTO `repository_item` VALUES ('12', '1', '12', '300');
INSERT INTO `repository_item` VALUES ('13', '1', '13', '200');
INSERT INTO `repository_item` VALUES ('14', '2', '10', '173');
INSERT INTO `repository_item` VALUES ('15', '2', '11', '200');
INSERT INTO `repository_item` VALUES ('19', '1', '10', '111');
INSERT INTO `repository_item` VALUES ('20', '4', '8', '500');
INSERT INTO `repository_item` VALUES ('21', '6', '8', '500');
INSERT INTO `repository_item` VALUES ('22', '7', '8', '500');
