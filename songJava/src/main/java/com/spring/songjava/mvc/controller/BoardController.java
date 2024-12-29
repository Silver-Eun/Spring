package com.spring.songjava.mvc.controller;

import com.spring.songjava.mvc.domain.Board;
import com.spring.songjava.mvc.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping
    public List<Board> getList() {
        return boardService.getList();
    }

    @GetMapping("/{boardSeq}")
    public Board get(@PathVariable int boardSeq) {
        return boardService.get(boardSeq);
    }

    @GetMapping("/save")
    public int save(Board board) {
        boardService.save(board);
        return board.getBoardSeq();
    }

    @GetMapping("/delete/{boardSeq}")
    public boolean delete(@PathVariable int boardSeq) {
        Board board = boardService.get(boardSeq);
        if (board == null) {
            return false;
        }
        boardService.delete(boardSeq);
        return true;
    }
}
