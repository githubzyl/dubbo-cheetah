/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2018/12/29 11:18:39                          */
/*==============================================================*/


drop table if exists t_user;

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   int(11) not null auto_increment,
   user_name            varchar(50) not null comment '用户名',
   password             varchar(32) not null comment '登陆密码',
   real_name            varchar(50) not null comment '真实姓名',
   mobile               varchar(11) not null comment '手机号码',
   email                varchar(50) not null comment '邮箱地址',
   user_status          char(1) not null default '0' comment '用户状态(0：正常，1：禁用，默认正常)',
   lock_status          char(1) not null default '0' comment '锁定状态0：非锁定，1：锁定，默认非锁定)',
   gender               char(1) default NULL comment '性别(男：''0'',女：''1'')',
   photo                varchar(255) default NULL comment '头像',
   department_id        int(11) not null default 1 comment '部门ID',
   tenant_id            int not null default 1 comment '租户ID',
   is_del               int not null default 0 comment '是否删除(0:未删除,1:已删除,默认0)',
   version              int not null default 0 comment '乐观锁版本号',
   creater              int not null,
   create_time          datetime not null,
   last_modifier        int not null,
   last_modify_time     datetime not null,
   primary key (id),
   unique key ak_mobile (mobile),
   unique key ak_username (user_name),
   unique key ak_email (email)
)
ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

