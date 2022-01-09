package com.example.sampleboard.entity.comment.dto;

import com.example.sampleboard.entity.board.Board;
import com.example.sampleboard.entity.comment.Comment;
import lombok.Data;

@Data
public class CommentResponseDto {

    private Long commentId;
    private String contents;

    private Board board;

    public CommentResponseDto(Comment reply){
        this.commentId = reply.getCommentId();
        this.contents = reply.getContents();
        this.board = reply.getBoard();
    }

}
