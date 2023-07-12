package com.bitggal.member.repository;

import com.bitggal.member.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // 특수 목적의 쿼리들은 별도로 메소드로 정의할 필요있음
    // update board_table set boardView=boardView+1 where id=?
    /** 조회 수 증가 */

    // entity 기준으로 하는 쿼리문 >> 약어가 필수
    @Modifying
    @Query(value="update BoardEntity b set b.boardView=b.boardView+1 where b.Id=:id")
    void updateView (@Param("id") Long id);

}
