drop table if exists short_url;

/*==============================================================*/
/* Table: short_url                                             */
/*==============================================================*/
create table short_url
(
   id                   varchar(32) not null comment 'ID',
   long_url             varchar(512) comment '长链接',
   short_url            varchar(128) comment '短链接',
   created_date         timestamp comment '创建日期',
   expires_date         timestamp comment '失效日期',
   primary key (id)
);

alter table short_url comment '短链表';