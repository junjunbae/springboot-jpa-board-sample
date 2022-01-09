package com.example.sampleboard.entity.comment.dto;

import com.example.sampleboard.entity.board.Board;
import com.example.sampleboard.entity.comment.Comment;
import lombok.Data;

@Data
public class CommentRequestDto {

    private String contents;
    private Long boardId;

    private Board board;

    public Comment toEntity(){
        return Comment.commentRegistration(contents, board);
    }

}
