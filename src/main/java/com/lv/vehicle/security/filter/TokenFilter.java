package com.lv.vehicle.security.filter;

import com.lv.vehicle.domain.User;
import com.lv.vehicle.security.common.JwtProperties;
import com.lv.vehicle.security.vo.AuthUser;
import com.lv.vehicle.utils.JwtUtil;
import com.lv.vehicle.utils.RsaUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cloud.bootstrap.encrypt.KeyProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.PublicKey;
import java.util.Collection;


public class TokenFilter extends OncePerRequestFilter {

    private final JwtProperties jwtProperties;

    private final KeyProperties keyProperties;

    private RequestMatcher requiresAuthenticationRequestMatcher;

    public TokenFilter(JwtProperties jwtProperties, KeyProperties keyProperties){
        this.requiresAuthenticationRequestMatcher = new RequestHeaderRequestMatcher(jwtProperties.getAuthHeader());
        this.jwtProperties = jwtProperties;
        this.keyProperties = keyProperties;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!requiresAuthentication(request)){
            filterChain.doFilter(request, response);
            return;
        }
        String token = getToken(request);
        if (StringUtils.isNotBlank(token)){
            PublicKey publicKey = RsaUtil.getPublicKey(keyProperties);
            AuthUser authUser = JwtUtil.getUserFromToken(token, publicKey);
            Collection<? extends GrantedAuthority> authorities = authUser.getAuthorities();
            User user = new User();
            user.setUserId(authUser.getUserId());
            user.setUserName(authUser.getUsername());
            user.setRealName(authUser.getRealName());
            user.setDingId(authUser.getDingId());
            user.setAuthorities(authorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user, "", authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else {
            System.out.println("token = " + token);
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
