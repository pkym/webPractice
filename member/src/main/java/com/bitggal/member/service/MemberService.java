package com.bitggal.member.service;

import com.bitggal.member.dto.MemberDTO;
import com.bitggal.member.entity.MemberEntity;
import com.bitggal.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    /** 회원가입 메소드 */
    public void join(MemberDTO memberDTO){
        //repository의 save메소드 호출, 반드시 entity객체를 넘겨줘야함
        // 1. dto > entity 로 변환후 넘겨주기
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
        // 2. repository 의 save 메소드(jpa제공) 호출( entity 객체를 넘겨줘야함)
        memberRepository.save(memberEntity);
    }

    /** 로그인 메소드 */
    public MemberDTO login(MemberDTO memberDTO){
        //1. 회원이 입력한 이메일 db조회
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberDTO.getMemberEmail());
        //2. 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
        if(byMemberEmail.isPresent()){
            // 정상적인 로그인
            // optional 에 있는 객체를 entity 객체로 가져오기 .get()
            MemberEntity memberEntity = byMemberEmail.get();

            if(memberEntity.getMemberPw().equals(memberDTO.getMemberPw())){
                // 비밀번호 일치시 entity > dto 로 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            }else{
                // 비밀번호 불일치(로그인실패)
                return null;
            }
        } else {
            //  해당이메일 존재 x
            return null;
        }
    }

    /** 전체 회원조회 메소드*/
    public List<MemberDTO> findAll(){
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        // for문으로 entity값을 dto에 하나하나 옮겨닮음
        for(MemberEntity memberEntity: memberEntityList){
            //memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
            MemberDTO memberDTO = MemberDTO.toMemberDTO((memberEntity));
            memberDTOList.add(memberDTO);
        }
        return memberDTOList;
    }

    /** 한명 회원조회 메소드 */
    public MemberDTO findById(Long id){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if(optionalMemberEntity.isPresent()){
            MemberEntity memberEntity = optionalMemberEntity.get();
            MemberDTO memberDTO = MemberDTO.toMemberDTO(memberEntity);
            return memberDTO;
        }else{
            return null;
        }
    }

    /** 회원 정보 수정화면 출력 메소드 */
    public MemberDTO updateForm(String myEmail){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if(optionalMemberEntity.isPresent()){
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        }else{
            return null;
        }
    }

    /** 회원 정보 수정 반영 메소드 */
    public void update(MemberDTO memberDTO){
        // Id 있으면 update 쿼리를 작성해줌
        memberRepository.save(MemberEntity.toupdateMemberEntity(memberDTO));
    }

    /** 회원 삭제 메소드 */
    public void deleteById(Long id){
        memberRepository.deleteById(id);
    }



}
