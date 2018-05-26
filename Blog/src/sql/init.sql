use java_blog;

# drop all exist table

DROP TABLE IF EXISTS `tag`;
DROP TABLE IF EXISTS `comment`;
DROP TABLE IF EXISTS `user`;
DROP TABLE IF EXISTS `article`;

# create table

CREATE TABLE `article` (
  id      INT         NOT NULL AUTO_INCREMENT, -- id
  title   VARCHAR(80) NOT NULL, -- 标题
  author  VARCHAR(30) NOT NULL, -- 作者
  sort    VARCHAR(30) NOT NULL, -- 分类
  time    DATETIME             DEFAULT now(), -- 文章发布时间
  content TEXT,
  PRIMARY KEY (id)
)
  ENGINE = INNODB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8;

CREATE TABLE `tag` (
  id         INT         AUTO_INCREMENT PRIMARY KEY,
  content    VARCHAR(30) DEFAULT NULL,
  article_id INT         DEFAULT NULL,
  CONSTRAINT fk_tag_article_id FOREIGN KEY (article_id) REFERENCES article (id)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
  ENGINE = INNODB
  AUTO_INCREMENT = 13
  DEFAULT CHARSET = utf8;

CREATE TABLE `user` (
  id       INT         AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  nickname VARCHAR(32) DEFAULT '热心网友'
)
  ENGINE = INNODB
  AUTO_INCREMENT = 37
  DEFAULT CHARSET = utf8;

CREATE TABLE `comment` (
  id         INT      AUTO_INCREMENT PRIMARY KEY,
  content    TEXT,
  time       DATETIME DEFAULT now(),
  user_id    INT NOT NULL,
  article_id INT NOT NULL,
  CONSTRAINT fk_comment_user_id FOREIGN KEY (user_id) REFERENCES user (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_comment_article_id FOREIGN KEY (article_id) REFERENCES article (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
  ENGINE = INNODB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8;

# init table

INSERT INTO article (id, title, author, sort, content)
VALUES
  (10, 'Hello World', 'admin', 'default', '我的博客第一天！'),
  (11, 'Markdown 测试', 'admin', '未分类', '
链接测试：[baidu](https://www.baidu.com)
图片测试：![image](https://java.dx.style/img/avatar.jpg)
代码块测试：
```python
print(\'Hello World1\')
```
');

INSERT INTO `tag` (content, article_id)
VALUES
  ('Hello', 10),
  ('test', 11);

INSERT INTO `user` (id, username, password, nickname)
VALUES
  (1, 1, 'dev', 'I\'m default user!'),
  (2, 2, 'std', 'a new register user...');

INSERT INTO comment (content, user_id, article_id)
VALUES
  ('First Comment: Hello', 1, 10),
  ('Second Comment: Hello Again!', 2, 10);
