package com.spring.songjava.mvc.parameter;

import com.spring.songjava.mvc.domain.BoardType;
import lombok.Data;

import java.util.List;

@Data
public class BoardSearchParameter {

    private String keyword;
    private List<BoardType> boardTypes;

    public BoardSearchParameter() {

    }
}
