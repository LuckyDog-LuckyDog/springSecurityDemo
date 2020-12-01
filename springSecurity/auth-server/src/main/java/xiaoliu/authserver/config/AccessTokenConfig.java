package xiaoliu.authserver.config;

import cn.hutool.log.Log;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Description: 授权服务器配置
 *
 * @author:xiaoLiu
 */
@Configuration
public class AccessTokenConfig {
    Log log = Log.get();
    @Bean
    TokenStore tokenStore() {
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    JwtAccessTokenConverter jwtAccessTokenConverter() {
        log.info(">>>>>>>>token 转换器");
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("javaboy");
        return converter;
    }
}
