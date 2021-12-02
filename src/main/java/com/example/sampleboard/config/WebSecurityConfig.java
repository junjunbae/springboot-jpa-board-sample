package com.example.sampleboard.config;

import com.example.sampleboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private String[] AUTH_WHITELIST = {
            // 스웨거 & 정적파일
            "/v2/api-docs",
            "/v3/api-docs/**",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/file/**",
            "/image/**",
            "/swagger/**",
            "/swagger-ui/**",
            "/css/**", 
            "/js/**", 
            "/img/**"
    };

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(AUTH_WHITELIST); // 예외허용
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/signup", "/user").permitAll() // 허용 경로
                .antMatchers("/").hasRole("USER") // USER 그룹
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN 그룹
                .anyRequest().authenticated() // Role구분 값에 관계없이 권한필요
            .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/") // 로그인 > 루트 페이지로 리다이렉션
            .and()
                .logout()
                .logoutSuccessUrl("/login") // 로그아웃 > 로그인 페이지로 리다이렉션
                .invalidateHttpSession(true) // 세션 해제
            .and()
                .exceptionHandling().accessDeniedPage("/denied"); // 권한x
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // userService에서 UserDetailsService implements -> loadUserByUsername() 구현
        // 로그인시 필요한 정보를 가져오는 함수이며 password 인코더는 BCrypt로 설정
        auth.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
