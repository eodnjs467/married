package com.daewon.married.service;

import com.daewon.married.dto.MemberDTO;
import com.daewon.married.entity.MarriedMemberRole;
import com.daewon.married.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Random;

public interface MemberService {

    Long register(MemberDTO memberDTO);

    String authUpdate(MemberDTO memberDTO);

}