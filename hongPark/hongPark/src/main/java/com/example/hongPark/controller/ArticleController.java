package com.example.hongPark.controller;

import com.example.hongPark.dto.ArticleForm;
import com.example.hongPark.dto.CommentDto;
import com.example.hongPark.entity.Article;
import com.example.hongPark.repository.ArticleRepository;
import com.example.hongPark.service.CommentService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {
    @Autowired // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결
    private ArticleRepository articleRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/articles/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String createArticles(ArticleForm form) {
        log.info(form.toString());
//        System.out.println(form.toString()); -> 로깅으로 대체

        // 1. DTO를 변환 : Entity
        Article article = form.toEntity();
        log.info("Entity -> " + article.toString());
//        System.out.println("Entity -> " + article.toString());
        // 2. Repository에서 Entity를 DB 안에 저장
        Article saved = articleRepository.save(article);
        log.info("saved -> " + saved.toString());
//        System.out.println("saved -> " + saved.toString());
        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = " + id);

        // 1. id로 데이터를 가져옴
        Article articleEntity = articleRepository.findById(id).orElse(null);
        List<CommentDto> commentDtos = commentService.comments(id);
        // 2. 가져온 데이터를 모델에 등록
        model.addAttribute("article", articleEntity);
        model.addAttribute("commentDtos", commentDtos);
        // 3. 보여줄 페이지를 설정
        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        // 1. 모든 Article을 가져옴
        List<Article> articleEntityList = articleRepository.findAll();
        // 2. 가져온 Article 몪음을 뷰로 전달
        model.addAttribute("articleList", articleEntityList);
        // 3. 뷰 페이지 설정
        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        // 수정할 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        // 모델에 데이터 등록
        model.addAttribute("article", articleEntity);
        // 뷰 페이지 설정
        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm form) {
        // 1. DTO를 Entity로 변환
        Article articleEntity = form.toEntity();
        // 2. Entity를 DB로 저장
        // 2 - 1 DB에서 기존 데이터를 가져옴
        Article article = articleRepository.findById(articleEntity.getId()).orElse(null);
        // 2 - 2 기존 데이터 값을 수정
        if(article != null) {
            articleRepository.save(articleEntity);
        }
        // 3. 수정 결과 페이지로 리다이텍트
        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr) {
        // 1. 삭제 대상을 가져옴
        Article article = articleRepository.findById(id).orElse(null);
        // 2. 대상을 삭제
        if(article != null) {
            articleRepository.deleteById(article.getId());
            rttr.addFlashAttribute("msg", "삭제완료");
        }
        // 3. 결과페이지
        return "redirect:/articles";
    }
}
