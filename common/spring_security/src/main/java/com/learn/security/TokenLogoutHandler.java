package com.learn.security;


import com.learn.utils.RedisConfig;
import com.learn.utils.utils.R;
import com.learn.utils.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//JWT  生成TOKEN
public class TokenLogoutHandler implements LogoutHandler {

    @Autowired
    private TokenManager tokenManager;
    @Autowired
    private RedisTemplate template;

    public TokenLogoutHandler(TokenManager tokenManager, RedisTemplate template) {
        this.tokenManager = tokenManager;
        this.template = template;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        //从 header 中获取token
        //token  不为null 移除  从redis 中移除

        String token = request.getHeader("token");

        if (token != null) {

            tokenManager.removeToken(token); //移除 token
            String username = tokenManager.getUserInfoFromToken(token); //从token  中获取token
            template.delete(username);
        }
//        R.ok() R  对象
        ResponseUtil.out(response, R.ok());
    }
}
