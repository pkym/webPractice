package com.bitggal.member.controller;

import com.bitggal.member.dto.MemberDTO;
import com.bitggal.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Member;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {
    /** 생성자주입 memberservice를 통해 db에 접근하기 */
    private final MemberService memberService;

    /** 회원가입페이지 출력요청 */
    @GetMapping("/member/join")
    public String joinForm(){
        return "join";
    }

    /** 회원가입페이지에서 값을 DTO로 담아서 보내기 */
    @PostMapping("/member/join")
    public String join(@ModelAttribute MemberDTO memberDTO){
        System.out.println("MemberController.join");
        System.out.println("memberDTO = " + memberDTO);
        memberService.join(memberDTO);

        return "login";
    }
    /* ModelAttribute 대신에 각각의 param 을 보낼 수도 있다
    public String join(@RequestParam("memberEmail") String memberEmail,
                       @RequestParam("memberPw") String memberPw,
                       @RequestParam("memberName") String memberName){
        System.out.println("MemberController.join");
        System.out.println("memberEmail = " + memberEmail + ", memberPw = " + memberPw + ", memberName = " + memberName);
        return "main";
    }*/

    /** 로그인 페이지 출력요청 */
    @GetMapping("/member/login")
    public String loginForm(){
        System.out.println("로그인페이지가기");

        return "login";
    }

    /** 로그인 페이지에서 값을 DTO로 담아서 보내고 해당회원정보 받아오기 */
    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session){
        MemberDTO loginResult = memberService.login(memberDTO);
        if(loginResult !=null){
            //  로그인 성공시 홈 페이지로 이동
            session.setAttribute("loginName", loginResult.getMemberName());
            session.setAttribute("loginEmail", loginResult.getMemberEmail());
            return "home";
            /** 로그인 시 게시판 목록이 나오게 하기 ? */
            // return redirect:/board/;
        }else{
            // 로그인 실패시 계속 로그인 페이지에 머물기
            return "login";
        }
    }

    /** 전체 회원 페이지 출력요청 */
    @GetMapping("/member/")
    public String findAll(Model model){
        List<MemberDTO> memberDTOList = memberService.findAll();
        // html 로 가져갈 데이터있다면 model 사용
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }
    /** 회원상세보기 출력요청 */
    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model){
        // PathVariable: 경로상의 값 가져올 때 사용
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";

    }
    /** 회원수정하기 출력요청 */
    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model){
        String myEmail = (String)session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }
    /** 회원 수정하기 페이지에서 값을 받아 상세보기 페이지로 이동 */
    @PostMapping("/member/update")
    public String update(@ModelAttribute MemberDTO memberDTO){
        memberService.update(memberDTO);
        return "redirect:/member/"+memberDTO.getId();
    }
    /** 회원삭제하기 요청 */
    @GetMapping("/member/delete/{id}")
    public String deleteById(@PathVariable Long id){
        memberService.deleteById(id);
        return "redirect:/member/";
    }
    /** 로그아웃하기 요청 */
    @GetMapping("/member/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "main";
    }

}