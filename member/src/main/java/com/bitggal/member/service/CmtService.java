package com.bitggal.member.service;

import com.bitggal.member.dto.CmtDTO;
import com.bitggal.member.entity.BoardEntity;
import com.bitggal.member.entity.CmtEntity;
import com.bitggal.member.repository.BoardRepository;
import com.bitggal.member.repository.CmtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CmtService {
    private final CmtRepository cmtRepository;
    private final BoardRepository boardRepository;

    /** 원글에 댓글저장하기 메소드 */
    public Long save(CmtDTO cmtDTO){
        // 부모 엔티티 조회
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(cmtDTO.getBoardId());
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            CmtEntity cmtEntity = CmtEntity.toSaveEntity(cmtDTO, boardEntity);
            return cmtRepository.save(cmtEntity).getId();
        }else{
            return null;
        }
    }

    /** 원글에 댓글불러오기 메소드 */
    public List<CmtDTO> findAll(Long boardId){
        BoardEntity boardEntity =boardRepository.findById(boardId).get();
        List<CmtEntity> cmtEntityList = cmtRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        // Entitylist >> DTOlist 로 변환
        List<CmtDTO> cmtDTOList = new ArrayList<>();
        for(CmtEntity cmtEntity: cmtEntityList){
            CmtDTO cmtDTO = CmtDTO.toCmtDTO(cmtEntity, boardId);
            cmtDTOList.add(cmtDTO);
        }
        return cmtDTOList;
    }

    /** 댓글 삭제하기 메소드 */
    public void delete(Long id){
        cmtRepository.deleteById(id);
        System.out.println("삭제가 되었습니다!!!!!22222"+id);

    }



}
