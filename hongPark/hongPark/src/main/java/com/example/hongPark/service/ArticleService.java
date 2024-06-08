package com.example.hongPark.service;

import com.example.hongPark.dto.ArticleForm;
import com.example.hongPark.entity.Article;
import com.example.hongPark.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service // 서비스 선언(서비스 객체를 스프링에 생성)
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public List<Article> index() {
        return articleRepository.findAll();
    }

    public Article show(Long id) {
        return articleRepository.findById(id).orElse(null);
    }

    public Article create(ArticleForm dto) {
        Article article = dto.toEntity();
        if(article.getId() != null) {
            return null;
        }
        return articleRepository.save(article);
    }

    public Article update(Long id, ArticleForm dto) {
        // 1. 수정용 엔티티 생성
        Article article = dto.toEntity();
        // 2. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리(대상이 없거나 아이디가 다른 경우)
        if(target != null || id != article.getId()) {
            return null;
        }
        // 4. 업데이트 생성 및 정상 응답
        target.patch(article);
        Article updated = articleRepository.save(target);
        return updated;
    }

    public Article delete(Long id) {
        // 1. 대상 엔티티 찾기
        Article target = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리(대상이 없는 경우)
        if(target == null) {
            return null;
        }
        // 3. 대상 삭제 후 응답 반환
        articleRepository.delete(target);
        return target;
    }
    @Transactional // 해당 메소드를 transaction으로 묶음
    public List<Article> createarticles(List<ArticleForm> dtos) {
        // dto 묶음을 entity 묶음으로 변환
        List<Article> articleList = dtos.stream().map(dto -> dto.toEntity()).collect(Collectors.toList());
        // entity 묶음을 DB로 저장
        articleList.stream().forEach(article -> articleRepository.save(article));
        // 강제 예외 발생
        articleRepository.findById(-1L).orElseThrow(
                () -> new IllegalArgumentException("검색 실패")
        );
        // 결과값 반환
        return articleList;
    }
}
