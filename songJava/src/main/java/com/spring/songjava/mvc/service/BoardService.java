package com.spring.songjava.mvc.service;

import com.spring.songjava.mvc.domain.Board;
import com.spring.songjava.mvc.parameter.BoardParameter;
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

    public void save(BoardParameter boardParameter) {
        Board board = repository.get(boardParameter.getBoardSeq());
        if (board == null) {
            repository.save(boardParameter);
        } else {
            repository.update(boardParameter);
        }
    }

    public void delete(int boardSeq) {
        repository.delete(boardSeq);
    }
}
