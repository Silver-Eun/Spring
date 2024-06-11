package com.example.hongPark.repository;

import com.example.hongPark.entity.Article;
import com.example.hongPark.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest // JPA와 연동항 테스트
class CommentRepositoryTest {

    @Autowired CommentRepository commentRepository;

    @Test
    @DisplayName("특정 게시글의 모든 댓글 조회")
    void findByArticleId() {
        // 4번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 4L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상
            Article article = new Article(4L, "your favorite movie", "1111");
            Comment a = new Comment(1L, article, "Park", "Good will Hunting");
            Comment b = new Comment(2L, article, "Kim", "I am Sam");
            Comment c = new Comment(3L, article, "Choi", "Shawshank Escape");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "4번 글의 모든 댓글 출력");
        }
        // 1번 게시글의 모든 댓글 조회
        {
            // 입력 데이터 준비
            Long articleId = 1L;
            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);
            // 예상
            Article article = new Article(1L, "aaaa", "1111");
            List<Comment> expected = Arrays.asList();
            // 검증
            assertEquals(expected.toString(), comments.toString(), "1번 글은 댓글 없음");
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        // Park의 모든 댓글 조회
        {
            // 입력 데이터 준비
            String nickname = "Park";
            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);
            // 예상
            Comment a = new Comment(1L, new Article(4L, "your favorite movie", "1111"), nickname, "Good will Hunting");
            Comment b = new Comment(4L, new Article(5L, "your favorite food", "2222"), nickname, "chicken");
            Comment c = new Comment(7L, new Article(6L, "your hobby", "3333"), nickname, "jogging");
            List<Comment> expected = Arrays.asList(a, b, c);
            // 검증
            assertEquals(expected.toString(), comments.toString(), "Park의 모든 댓글 출력");
        }
    }
}