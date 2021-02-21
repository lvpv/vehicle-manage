package com.lv.vehicle.security.filter;

import com.lv.vehicle.security.common.JwtProperties;
import com.lv.vehicle.security.service.TokenService;
import com.lv.vehicle.security.vo.AuthUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;

    private RequestMatcher requiresAuthenticationRequestMatcher;

    private final TokenService tokenService;

    public TokenFilter(JwtProperties jwtProperties, TokenService tokenService){
        this.requiresAuthenticationRequestMatcher = new RequestHeaderRequestMatcher(jwtProperties.getAuthHeader());
        this.jwtProperties = jwtProperties;
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!requiresAuthentication(request)){
            filterChain.doFilter(request, response);
            return;
        }
        String token = getToken(request);
        if (StringUtils.isNotBlank(token)){
            AuthUser authUser = tokenService.getUserByToken(token);
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authUser != null && authentication == null){
                tokenService.verifyToken(authUser);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(authUser,null,authUser.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);

    }

    private String getToken(HttpServletRequest request){
        String token = request.getHeader(jwtProperties.getAuthHeader());
        if (StringUtils.isNotBlank(token) && token.startsWith(jwtProperties.getStartWith())){
            return StringUtils.removeStart(token,jwtProperties.getStartWith());
        }
        return null;
    }

    protected boolean requiresAuthentication(HttpServletRequest request) {
        return requiresAuthenticationRequestMatcher.matches(request);
    }
}
