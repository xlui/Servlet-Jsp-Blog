package style.dx.java.servlet;

import style.dx.java.model.Article;
import style.dx.java.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/article")
public class ArticleServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ArticleService articleService = ArticleService.getInstance();
		Article article = articleService.getArticle("id", id).get(0);
		request.setAttribute("article", article);


		request.setAttribute("article_tags", null);
		// todo: ArticleService.getTagById
		request.setAttribute("article_previous", articleService.getPreviousArticle(article.getTime()));
		request.setAttribute("article_previous", articleService.getNextArticle(article.getTime()));

		request.getRequestDispatcher("/page/article.jsp").forward(request, response);
	}
}
