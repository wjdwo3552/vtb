package com.tvb.api.logging;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class LoggingFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String ipAddress = request.getHeader("X-Forwarded-For");
        String domain =request.getServerName();
        String requestId = request.getHeader("X-RequestID");
        MDC.put("requestId", requestId);
        MDC.put("ipAddress", ipAddress);
        MDC.put("domain", domain);
        filterChain.doFilter(request, response);
        MDC.remove("requestId");
        MDC.remove("ipAddress");
        MDC.remove("domain");
    }
}
