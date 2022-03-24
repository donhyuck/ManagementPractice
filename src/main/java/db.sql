DROP DATABASE IF EXISTS mp;
CREATE DATABASE mp;
USE mp;

# article 테이블 생성
CREATE TABLE article (
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME NOT NULL,
    title CHAR(255) NOT NULL,
    `body` LONGTEXT NOT NULL
);

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