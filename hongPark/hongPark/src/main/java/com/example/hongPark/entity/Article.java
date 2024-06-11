package com.example.hongPark.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity // DB가 해당 객체를 인식 가능
@AllArgsConstructor
@NoArgsConstructor // 디폴트 생성자
@ToString
@Getter
public class Article {
    
    @Id // 대표값
    // DB가 1, 2, 3...를 자동 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;
    @Column
    private String content;

    public void patch(Article article) {
        if(article.title != null)
            this.title = article.title;
        if(article.content != null)
            this.content = article.content;
    }
}
