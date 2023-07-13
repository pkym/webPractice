package com.bitggal.member.controller;

import com.bitggal.member.dto.BoardDTO;
import com.bitggal.member.dto.CmtDTO;
import com.bitggal.member.service.BoardService;
import com.bitggal.member.service.CmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor //service class 호출하고 가야함
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final CmtService cmtService;
    /** 글쓰기 페이지 요청 */
    @GetMapping("/save")
    public String saveForm(HttpSession session){
        return "board/write";
    }
    /** 글쓰기 후 메인페이지로 이동*/
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO, HttpSession session){
        System.out.println("boardDTO = " + boardDTO);
        boardService.save(boardDTO);
        return "redirect:/home/";
    }
    /** 글 목록(커뮤니티) 요청하기 */
    @GetMapping("/")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList = boardService.findAll();
        // html로 가져갈 때 model 에 담아서 가기
        model.addAttribute("boardList", boardDTOList);
        return "board/list";
    } //로그인 했을 때만 볼 수 있어야 하는데 /board/를 치면 바로 볼 수 있는 문제점 있음

    /** 글 상세 목록 요청하기 */
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model, HttpSession session){
        // 해당 게시글의 조회수를 하나 올리고
        boardService.updateView(id);
        // 게시글 데이터를 가져와서 detail.html에 출력
        BoardDTO boardDTO = boardService.findById(id);
        model.addAttribute("board", boardDTO);
        // 댓글 목록 가져오기
        List<CmtDTO> cmtDTOList = cmtService.findAll(id);
        model.addAttribute("cmtList", cmtDTOList);

        return "board/detail";
    }

    /** 글 수정 화면 출력요청 */
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable Long id, Model model){
        BoardDTO boardDTO = boardService.findById(id);
        // 수정페이지에 내가 썼던 작성 기록이 남아있어야 함
        model.addAttribute("boardUpdate", boardDTO);
        return "board/update";
    }

    /** 글 수정한 후 바로 반영하기*/
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model){
        BoardDTO board = boardService.update(boardDTO);
        model.addAttribute("board",board);
        // 아니면 admindetail.html 로 바로 이동하기
        return "redirect:/board/" + boardDTO.getId();
    }

    /** 글 삭제하기 */
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        boardService.delete(id);
        return "redirect:/board/";
    }
}
