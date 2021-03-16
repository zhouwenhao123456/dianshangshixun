/*
 Navicat Premium Data Transfer

 Source Server         : 阿炜
 Source Server Type    : MySQL
 Source Server Version : 50536
 Source Host           : localhost:3306
 Source Schema         : template

 Target Server Type    : MySQL
 Target Server Version : 50536
 File Encoding         : 65001

 Date: 09/06/2020 09:46:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES (1, 'admin', '123456', 'admin');
INSERT INTO `t_admin` VALUES (2, 'business', '123456', 'business');
INSERT INTO `t_admin` VALUES (3, 'supplier', '123456', 'supplier');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `no` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `proName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` double(255, 3) NULL DEFAULT NULL,
  `orderState` int(1) NOT NULL,
  `payState` bit(1) NOT NULL,
  `isAccept` bit(1) NOT NULL,
  `sendState` bit(1) NOT NULL,
  PRIMARY KEY (`no`) USING BTREE,
  INDEX `proName`(`proName`) USING BTREE,
  INDEX `t_order_ibfk_1`(`username`) USING BTREE,
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`username`) REFERENCES `t_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_order_ibfk_2` FOREIGN KEY (`proName`) REFERENCES `t_product` (`proName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 45687674 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product`  (
  `no` int(11) NOT NULL,
  `proName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `proInfo` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `money` double(255, 3) NULL DEFAULT NULL,
  `num` int(11) NULL DEFAULT NULL,
  `type` int(1) NULL DEFAULT NULL,
  PRIMARY KEY (`no`) USING BTREE,
  UNIQUE INDEX `proName`(`proName`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_shoppingcar
-- ----------------------------
DROP TABLE IF EXISTS `t_shoppingcar`;
CREATE TABLE `t_shoppingcar`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `proName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `money` double(255, 3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `username`(`username`) USING BTREE,
  INDEX `proName`(`proName`) USING BTREE,
  CONSTRAINT `t_shoppingcar_ibfk_1` FOREIGN KEY (`username`) REFERENCES `t_user` (`username`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_shoppingcar_ibfk_2` FOREIGN KEY (`proName`) REFERENCES `t_product` (`proName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `info` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
