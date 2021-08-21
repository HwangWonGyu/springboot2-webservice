package com.jsh440274.book.springboot.config.auth;

import com.jsh440274.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
//Spring Security 설정 활성화
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    //and()를 기준으로 각 설정에 대한 진입점을 나타낸다.
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                //h2-console사용을 위해서 비활성화해준다.
                .csrf().disable()
                .headers().frameOptions().disable()

                .and()
                //URL별 권한 관리를 설정하는 옵션의 시작점
                //authorizeRequests가 선언되어야만 antMatchers옵션을 사용할 수 있다.
                .authorizeRequests()
                //권한 관리 대상을 지정하는 옵션.
                //URL, HTTP 메소드별로 관리가 가능하다.
                /*
                h2-console로 데이터베이스를 볼 수 있다 -> 왜 permitAll일까? 인증된 사용자들 중에서도 관리자만 볼 수 있어야 하는것이 아닐까?
                */
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //설정된 값들 이외의 URL들은 인증된 사용자들에게만 허용한다.
                .anyRequest().authenticated()

                .and()
                //로그아웃 기능에 대한 설정의 진입점.
                .logout()
                //로그아웃 성공시 "/"의 주소로 이동한다.
                .logoutSuccessUrl("/")

                .and()
                //OAuth2로그인 기능에 대한 설정의 진입점.
                .oauth2Login()
                //로그인 성공 이후 사용자 정보를 가져올때 설정 담당
                .userInfoEndpoint()
                //소셜 로그인 성공 시 후속조치를 진행할 인터페이스의 구현체를 등록한다.
                //리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
                .userService(customOAuth2UserService);
    }
}
