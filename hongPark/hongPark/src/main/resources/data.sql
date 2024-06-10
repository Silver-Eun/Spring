INSERT INTO article(id, title, content) VALUES ( 1, "aaaa", "1111" );
INSERT INTO article(id, title, content) VALUES ( 2, "bbbb", "2222" );
INSERT INTO article(id, title, content) VALUES ( 3, "cccc", "3333" );

-- article 더미 데이터
INSERT INTO article(id, title, content) VALUES ( 4, "your favorite movie", "1111" );
INSERT INTO article(id, title, content) VALUES ( 2, "your favorite food", "2222" );
INSERT INTO article(id, title, content) VALUES ( 3, "your hobby", "3333" );

-- comment 더미 데이터
--- 4번 게시글 댓글들
INSERT INTO comment(id, article_id, nickname, body) values ( 1, 4, "Park", "Good will Hunting" );
INSERT INTO comment(id, article_id, nickname, body) values ( 2, 4, "Kim", "I am Sam" );
INSERT INTO comment(id, article_id, nickname, body) values ( 3, 4, "Choi", "Shawshank Escape" );
--- 5번 게시글 댓글들
INSERT INTO comment(id, article_id, nickname, body) values ( 4, 5, "Park", "chicken" );
INSERT INTO comment(id, article_id, nickname, body) values ( 5, 5, "Kim", "shabushabu" );
INSERT INTO comment(id, article_id, nickname, body) values ( 6, 5, "Choi", "sushi" );
--- 5번 게시글 댓글들
INSERT INTO comment(id, article_id, nickname, body) values ( 7, 6, "Park", "jogging" );
INSERT INTO comment(id, article_id, nickname, body) values ( 8, 6, "Kim", "youtube" );
INSERT INTO comment(id, article_id, nickname, body) values ( 9, 6, "Choi", "hiking" );