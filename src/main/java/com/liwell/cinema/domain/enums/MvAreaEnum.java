package com.liwell.cinema.domain.enums;

import lombok.Getter;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/24
 */
@Getter
public enum MvAreaEnum implements BaseEnum {

    UNKNOWN(-1, "未知", ""),
    CHINESE(1, "中国大陆", "中国大陆内地内陆国内,CHINA,China,china"),
    HKM(2, "港澳", "中国香港中国澳门港澳,HONG KONG,hong kong,Hong Kong"),
    TAIWAN(3, "台湾", "中国台湾岛台湾省港台TAIWAN,taiwan"),
    JAPAN(4, "日本", "日本国JAPAN,Japan"),
    KOREA(5, "韩国", "日韩国KOREA,Korea"),
    AMERICA(6, "美国", "美国欧美AMERICA,America"),
    UNITED_KINGDOM(7, "英国", "英国英格兰"),
    FRANCE(8, "法国", "法国法兰西"),
    GERMANY(9, "德国", "德国慕尼黑"),
    INDIA(10, "印度", "印度印第安"),
    THAILAND(11, "泰国", "泰国"),
    DAN_MAI(12, "丹麦", "丹麦"),
    RUI_DIAN(13, "瑞典", "瑞典"),
    BRAZIL(14, "巴西", "巴西"),
    CANADA(15, "加拿大", "加拿大"),
    RASIA(16, "俄罗斯", "俄罗斯俄国"),
    ITALY(17, "意大利", "意大利"),
    BELGIUM(18, "比利时", "比利时"),
    IRELAND(19, "爱尔兰", "爱尔兰"),
    SPAIN(20, "西班牙", "西班牙"),
    AUSTRALIA(21, "澳大利亚", "澳大利亚澳洲"),
    VIETNAM(22, "越南", "越南"),
    FEI_LV_BIN(23, "菲律宾", "菲律宾"),
    NETHERLANDS(24, "荷兰", "荷兰"),
    TURKEY(25, "土耳其", "土耳其"),
    ;

    private Integer value;

    private String description;

    private String pattern;

    MvAreaEnum(Integer value, String description) {
        this.value = value;
        this.description = description;
    }

    MvAreaEnum(Integer value, String description, String pattern) {
        this.value = value;
        this.description = description;
        this.pattern = pattern;
    }

    public static MvAreaEnum getByPattern(String pattern) {
        MvAreaEnum[] enumConstants = MvAreaEnum.class.getEnumConstants();
        for (MvAreaEnum enumConstant : enumConstants) {
            if (enumConstant.getPattern().contains(pattern)) {
                return enumConstant;
            }
        }
        return UNKNOWN;
    }
}