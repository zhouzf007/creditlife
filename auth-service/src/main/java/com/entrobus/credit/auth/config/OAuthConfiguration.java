package com.entrobus.credit.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OAuthConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager auth;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Autowired
    private Environment env;

    @Autowired
    private DataSource dataSource;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

//    @Bean
//    public JdbcTokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }

//    @Bean
//    public InMemoryTokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    }

    @Bean
    public RedisTokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
            throws Exception {
        security.passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints
                .authenticationManager(auth)
                .tokenStore(tokenStore());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
//        clients.jdbc(dataSource)
//           clients.inMemory()
//                .withClient("client")
//                .secret("secret")
//                .authorizedGrantTypes("password", "refresh_token")
//                .scopes("read", "write")
//                .accessTokenValiditySeconds(3600) // 1 hour
//                .refreshTokenValiditySeconds(2592000) // 30 days
//                .and()
//                .withClient("client2")
//                .secret("secret2")
//                .authorizedGrantTypes("password", "refresh_token")
//                .scopes("read", "write")
//                .accessTokenValiditySeconds(3600) // 1 hour
//                .refreshTokenValiditySeconds(3600) // 30 days
//                .and()
//                .withClient("svca-service")
//                .secret("password")
//                .authorizedGrantTypes("client_credentials", "refresh_token")
//                .scopes("server")
//                .and()
//                .withClient("svcb-service")
//                .secret("password")
//                .authorizedGrantTypes("client_credentials", "refresh_token")
//                .scopes("server")
//        ;

        clients.inMemory()
                .withClient("client")
                .secret(new BCryptPasswordEncoder().encode("secret"))
                .authorizedGrantTypes("password", "refresh_token")
                .scopes("read", "write")
                .accessTokenValiditySeconds(3600) // 1 hour
                .refreshTokenValiditySeconds(2592000) // 30 days
                .and()
                .withClient("msg-service")
//                .secret(env.getProperty("security.user.password"))
                .secret(new BCryptPasswordEncoder().encode("password"))
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server")
                .accessTokenValiditySeconds(3600) // 1 hour
                .refreshTokenValiditySeconds(2592000) // 30 days
                .and()
                .withClient("user-service")
                .secret(new BCryptPasswordEncoder().encode("password"))
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server")
                .and()
                .withClient("log-service")
                .secret(new BCryptPasswordEncoder().encode("password"))
                .authorizedGrantTypes("client_credentials", "refresh_token")
                .scopes("server")

        ;
    }

    //
    @Configuration
    @Order(-20)
    protected static class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {

//        @Autowired
//        private DataSource dataSource;

        @Override
        public void init(AuthenticationManagerBuilder auth) throws Exception {
//
//            auth.jdbcAuthentication().dataSource(dataSource)
//                    .withUser("dave").password("secret").roles("USER")
//                    .and()
//                    .withUser("anil").password("password").roles("ADMIN")
//            ;
//
            auth.inMemoryAuthentication()
                    .withUser("dave").password("secret").roles("USER")
                    .and()
                    .withUser("anil").password("password").roles("ADMIN")
            ;
        }
    }

}