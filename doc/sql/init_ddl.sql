# =======================================================================
create table if not exists dunhao.actor
(
    id   int auto_increment comment 'id'
    primary key,
    name varchar(128) default '' not null comment '演员名称'
    )
    comment '演员表';

create table if not exists dunhao.actor_movie
(
    actor_id int not null comment '演员id'
    primary key,
    movie_id int not null comment '影片id'
)
    comment '演员影片表';

create index movie_id
    on dunhao.actor_movie (movie_id);

create table if not exists dunhao.category
(
    id     int auto_increment comment 'id'
    primary key,
    name   varchar(32) default '' not null comment '分类名称',
    sort   int         default 1  not null comment '排序号',
    parent int                    null comment '父分类id',
    level  tinyint     default 0  not null comment '分类级别'
    )
    comment '影片分类表';

create table if not exists dunhao.category_mapping
(
    id             int auto_increment comment 'id'
    primary key,
    category_id    int                    not null comment '影片分类id',
    category_name  varchar(32) default '' not null comment '影片分类名称',
    source_id      int                    not null comment '源采集配置id',
    source_type_id int                    not null comment '源分类id'
    )
    comment '影片分类映射表';

create index source_id
    on dunhao.category_mapping (source_id, source_type_id);

create table if not exists dunhao.collect_task
(
    id           int auto_increment comment 'id'
    primary key,
    source_id    int               not null comment '采集源id',
    duration     int               null comment '采集时间段,单位小时',
    create_time  datetime          null comment '创建时间',
    start_time   datetime          null comment '开始时间',
    pause_time   datetime          null comment '暂停时间',
    stop_time    datetime          null comment '停止时间',
    finish_time  datetime          null comment '完成时间',
    current_page int               null comment '当前采集页',
    total_page   int               null comment '总采集页',
    state        tinyint default 0 not null comment '采集状态'
)
    comment '采集任务表';

create table if not exists dunhao.director
(
    id   int auto_increment comment 'id'
    primary key,
    name varchar(128) default '' not null comment '导演名称'
    )
    comment '导演表';

create table if not exists dunhao.director_movie
(
    director_id int not null comment '导演id'
    primary key,
    movie_id    int not null comment '影片id'
)
    comment '导演影片表';

create index movie_id
    on dunhao.director_movie (movie_id);

create table if not exists dunhao.permission
(
    id         int auto_increment comment 'id'
    primary key,
    menu_name  varchar(64)       not null comment '菜单名称',
    url        varchar(128)      null comment '访问路径',
    permission varchar(128)      null comment '权限',
    type       tinyint           not null comment '类型：1菜单，2目录，3权限',
    icon       varchar(64)       null comment '图标',
    level      tinyint default 0 not null comment '菜单层级',
    parent_id  int               null comment '父菜单id',
    sort       tinyint default 0 not null comment '排序'
    )
    comment '菜单权限表';

create table if not exists dunhao.movie
(
    id             int auto_increment comment 'id'
    primary key,
    mv_name        varchar(128) default ''   not null comment '影片名称',
    mv_type        tinyint      default 0    not null comment '类型',
    mv_area        tinyint      default 0    not null comment '地区',
    mv_year        int          default 1980 not null comment '出厂年份',
    create_date    date                      not null comment '创建日期',
    update_date    date                      null comment '更新日期',
    update_info    int                       null comment '更新信息',
    description    longtext                  null comment '详情',
    actor_list     varchar(512)              null comment '演员列表',
    director_list  varchar(512)              null comment '导演列表',
    state          tinyint      default 0    not null comment '影片状态：0失效、1有效',
    picture        varchar(512)              null comment '影片主图',
    screen_picture varchar(512)              null comment '主图（轮播图）',
    score          double       default 0    null comment '评分',
    constraint mv_name_idx
    unique (mv_name)
    )
    comment '影片信息表';

create table if not exists dunhao.playlist
(
    id              int auto_increment comment 'id'
    primary key,
    movie_id        int         not null comment '影片id',
    source_id       int         not null comment '采集源id',
    source_movie_id int         not null comment '源影片id',
    play_type       varchar(32) null comment '播放类型',
    play_url        longtext    not null comment '影片地址',
    update_time     datetime    not null comment '更新时间',
    separator_note  varchar(16) null comment '分割标志',
    constraint source_movie_idx
    unique (source_id, source_movie_id)
    )
    comment '影片播放列表';

create table if not exists dunhao.role
(
    id        int auto_increment comment 'id'
    primary key,
    role_name varchar(24) not null comment '角色名称',
    code      varchar(16) not null comment '角色代号'
    )
    comment '角色表';

create table if not exists dunhao.role_menu
(
    id      int auto_increment comment 'id'
    primary key,
    role_id int not null comment '角色id',
    menu_id int not null comment '菜单id'
)
    comment '角色菜单表';

create table if not exists dunhao.source_config
(
    id          int auto_increment comment 'id'
    primary key,
    source_name varchar(32)  default '' not null comment '采集源名称',
    list_url    varchar(256) default '' not null comment '采集列表url',
    detail_url  varchar(256) default '' not null comment '采集详情url',
    state       tinyint      default 0  not null comment '采集源状态'
    )
    comment '采集配置表';

create table if not exists dunhao.user
(
    id          int auto_increment comment 'id'
    primary key,
    username    varchar(128)                       not null comment '用户名',
    password    varchar(128)                       not null comment '密码',
    phone       char(33)                           null comment '号码',
    email       varchar(128)                       null comment '邮箱',
    avatar      varchar(256)                       null comment '头像',
    sex         tinyint  default 1                 not null comment '性别：0女，1男',
    create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time datetime default CURRENT_TIMESTAMP not null comment '更新时间',
    state       tinyint  default 1                 not null comment '状态：0失效，1有效'
    )
    comment '用户表';

create table if not exists dunhao.user_role
(
    id      int auto_increment comment 'id'
    primary key,
    user_id int not null comment '用户id',
    role_id int not null comment '角色id'
)
    comment '用户角色表';

