package com.bitggal.member.repository;

import com.bitggal.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//db로 작업해주는 인터페이스
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
    // 추상 메소드 정의하기 >> 그 메소드에 맞는 쿼리가 알아서 만들어짐
    // 이메일로 회원 정보 조희 (select * from member_table where member_email =?)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}
