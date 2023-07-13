package com.bitggal.member.repository;

import com.bitggal.member.entity.BoardEntity;
import com.bitggal.member.entity.CmtEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CmtRepository extends JpaRepository<CmtEntity, Long> {

    /** select * from cmt_table where board_id? order by id desc; */
    List<CmtEntity> findAllByBoardEntityOrderByIdDesc(BoardEntity boardEntity);

}
