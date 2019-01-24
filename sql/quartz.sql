-- ----------------------------
-- 1、存储每一个已配置的 jobDetail 的详细信息
-- ----------------------------
drop table if exists QRTZ_JOB_DETAILS;
create table QRTZ_JOB_DETAILS (
  sched_name           varchar(120)    not null,
  job_name             varchar(200)    not null,
  job_group            varchar(200)    not null,
  description          varchar(250)    null,
  job_class_name       varchar(250)    not null,
  is_durable           varchar(1)      not null,
  is_nonconcurrent     varchar(1)      not null,
  is_update_data       varchar(1)      not null,
  requests_recovery    varchar(1)      not null,
  job_data             image           null,
  primary key (sched_name,job_name,job_group)
);

-- ----------------------------
-- 2、 存储已配置的 Trigger 的信息
-- ----------------------------
drop table if exists QRTZ_TRIGGERS;
create table QRTZ_TRIGGERS (
  sched_name           varchar(120)    not null,
  trigger_name         varchar(200)    not null,
  trigger_group        varchar(200)    not null,
  job_name             varchar(200)    not null,
  job_group            varchar(200)    not null,
  description          varchar(250)    null,
  next_fire_time       bigint          null,
  prev_fire_time       bigint          null,
  priority             integer         null,
  trigger_state        varchar(16)     not null,
  trigger_type         varchar(8)      not null,
  start_time           bigint          not null,
  end_time             bigint          null,
  calendar_name        varchar(200)    null,
  misfire_instr        smallint        null,
  job_data             image           null,
  primary key (sched_name,trigger_name,trigger_group),
  foreign key (sched_name,job_name,job_group) references QRTZ_JOB_DETAILS(sched_name,job_name,job_group)
);

-- ----------------------------
-- 3、 存储简单的 Trigger，包括重复次数，间隔，以及已触发的次数
-- ----------------------------
drop table if exists QRTZ_SIMPLE_TRIGGERS;
create table QRTZ_SIMPLE_TRIGGERS (
  sched_name           varchar(120)    not null,
  trigger_name         varchar(200)    not null,
  trigger_group        varchar(200)    not null,
  repeat_count         bigint          not null,
  repeat_interval      bigint          not null,
  times_triggered      bigint          not null,
  primary key (sched_name,trigger_name,trigger_group),
  foreign key (sched_name,trigger_name,trigger_group) references QRTZ_TRIGGERS(sched_name,trigger_name,trigger_group)
);

-- ----------------------------
-- 4、 存储 Cron Trigger，包括 Cron 表达式和时区信息
-- ----------------------------
drop table if exists QRTZ_CRON_TRIGGERS;
create table QRTZ_CRON_TRIGGERS (
  sched_name           varchar(120)    not null,
  trigger_name         varchar(200)    not null,
  trigger_group        varchar(200)    not null,
  cron_expression      varchar(200)    not null,
  time_zone_id         varchar(80),
  primary key (sched_name,trigger_name,trigger_group),
  foreign key (sched_name,trigger_name,trigger_group) references QRTZ_TRIGGERS(sched_name,trigger_name,trigger_group)
);

-- ----------------------------
-- 5、 Trigger 作为 Blob 类型存储(用于 Quartz 用户用 JDBC 创建他们自己定制的 Trigger 类型，JobStore 并不知道如何存储实例的时候)
-- ----------------------------
drop table if exists QRTZ_BLOB_TRIGGERS;
create table QRTZ_BLOB_TRIGGERS (
  sched_name           varchar(120)    not null,
  trigger_name         varchar(200)    not null,
  trigger_group        varchar(200)    not null,
  blob_data            image           null,
  primary key (sched_name,trigger_name,trigger_group),
  foreign key (sched_name,trigger_name,trigger_group) references QRTZ_TRIGGERS(sched_name,trigger_name,trigger_group)
);

-- ----------------------------
-- 6、 以 Blob 类型存储存放日历信息， quartz可配置一个日历来指定一个时间范围
-- ----------------------------
drop table if exists QRTZ_CALENDARS;
create table QRTZ_CALENDARS (
  sched_name           varchar(120)    not null,
  calendar_name        varchar(200)    not null,
  calendar             image           not null,
  primary key (sched_name,calendar_name)
);

-- ----------------------------
-- 7、 存储已暂停的 Trigger 组的信息
-- ----------------------------
drop table if exists QRTZ_PAUSED_TRIGGER_GRPS;
create table QRTZ_PAUSED_TRIGGER_GRPS (
  sched_name           varchar(120)    not null,
  trigger_group        varchar(200)    not null,
  primary key (sched_name,trigger_group)
);

