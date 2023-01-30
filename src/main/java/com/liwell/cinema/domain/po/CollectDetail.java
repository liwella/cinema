package com.liwell.cinema.domain.po;

import lombok.Data;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/25
 */
@Data
public class CollectDetail {

    private Integer group_id;
    private Integer type_id; // 分类id
    private Integer type_id_1;
    private String type_name; // 分类名称
    private String vod_actor; // 演员列表
    private String vod_area; // 地区
    private String vod_author;
    private String vod_behind;
    private String vod_blurb; // 详情简介
    private String vod_class;
    private String vod_color;
    private String vod_content; // 详情
    private Integer vod_copyright;
    private String vod_director; // 导演
    private Integer vod_douban_id; // 豆瓣id
    private String vod_douban_score; // 豆瓣评分
    private Integer vod_down;
    private String vod_down_from;
    private String vod_down_note;
    private String vod_down_server;
    private String vod_down_url;
    private String vod_duration;
    private String vod_en;
    private Integer vod_hits;
    private Integer vod_hits_day;
    private Integer vod_hits_month;
    private Integer vod_hits_week;
    private Integer vod_id; // 影片id
    private Integer vod_isend;
    private String vod_jumpurl;
    private String vod_lang; // 影片语言
    private String vod_letter;
    private Integer vod_level;
    private Integer vod_lock;
    private String vod_name; // 影片名称
    private String vod_pic; // 影片图片
    private String vod_pic_screenshot;
    private String vod_pic_slide;
    private String vod_pic_thumb;
    private String vod_play_from;
    private String vod_play_note;
    private String vod_play_server;
    private String vod_play_url; // 播放链接
    private Integer vod_plot;
    private String vod_plot_detail;
    private String vod_plot_name;
    private Integer vod_points;
    private Integer vod_points_down;
    private Integer vod_points_play;
    private String vod_pubdate;
    private String vod_pwd;
    private String vod_pwd_down;
    private String vod_pwd_down_url;
    private String vod_pwd_play;
    private String vod_pwd_play_url;
    private String vod_pwd_url;
    private String vod_rel_art;
    private String vod_rel_vod;
    private String vod_remarks; // 备注
    private String vod_reurl;
    private String vod_score;
    private Integer vod_score_all;
    private Integer vod_score_num;
    private String vod_serial;
    private String vod_state;
    private Integer vod_status;
    private String vod_sub;
    private String vod_tag;
    private String vod_time; // 上架时间
    private Integer vod_time_add; // 上架时间戳
    private Integer vod_time_hits;
    private Integer vod_time_make;
    private Integer vod_total; // 总集数
    private String vod_tpl;
    private String vod_tpl_down;
    private String vod_tpl_play;
    private Integer vod_trysee;
    private String vod_tv;
    private Integer vod_up;
    private String vod_version;
    private String vod_weekday;
    private String vod_writer; // 编剧
    private String vod_year; // 出厂年份

}
