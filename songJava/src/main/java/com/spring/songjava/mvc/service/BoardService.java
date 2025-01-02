package com.spring.songjava.mvc.service;

import com.spring.songjava.mvc.domain.Board;
import com.spring.songjava.mvc.parameter.BoardParameter;
import com.spring.songjava.mvc.parameter.BoardSearchParameter;
import com.spring.songjava.mvc.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BoardService {

    @Autowired
    private BoardRepository repository;

    public List<Board> getList(BoardSearchParameter parameter) {
        return repository.getList(parameter);
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

    public void saveList1(List<BoardParameter> list) {
        for (BoardParameter parameter : list) {
            repository.save(parameter);
        }
    }

    public void saveList2(List<BoardParameter> boardList) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("boardList", boardList);
        repository.saveList(paramMap);
    }

    public void delete(int boardSeq) {
        repository.delete(boardSeq);
    }
}
