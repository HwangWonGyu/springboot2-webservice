package com.beststar.book.springboot.config.auth;

import com.beststar.book.springboot.config.auth.dto.OAuthAttributes;
import com.beststar.book.springboot.config.auth.dto.SessionUser;
import com.beststar.book.springboot.domain.user.User;
import com.beststar.book.springboot.domain.user.UserRepository;
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

        String registrationId = userRequest
                .getClientRegistration()
                .getRegistrationId();
        // registrationId
        // 현재 로그인 진행 중인 서비스를 구분하는 코드
        // 지금은 구글만 사용하는 불필요한 값이지만, 이후 네이버 로그인 연동 시에 네이버 로그인인지, 구글 로그인인지 구분하기 위해 사용

        String userNameAttributeName = userRequest
                .getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        // userNameAttributeName
        // OAuth2 로그인 진행 시 키가 되는 필드값. Primary Key와 같은 의미
        // 구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 기본 지원하지 않음
        // 구글의 기본 코드는 "sub"
        // 이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용

        OAuthAttributes attributes = OAuthAttributes
                .of(registrationId, userNameAttributeName, oAuth2User.getAttributes());
        // OAuthAttributes
        // OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
        // 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용
        // 바로 아래에서 이 클래스의 코드가 나오니 차례로 생성하면 됨

        User user = saveOrUpdate(attributes);
        httpSession.setAttribute("user", new SessionUser(user));
        // SessionUser
        // 세션에 사용자 정보를 저장하기 위한 Dto 클래스
        // 만약 User 클래스를 그대로 사용했다면 다음과 같은 에러가 발생
        // Failed to convert from type [java.lang.Object] to type [byte[]] for value
        // 'com.beststar.book.springboot.domain.user.User@4a43d6'
        // -> 이는 세션에 저장하기 위해 User 클래스를 세션에 저장하려고 하니,
        // User 클래스에 직렬화를 구현하지 않았다는 의미의 에러
        // 그렇다고 User라는 엔티티 클래스에 직렬화를 그대로 적용하면
        // 후에 @OneToMany, @ManyToMany 등 자식 엔티티를 갖게 될때 직렬화 대상에 자식 엔티티까지 포함되니
        // 성능 이슈, 부수 효과가 발생할 확률이 높아짐
        // 그래서 직렬화 기능을 가진 세션 Dto를 하나 추가로 만드는 것이 이후 운영 및 유지보수 때 많은 도움이 됨

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
            );
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(), attributes.getPicture()))
                .orElse(attributes.toEntity());

        return userRepository.save(user);
    }
}
