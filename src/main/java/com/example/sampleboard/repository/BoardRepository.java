package com.example.sampleboard.repository;

import com.example.sampleboard.entity.board.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findByUseYn(boolean useYn, Pageable pageSort);

    Page<Board> findByUseYnAndTitleContains(boolean useYn, String searchKeyword, Pageable boardId);

    Page<Board> findByUseYnAndContentsContains(boolean useYn, String searchKeyword, Pageable boardId);

    Page<Board> findByUseYnAndCreatedIdContains(boolean useYn, String searchKeyword, Pageable boardId);
}