-- ----------------------------
-- 8、 存储与已触发的 Trigger 相关的状态信息，以及相联 Job 的执行信息
-- ----------------------------
drop table if exists QRTZ_FIRED_TRIGGERS;
create table QRTZ_FIRED_TRIGGERS (
  sched_name           varchar(120)    not null,
  entry_id             varchar(95)     not null,
  trigger_name         varchar(200)    not null,
  trigger_group        varchar(200)    not null,
  instance_name        varchar(200)    not null,
  fired_time           bigint          not null,
  sched_time           bigint          not null,
  priority             integer         not null,
  state                varchar(16)     not null,
  job_name             varchar(200)    null,
  job_group            varchar(200)    null,
  is_nonconcurrent     varchar(1)      null,
  requests_recovery    varchar(1)      null,
  primary key (sched_name,entry_id)
);

-- ----------------------------
-- 9、 存储少量的有关 Scheduler 的状态信息，假如是用于集群中，可以看到其他的 Scheduler 实例
-- ----------------------------
drop table if exists QRTZ_SCHEDULER_STATE;
create table QRTZ_SCHEDULER_STATE (
  sched_name           varchar(120)    not null,
  instance_name        varchar(200)    not null,
  last_checkin_time    bigint          not null,
  checkin_interval     bigint          not null,
  primary key (sched_name,instance_name)
);

-- ----------------------------
-- 10、 存储程序的悲观锁的信息(假如使用了悲观锁)
-- ----------------------------
drop table if exists QRTZ_LOCKS;
create table QRTZ_LOCKS (
  sched_name           varchar(120)    not null,
  lock_name            varchar(40)     not null,
  primary key (sched_name,lock_name)
);

drop table if exists QRTZ_SIMPROP_TRIGGERS;
create table QRTZ_SIMPROP_TRIGGERS (
  sched_name           varchar(120)    not null,
  trigger_name         varchar(200)    not null,
  trigger_group        varchar(200)    not null,
  str_prop_1           varchar(512)    null,
  str_prop_2           varchar(512)    null,
  str_prop_3           varchar(512)    null,
  int_prop_1           int             null,
  int_prop_2           int             null,
  long_prop_1          bigint          null,
  long_prop_2          bigint          null,
  dec_prop_1           numeric(13,4)   null,
  dec_prop_2           numeric(13,4)   null,
  bool_prop_1          varchar(1)      null,
  bool_prop_2          varchar(1)      null,
  primary key (sched_name,trigger_name,trigger_group),
  foreign key (sched_name,trigger_name,trigger_group) references QRTZ_TRIGGERS(sched_name,trigger_name,trigger_group)
);


-- ----------------------------
-- 16、定时任务调度表
-- ----------------------------
drop table if exists sys_job;
create table sys_job (
  job_id 		          BIGINT 	  ,
  job_name            nvarchar(100)   default '' ,
  job_group           nvarchar(128)   default '' ,
  method_name         nvarchar(500)   default '' ,
  method_params       nvarchar(100)   default null,
  cron_expression     nvarchar(255)   default '' ,
  misfire_policy      nvarchar(40)    default '3',
  status              nvarchar(100)   default '0',
  create_by           nvarchar(200)   default '' ,
  create_time         datetime                   ,
  update_by           nvarchar(200)   default '' ,
  update_time         datetime                   ,
  remark              nvarchar(500)   default '' ,
  primary key (job_id, job_name, job_group)
);

insert into sys_job values(1, 'bridgeTask', '系统默认（无参）', 'NoParams',  '',   '0/10 * * * * ?', '3', '1', 'bridge', '2019-01-21 11:33:00', 'bridge', '2019-01-21 11:33:00', '');
insert into sys_job values(2 ,'bridgeTask', '系统默认（有参）', 'Params',    'bridge', '0/20 * * * * ?', '3', '1', 'bridge', '2019-01-21 11:33:00', 'bridge', '2019-01-21 11:33:00', '');

-- ----------------------------
-- 17、定时任务调度日志表
-- ----------------------------
drop table if exists sys_job_log;
create table sys_job_log (
  job_log_id          int 	   identity(1,1)    ,
  job_name            varchar(64)   not null    ,
  job_group           varchar(64)   not null    ,
  method_name         varchar(500)              ,
  method_params       varchar(50)   default null,
  job_message         varchar(500)              ,
  status              char(1)       default '0' ,
  exception_info      varchar(2000) default ''  ,
  create_time         datetime                  ,
  primary key (job_log_id)
);