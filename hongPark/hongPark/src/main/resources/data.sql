INSERT INTO article(title, content)VALUES ('aaaa', '1111' );
INSERT INTO article(title, content) VALUES ('bbbb', '2222' );
INSERT INTO article(title, content) VALUES ('cccc', '3333' );

-- article 더미 데이터
INSERT INTO article(title, content) VALUES ('your favorite movie', '1111' );
INSERT INTO article(title, content) VALUES ('your favorite food', '2222' );
INSERT INTO article(title, content) VALUES ('your hobby', '3333' );

-- comment 더미 데이터
--- 4번 게시글 댓글들
INSERT INTO comment(article_id, nickname, body) values (4, 'Park', 'Good will Hunting' );
INSERT INTO comment(article_id, nickname, body) values (4, 'Kim', 'I am Sam' );
INSERT INTO comment(article_id, nickname, body) values (4, 'Choi', 'Shawshank Escape' );
--- 5번 게시글 댓글들
INSERT INTO comment(article_id, nickname, body) values (5, 'Park', 'chicken' );
INSERT INTO comment(article_id, nickname, body) values (5, 'Kim', 'shabushabu' );
INSERT INTO comment(article_id, nickname, body) values (5, 'Choi', 'sushi' );
--- 5번 게시글 댓글들
INSERT INTO comment(article_id, nickname, body) values (6, 'Park', 'jogging' );
INSERT INTO comment(article_id, nickname, body) values (6, 'Kim', 'youtube' );
INSERT INTO comment(article_id, nickname, body) values (6, 'Choi', 'hiking' );