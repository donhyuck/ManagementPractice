DROP DATABASE IF EXISTS mp;
CREATE DATABASE mp;
USE mp;

DROP TABLE article;

# article 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title CHAR(255) NOT NULL,
    `body` LONGTEXT NOT NULL
);

# 게시글 목록보기
SELECT * FROM article;

INSERT INTO article
SET regDate=NOW(),
title='제목1',
`body`='내용1';

INSERT INTO article
SET regDate=NOW(),
title='제목2',
`body`='내용2';

INSERT INTO article
SET regDate=NOW(),
title='제목3',
`body`='내용3';

INSERT INTO article
SET regDate=NOW(),
title='제목4',
`body`='내용4';

INSERT INTO article
SET regDate=NOW(),
title='제목5',
`body`='내용5';

# 게시글 전체 목록보기(번호정렬)
SELECT *
FROM article
ORDER BY id DESC;

# 게시글 상세보기
SELECT * FROM article
WHERE id =1;

# 페이지
SELECT *
FROM article
ORDER BY id DESC
LIMIT 5, 20;

# 게시글 등록하기
INSERT INTO article
SET regDate=NOW(),
title='제목1',
`body`='내용1';

# 여러개 삭제
DELETE FROM article
WHERE id IN (290, 291);
# 위와 동일
DELETE FROM article
WHERE id = 290 OR id = 291;