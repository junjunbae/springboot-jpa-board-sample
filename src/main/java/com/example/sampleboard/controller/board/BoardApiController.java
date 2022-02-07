package com.example.sampleboard.controller.board;

import com.example.sampleboard.entity.board.dto.BoardRequestDto;
import com.example.sampleboard.entity.board.dto.BoardResponseDto;
import com.example.sampleboard.enumc.SearchType;
import com.example.sampleboard.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/boards")
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping(value = "")
    public Long registerBoard(@RequestBody BoardRequestDto boardRequestDto) {
        return boardService.registerBoard(boardRequestDto);
    }

    @GetMapping("")
    public HashMap<String, Object> findBoardAll(@PageableDefault(page = 0, size = 5) Pageable page,
                                                @RequestParam String searchType,
                                                @RequestParam String searchKeyword) {
        return boardService.findBoardAll(page, searchType, searchKeyword);
    }

    @GetMapping("/{boardId}")
    public BoardResponseDto findBoardByBoardId(@PathVariable Long boardId) {
        return boardService.findBoardByBoardId(boardId);
    }

    @PutMapping("/{boardId}")
    public Long updateBoardByBoardId(@PathVariable Long boardId, @RequestBody BoardRequestDto boardRequestDto) {
        return boardService.updateBoardByBoardId(boardId, boardRequestDto);
    }

    @DeleteMapping("/{boardId}")
    public void deleteBoardByBoardId(@PathVariable Long boardId){
        boardService.deleteBoardByBoardId(boardId);
    }

}
