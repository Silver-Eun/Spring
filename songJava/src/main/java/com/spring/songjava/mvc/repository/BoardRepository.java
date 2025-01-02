package com.spring.songjava.mvc.repository;

import com.spring.songjava.mvc.domain.Board;
import com.spring.songjava.mvc.parameter.BoardParameter;
import com.spring.songjava.mvc.parameter.BoardSearchParameter;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface BoardRepository {
    List<Board> getList(BoardSearchParameter parameter);

    Board get(int boardSeq);

    void save(BoardParameter board);

    void saveList(Map<String, Object> paramMap);

    void update(BoardParameter board);

    void delete(int boardSeq);
}
