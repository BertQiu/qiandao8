/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : qiandao8

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 20/10/2019 13:03:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `originator` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `start_time` datetime(0) DEFAULT NULL,
  `end_time` datetime(0) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `basic_selc` text CHARACTER SET utf8 COLLATE utf8_bin,
  `list_selc` text CHARACTER SET utf8 COLLATE utf8_bin,
  `participants_nums` int(11) DEFAULT NULL,
  `create_time` datetime(0) DEFAULT NULL,
  `update_time` datetime(0) DEFAULT NULL,
  `originator_id` bigint(20) DEFAULT NULL,
  `static_qrcode_token` char(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  `rule_excel_name` char(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 72 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `activity_id` bigint(20) DEFAULT NULL,
  `basic_selc_info` text CHARACTER SET utf8 COLLATE utf8_bin,
  `list_selc_info` text CHARACTER SET utf8 COLLATE utf8_bin,
  `status` int(11) DEFAULT NULL,
  `ip_addr` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `check_in_time` datetime(0) DEFAULT NULL,
  `current_location` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `verify_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `password` char(32) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `phone_number` char(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_time` datetime(6) DEFAULT NULL,
  `update_time` datetime(6) DEFAULT NULL,
  `last_login_time` datetime(0) DEFAULT NULL,
  `ip_addr` char(15) CHARACTER SET latin1 COLLATE latin1_swedish_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 60 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
