package com.bitggal.member.controller;

import com.bitggal.member.dto.BoardDTO;
import com.bitggal.member.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {
    /** 기본페이지 요청 메소드 */
    @GetMapping("/")
    public String index(){
        return "main";
    }

    private final BoardService boardService;
    /** 다시 홈으로 돌아가는 요청 메소드 */
    @GetMapping("/home")
    public String  homepage(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "home";
    }

    /** 에러발생시 에러페이지 요청 메소드 */
    @GetMapping("/error")
    public String handleError(){
        return "error";
    }

}
