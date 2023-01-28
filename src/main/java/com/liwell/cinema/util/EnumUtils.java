package com.liwell.cinema.util;

import com.baomidou.mybatisplus.annotation.IEnum;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/27
 */
public class EnumUtils {

    public static <T extends IEnum> T get(Class<T> c, Object value) {
        for (T e : c.getEnumConstants()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }

}
