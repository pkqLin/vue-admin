/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : vuel

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 14/06/2023 18:03:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `pid` int(9) NULL DEFAULT NULL COMMENT 'pid',
  `id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('user ', 'el-icon-s-custom', 'icon', NULL, 1);
INSERT INTO `sys_dict` VALUES ('file', 'el-icon-document', 'icon', NULL, 2);
INSERT INTO `sys_dict` VALUES ('main', 'el-icon-house', 'icon', NULL, 3);
INSERT INTO `sys_dict` VALUES ('menu', 'el-icon-menu', 'icon', NULL, 4);
INSERT INTO `sys_dict` VALUES ('s-grid', 'el-icon-s-grid', 'icon', NULL, 5);

SET FOREIGN_KEY_CHECKS = 1;
