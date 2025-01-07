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
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
//@RequestMapping("/board")
@Tag(name = "게시판 API")
public class BoardController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private BoardService boardService;

    //    @GetMapping("/")
    //    @ResponseBody
    @GetMapping("/{menuType}")
    @Operation(summary = "목록 조회", description = "게시글 목록 정보 조회")
//  public BaseResponse<List<Board>> getList(@Parameter BoardSearchParameter parameter,
    public String list(@PathVariable MenuType menuType,
                       BoardSearchParameter parameter,
                       MySQLPageRequest pageRequest,
                       Model model) {
        logger.info("menuType : {}", menuType);
        logger.info("pageRequest : {}", pageRequest);
        PageRequestParameter<BoardSearchParameter> pageRequestParameter
                = new PageRequestParameter<BoardSearchParameter>(pageRequest, parameter);
        List<Board> boardList = boardService.getList(pageRequestParameter);
        model.addAttribute("boardList", boardList);

        return "/board/list";
    }

    @GetMapping("/detail/{boardSeq}")
//    @ResponseBody
//    @Operation(summary = "상세 조회", description = "게시글 번호에 해당하는 상세 정보 조회")
//    @Parameters({
//            @Parameter(name = "boardSeq", description = "게시글 번호", example = "1")
//    })
//    public BaseResponse<Board> get(@PathVariable int boardSeq) {
    public String detail(@PathVariable int boardSeq, Model model) {
        Board board = boardService.get(boardSeq);
        if (board == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[]{"게시물"});
        }
        model.addAttribute("board", board);
        return "/board/detail";
    }

    // 등록 / 수정 화면
    @GetMapping("/form")
    @RequestConfig(loginCheck = false)
    public void form(BoardParameter parameter, Model model) {
        if (parameter.getBoardSeq() > 0) {
            Board board = boardService.get(parameter.getBoardSeq());
            model.addAttribute("board", board);
        }
        model.addAttribute("parameter", parameter);
    }

    @GetMapping("/edit/{boardSeq}")
    @RequestConfig(loginCheck = false)
    public String edir(@PathVariable(required = true) int boardSeq,
                       BoardParameter parameter, Model model) {
        Board board = boardService.get(parameter.getBoardSeq());
        if (board == null) {
            throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[]{"게시물"});
        }
        model.addAttribute("board", board);
        model.addAttribute("parameter", parameter);

        return "/board/form";
    }

    //    @PutMapping("/save")
    @PostMapping("/save")
    @RequestConfig(loginCheck = false)
    @ResponseBody
    @Operation(summary = "등록 / 수정 처리", description = "신규 게시글 저장 및 기존 게시글 업데이트")
    @Parameters({
            @Parameter(name = "boardSeq", description = "게시글 번호", example = "1"),
            @Parameter(name = "title", description = "제목", example = "spring"),
            @Parameter(name = "contents", description = "내용", example = "spring 강의")
    })
//    public BaseResponse<Integer> save(@RequestBody BoardParameter board) {
    public BaseResponse<Integer> save(BoardParameter board) {
        // 제목 필수 체크
        if (StringUtils.isEmpty(board.getTitle())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"title", "제목"});
        }
        // 내용 필수 체크
        if (StringUtils.isEmpty(board.getContents())) {
            throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[]{"contents", "내용"});
        }
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

    @PutMapping("/saveList")
    @Operation(summary = "대용량 등록처리1", description = "대용량 등록처리1")
    public BaseResponse<Boolean> saveList1() {
        int count = 0;
        // 테스트를 위한 1000건의 랜덤 데이터 생성
        List<BoardParameter> list = new ArrayList<BoardParameter>();
        while (true) {
            count++;
            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);
            list.add(new BoardParameter(title, contents));
            if (count >= 1000) {
                break;
            }
        }
        long start = System.currentTimeMillis();
        boardService.saveList1(list);
        long end = System.currentTimeMillis();
        logger.info("실행시간 : {}", (end - start) / 1000.0);
        return new BaseResponse<Boolean>(true);
    }

    @PutMapping("/saveList2")
    @Operation(summary = "대용량 등록처리2", description = "대용량 등록처리2")
    public BaseResponse<Boolean> saveList2() {
        int count = 0;
        // 테스트를 위한 랜덤 1000건의 데이터를 생성
        List<BoardParameter> list = new ArrayList<BoardParameter>();
        while (true) {
            count++;
            String title = RandomStringUtils.randomAlphabetic(10);
            String contents = RandomStringUtils.randomAlphabetic(10);
            list.add(new BoardParameter(title, contents));
            if (count >= 10000) {
                break;
            }
        }
        long start = System.currentTimeMillis();
        boardService.saveList2(list);
        long end = System.currentTimeMillis();
        logger.info("실행 시간 : {}", (end - start) / 1000.0);
        return new BaseResponse<Boolean>(true);
    }
}
