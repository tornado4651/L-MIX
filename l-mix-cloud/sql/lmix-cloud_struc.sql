# ************************************************************
# 主机: 127.0.0.1 (MySQL 8.0.30)
# 数据库: lmix-cloud
# 备注：该项目中所有微服务公用一个数据库，用表前缀区分微服务，需要时再根据表前缀拆分
# ************************************************************

CREATE DATABASE IF NOT EXISTS `lmix`;
USE `lmix`;


# ------------------------------------------------------------
# lmix-cloud-admin微服务：用户基础表
# ------------------------------------------------------------

DROP TABLE IF EXISTS `a_user`;
CREATE TABLE `a_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '昵称',
  `gender` tinyint DEFAULT NULL COMMENT '性别（0女；1男）',
  `birthday` date DEFAULT NULL COMMENT '出生年月',
  `telephone` varchar(12) DEFAULT NULL COMMENT '联系电话',
  `avatar` varchar(255) DEFAULT 'https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif' COMMENT '头像',
  `status` tinyint DEFAULT NULL COMMENT '用户状态（0：锁定；1：正常）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = 'lmix-cloud-admin微服务：用户基础表';


# ------------------------------------------------------------
# lmix-cloud-admin微服务：角色表
# ------------------------------------------------------------

DROP TABLE IF EXISTS `a_role`;
CREATE TABLE `a_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '角色名称',
  `role_name` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '角色昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT = 'lmix-cloud-admin微服务：角色表';


# ------------------------------------------------------------
# 用户角色关联表 user_role
# ------------------------------------------------------------

DROP TABLE IF EXISTS `a_user_role`;
CREATE TABLE `a_user_role` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` int DEFAULT NULL COMMENT '用户主键',
  `role_id` int DEFAULT NULL COMMENT '角色主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

