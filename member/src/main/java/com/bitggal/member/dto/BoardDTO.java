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
        boardDTO.setMemberEmail(boardEntity.getMemberEmail());
        boardDTO.setMemberName(boardEntity.getMemberName());
        boardDTO.setBoardDate(boardEntity.getBoardDate());
        return boardDTO;
    }


}
