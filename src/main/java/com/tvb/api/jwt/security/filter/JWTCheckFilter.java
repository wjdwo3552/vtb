package com.tvb.api.jwt.security.filter;

import com.tvb.api.jwt.security.auth.UserPrincipal;
import com.tvb.api.jwt.security.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JWTCheckFilter extends OncePerRequestFilter {
    private JWTUtil jwtUtil;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        System.out.println("check path: " + path);
        if (path.startsWith("/api/v1/auth") || path.startsWith("/api/v1/register")) {
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String headerStr = request.getHeader("Authorization");
        if(headerStr == null || !headerStr.startsWith("Bearer ")) {
            handleException(response, new Exception("Access Token Not Found."));
            return;
        }
        String accessToken = headerStr.substring(7);
        try {
            java.util.Map<String, Object> tokenMap = jwtUtil.validateToken(accessToken);

            //TODO : uid의 정보를 가져올 수 있는 코드 추가 필요
            String uid = "test";
            //TODO : roles라는 역할이 추가될 때 마찬가지로 roles를 받아오는 코드 추가 필요
            String[] roles = {"user", "admin"};

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    new UserPrincipal(uid), null, Arrays.stream(roles)
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                            .collect(Collectors.toList())
            );

            SecurityContext context = SecurityContextHolder.getContext();
            context.setAuthentication(authenticationToken);

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            handleException(response, e);
        }

    }
    private void handleException(HttpServletResponse response, Exception e) throws IOException {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType("application/json");
        response.getWriter().println("{\"error\":\"" + e.getMessage() + "\"}");
    }
}
