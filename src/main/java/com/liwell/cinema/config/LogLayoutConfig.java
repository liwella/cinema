package com.liwell.cinema.config;

import ch.qos.logback.classic.pattern.ExtendedThrowableProxyConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import cn.hutool.core.util.RandomUtil;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * @author litianyi
 * @purpose
 * @history 2023/8/18 litianyi
 */
public class LogLayoutConfig extends LayoutBase<ILoggingEvent> {

    private static final String PROJECT_NAME = "dunhao";

    public static ThreadLocal<Long> unionId = new ThreadLocal<>();

    @Override
    public String doLayout(ILoggingEvent event) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("\"project\":");
        sb.append("\"" + PROJECT_NAME + "\", ");
        sb.append("\"timestamp\":");
        sb.append("\"").append(timeOfLongToStr(event.getTimeStamp())).append("\"");
        sb.append(", \"level\":");
        sb.append("\"").append(event.getLevel()).append("\"");
        sb.append(", \"thread\":");
        sb.append("\"").append(event.getThreadName()).append("\"");
        sb.append(", \"class\": ");
        sb.append("\"").append(event.getLoggerName()).append("\"");
        sb.append(", \"unionId\": ");
        sb.append("\"").append(getUnionId()).append("\"");
        sb.append(",\"message\": ");
        String message = event.getFormattedMessage();
        if (event.getThrowableProxy() != null) {
            ExtendedThrowableProxyConverter throwableConverter = new ExtendedThrowableProxyConverter();
            throwableConverter.start();
            message = event.getFormattedMessage() + "\n" + throwableConverter.convert(event);
            throwableConverter.stop();
        }
        sb.append("\"").append(message).append("\"");
        sb.append("}");
        sb.append(CoreConstants.LINE_SEPARATOR);
        return sb.toString();
    }

    private String timeOfLongToStr(long time) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
        return ftf.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault()));
    }

    private Long getUnionId() {
        Long result = unionId.get();
        if (result == null) {
            result = RandomUtil.randomLong();
            unionId.set(result);
        }
        return result;
    }

}
