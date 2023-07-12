package com.bitggal.member.dto;

import com.bitggal.member.entity.BoardEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
    private long id;
    private String boardType;
    private String boardTitle;
    private String boardContent;
    //private String boardFile;
    private int boardView;
    private String memberEmail;
    private String memberName;
    private LocalDateTime boardDate;

    public static BoardDTO toBoardDTO(BoardEntity boardEntity){
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(boardEntity.getId());
        boardDTO.setBoardType(boardEntity.getBoardType());
        boardDTO.setBoardTitle(boardEntity.getBoardTitle());
        boardDTO.setBoardContent(boardEntity.getBoardContent());
        // boardfile 자리
        boardDTO.setBoardView(boardEntity.getBoardView());
        // email 은 가져오지않았음
        boardDTO.setMemberName(boardEntity.getMemberName());
        boardDTO.setBoardDate(boardEntity.getBoardDate());
        return boardDTO;
    }


}
