USE java_blog;
CREATE TABLE tag (
  id INT DEFAULT NULL,
  tag VARCHAR(30) DEFAULT NULL,
  KEY id (id),
  CONSTRAINT tag_article_id FOREIGN KEY (id) REFERENCES article(id) ON DELETE CASCADE ON UPDATE NO ACTION
)ENGINE=INNODB DEFAULT CHARSET=utf8;