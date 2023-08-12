package com.liwell.cinema.util;

import com.liwell.cinema.domain.enums.BaseEnum;

import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/1/27
 */
public class EnumUtils {

    public static <T extends BaseEnum> T get(Class<T> c, Object value) {
        for (T e : c.getEnumConstants()) {
            if (e.getValue().equals(value)) {
                return e;
            }
        }
        return null;
    }

    public static <T extends BaseEnum> List<T> listEnums(Class<T> c) {
        return Arrays.asList(c.getEnumConstants());
    }

}
