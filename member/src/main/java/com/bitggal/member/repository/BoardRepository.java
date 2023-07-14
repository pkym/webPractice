package com.bitggal.member.repository;

import com.bitggal.member.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {

    // 특수 목적의 쿼리들은 별도로 메소드로 정의할 필요있음

    /** 조회 수 증가 */
    // update board_table set boardView=boardView+1 where id=?
    // entity 기준으로 하는 쿼리문 >> 약어가 필수
    // @Modifying: 수정, 삭제시 붙여줘야함
    @Modifying
    @Query(value="update BoardEntity b set b.boardView=b.boardView+1 where b.Id=:id")
    void updateView (@Param("id") Long id);


    /** 로그인 후 메인 홈에서 글 갯수 제한 */

    @Query(value ="select * from board_table ORDER BY board_view DESC limit 5", nativeQuery = true)
    List<BoardEntity> findTopFive();


}
