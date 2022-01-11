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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public Long registerReply(CommentRequestDto commentRequestDto) {
        Optional<Board> board = boardRepository.findByBoardIdAndUseYn(commentRequestDto.getBoardId(), true);

        commentRequestDto.setBoard(board.get());

        return commentRepository.save(commentRequestDto.toEntity()).getCommentId();
    }

    public List<CommentResponseDto> findReplyByBoardId(Long boardId) {
        List<Comment> comments = commentRepository.findByBoardId(boardId);

        List<CommentResponseDto> resComments = comments.stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());

        return resComments;
    }
}
