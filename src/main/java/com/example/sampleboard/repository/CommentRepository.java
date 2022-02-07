package com.example.sampleboard.repository;

import com.example.sampleboard.entity.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from Comment c where c.board.boardId = :boardId")
    List<Comment> findByBoardId(@Param("boardId") Long boardId);

}
