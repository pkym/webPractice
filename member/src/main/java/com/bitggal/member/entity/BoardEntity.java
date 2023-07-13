package com.bitggal.member.entity;

import com.bitggal.member.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// DB 의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity{
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long Id;

    @Column
    private String boardType;

    @Column // size 255, nullable
    private String boardTitle;

    @Column(length = 500)
    private String boardContent;

    @Column
    private int boardView;

    @Column
    private String memberEmail;

    @Column
    private String memberName;

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<CmtEntity> cmtEntityList = new ArrayList<>();

    public static BoardEntity toSaveEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardType(boardDTO.getBoardType());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
        boardEntity.setBoardView(0);
        boardEntity.setMemberEmail(boardDTO.getMemberEmail());
        boardEntity.setMemberName(boardDTO.getMemberName());
        return boardEntity;
    }
    public static BoardEntity toUpdateEntity(BoardDTO boardDTO){
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardTitle(boardDTO.getBoardTitle());
        boardEntity.setBoardContent(boardDTO.getBoardContent());
        boardEntity.setBoardView(boardDTO.getBoardView());
        boardEntity.setMemberEmail(boardDTO.getMemberEmail());
        boardEntity.setMemberName(boardDTO.getMemberName());
        return boardEntity;
    }

}

