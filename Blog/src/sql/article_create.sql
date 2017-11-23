use java_blog;
CREATE TABLE article (
  id INT NOT NULL AUTO_INCREMENT, -- id
  title VARCHAR(80) NOT NULL,     -- 标题
  author VARCHAR(30) NOT NULL,    -- 作者
  sort VARCHAR(30) NOT NULL,      -- 分类
  time DATETIME DEFAULT '2017-11-15 00:00:00',
  content TEXT,
  comment int DEFAULT 0,
  PRIMARY KEY (id)
) ENGINE=INNODB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;