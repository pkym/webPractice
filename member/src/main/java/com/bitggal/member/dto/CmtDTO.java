package com.bitggal.member.dto;

import com.bitggal.member.entity.CmtEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CmtDTO {
    private Long id;
    private String memberName;
    private String cmtContent;
    private Long boardId;
    private LocalDateTime cmtDate;

    public static CmtDTO toCmtDTO(CmtEntity cmtEntity, Long boardId){
        CmtDTO cmtDTO = new CmtDTO();
        cmtDTO.setId(cmtEntity.getId());
        cmtDTO.setMemberName(cmtEntity.getMemberName());
        cmtDTO.setCmtContent(cmtEntity.getCmtContents());
        cmtDTO.setCmtDate(cmtEntity.getCmtDate());
        cmtDTO.setBoardId(boardId);

        // boardId를 안받아온다면
        // cmtDTO.setBoardId(cmtEntity.getBoardEntity().getId());
        // 라고 해도 됨
        return  cmtDTO;
    }

}
