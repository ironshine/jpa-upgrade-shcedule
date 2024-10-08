package com.sparta.jpaupgradeschedule.filter;

import com.sparta.jpaupgradeschedule.entity.User;
import com.sparta.jpaupgradeschedule.jwt.JwtUtil;
import com.sparta.jpaupgradeschedule.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j(topic = "AuthFilter")
@Component
@Order(2)
public class AuthFilter implements Filter {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthFilter(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String url = httpServletRequest.getRequestURI();

        if (StringUtils.hasText(url) &&
                (url.equals("/users/login") || url.equals("/users/signup") || url.startsWith("/css") || url.startsWith("/js"))
        ) {
            // 회원가입, 로그인 관련 API 는 인증 필요없이 요청 진행
            chain.doFilter(request, response); // 다음 Filter 로 이동
        } else {
            // 나머지 API 요청은 인증 처리 진행
            // 토큰 확인
            String tokenValue = jwtUtil.getTokenFromRequest(httpServletRequest);

            if (StringUtils.hasText(tokenValue)) { // 토큰이 존재하면 검증 시작
                // JWT 토큰 substring
                String token = jwtUtil.substringToken(tokenValue);

                // 토큰 검증
                if (!jwtUtil.validateToken(token)) {
                    httpServletResponse.sendError(401, "Wrong Token");
                }
                else {
                    // 토큰에서 사용자 정보 가져오기
                    Claims info = jwtUtil.getUserInfoFromToken(token);

                    User user = userRepository.findByEmail(info.getSubject());
                    if (user == null) {
                        httpServletResponse.sendError(401, "Not Found Email");
                    } else if (!user.getEmail().equals(info.getSubject())) {
                        httpServletResponse.sendError(401, "Not Found Email");
                    }

                    request.setAttribute("user", user);
                }
                chain.doFilter(request, response); // 다음 Filter 로 이동
            } else {
                httpServletResponse.sendError(400, "Not Found Token");
//                throw new IllegalArgumentException("400, Not Found Token");
            }
        }
    }
}