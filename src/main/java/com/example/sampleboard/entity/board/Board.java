package com.example.sampleboard.entity.board;

import com.example.sampleboard.entity.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "TB_BOARD")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardId;

    private String title;
    private String contents;
    @ColumnDefault("0")
    private int hit;
    @ColumnDefault("1")
    private boolean useYn;

    private Board(String title, String contents, int hit, boolean useYn) {
        this.title = title;
        this.contents = contents;
        this.hit = hit;
        this.useYn = useYn;
    }

    public static Board boardRegistration(String title, String contents, int hit, boolean useYn) {
        return new Board(title, contents, hit, useYn);
    }


    // 수정
    public void update(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    // 조회수
    public void increaseHits() {
        this.hit++;
    }

    // 삭제
    public void delete() {
        this.useYn = false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) { // null인지
            return false;
        }

        if (obj == this) { // this인지
            return true;
        }

        if(getClass() != obj.getClass()){ // 참조 클래스가 같은지
            return false;
        }

        Board board = (Board)obj; // 같은 클래스인 경우 형변환
        return (this.getBoardId() == board.getBoardId()); // this와 obj의 pk값 비교
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = (int) (PRIME * result + getBoardId());
        return result;
    }
}
