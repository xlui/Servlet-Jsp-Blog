use java_blog;
CREATE TABLE comment (
  id int NOT NULL AUTO_INCREMENT,
  article_id int DEFAULT NULL ,
  nickname VARCHAR(30) DEFAULT NULL ,
  content TEXT,
  time VARCHAR(20) DEFAULT null,
  PRIMARY KEY (id),
  key article_id (article_id),
  CONSTRAINT comment_article_id FOREIGN KEY (article_id) REFERENCES article (id) on DELETE CASCADE on UPDATE NO ACTION
)ENGINE=INNODB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

