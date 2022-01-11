package com.example.sampleboard.entity.comment;

import com.example.sampleboard.entity.base.BaseEntity;
import com.example.sampleboard.entity.board.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @ColumnDefault("0")
    private int likeCnt;

    @ColumnDefault("1")
    private boolean useYn;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    public Comment(Long commentId, String contents, boolean useYn, int likeCnt, Board board) {
        this.commentId = commentId;
        this.contents = contents;
        this.useYn = useYn;
        this.likeCnt = likeCnt;
        this.board = board;
    }

    public Comment(String contents, Board board) {
        this.contents = contents;
        this.board = board;
    }

    public static Comment commentRegistration(String contents, Board board) {
        return new Comment(contents, board);
    }
}
