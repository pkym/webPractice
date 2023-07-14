package com.bitggal.member.controller;

import com.bitggal.member.dto.BoardDTO;
import com.bitggal.member.dto.CmtDTO;
import com.bitggal.member.service.CmtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cmt")
public class CmtController {
    private final CmtService cmtService;
    /** 댓글 저장 요청하기 (ajax >> @ResponseBody String >> ResponseEntity) */
    @PostMapping("/save")
    public ResponseEntity save(@ModelAttribute CmtDTO cmtDTO, HttpSession session) {
        System.out.println("cmtDTO = " + cmtDTO);
        Long saveResult = cmtService.save(cmtDTO);
        if (saveResult != null) {
            // 작성 성공시 댓글 목록 가져와서 리턴
            List<CmtDTO> cmtDTOList = cmtService.findAll(cmtDTO.getBoardId());
            // 댓글목록: 해당게시글의 댓글 전체
            return new ResponseEntity<>(cmtDTOList, HttpStatus.OK);
            // entity 는 바디와 헤더를 같이 다룰 수 있는 객체
            // 목록데이터가 ajax 에서 res에 담아서 확인할 수 있음
        } else {
            // 작성 실패
            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다", HttpStatus.NOT_FOUND);
        }
    }

    /** 댓글 삭제하기 */
    @GetMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        cmtService.delete(id);
        System.out.println("삭제가 되었습니다!!!!"+id);
        return new ResponseEntity("댓글이 삭제되었습니다", HttpStatus.OK);
    }


}
