SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `parent_id`   bigint                                                        NOT NULL DEFAULT '0' COMMENT '父菜单ID',
    `name`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '权限名称',
    `permission`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限标识',
    `type`        tinyint                                                       NOT NULL COMMENT '菜单类型（1：菜单 2：按钮）',
    `sort`        int                                                           NOT NULL DEFAULT '0' COMMENT '显示顺序',
    `path`        varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '路由地址',
    `icon`        varchar(100) COLLATE utf8mb4_unicode_ci                       NOT NULL DEFAULT '' COMMENT '菜单icon',
    `status`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `deleted`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
    `creator`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '创建人',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '更新人',
    `update_time` datetime                                                      NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='菜单信息表';

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `tenant_id`   bigint                                                        NOT NULL DEFAULT '0' COMMENT '租户ID',
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '角色名',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `status`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `deleted`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
    `creator`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '创建人',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '更新人',
    `update_time` datetime                                                      NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY           `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `menu_id` bigint NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单表';

-- ----------------------------
-- Table structure for t_system_properties
-- ----------------------------
DROP TABLE IF EXISTS `t_system_properties`;
CREATE TABLE `t_system_properties`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT,
    `name`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配置key',
    `value`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配置value',
    `status`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `deleted`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
    `creator`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '创建人',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '更新人',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY           `idx_key` (`name`) USING BTREE COMMENT 'key索引'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统配置表';

-- ----------------------------
-- Table structure for t_tenant
-- ----------------------------
DROP TABLE IF EXISTS `t_tenant`;
CREATE TABLE `t_tenant`
(
    `id`             bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '租户ID',
    `name`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '租户名',
    `contact_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '联系人姓名',
    `contract_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '联系人手机号',
    `contract_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '联系人邮箱',
    `app_id`         varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT 'appid',
    `app_secret`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'appsecret',
    `expire_time`    datetime                                                      NOT NULL COMMENT '过期时间',
    `status`         tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `deleted`        tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
    `creator`        varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '创建人',
    `create_time`    datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`        varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '更新人',
    `update_time`    datetime                                                      NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`),
    KEY              `idx_app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户表';

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`
(
    `id`          bigint                                                        NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    `tenant_id`   bigint                                                        NOT NULL DEFAULT '0' COMMENT '租户ID',
    `username`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户账号',
    `password`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
    `salt`        varchar(100) COLLATE utf8mb4_unicode_ci                       NOT NULL COMMENT '密码盐',
    `nickname`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL COMMENT '用户昵称',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
    `email`       varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '用户邮箱',
    `mobile`      varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '手机号码',
    `avatar`      varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '头像地址',
    `login_ip`    varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime                                                               DEFAULT NULL COMMENT '最后登录时间',
    `status`      tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `deleted`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
    `creator`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '创建人',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater`     varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL DEFAULT '' COMMENT '更新人',
    `update_time` datetime                                                      NOT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY           `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户信息表';

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`
(
    `id`      bigint NOT NULL AUTO_INCREMENT,
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`id`),
    KEY       `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';

SET
FOREIGN_KEY_CHECKS = 1;
