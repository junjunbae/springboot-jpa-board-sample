package com.example.sampleboard.controller.comment;

import com.example.sampleboard.entity.comment.dto.CommentRequestDto;
import com.example.sampleboard.entity.comment.dto.CommentResponseDto;
import com.example.sampleboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping(value = "")
    public Long registerReply(@RequestBody CommentRequestDto commentRequestDto){
        return commentService.registerComment(commentRequestDto);
    }

    @GetMapping(value = "/{boardId}")
    public List<CommentResponseDto> findReplyByBoardId(@PathVariable Long boardId){
        return commentService.findReplyByBoardId(boardId);
    }

}
