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

LOCK TABLES `a_user` WRITE;
INSERT INTO `a_user` (`id`, `username`, `password`, `nickname`, `gender`, `birthday`, `telephone`, `avatar`, `status`)
VALUES (1,'admin','123456','管理员',1,'1996-01-01','13888888888','https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif',1);
UNLOCK TABLES;


# ------------------------------------------------------------
# lmix-cloud-admin微服务：角色表
# ------------------------------------------------------------

LOCK TABLES `a_role` WRITE;
insert into `a_role` (id, name, role_name)
values  (1, 'ADMIN', '超级管理员'),
        (2, 'AUDIT', '审计人员'),
        (3, 'NORMAL', '普通用户'),
        (4, 'VIP', 'VIP用户');
UNLOCK TABLES;


# ------------------------------------------------------------
# lmix-cloud-admin微服务: 用户角色关联表
# ------------------------------------------------------------

LOCK TABLES `a_user_role` WRITE;
insert into `a_user_role` (id, user_id, role_id)
values  (1, 1, 1);
UNLOCK TABLES;



