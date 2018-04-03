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

CREATE TABLE tb_admin_config (
  id VARCHAR(36) NOT NULL COMMENT 'UUID',
  config_name VARCHAR(100) DEFAULT NULL COMMENT '配置项名',
  config_value VARCHAR(100) DEFAULT NULL COMMENT '配置项值',
  description VARCHAR(200)DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台配置表';

CREATE TABLE tb_menu_info (
  id varchar(36) NOT NULL COMMENT='UUID',
  menu_name varchar(64) DEFAULT NULL COMMENT '菜单名',
  menu_url varchar(200) DEFAULT NULL COMMENT '菜单跳转地址',
  status varchar(11) DEFAULT NULL COMMENT '菜单状态',
  show_order int(11) DEFAULT NULL COMMENT '排序号',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台菜单表';

CREATE TABLE tb_course_classroom (
  id varchar(36) NOT NULL COMMENT 'UUID',
  classroom_name varchar(64) DEFAULT NULL COMMENT '课室名',
  admiss_num INT DEFAULT NULL COMMENT '课室可容纳人数',
  address VARCHAR(200) DEFAULT NULL COMMENT '课室地址',
  ismultime tinyint(4) DEFAULT NULL COMMENT '是否有多媒体(0-没有 1-有)',
  status tinyint(4) DEFAULT NULL COMMENT '课室状态(0-停用 1-启用)',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  creator varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程课室表';

CREATE TABLE tb_course_term (
  id varchar(36) NOT NULL COMMENT 'UUID',
  term_year varchar(64) DEFAULT NULL COMMENT '学期年度',
  term varchar(32) DEFAULT NULL COMMENT '学期',
  start_time datetime DEFAULT NULL COMMENT '开始时间',
  end_time datetime DEFAULT NULL COMMENT '结束时间',
  week_num INT DEFAULT NULL COMMENT '总周数',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  creator varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程学期表';

CREATE TABLE tb_course_info (
  id varchar(36) NOT NULL COMMENT 'UUID',
  course_name varchar(64) DEFAULT NULL COMMENT '课程名',
  term_id varchar(36) DEFAULT NULL COMMENT '学期id',
  term_name varchar(36) DEFAULT NULL COMMENT '学期名',
  start_week INT DEFAULT NULL COMMENT '开始周',
  end_week INT DEFAULT NULL COMMENT '结束周',
  class_time INT DEFAULT NULL COMMENT '上课时间(1-周一 2-周二 3-周三)',
  sessions varchar(36) DEFAULT NULL COMMENT '上课节数',
  classroom_id varchar(36) DEFAULT NULL COMMENT '课室id',
  classroom_name varchar(64) DEFAULT NULL COMMENT '课室名',
  teacher_name varchar(50) DEFAULT NULL COMMENT '课程教师名字',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  creator varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='课程表';

  ALTER TABLE tb_course_info ADD COLUMN class_time INT DEFAULT NULL COMMENT '上课时间(1-周一 2-周二 3-周三)',
  ALTER TABLE tb_course_info ADD COLUMNsessions varchar(36) DEFAULT NULL COMMENT '上课节数',
  ALTER TABLE tb_course_info ADD COLUMNclassroom_id varchar(36) DEFAULT NULL COMMENT '课室id',
  ALTER TABLE tb_course_info ADD COLUMNclassroom_name varchar(64) DEFAULT NULL COMMENT '课室名',
CREATE TABLE tb_daily_arrange (
  id varchar(36) NOT NULL COMMENT 'UUID',
  sessions INT DEFAULT NULL COMMENT '上课节数',
  classroom_id varchar(36) DEFAULT NULL COMMENT '课室id',
  classroom_name varchar(64) DEFAULT NULL COMMENT '课室名',
  create_time datetime DEFAULT NULL COMMENT '创建时间',
  update_time datetime DEFAULT NULL COMMENT '更新时间',
  creator varchar(100) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日常安排表';








