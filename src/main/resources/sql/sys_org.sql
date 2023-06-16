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

 Date: 16/06/2023 18:56:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `org_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织编码',
  `org_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织名称',
  `level` int(11) NULL DEFAULT NULL COMMENT '所在等级',
  `pcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父节点',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 7 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_org
-- ----------------------------
INSERT INTO `sys_org` VALUES (1, '0', '中国人寿有限公司', 0, '-1', '2023-06-15 14:23:55', NULL, '2023-06-15 14:23:55', NULL);
INSERT INTO `sys_org` VALUES (2, '1', '上海分公司', 1, '0', '2023-06-15 14:24:26', NULL, '2023-06-15 14:24:26', NULL);
INSERT INTO `sys_org` VALUES (3, '11', '科技部', 2, '1', '2023-06-15 14:39:52', NULL, '2023-06-15 14:39:52', NULL);
INSERT INTO `sys_org` VALUES (4, '2', '北京分公司', 1, '0', '2023-06-15 14:40:07', NULL, '2023-06-15 14:40:07', NULL);
INSERT INTO `sys_org` VALUES (5, '22', '北京研发中心', 2, '2', '2023-06-15 14:40:37', NULL, '2023-06-15 14:40:37', NULL);
INSERT INTO `sys_org` VALUES (6, '12', '上海数据中心', 1, '1', '2023-06-15 14:41:35', NULL, '2023-06-15 14:41:35', NULL);

SET FOREIGN_KEY_CHECKS = 1;
