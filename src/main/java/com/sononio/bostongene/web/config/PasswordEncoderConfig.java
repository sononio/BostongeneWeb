package com.sononio.bostongene.web.config;


import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configures encryption algorithm for passwords.
 */
@Configuration
public class PasswordEncoderConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return DigestUtils.sha256Hex(rawPassword.toString());
            }
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return DigestUtils.sha256Hex(rawPassword.toString()).equals(encodedPassword);
            }
        };
    }
}
