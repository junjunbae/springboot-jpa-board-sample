package com.example.sampleboard.entity.board.dto;

import com.example.sampleboard.entity.base.BaseEntity;
import com.example.sampleboard.entity.board.Board;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardResponseDto {

    Long boardId;
    private String title;
    private String contents;
    private int hit;
    private boolean useYn;
    private String createdId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;
    private String modifiedId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime modifiedDate;

    public BoardResponseDto(Board board) {
        this.boardId = board.getBoardId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.hit = board.getHit();
        this.useYn = board.isUseYn();
        this.createdId = board.getCreatedId();
        this.createdDate = board.getCreatedDate();
        this.modifiedId = board.getModifiedId();
        this.modifiedDate = board.getModifiedDate();
    }
}
