package com.learn.security;

import com.learn.utils.utils.MD5;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DefaultPasswordEncoder implements PasswordEncoder {

    public DefaultPasswordEncoder() {
        this(-1);
    }


    public DefaultPasswordEncoder(int str) {

    }
    // 进行md5 加密
    @Override
    public String encode(CharSequence charSequence) {

        return MD5.encrypt((String) charSequence);
    }



    /**
     * 进行md5 比对
     * @param charSequence   加密后的密码
     * @param encodePassword   传入密码
     * @return boolean
     */
    @Override
    public boolean matches(CharSequence charSequence, String encodePassword) {

        return encodePassword.equals(MD5.encrypt((String) charSequence));
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
