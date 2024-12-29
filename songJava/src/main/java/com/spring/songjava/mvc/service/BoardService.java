package com.spring.songjava.mvc.service;

import com.spring.songjava.mvc.domain.Board;
import com.spring.songjava.mvc.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository repository;

    public List<Board> getList() {
        return repository.getList();
    }

    public Board get(int boardSeq) {
        return repository.get(boardSeq);
    }

    public void save(Board board) {
        Board b = repository.get(board.getBoardSeq());
        if (b == null) {
            repository.save(board);
        } else {
            repository.update(board);
        }
    }

    public void delete(int boardSeq) {
        repository.delete(boardSeq);
    }
}
