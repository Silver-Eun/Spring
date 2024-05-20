package com.example.hongPark.controller;

import com.example.hongPark.dto.ArticleForm;
import com.example.hongPark.entity.Article;
import com.example.hongPark.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticleController {
    @Autowired // 스프링부트가 미리 생성해놓은 객체를 가져다가 자동으로 연결
    private ArticleRepository articleRepository;

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
        return "";
    }
}
