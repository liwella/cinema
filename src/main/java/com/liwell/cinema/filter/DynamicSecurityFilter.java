package com.liwell.cinema.filter;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;

import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * Description:
 *
 * @author Li
 * @date Created on 2023/3/16
 */

public class DynamicSecurityFilter extends AbstractSecurityInterceptor implements Filter {


    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return null;
    }

}
