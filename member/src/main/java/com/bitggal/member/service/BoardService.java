package com.bitggal.member.service;

import com.bitggal.member.dto.BoardDTO;
import com.bitggal.member.dto.MemberDTO;
import com.bitggal.member.entity.BoardEntity;
import com.bitggal.member.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    /** 글쓰기 메소드 */
    public void save(BoardDTO boardDTO){
        BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
        boardRepository.save(boardEntity);
    }
    /** 전체 글목록 가져오기 메소드 */
    public List<BoardDTO> findAll(){
        // 전체가져오면 엔티티리스트 형태로 가져와지게 됨
        List<BoardEntity> boardEntityList = boardRepository.findAll(Sort.by(Sort.Direction.DESC,"boardDate"));
        // 리스트 디티오에 옮겨 닮기
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity: boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
    /** 일부글 가져오기 메소드*/
    public List<BoardDTO> findfive(){
        List<BoardEntity> boardEntityList = boardRepository.findTopFive();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity: boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }

    /** 조회 수 증가 메소드 */
    @Transactional
    public void updateView(Long id){
        boardRepository.updateView(id);
    }
    /** 글 하나 조회 메소드 및 글 수정화면 출력 메소드 */
    public BoardDTO findById(Long id){
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(id);
        if(optionalBoardEntity.isPresent()){
            BoardEntity boardEntity = optionalBoardEntity.get();
            BoardDTO boardDTO = BoardDTO.toBoardDTO(boardEntity);
            return boardDTO;
        }else{
            return null;
        }
    }
    /** 글 수정 메소드 */
    public BoardDTO update(BoardDTO boardDTO){
        boardRepository.save(BoardEntity.toUpdateEntity(boardDTO));
        return findById(boardDTO.getId());
    }
    /** 글 삭제 메소드 */
    public void delete(Long id){
        boardRepository.deleteById(id);
    }
}
