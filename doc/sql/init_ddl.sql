create table movie (
	id int(11) not null auto_increment comment 'id',
	mv_name varchar(128) not null default '' comment '影片名称',
	mv_type tinyint(4) not null default 0 comment '类型',
	mv_area tinyint(4) not null default 0 comment '地区',
	mv_year int(11) not null default 1980 comment '出厂年份',
	create_time datetime not null comment '创建时间',
	update_time datetime default null comment '更新时间',
	description varchar(1024) default null comment '详情',
	actor_list varchar(32) default null comment '演员列表',
	director_list varchar(32) default null comment '导演列表',
	state tinyint(4) not null default 0 comment '影片状态：0失效、1有效',
	picture varchar(256) default null comment '影片主图',
	screen_picture varchar(256) default null comment '主图（轮播图）',
	score int(11) default 0 comment '评分',
	primary key (id)
) comment '影片信息表';

create table playlist (
	id int(11) not null auto_increment comment 'id',
	movie_id int(11) not null comment '影片id',
	number int(11) not null default 1 comment '集数',
	url varchar(256) not null comment '影片地址',
	primary key (id),
	index (movie_id,number)
) comment '影片播放列表';

create table actor (
	id int(11) not null auto_increment comment 'id',
	sort varchar(128) not null default '' comment '演员名称',
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


create table movie_class (
	id int(11) not null auto_increment comment 'id',
	name varchar(32) not null default '' comment '分类名称',
	sort int(11) not null default 1 comment '排序号',
	parent int(11) default null comment '父分类id',
	level tinyint(4) not null default 0 comment '分类级别',
	primary key (id)
) comment '影片分类表';

create table collect_config (
	id int(11) not null auto_increment comment 'id',
	list_url varchar(256) not null default '' comment '采集列表url',
	detail_url varchar(256) not null default '' comment '采集详情url',
	state bit(1) not null default b'0' comment '是否有效',
	primary key (id)
) comment '采集配置表';
