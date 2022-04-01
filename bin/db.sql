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

# 게시글 수정하기
UPDATE article
SET regDate=NOW(),
title='제목aa',
`body`='내용bb'
WHERE id = 285;

#글 갯수 2배
INSERT INTO article (regDate,title,`body`)
SELECT NOW(), CONCAT('제목_',RAND()), CONCAT('내용_',RAND())
FROM article;

# 회원테이블 삭제
DROP TABLE `member`;

# 회원 테이블 생성
CREATE TABLE `member` (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    loginId CHAR(100) NOT NULL UNIQUE,
    loginpw CHAR(100) NOT NULL,
    `name` CHAR(100) NOT NULL
);
# 회원등록
INSERT INTO `member`
SET regDate=NOW(),
loginId='test2',
loginPw='test2',
`name`='테스터2';

SELECT * FROM `member`;