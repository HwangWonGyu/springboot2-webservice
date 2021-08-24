package com.jsh440274.book.springboot.config.auth;

import com.jsh440274.book.springboot.config.auth.dto.OAuthAttributes;
import com.jsh440274.book.springboot.config.auth.dto.SessionUser;
import com.jsh440274.book.springboot.domain.user.User;
import com.jsh440274.book.springboot.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        //현재 로그인중인 서비스를 구분하는 코드
        //구글만 사용한다면 불필요하지만, 네이버 로그인까지 연동하려 할 때 어떤 로그인인지 구분하기 위함.
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        //OAuth2 로그인 진행시 키가 되는 필드값. PK와 같은 의미
        //구글은 기본코드 sub을 지원하지만 네이버나 카카오는 기본코드를 지원하지 않는다.
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        OAuthAttributes attributes = OAuthAttributes.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        User user = saveOrUpdate(attributes);

        //세션에 사용자 정보를 저장하기 위한 Dto클래스
        //User클래스를 사용하지 않고 새로 만들어서 사용한다.
        /*
        세션을 사용하는 경우와 아닌 경우의 차이
        세션의 경우 브라우저가 종료될 때 까지만 유지 되는 정보를 가지고 있다. 일반적으로 사용자가 브라우저에 머물러 있을 때만 정보를 가지고 있어야 하는 로그인의 특성 상 자주 사용 됨.
        세션리스의 경우는 브라우저에 머물러 있을 때만 가지고 있어야 하는 정보가 아닌 경우, 고정적인 정보를 화면에 띄워주게 되는 경우에 사용할 수 있다.
         */
        httpSession.setAttribute("user", new SessionUser(user));

        return new DefaultOAuth2User(Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(), attributes.getNameAttributeKey());
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
