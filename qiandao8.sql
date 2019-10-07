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

 Date: 07/10/2019 21:55:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES (4, '第一次活动', '阿三', '2019-10-04 10:50:30', '2019-10-04 10:50:30', 1, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 34, '2019-10-04 22:15:05', '2019-10-04 22:15:05', 29);
INSERT INTO `activity` VALUES (5, '第一次活动', '阿三', '2019-10-04 10:50:30', '2019-10-05 10:50:30', 1, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-04 23:21:55', '2019-10-04 23:21:55', 29);
INSERT INTO `activity` VALUES (6, '第一次活动', '阿三', '2019-10-04 10:50:30', '2019-12-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 1, '2019-10-04 23:23:04', '2019-10-04 23:23:04', 29);
INSERT INTO `activity` VALUES (7, '第而次活动', '阿三', '2018-10-04 10:50:30', '2018-12-04 10:50:30', 1, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:36:28', '2019-10-05 14:36:28', 29);
INSERT INTO `activity` VALUES (8, '第而次活动', '阿三', '2018-10-04 10:50:30', '2018-12-04 10:50:30', 1, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:36:30', '2019-10-05 14:36:30', 29);
INSERT INTO `activity` VALUES (9, '第而次活动', '阿三', '2018-10-04 10:50:30', '2018-12-04 10:50:30', 1, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:36:32', '2019-10-05 14:36:32', 29);
INSERT INTO `activity` VALUES (10, '第而次活动', '阿三', '2018-10-04 10:50:30', '2018-12-04 10:50:30', 1, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:36:34', '2019-10-05 14:36:34', 29);
INSERT INTO `activity` VALUES (11, '第而次活动', '阿三', '2018-10-04 10:50:30', '2018-12-04 10:50:30', 1, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:36:35', '2019-10-05 14:36:35', 29);
INSERT INTO `activity` VALUES (12, '第3次活动', '阿三', '2018-10-04 10:50:30', '2018-12-04 10:50:30', 1, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:36:46', '2019-10-05 14:36:46', 29);
INSERT INTO `activity` VALUES (13, '第3次活动', '阿三', '2018-10-04 10:50:30', '2028-12-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:36:48', '2019-10-05 14:36:48', 29);
INSERT INTO `activity` VALUES (14, '第123次活动', '阿三', '2018-10-04 10:50:30', '2028-12-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:36:51', '2019-10-05 14:36:51', 29);
INSERT INTO `activity` VALUES (15, '第123次活动', '阿三', '2038-10-04 10:50:30', '2038-10-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:38:57', '2019-10-05 14:38:57', 29);
INSERT INTO `activity` VALUES (16, '第123次活动', '阿三', '2038-10-04 10:50:30', '2038-10-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:38:58', '2019-10-05 14:38:58', 29);
INSERT INTO `activity` VALUES (17, '第123次活动', '阿三', '2038-10-04 10:50:30', '2048-12-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:39:08', '2019-10-05 14:39:08', 29);
INSERT INTO `activity` VALUES (18, '第112323次活动', '阿三', '2038-10-04 10:50:30', '2048-12-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:39:11', '2019-10-05 14:39:11', 29);
INSERT INTO `activity` VALUES (19, '第112323次活动', '阿三', '2038-10-04 10:50:30', '2048-12-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:39:12', '2019-10-05 14:39:12', 29);
INSERT INTO `activity` VALUES (20, '第11232223次活动', '阿三', '2038-10-04 10:50:30', '2048-12-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:40:30', '2019-10-05 14:40:30', 29);
INSERT INTO `activity` VALUES (21, '第11232223次活动', '阿三', '2038-10-04 10:50:30', '2058-12-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:40:32', '2019-10-05 14:40:32', 29);
INSERT INTO `activity` VALUES (22, '第11232223次活动', '阿三', '2038-10-04 10:50:30', '2058-12-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:40:33', '2019-10-05 14:40:33', 29);
INSERT INTO `activity` VALUES (23, '第11232223次活动', '阿三', '2038-10-04 10:50:30', '2058-12-04 10:50:30', 0, 0, '[{\"title\":\"姓名\",\"remarks\":\"aaa\",\"content\":null},{\"title\":\"手机\",\"remarks\":null,\"content\":null},{\"title\":\"呵呵\",\"remarks\":\"123\",\"content\":null}]', '[{\"title\":\"性别\",\"options\":{\"女\":false,\"男\":false}},{\"title\":\"年龄\",\"options\":{\"40\":false,\"30\":false,\"20\":false}}]', 0, '2019-10-05 14:40:34', '2019-10-05 14:40:34', 29);

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `event_id` bigint(20) DEFAULT NULL,
  `basic_selc_info` text CHARACTER SET utf8 COLLATE utf8_bin,
  `list_selc_info` text CHARACTER SET utf8 COLLATE utf8_bin,
  `status` int(11) DEFAULT NULL,
  `ip_addr` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `check_in_time` datetime(0) DEFAULT NULL,
  `current_location` varchar(80) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `verify_password` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES (1, 6, '123', '321', 0, '0:0:0:0:0:0:0:1', '2019-10-06 12:46:55', NULL, NULL);
INSERT INTO `attendance` VALUES (2, 6, '123', '32133', 0, '0:0:0:0:0:0:0:1', '2019-10-06 13:01:56', NULL, NULL);

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
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES (1, 'asan', '3123', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES (2, '123123', 'ddd', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES (3, 'sssd', '啊', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES (4, '4123123', '额', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES (5, '1231', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES (6, 'dasdfas', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES (7, 'qewr', '312322', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES (8, 'asan2', NULL, 'fuckyou', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES (11, 'asan2', NULL, 'fuckyou', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES (12, 'asan2', NULL, 'fuckyou', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `user_info` VALUES (17, 'dd', '123123', '4f6faa2b1ba38badea0f436aed98b18f', NULL, '123123', '2019-10-03 12:15:35.000000', '2019-10-04 22:10:44.000000', NULL);
INSERT INTO `user_info` VALUES (18, 'dd', 'null', '0634a925b27c34765093bfd4ec409ca8', NULL, NULL, '2019-10-03 12:16:22.000000', '2019-10-03 12:16:22.000000', NULL);
INSERT INTO `user_info` VALUES (21, 'asan', 'asan', '0634a925b27c34765093bfd4ec409ca8', NULL, NULL, '2019-10-03 12:18:40.000000', '2019-10-03 12:18:40.000000', NULL);
INSERT INTO `user_info` VALUES (23, 'asan', 'asan3', '8c12947042229b56a5acd67c1feee2dc', NULL, NULL, '2019-10-03 12:18:52.000000', '2019-10-03 12:18:52.000000', NULL);
INSERT INTO `user_info` VALUES (25, 'asan', 'a3san3', '8c12947042229b56a5acd67c1feee2dc', '123123@qq.com', '123123123', '2019-10-03 12:20:02.000000', '2019-10-03 12:20:02.000000', NULL);
INSERT INTO `user_info` VALUES (26, 'asan', '1', '6d746219a0d251f6ce37c03e6cf45b4f', '123123@qq.com', '123123123', '2019-10-03 12:25:16.000000', '2019-10-03 12:25:16.000000', NULL);
INSERT INTO `user_info` VALUES (28, '阿三', '21', 'de5565df8e98aff814836887a4f3828d', '2122', NULL, '2019-10-03 12:27:23.000000', '2019-10-03 19:07:43.000000', NULL);
INSERT INTO `user_info` VALUES (29, 'asan', 'admin', 'a0570a8475f84ea59e7f08420fafd7bf', '123123@qq.com', '123123123', '2019-10-03 21:07:20.000000', '2019-10-03 21:07:20.000000', '2019-10-07 18:12:02');

SET FOREIGN_KEY_CHECKS = 1;
