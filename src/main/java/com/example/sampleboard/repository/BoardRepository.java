package com.example.sampleboard.repository;

import com.example.sampleboard.entity.board.Board;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByUseYn(boolean useYn, Sort boardId);
}
