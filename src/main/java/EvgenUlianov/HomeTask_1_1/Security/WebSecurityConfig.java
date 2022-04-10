package EvgenUlianov.HomeTask_1_1.Security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    final private CustomUserDetailsService userDetailsService;
    final private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                        .antMatchers("/")//
                        .permitAll()
                        .and()
                .authorizeRequests()
                        .antMatchers("/hello")//
                        .permitAll()
                        .and()
                .authorizeRequests()
                        .antMatchers("/task/**", "/tasks/**", "/taskRest/**")//
                        .hasRole("USER")
                        .and()
                .authorizeRequests()
                        .antMatchers("/userRest/**")//
                        .hasRole("ADMIN")
                        .and()
                .httpBasic()
                        .and().csrf().disable()
                .formLogin()
                        .loginPage("/login")
                        .permitAll()
                        .and()
                .logout()
                        .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

}