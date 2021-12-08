package com.example.sampleboard.service;

import com.example.sampleboard.entity.board.Board;
import com.example.sampleboard.entity.board.dto.BoardRequestDto;
import com.example.sampleboard.entity.board.dto.BoardResponseDto;
import com.example.sampleboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long registerBoard(BoardRequestDto boardRequestDto) {
        return boardRepository.save(boardRequestDto.toEntity()).getBoardId();
    }

    public List<BoardResponseDto> findBoardAll() {
        List<Board> list = boardRepository.findByUseYn(true, Sort.by(Sort.Direction.DESC, "boardId"));

        return list.stream() // 조회결과 list를 stream으로 읽고
                .map(BoardResponseDto::new) // 각각의 요소들을 BoardResponseDto로 형변환하여
                .collect(Collectors.toList()); // list로 담아 최종 리턴.
    }

    @Transactional
    public BoardResponseDto findBoardByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);
        board.increaseHits();

        return new BoardResponseDto(board);
    }

    @Transactional
    public Long updateBoardByBoardId(Long boardId, BoardRequestDto boardRequestDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);

        board.update(boardRequestDto.getTitle(), boardRequestDto.getContents()); // dirty checking

        return boardId;
    }

    @Transactional
    public void deleteBoardByBoardId(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(NoSuchElementException::new);
        board.delete();
    }
}
