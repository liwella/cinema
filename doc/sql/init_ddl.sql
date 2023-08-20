create table movie (
	id int(11) not null auto_increment comment 'id',
	mv_name varchar(128) not null default '' comment '影片名称',
	mv_type tinyint(4) not null default 0 comment '类型',
	mv_area tinyint(4) not null default 0 comment '地区',
	mv_year int(11) not null default 1980 comment '出厂年份',
	create_time datetime not null comment '创建时间',
	update_time datetime default null comment '更新时间',
	update_info int(11) default null comment '更新信息',
	description varchar(1024) default null comment '详情',
	actor_list varchar(32) default null comment '演员列表',
	director_list varchar(32) default null comment '导演列表',
	state tinyint(4) not null default 0 comment '影片状态：0失效、1有效',
	picture varchar(256) default null comment '影片主图',
	screen_picture varchar(256) default null comment '主图（轮播图）',
	score double default 0 comment '评分',
	primary key (id)
) comment '影片信息表';

create table playlist (
	id int(11) not null auto_increment comment 'id',
	movie_id int(11) not null comment '影片id',
	source_id int(11) not null comment '采集源id',
	source_movie_id int(11) not null comment '源影片id',
	play_type varchar(32) default null comment '播放类型',
	play_url longtext not null comment '影片地址',
	update_time datetime not null comment '更新时间',
	separator_note varchar(16) default null comment '分割标志',
	primary key (id)
) comment '影片播放列表';

create table actor (
	id int(11) not null auto_increment comment 'id',
	name varchar(128) not null default '' comment '演员名称',
	primary key (id)
) comment '演员表';

create table director (
	id int(11) not null auto_increment comment 'id',
	name varchar(128) not null default '' comment '导演名称',
	primary key (id)
) comment '导演表';

create table actor_movie (
	actor_id int(11) not null comment '演员id',
	movie_id int(11) not null comment '影片id',
	primary key (actor_id),
	index (movie_id)
) comment '演员影片表';

create table director_movie (
	director_id int(11) not null comment '导演id',
	movie_id int(11) not null comment '影片id',
	primary key (director_id),
	index (movie_id)
) comment '导演影片表';

create table category (
	id int(11) not null auto_increment comment 'id',
	name varchar(32) not null default '' comment '分类名称',
	sort int(11) not null default 1 comment '排序号',
	parent int(11) default null comment '父分类id',
	level tinyint(4) not null default 0 comment '分类级别',
	primary key (id)
) comment '影片分类表';

create table source_config (
	id int(11) not null auto_increment comment 'id',
	list_url varchar(256) not null default '' comment '采集列表url',
	detail_url varchar(256) not null default '' comment '采集详情url',
	state bit(1) not null default b'0' comment '是否有效',
	primary key (id)
) comment '采集配置表';

create table category_mapping (
	id int not null auto_increment comment 'id',
	category_id int not null comment '影片分类id',
	category_name varchar(32) not null default '' comment '影片分类名称',
	source_id int not null comment '源采集配置id',
	source_type_id int not null comment '源分类id',
	primary key (id),
	index (source_id,source_type_id)
) comment '影片分类映射表';

# =============================================================================
create table user(
	id int(11) not null auto_increment comment 'id',
	username varchar(128) not null comment '用户名',
	password varchar(128) not null comment '密码',
	phone char(33) comment '号码',
	email varchar(128) comment '邮箱',
	avatar varchar(256) comment '头像',
	sex bit(1) not null default 1 comment '性别：0女，1男',
	create_time datetime not null default now() comment '创建时间',
	update_time datetime not null default now() comment '更新时间',
	state bit(1) not null default 1 comment '状态：0失效，1有效',
	primary key (id)
) comment '用户表';

create table role(
	id int(11) not null auto_increment comment 'id',
	role_name varchar(24) not null comment '角色名称',
	code varchar(16) not null comment '角色代号',
	primary key (id)
) comment '角色表';

create table user_role (
	id int(11) not null auto_increment comment 'id',
	user_id int(11) not null comment '用户id',
	role_id int(11) not null comment '角色id',
	primary key (id)
) comment '用户角色表';


create table menu (
	id int(11) not null auto_increment comment 'id',
	menu_name varchar(64) not null comment '菜单名称',
	url varchar(128) comment '访问路径',
	permission varchar(128) comment '权限',
	type tinyint(4) not null comment '类型：1菜单，2目录，3权限',
	icon varchar(64) comment '图标',
	level tinyint(4) not null default 0 comment '菜单层级',
	parent_id int(11) comment '父菜单id',
	sort tinyint(4) not null default 0 comment '排序',
	primary key (id)
) comment '菜单权限表';

create table role_menu (
	id int(11) not null auto_increment comment 'id',
	role_id int(11) not null comment '角色id',
	menu_id int(11) not null comment '菜单id',
	primary key (id)
) comment '角色菜单表';