package com.example.sampleboard.repository;

import com.example.sampleboard.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByBoardIdAndUseYn(Long boardId, boolean useYn);

    Page<Board> findByUseYnTrue(Pageable pageSort);

    Page<Board> findByUseYnTrueAndTitleContains(String searchKeyword, Pageable boardId);

    Page<Board> findByUseYnTrueAndContentsContains(String searchKeyword, Pageable boardId);

    Page<Board> findByUseYnTrueAndCreatedIdContains(String searchKeyword, Pageable boardId);
}
