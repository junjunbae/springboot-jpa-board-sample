package com.example.sampleboard.entity.board.dto;

import com.example.sampleboard.entity.board.Board;
import lombok.Data;

@Data
public class BoardRequestDto {

    private String title;
    private String contents;
    private int hit;
    private boolean useYn;

    public Board toEntity(){
        return Board.boardRegistration(title,contents,hit,useYn);
    }

}
