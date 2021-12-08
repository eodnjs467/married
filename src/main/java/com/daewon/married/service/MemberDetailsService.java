package com.daewon.married.service;

import com.daewon.married.dto.MemberAuthDTO;
import com.daewon.married.entity.Member;
import com.daewon.married.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("loadUserByUsername : " + username);

        Optional<Member> result = memberRepository.findByEmail(username, false);

        if (result.isPresent()==false) {
            throw new UsernameNotFoundException("Email을 확인해주세요.");
        }
        Member member = result.get();

//        log.info(member);

        MemberAuthDTO memberAuthDTO = new MemberAuthDTO(member.getEmail(), member.getPassword(),
                member.isFromSocial(), Arrays.stream(member.getRoleMap().get("MARRIED_47").values()).map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toSet()));
//                values().stream()
//                .map();

        memberAuthDTO.setName(member.getName());
        memberAuthDTO.setFromSocial(member.isFromSocial());

        return memberAuthDTO;
    }
}
