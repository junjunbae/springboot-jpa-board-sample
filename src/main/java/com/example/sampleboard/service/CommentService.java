package com.example.sampleboard.service;

import com.example.sampleboard.entity.board.Board;
import com.example.sampleboard.entity.comment.Comment;
import com.example.sampleboard.entity.comment.dto.CommentRequestDto;
import com.example.sampleboard.entity.comment.dto.CommentResponseDto;
import com.example.sampleboard.repository.BoardRepository;
import com.example.sampleboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long registerComment(CommentRequestDto commentRequestDto) {
        Board board = boardRepository.findByBoardIdAndUseYn(commentRequestDto.getBoardId(), true)
                .orElseThrow(NoSuchElementException::new);

        commentRequestDto.setBoard(board);

        return commentRepository.save(commentRequestDto.toEntity()).getCommentId();
    }

    public List<CommentResponseDto> findReplyByBoardId(Long boardId) {
        List<Comment> comments = commentRepository.findByBoardId(boardId);

        List<CommentResponseDto> resComments = comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

//        for(Comment comment : comments){
//            resComments.add(new CommentResponseDto(comment.getCommentId(),
//                    comment.getContents(),
//                    comment.isUseYn(),
//                    comment.getLikeCnt(),
//                    comment.getCreatedId(),
//                    comment.getCreatedDate(),
//                    comment.getModifiedId(),
//                    comment.getModifiedDate(),
//                    comment.getBoard()); // 얘는 어떠케,,,?
//        }

        return resComments;
    }


}
