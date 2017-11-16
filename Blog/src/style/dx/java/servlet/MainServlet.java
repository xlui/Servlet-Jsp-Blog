package style.dx.java.servlet;

import style.dx.java.service.ArticleService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/index")
public class MainServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleService articleService = ArticleService.getInstance();
		request.setAttribute("article_list", articleService.getArticle());
		log("Articles:\n");
		log(String.valueOf(articleService.getArticle()));
		request.getRequestDispatcher("/main.jsp").include(request, response);
	}
}
