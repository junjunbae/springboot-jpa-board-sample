package com.example.sampleboard.entity.comment;

import com.example.sampleboard.entity.base.BaseEntity;
import com.example.sampleboard.entity.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_COMMENT")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, length = 100)
    private String contents;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    public Comment(String contents, Board board) {
        this.contents = contents;
        this.board = board;
    }

    public static Comment commentRegistration(String contents, Board board) {
        return new Comment(contents, board);
    }
}
