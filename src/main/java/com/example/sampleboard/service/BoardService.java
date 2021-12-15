package com.example.sampleboard.service;

import com.example.sampleboard.entity.board.Board;
import com.example.sampleboard.entity.board.dto.BoardRequestDto;
import com.example.sampleboard.entity.board.dto.BoardResponseDto;
import com.example.sampleboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long registerBoard(BoardRequestDto boardRequestDto) {
        return boardRepository.save(boardRequestDto.toEntity()).getBoardId();
    }

    public HashMap<String, Object> findBoardAll(Pageable page) {
        HashMap<String, Object> map = new HashMap();

        Page<Board> boards = boardRepository.findByUseYn(true, PageRequest.of(page.getPageNumber(), page.getPageSize(), Sort.by(Sort.Direction.DESC, "boardId")));
//        List<BoardResponseDto> boardResponseDtoList = list.getContent().stream().map(BoardResponseDto::new).collect(Collectors.toList());

        int startPage = Math.max(1, boards.getPageable().getPageNumber()-4);
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber()+4);
        map.put("startPage", startPage);
        map.put("endPage", endPage);
        map.put("RESULT", boards);

        return map;
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
