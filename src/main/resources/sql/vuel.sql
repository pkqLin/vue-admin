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

 Date: 16/06/2023 18:59:14
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
) ENGINE = MyISAM AUTO_INCREMENT = 444 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('user', 'el-icon-user-solid', 'icon', 0, 1);
INSERT INTO `sys_dict` VALUES ('file', 'el-icon-document', 'icon', NULL, 2);
INSERT INTO `sys_dict` VALUES ('main', 'el-icon-house', 'icon', NULL, 3);
INSERT INTO `sys_dict` VALUES ('menu', 'el-icon-menu', 'icon', NULL, 4);
INSERT INTO `sys_dict` VALUES ('s-grid', 'el-icon-s-grid', 'icon', NULL, 5);
INSERT INTO `sys_dict` VALUES ('data', 'el-icon-s-data', 'icon', 0, 433);
INSERT INTO `sys_dict` VALUES ('userRole', 'el-icon-user', 'icon', 0, 434);
INSERT INTO `sys_dict` VALUES ('location', 'el-icon-map-location', 'icon', 0, 435);
INSERT INTO `sys_dict` VALUES ('tools', 'el-icon-s-tools', 'icon', 0, 436);
INSERT INTO `sys_dict` VALUES ('homeCompany', 'el-icon-s-home', 'icon', 0, 443);

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
  `md5` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'md5校验',
  `is_del` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `enable` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '是否禁用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `index_md5`(`md5`) USING BTREE COMMENT 'md5索引'
) ENGINE = MyISAM AUTO_INCREMENT = 8 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES (6, 'R-C.jpg', 'jpg', 16, 'http://localhost:8091/file/61c24ad792314b788efbee329826df0a.jpg', '42b0ffd25b6dd1f691b2c3f06d47855d', 0, 1);
INSERT INTO `sys_file` VALUES (5, '微信截图_20230404110809.png', 'png', 25, 'http://localhost:8091/file/2ef241545f5f44219a3a9371d4153448.png', 'e4796a3046fa7aebb726c0d44d446084', 0, 1);
INSERT INTO `sys_file` VALUES (7, '微信截图_20230404110828.png', 'png', 27, 'http://localhost:8091/file/f628419bcfe346e5a2e6d9fd29ab6ca3.png', 'b5e734faada8b4a2cf48ee39ae67b9d3', 0, 1);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `pid` int(11) NULL DEFAULT NULL COMMENT '父id',
  `page_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '页面路径',
  `sort_num` int(11) NULL DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 21712910 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (21712897, '主页', '/home', 'el-icon-house', '1', NULL, 'Home', 1);
INSERT INTO `sys_menu` VALUES (21712899, '系统管理', NULL, 'el-icon-s-grid', NULL, NULL, NULL, 2);
INSERT INTO `sys_menu` VALUES (21712900, '用户管理', '/user', 'el-icon-user', NULL, 21712899, 'User', NULL);
INSERT INTO `sys_menu` VALUES (21712901, '角色管理', '/role', 'el-icon-s-custom', NULL, 21712899, 'Role', 2);
INSERT INTO `sys_menu` VALUES (21712902, '文件管理', '/file', 'el-icon-document', NULL, 21712899, 'File', 3);
INSERT INTO `sys_menu` VALUES (21712903, '菜单管理', '/menu', 'el-icon-menu', NULL, 21712899, 'Menu', 4);
INSERT INTO `sys_menu` VALUES (21712904, '高德地图', '/map', 'el-icon-map-location', '高德地图', NULL, 'Map', 1);
INSERT INTO `sys_menu` VALUES (21712906, '字典管理', '/dict', 'el-icon-s-data', NULL, 21712899, 'Dict', 5);
INSERT INTO `sys_menu` VALUES (21712908, '组织管理', '/org', 'el-icon-s-home', NULL, 21712899, 'Org', 6);
INSERT INTO `sys_menu` VALUES (21712909, '中国地图', '/echartMap', 'el-icon-map-location', NULL, NULL, 'EchartMap', 3);

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

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限',
  `flag` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一标识',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 1904979972 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1904979970, '管理员', 'ROLE_ADMIN', '管理员');
INSERT INTO `sys_role` VALUES (1904979971, '普通用户', 'ROLE_USER', '普通用户');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` int(11) NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL
) ENGINE = MyISAM CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Fixed;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (1904979970, 21712908);
INSERT INTO `sys_role_menu` VALUES (1904979970, 21712906);
INSERT INTO `sys_role_menu` VALUES (1904979970, 21712903);
INSERT INTO `sys_role_menu` VALUES (1904979970, 21712902);
INSERT INTO `sys_role_menu` VALUES (1904979970, 21712901);
INSERT INTO `sys_role_menu` VALUES (1904979970, 21712900);
INSERT INTO `sys_role_menu` VALUES (1904979970, 21712899);
INSERT INTO `sys_role_menu` VALUES (1904979970, 21712904);
INSERT INTO `sys_role_menu` VALUES (1904979970, 21712897);
INSERT INTO `sys_role_menu` VALUES (1904979971, 21712902);
INSERT INTO `sys_role_menu` VALUES (1904979971, 21712899);
INSERT INTO `sys_role_menu` VALUES (1904979971, 21712897);
INSERT INTO `sys_role_menu` VALUES (1904979970, 21712909);

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
  `role` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = MyISAM AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '组织表\r\n' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', 'admin', '管理员', 'admin@qq.com', '18888888888', '上海', '2023-04-06 14:57:14', NULL, '2023-04-06 14:57:14', NULL, 'http://localhost:8091/file/f628419bcfe346e5a2e6d9fd29ab6ca3.png', 'ROLE_ADMIN');
INSERT INTO `sys_user` VALUES (2, '张三', 'zhangsan', '张三', 'zhangsan@qq.com', '18888888888', '上海', '2022-11-24 16:47:46', NULL, '2022-11-24 16:47:46', NULL, NULL, 'ROLE_USER');
INSERT INTO `sys_user` VALUES (3, '李四', 'lisi', '李四呂', 'zlisin@qq.com', '18888888888', '上海', '2023-04-06 11:34:41', 'admin', '2023-04-06 11:34:41', 'admin', NULL, NULL);
INSERT INTO `sys_user` VALUES (4, '王五', 'li1si', '王五吕', 'zl1isin@qq.com', '18888888888', '上海', '2023-04-06 11:34:43', 'admin', '2023-04-06 11:34:43', 'admin', NULL, NULL);
INSERT INTO `sys_user` VALUES (5, '哈哈', '131423', '哈哈', 'zl1isin@qq.com', '18888888888', '上海', '2022-11-24 15:52:21', 'admin', '2022-11-24 15:52:21', 'admin', NULL, NULL);
INSERT INTO `sys_user` VALUES (6, '哈哈1', '1314213', '哈哈1', 'zl1isin@qq.com', '18888888888', '上海', '2022-11-24 15:52:29', 'admin', '2022-11-24 15:52:29', 'admin', NULL, NULL);
INSERT INTO `sys_user` VALUES (7, '哈哈2', '1314212', '哈哈2', 'zl1isin@qq.com', '18888888888', '上海', '2022-11-24 15:52:37', 'admin', '2022-11-24 15:52:37', 'admin', NULL, NULL);
INSERT INTO `sys_user` VALUES (8, '久久', 'jiujiu', '久久', 'zl1isin@qq.com', '18888888888', '上海', '2022-11-24 15:52:56', 'admin', '2022-11-24 15:52:56', 'admin', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
