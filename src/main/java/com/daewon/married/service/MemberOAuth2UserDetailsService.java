package com.daewon.married.service;

import com.daewon.married.dto.MemberAuthDTO;
import com.daewon.married.dto.MemberDTO;
import com.daewon.married.entity.MarriedMemberRole;
import com.daewon.married.entity.Member;
import com.daewon.married.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class MemberOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {


        log.info("----------------------------");
        log.info("userRequest : " + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();
        log.info("clientName : " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("============================");

        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + " : " + v);    // sub, picture, email, email_verified, ...
        });

        String email = null;

        if (clientName.equals("Google")){
            email = oAuth2User.getAttribute("email");
        }

        log.info("EMAIL : " + email);

//        Member member = registerSocialMember(oAuth2User);
//
//        return oAuth2User;
        Member member = registerSocialMember(oAuth2User);
        MemberAuthDTO memberAuthDTO = new MemberAuthDTO(
                member.getEmail(),
                member.getPassword(),
                true,
                member.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority("ROLE_" + role.name())
                ).collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );
        memberAuthDTO.setName(member.getName());

        System.out.println("=======================================================================");
        System.out.println("=======================================================================");

        System.out.println(oAuth2User.getAttributes());
        for (String key : oAuth2User.getAttributes().keySet()){
            System.out.println("key : " + key + ", value : " + oAuth2User.getAttributes().get(key));
        }

        System.out.println("=======================================================================");
        System.out.println("=======================================================================");

        return memberAuthDTO;
    }

    private Member registerSocialMember(OAuth2User oAuth2User) {
        Optional<Member> result = memberRepository.findByEmail(oAuth2User.getAttribute("email"), true);

        if (result.isPresent()) return result.get();

        Member member = Member.builder()
                .email(oAuth2User.getAttribute("email"))
//                .name(oAuth2User.getAttribute("name"))
//                .age(oAuth2User.getAttribute("age"))
                .fromSocial(true)
//                .password(passwordEncoder.encode(oAuth2User.getAttribute("password")))
                .password(passwordEncoder.encode("1234"))
                .build();

        member.addMemberRole(MarriedMemberRole.MEMBER);

        memberRepository.save(member);

        return member;
    }
}