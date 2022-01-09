package com.example.sampleboard.controller.comment;

import com.example.sampleboard.entity.comment.dto.CommentRequestDto;
import com.example.sampleboard.entity.comment.dto.CommentResponseDto;
import com.example.sampleboard.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/reply")
public class CommentApiController {

    private final ReplyService replyService;

    @PostMapping(value = "")
    public Long registerReply(@RequestBody CommentRequestDto commentRequestDto){
        return replyService.registerReply(commentRequestDto);
    }

    @GetMapping(value = "/{boardId}")
    public List<CommentResponseDto> findReplyByBoardId(@PathVariable Long boardId){
        return replyService.findReplyByBoardId(boardId);
    }

}
