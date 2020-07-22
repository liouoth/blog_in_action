package com.leo.ui_in_action.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *  @name WebSecurityConfig
 *  @Description will of coding divide into two system
 *  blog system allow all people to visit
 *  admin system only for will to manage
 *  @author will7 Mao
 *  @Date  2020/7/16
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/css/**","/js/**","/img/**").permitAll()  // static resources
                .antMatchers("/blog/**","/").permitAll() //open for view
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/blog").permitAll()
                .and().logout().permitAll();
    }
}
