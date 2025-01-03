SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for b_login_log
-- ----------------------------
DROP TABLE IF EXISTS `b_login_log`;
CREATE TABLE `b_login_log` (
                               `id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志ID',
                               `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户ID',
                               `username` varchar(30) NOT NULL DEFAULT '0' COMMENT '用户名',
                               `log_type` tinyint NOT NULL COMMENT '日志类型',
                               `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '登录IP',
                               `login_date` datetime NOT NULL COMMENT '登录时间',
                               `user_agent` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'UserAgent',
                               `success` tinyint NOT NULL COMMENT '是否成功（0：成功，1：失败）',
                               `error_message` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '失败原因',
                               `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (`id`,`tenant_id`) USING BTREE,
                               KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='登录日志表';

-- ----------------------------
-- Table structure for b_menu
-- ----------------------------
DROP TABLE IF EXISTS `b_menu`;
CREATE TABLE `b_menu` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
                          `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父菜单ID',
                          `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
                          `permission` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '权限标识',
                          `type` tinyint NOT NULL COMMENT '菜单类型（1：菜单 2：按钮）',
                          `sort` int NOT NULL DEFAULT '0' COMMENT '显示顺序',
                          `path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '路由地址',
                          `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单icon',
                          `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                          `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                          `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                          `update_time` datetime NOT NULL COMMENT '更新时间',
                          PRIMARY KEY (`id`) USING BTREE,
                          KEY `idx_parent_id` (`parent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单信息表';

-- ----------------------------
-- Table structure for b_operation_log
-- ----------------------------
DROP TABLE IF EXISTS `b_operation_log`;
CREATE TABLE `b_operation_log` (
                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '操作日志ID',
                                   `tenant_id` bigint NOT NULL COMMENT '租户ID',
                                   `operator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '操作人',
                                   `operation_type` tinyint NOT NULL COMMENT '操作类型（1：查询，2：新增，3：更新，4：删除）',
                                   `operation_time` datetime NOT NULL COMMENT '操作时间',
                                   `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '操作内容',
                                   `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   PRIMARY KEY (`id`) USING BTREE,
                                   KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='操作日志表';

-- ----------------------------
-- Table structure for b_role
-- ----------------------------
DROP TABLE IF EXISTS `b_role`;
CREATE TABLE `b_role` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
                          `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户ID',
                          `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '角色名',
                          `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
                          `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                          `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                          `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                          `update_time` datetime NOT NULL COMMENT '更新时间',
                          PRIMARY KEY (`id`),
                          KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

-- ----------------------------
-- Table structure for b_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `b_role_menu`;
CREATE TABLE `b_role_menu` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `role_id` bigint NOT NULL COMMENT '角色ID',
                               `menu_id` bigint NOT NULL COMMENT '菜单ID',
                               PRIMARY KEY (`id`),
                               KEY `idx_basic` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色菜单表';

-- ----------------------------
-- Table structure for b_tenant
-- ----------------------------
DROP TABLE IF EXISTS `b_tenant`;
CREATE TABLE `b_tenant` (
                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '租户ID',
                            `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '租户名',
                            `contact_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '联系人姓名',
                            `contract_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '联系人手机号',
                            `contract_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '联系人邮箱',
                            `app_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'appid',
                            `app_secret` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'appsecret',
                            `expire_time` datetime NOT NULL COMMENT '过期时间',
                            `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                            `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                            `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                            `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                            `update_time` datetime NOT NULL COMMENT '更新时间',
                            PRIMARY KEY (`id`),
                            KEY `idx_app_id` (`app_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='租户表';

-- ----------------------------
-- Table structure for b_user
-- ----------------------------
DROP TABLE IF EXISTS `b_user`;
CREATE TABLE `b_user` (
                          `id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                          `tenant_id` bigint NOT NULL DEFAULT '0' COMMENT '租户ID',
                          `username` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户账号',
                          `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '密码',
                          `salt` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码盐',
                          `nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户昵称',
                          `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '备注',
                          `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户邮箱',
                          `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号码',
                          `avatar` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '头像地址',
                          `login_ip` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '最后登录IP',
                          `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
                          `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                          `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                          `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                          `update_time` datetime NOT NULL COMMENT '更新时间',
                          PRIMARY KEY (`id`) USING BTREE,
                          KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- ----------------------------
-- Table structure for b_user_role
-- ----------------------------
DROP TABLE IF EXISTS `b_user_role`;
CREATE TABLE `b_user_role` (
                               `id` bigint NOT NULL AUTO_INCREMENT,
                               `user_id` bigint NOT NULL COMMENT '用户ID',
                               `role_id` bigint NOT NULL COMMENT '角色ID',
                               PRIMARY KEY (`id`),
                               KEY `idx_user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户角色表';

-- ----------------------------
-- Table structure for b_wechat_app
-- ----------------------------
DROP TABLE IF EXISTS `b_wechat_app`;
CREATE TABLE `b_wechat_app` (
                                `id` bigint NOT NULL AUTO_INCREMENT COMMENT '小程序主键',
                                `tenant_id` bigint NOT NULL COMMENT '租户ID',
                                `name` varchar(100) NOT NULL DEFAULT '' COMMENT '小程序名称',
                                `wechat_sign` varchar(255) NOT NULL COMMENT '微信原始ID',
                                `wechat_app_id` varchar(255) NOT NULL COMMENT '微信AppId',
                                `wechat_app_secret` varchar(255) NOT NULL COMMENT '微信Secret',
                                `business_type` tinyint(1) NOT NULL COMMENT '业务类型',
                                `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
                                `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                `update_time` datetime NOT NULL COMMENT '更新时间',
                                PRIMARY KEY (`id`),
                                KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='微信小程序表';

-- ----------------------------
-- Table structure for c_education_course
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course`;
CREATE TABLE `c_education_course` (
                                      `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                      `tenant_id` bigint NOT NULL COMMENT '租户ID',
                                      `wechat_id` bigint NOT NULL COMMENT '微信ID',
                                      `course_no` varchar(100) NOT NULL COMMENT '课程编号',
                                      `name` varchar(100) NOT NULL COMMENT '课程名称',
                                      `description` varchar(255) NOT NULL COMMENT '课程描述',
                                      `course_type` tinyint NOT NULL DEFAULT '1' COMMENT '课程类型（1:线下 2：线上 3：录像）',
                                      `course_price` decimal(10,2) NOT NULL COMMENT '课程价格',
                                      `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                      `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                      `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                      `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                      `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                      `update_time` datetime NOT NULL COMMENT '更新时间',
                                      PRIMARY KEY (`id`),
                                      KEY `idx_basic` (`tenant_id`,`wechat_id`,`course_no`) USING BTREE COMMENT '基础索引'
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程信息表';

-- ----------------------------
-- Table structure for c_education_course_attach
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course_attach`;
CREATE TABLE `c_education_course_attach` (
                                             `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                             `course_no` varchar(100) NOT NULL COMMENT '课程编号',
                                             `attach_type` tinyint NOT NULL COMMENT '附件类型',
                                             `attach_url` varchar(255) NOT NULL COMMENT '附件路径',
                                             `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                             `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                             `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                             `update_time` datetime NOT NULL COMMENT '更新时间',
                                             PRIMARY KEY (`id`),
                                             KEY `idx_course_no` (`course_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程附件表';

-- ----------------------------
-- Table structure for c_education_course_class
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course_class`;
CREATE TABLE `c_education_course_class` (
                                            `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                            `wechat_id` bigint NOT NULL COMMENT '微信ID',
                                            `course_no` varchar(100) NOT NULL COMMENT '课程编号',
                                            `class_no` varchar(100) NOT NULL COMMENT '班级编号',
                                            `name` varchar(100) NOT NULL COMMENT '班级名称',
                                            `class_hour` varchar(200) NOT NULL COMMENT '课时',
                                            `class_time` varchar(200) NOT NULL COMMENT '上课时间',
                                            `class_start_time` datetime NOT NULL COMMENT '班级开班日期',
                                            `class_finish_time` datetime NOT NULL COMMENT '班级结束日期',
                                            `registration_deadline` datetime NOT NULL COMMENT '报名截止日期',
                                            `class_price` decimal(10,2) NOT NULL COMMENT '价格',
                                            `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（1：待开班 2：开班中 3：结业 9：取消）',
                                            `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                            `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                            `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                            `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                            `update_time` datetime NOT NULL COMMENT '更新时间',
                                            PRIMARY KEY (`id`),
                                            KEY `idx_basic` (`wechat_id`,`course_no`,`class_no`) USING BTREE,
                                            KEY `idx_class_no` (`class_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程班级表';

-- ----------------------------
-- Table structure for c_education_course_class_enroll
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course_class_enroll`;
CREATE TABLE `c_education_course_class_enroll` (
                                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                   `wechat_id` bigint NOT NULL COMMENT '微信ID',
                                                   `course_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程编号',
                                                   `people_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报名人openId',
                                                   `pay_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '支付状态（0：未支付 1：已支付）',
                                                   `assign_status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '班级分配状态（0：未分配 1：已分配）',
                                                   `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（1：报名成功 2：班级分配中 3：班级分配完成）',
                                                   `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                                   `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                   `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                                   `update_time` datetime NOT NULL COMMENT '更新时间',
                                                   PRIMARY KEY (`id`),
                                                   KEY `idx_basic` (`wechat_id`,`course_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='报名管理表';

-- ----------------------------
-- Table structure for c_education_course_class_person
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course_class_person`;
CREATE TABLE `c_education_course_class_person` (
                                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                   `class_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级编号',
                                                   `type` tinyint NOT NULL COMMENT '人员类型（1：教师 2：正式学员 3：试课学员）',
                                                   `people_open_id` varchar(255) NOT NULL COMMENT '人员openId',
                                                   `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                                   `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                   `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                                   `update_time` datetime NOT NULL COMMENT '更新时间',
                                                   PRIMARY KEY (`id`),
                                                   KEY `idx_class_no` (`class_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程班级人员表';

-- ----------------------------
-- Table structure for c_education_course_class_record
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course_class_record`;
CREATE TABLE `c_education_course_class_record` (
                                                   `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                   `class_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级编号',
                                                   `class_begin_time` datetime NOT NULL COMMENT '课程开始时间',
                                                   `class_end_time` datetime NOT NULL COMMENT '课程结束时间',
                                                   `teacher` varchar(255) NOT NULL COMMENT '授课教师',
                                                   `student_count` int NOT NULL COMMENT '上课人数',
                                                   `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                                   `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                   `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                                   `update_time` datetime NOT NULL COMMENT '更新时间',
                                                   PRIMARY KEY (`id`),
                                                   KEY `idx_class_no` (`class_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程授课记录表';

-- ----------------------------
-- Table structure for c_education_course_class_schedule
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course_class_schedule`;
CREATE TABLE `c_education_course_class_schedule` (
                                                     `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                     `class_no` varchar(100) NOT NULL COMMENT '班级编号',
                                                     `class_hours_name` varchar(100) NOT NULL COMMENT '课时名',
                                                     `class_begin_time` datetime NOT NULL COMMENT '课程开始时间',
                                                     `class_end_time` datetime NOT NULL COMMENT '课程结束时间',
                                                     `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（1：待开课 2：开课中 3：已下课）',
                                                     `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                                     `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                                     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                     `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                                     `update_time` datetime NOT NULL COMMENT '更新时间',
                                                     PRIMARY KEY (`id`),
                                                     KEY `idx_class_no` (`class_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='班级课程表';

-- ----------------------------
-- Table structure for c_education_course_class_sign_in
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course_class_sign_in`;
CREATE TABLE `c_education_course_class_sign_in` (
                                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                    `wechat_id` bigint NOT NULL COMMENT '微信ID',
                                                    `course_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程编号',
                                                    `class_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '班级编号',
                                                    `class_schedule_id` bigint NOT NULL DEFAULT '0' COMMENT '课时ID',
                                                    `people_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报名人openId',
                                                    `sign_in_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '签到时间',
                                                    `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态（1：签到成功 2：签到失败）',
                                                    `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                                    `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                    `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                                    `update_time` datetime NOT NULL COMMENT '更新时间',
                                                    PRIMARY KEY (`id`),
                                                    KEY `idx_basic` (`wechat_id`,`course_no`,`class_no`,`class_schedule_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程签到表';

-- ----------------------------
-- Table structure for c_education_course_class_trail
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course_class_trail`;
CREATE TABLE `c_education_course_class_trail` (
                                                  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                  `wechat_id` bigint NOT NULL COMMENT '微信ID',
                                                  `course_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程编号',
                                                  `class_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '班级编号',
                                                  `class_schedule_id` bigint NOT NULL DEFAULT '0' COMMENT '课时ID',
                                                  `people_open_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '报名人openId',
                                                  `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0：已报名 1：已上课 2：未上课）',
                                                  `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                                  `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                                  `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                                  `update_time` datetime NOT NULL COMMENT '更新时间',
                                                  PRIMARY KEY (`id`),
                                                  KEY `idx_basic` (`wechat_id`,`course_no`,`class_no`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='试课管理表';

-- ----------------------------
-- Table structure for c_education_course_type
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course_type`;
CREATE TABLE `c_education_course_type` (
                                           `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                           `wechat_id` bigint NOT NULL COMMENT '微信ID',
                                           `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程分类名',
                                           `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '分类备注',
                                           `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                           `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                           `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                           `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                           `update_time` datetime NOT NULL COMMENT '更新时间',
                                           PRIMARY KEY (`id`),
                                           KEY `idx_wechat_id` (`wechat_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程分类表';

-- ----------------------------
-- Table structure for c_education_course_type_relation
-- ----------------------------
DROP TABLE IF EXISTS `c_education_course_type_relation`;
CREATE TABLE `c_education_course_type_relation` (
                                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                                    `course_no` varchar(100) NOT NULL COMMENT '课程编号',
                                                    `type_id` bigint NOT NULL COMMENT '类型ID',
                                                    PRIMARY KEY (`id`),
                                                    KEY `idx_basic` (`course_no`,`type_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程分类关联表';

-- ----------------------------
-- Table structure for c_user_education
-- ----------------------------
DROP TABLE IF EXISTS `c_user_education`;
CREATE TABLE `c_user_education` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                    `open_id` varchar(100) NOT NULL COMMENT '用户OpenId',
                                    `realname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '用户真实姓名',
                                    `mobile` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '手机号码',
                                    `identity_no` varchar(50) NOT NULL DEFAULT '' COMMENT '证件号',
                                    `identity_type` tinyint(1) NOT NULL DEFAULT '0' COMMENT '证件类型（0：未知）',
                                    `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                    `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                    `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                    `update_time` datetime NOT NULL COMMENT '更新时间',
                                    PRIMARY KEY (`id`),
                                    UNIQUE KEY `idx_open_id` (`open_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Table structure for s_kafka_dead_message
-- ----------------------------
DROP TABLE IF EXISTS `s_kafka_dead_message`;
CREATE TABLE `s_kafka_dead_message` (
                                        `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
                                        `kafka_topic` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '失败任务topic',
                                        `kafka_partition` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '失败任务所在分区',
                                        `kafka_key` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '失败任务key',
                                        `kafka_value` json NOT NULL COMMENT '失败任务数据',
                                        `status` tinyint(1) NOT NULL COMMENT '重试状态（0：未重试，1：重试成功，2：重试失败）',
                                        `retry_time` datetime DEFAULT NULL COMMENT '重试时间',
                                        `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                        PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Kafka死信队列表';

-- ----------------------------
-- Table structure for s_system_properties
-- ----------------------------
DROP TABLE IF EXISTS `s_system_properties`;
CREATE TABLE `s_system_properties` (
                                       `id` bigint NOT NULL AUTO_INCREMENT,
                                       `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配置key',
                                       `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '配置value',
                                       `status` tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
                                       `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除（0 正常 1 删除）',
                                       `creator` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '创建人',
                                       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `updater` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '更新人',
                                       `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                       PRIMARY KEY (`id`),
                                       KEY `idx_key` (`name`) USING BTREE COMMENT 'key索引'
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='系统配置表';

SET FOREIGN_KEY_CHECKS = 1;
