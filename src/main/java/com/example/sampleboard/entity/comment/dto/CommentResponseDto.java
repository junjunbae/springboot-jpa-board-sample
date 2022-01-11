package com.example.sampleboard.entity.comment.dto;

import com.example.sampleboard.entity.board.dto.BoardResponseDto;
import com.example.sampleboard.entity.comment.Comment;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponseDto {

    private Long commentId;
    private String contents;
    private boolean useYn;
    private int likeCnt;
    private String createdId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private String modifiedId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedDate;

    private BoardResponseDto resBoardDto;

    public CommentResponseDto(Comment comment){
        this.commentId = comment.getCommentId();
        this.contents = comment.getContents();
        this.useYn = comment.isUseYn();
        this.likeCnt = comment.getLikeCnt();
        this.createdId = comment.getCreatedId();
        this.createdDate = comment.getCreatedDate();
        this.modifiedId = comment.getModifiedId();
        this.modifiedDate = comment.getModifiedDate();
        this.resBoardDto = new BoardResponseDto(comment.getBoard());
    }

}
