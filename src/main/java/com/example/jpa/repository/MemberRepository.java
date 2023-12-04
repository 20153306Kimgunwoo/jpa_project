package com.example.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.jpa.model.Member;
import java.util.List;


@Repository
public interface MemberRepository extends JpaRepository<Member, String>{
    Member findByMemberIdAndMemberPw(String memberId, String memberPw);
    List<Member> findByMemberPwAndMemberId(String memberPw, String memberId);
    List<Member> findByMemberId(String memberId);
}
