package com.spring.songjava.mvc.controller;

import com.spring.songjava.configuration.exception.BaseException;
import com.spring.songjava.configuration.http.BaseResponse;
import com.spring.songjava.configuration.http.BaseResponseCode;
import com.spring.songjava.framework.data.domain.MySQLPageRequest;
import com.spring.songjava.framework.data.domain.PageRequestParameter;
import com.spring.songjava.framework.data.web.bind.annotation.RequestConfig;
import com.spring.songjava.mvc.domain.Board;
import com.spring.songjava.mvc.domain.MenuType;
import com.spring.songjava.mvc.parameter.BoardParameter;
import com.spring.songjava.mvc.parameter.BoardSearchParameter;
import com.spring.songjava.mvc.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Tag(name = "게시판 API")
public class BoardController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BoardService boardService;

    @GetMapping("/{menuType}")
    @Operation(summary = "목록 조회", description = "게시글 목록 정보 조회")
    public String list(@PathVariable MenuType menuType,
                       BoardSearchParameter parameter,
                       MySQLPageRequest pageRequest,
                       Model model) {
        logger.info("menuType : {}", menuType);
        logger.info("pageRequest : {}", pageRequest);

        parameter.setBoardType(menuType.boardType());
        PageRequestParameter<BoardSearchParameter> pageRequestParameter
                = new PageRequestParameter<>(pageRequest, parameter);
        List<Board> boardList = boardService.getList(pageRequestParameter);
        model.addAttribute("boardList", boardList);
        model.addAttribute("menuType", menuType);
        return "/board/list";
    }

    @GetMapping("/{menuType}/{boardSeq}")
    public String detail(@PathVariable MenuType menuType, @PathVariable int boardSeq, Model model) {
        Board board = boardService.get(boardSeq);
        if (board == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[]{"게시물"});
        }
        model.addAttribute("board", board);
        model.addAttribute("menuType", menuType);
        return "/board/detail";
    }

    // 등록 / 수정 화면
    @GetMapping("/{menuType}/form")
    @RequestConfig(loginCheck = false)
    public String form(@PathVariable MenuType menuType,
                       BoardParameter parameter, Model model) {
        if (parameter.getBoardSeq() > 0) {
            Board board = boardService.get(parameter.getBoardSeq());
            model.addAttribute("board", board);
        }
        model.addAttribute("parameter", parameter);
        model.addAttribute("menuType", menuType);

        return "/board/form";
    }

    @GetMapping("/{menuType}/edit/{boardSeq}")
    @RequestConfig(loginCheck = false)
    public String edit(@PathVariable MenuType menuType,
                       @PathVariable(required = true) int boardSeq,
                       BoardParameter parameter, Model model) {
        Board board = boardService.get(parameter.getBoardSeq());
        if (board == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[]{"게시물"});
        }
        model.addAttribute("board", board);
        model.addAttribute("parameter", parameter);
        model.addAttribute("menuType", menuType);

        return "/board/form";
    }

    @PostMapping("/{menuType}/save")
    @RequestConfig(loginCheck = false)
    @ResponseBody
    @Operation(summary = "등록 / 수정 처리", description = "신규 게시글 저장 및 기존 게시글 업데이트")
    @Parameters({
            @Parameter(name = "boardSeq", description = "게시글 번호", example = "1"),
            @Parameter(name = "title", description = "제목", example = "spring"),
            @Parameter(name = "contents", description = "내용", example = "spring 강의")
    })
    public BaseResponse<Integer> save(@PathVariable MenuType menuType,
                                      BoardParameter parameter,
                                      BoardParameter board) {
        // 제목 필수 체크
        if (StringUtils.isEmpty(board.getTitle())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"title", "제목"});
        }
        // 내용 필수 체크
        if (StringUtils.isEmpty(board.getContents())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"contents", "내용"});
        }
        parameter.setBoardType(menuType.boardType());
        boardService.save(board);
        return new BaseResponse<>(board.getBoardSeq());
    }

    @DeleteMapping("/delete/{boardSeq}")
    @RequestConfig
    @Operation(summary = "삭제 처리", description = "게시글 번호에 해당하는 게시글 삭제")
    @Parameters({
            @Parameter(name = "boardSeq", description = "게시글 번호", example = "1")
    })
    public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
        Board board = boardService.get(boardSeq);
        if (board == null) {
            return new BaseResponse<Boolean>(false);
        }
        boardService.delete(boardSeq);
        return new BaseResponse<Boolean>(true);
    }

}
