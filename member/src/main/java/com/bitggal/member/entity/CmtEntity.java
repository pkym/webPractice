package com.bitggal.member.entity;

import com.bitggal.member.dto.CmtDTO;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "cmt_table")
public class CmtEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /* Member:Cmt = 1;N
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_name")*/

    @Column
    private String memberName;

    @Column
    private String cmtContents;

    /** Board:Cmt = 1: N */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="board_id")
    private BoardEntity boardEntity;

    public static CmtEntity toSaveEntity(CmtDTO cmtDTO, BoardEntity boardEntity){
        CmtEntity cmtEntity = new CmtEntity();
        cmtEntity.setMemberName(cmtDTO.getMemberName());
        cmtEntity.setCmtContents(cmtDTO.getCmtContent());
        // 참조관계 맺었을 때의 문법
        cmtEntity.setBoardEntity(boardEntity);
        return cmtEntity;
    }


}
