package com.example.hongPark.service;

import com.example.hongPark.dto.CommentDto;
import com.example.hongPark.entity.Article;
import com.example.hongPark.entity.Comment;
import com.example.hongPark.repository.ArticleRepository;
import com.example.hongPark.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsFileUploadSupport;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
//        // 댓글 목록 조회
//        List<Comment> comments = commentRepository.findByArticleId(articleId);
//        // Entity -> DTO 변환
//        List<CommentDto> dtos = new ArrayList<>();
//        for (int i = 0; i < comments.size(); i++) {
//            Comment c = comments.get(i);
//            CommentDto dto = CommentDto.createCommentDto(c);
//            dtos.add(dto);
//        }


        // 반환
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto create(Long articleid, CommentDto dto) {
        // 게시글 조회 및 예외 발생
        Article article = articleRepository.findById(articleid)
                .orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패! 대상 게시글이 없습니다"));
        // 댓글 엔티티 생성
        Comment comment = Comment.createComment(dto, article);
        // 댓글 엔티티를 DB로 저장
        Comment created = commentRepository.save(comment);
        // DTO를 변경하여 변환
        return CommentDto.createCommentDto(created);
    }

    @Transactional
    public CommentDto update(Long id, CommentDto dto) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패! 해당 댓글이 없습니다"));
        // 댓글 수정
        target.patch(dto);
        // DB로 갱선
        Comment updated = commentRepository.save(target);
        // 댓글 엔티티를 DTO로 변환 및 반환
        return CommentDto.createCommentDto(updated);
    }

    @Transactional
    public CommentDto delete(Long id) {
        // 댓글 조회 및 예외 발생
        Comment target = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패! 대상이 없습니다"));
        // 댓글 삭제
        commentRepository.delete(target);
        // 삭제 댓글을 DTO로 변환
        return CommentDto.createCommentDto(target);
    }
}
