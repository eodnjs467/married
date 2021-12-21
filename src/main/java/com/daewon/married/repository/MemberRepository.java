package com.daewon.married.repository;

import com.daewon.married.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m where m.email=:email and m.fromSocial=:social")
    Optional<Member> findByEmailAndSocial(String email, boolean social);

    @Query("select m from Member m where m.email=:email")
    Member findByEmail(String email);


}
