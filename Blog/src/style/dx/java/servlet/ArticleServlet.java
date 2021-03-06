package style.dx.java.servlet;

import style.dx.java.model.Article;
import style.dx.java.service.ArticleService;
import style.dx.java.service.CommentService;
import style.dx.java.service.TagService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/article")
public class ArticleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArticleService articleService = ArticleService.getInstance();
        TagService tagService = TagService.getInstance();
        CommentService commentService = CommentService.getInstance();
        Integer id = Integer.parseInt(request.getParameter("id"));
        Article article = articleService.getArticle(id);
        request.setAttribute("article", article);


        request.setAttribute("article_tags", tagService.getTagById(String.valueOf(article.getId())));
        request.setAttribute("article_previous", articleService.getPreviousArticle(article.getId()));
        request.setAttribute("article_next", articleService.getNextArticle(article.getId()));
        request.setAttribute("comment", commentService.loadComment(article.getId()));

        request.getRequestDispatcher("/page/article.jsp").forward(request, response);
    }
}
