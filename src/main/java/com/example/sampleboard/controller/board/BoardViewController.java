package com.example.sampleboard.controller.board;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/view/boards")
public class BoardViewController {

    @RequestMapping({"", "/"})
    public String boardList(){
        return "/board/list";
    }

    @RequestMapping("/{boardId}")
    public String boardView(@PathVariable Long boardId, Model model){
        model.addAttribute("boardId", boardId);
        return "/board/view";
    }

    @RequestMapping("/write")
    public String boardWrite(){
        return "/board/write";
    }
}
