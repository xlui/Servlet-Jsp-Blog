use java_blog;

# create

DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  id INT NOT NULL AUTO_INCREMENT, -- id
  title VARCHAR(80) NOT NULL,     -- 标题
  author VARCHAR(30) NOT NULL,    -- 作者
  sort VARCHAR(30) NOT NULL,      -- 分类
  time DATETIME DEFAULT now(),
  content TEXT,
  comment int DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tag`;
CREATE TABLE tag (
  id INT DEFAULT NULL,
  tag VARCHAR(30) DEFAULT NULL,
  KEY id (id),
  CONSTRAINT tag_article_id FOREIGN KEY (id) REFERENCES article(id) ON DELETE CASCADE ON UPDATE NO ACTION
)ENGINE=INNODB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `user`;
CREATE TABLE user (
  id int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL
)ENGINE=INNODB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `comment`;
CREATE TABLE comment (
  id int NOT NULL AUTO_INCREMENT,
  article_id int DEFAULT NULL ,
  nickname VARCHAR(30) DEFAULT NULL ,
  content TEXT,
  time VARCHAR(20) DEFAULT NULL,
  PRIMARY KEY (id),
  key article_id (article_id),
  CONSTRAINT comment_article_id FOREIGN KEY (article_id) REFERENCES article (id) on DELETE CASCADE on UPDATE NO ACTION
)ENGINE=INNODB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;


# init

insert into article VALUES (NULL, "Hello World", "admin", "default", now(), "我的博客第一天！", 2);
insert into article VALUES (null, "Markdown 测试", "admin", "未分类", now(), "
链接测试：[baidu](https://www.baidu.com)
图片测试：![image](https://java.dx.style/img/avatar.jpg)
代码块测试：
```python
print('Hello World1')
```
", null);

insert into tag VALUES
  (10, "Hello"),
  (11, "test");

INSERT INTO user VALUES (null, 1, "dev");

insert into comment VALUES (null, 10, "jack", "First COmment: Hello", now());
insert into comment VALUES (null, 10, "lucy", "Second COmment: Hello Again!", now());
