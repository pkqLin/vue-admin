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

 Date: 04/04/2023 17:27:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型',
  `size` bigint(10) NULL DEFAULT NULL COMMENT '大小',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '下载链接',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `enable` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'email',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `create_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '更新人',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '管理员', 'admin@qq.com', '18888888888', '上海', '2022-11-24 16:47:42', NULL, '2022-11-24 16:47:42', NULL, NULL);
INSERT INTO `sys_user` VALUES (2, '张三', 'zhangsan', '张三', 'zhangsan@qq.com', '18888888888', '上海', '2022-11-24 16:47:46', NULL, '2022-11-24 16:47:46', NULL, NULL);
INSERT INTO `sys_user` VALUES (3, '李四', 'lisi', '李四', 'zlisin@qq.com', '18888888888', '上海', '2022-11-24 15:51:48', 'admin', '2022-11-24 15:51:48', 'admin', NULL);
INSERT INTO `sys_user` VALUES (4, '王五', 'li1si', '王五', 'zl1isin@qq.com', '18888888888', '上海', '2022-11-24 15:52:09', 'admin', '2022-11-24 15:52:09', 'admin', NULL);
INSERT INTO `sys_user` VALUES (5, '哈哈', '131423', '哈哈', 'zl1isin@qq.com', '18888888888', '上海', '2022-11-24 15:52:21', 'admin', '2022-11-24 15:52:21', 'admin', NULL);
INSERT INTO `sys_user` VALUES (6, '哈哈1', '1314213', '哈哈1', 'zl1isin@qq.com', '18888888888', '上海', '2022-11-24 15:52:29', 'admin', '2022-11-24 15:52:29', 'admin', NULL);
INSERT INTO `sys_user` VALUES (7, '哈哈2', '1314212', '哈哈2', 'zl1isin@qq.com', '18888888888', '上海', '2022-11-24 15:52:37', 'admin', '2022-11-24 15:52:37', 'admin', NULL);
INSERT INTO `sys_user` VALUES (8, '久久', 'jiujiu', '久久', 'zl1isin@qq.com', '18888888888', '上海', '2022-11-24 15:52:56', 'admin', '2022-11-24 15:52:56', 'admin', NULL);
INSERT INTO `sys_user` VALUES (9, 'zhangsan111', NULL, '张三112', 'zhangsan@qq.com', NULL, NULL, '2022-11-25 16:50:14', NULL, '2022-11-25 16:50:14', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
