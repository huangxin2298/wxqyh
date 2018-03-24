CREATE TABLE tb_agent_info (
  id varchar(36) NOT NULL,
  agent_code varchar(20) DEFAULT NULL,
  agent_name varchar(20) DEFAULT NULL,
  status int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_access_token (
  id varchar(36) NOT NULL,
  org_id varchar(36) DEFAULT NULL COMMENT '组织ID',
  agent_code varchar(20) DEFAULT NULL,
  token VARCHAR(500) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  valid_time datetime DEFAULT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_department_info (
  id varchar(36) NOT NULL,
  department_name varchar(50) DEFAULT NULL,
  parent_depart varchar(36) DEFAULT NULL,
  creator varchar(36) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  org_id varchar(36) DEFAULT NULL,
  dept_full_name varchar(512) DEFAULT NULL,
  show_order int(11) DEFAULT NULL,
  wx_id varchar(100) DEFAULT NULL,
  wx_parentid varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_org_info (
  id varchar(36) NOT NULL,
  org_name varchar(200) DEFAULT NULL COMMENT '机构名称',
  CREATE_TIME datetime DEFAULT NULL,
  corp_id varchar(128) DEFAULT NULL,
  corp_secret varchar(128) DEFAULT NULL,
  token varchar(128) DEFAULT NULL,
  wx_id varchar(100) DEFAULT NULL,
  wx_parentid varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tb_user_info (
  id varchar(36) NOT NULL,
  user_id varchar(36) DEFAULT NULL,
  org_id varchar(36) DEFAULT NULL,
  user_name varchar(50) DEFAULT NULL,
  mobile varchar(30) DEFAULT NULL,
  email varchar(50) DEFAULT NULL,
  dept_id varchar(200) DEFAULT NULL,
  sex varchar(10) DEFAULT NULL,
  position varchar(100) DEFAULT NULL,
  is_leader tinyint(4) DEFAULT NULL COMMENT '是否是领导',
  head_pic varchar(500) DEFAULT NULL,
  enable varchar(2) DEFAULT NULL,
  create_person varchar(36) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  update_time datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

CREATE TABLE tb_admin_user (
  id varchar(36) NOT NULL,
  user_name varchar(100) DEFAULT NULL,
  password varchar(60) DEFAULT NULL,
  create_time datetime DEFAULT NULL,
  last_login_time datetime DEFAULT NULL,
  org_id varchar(36) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理后台帐号表';






