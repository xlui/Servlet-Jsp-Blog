package style.dx.java.servlet;

import style.dx.java.service.ArticleService;
import style.dx.java.util.SideUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/index")
public class MainServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleService articleService = ArticleService.getInstance();
		request.setAttribute("article_list", articleService.getArticle());
		SideUtils.setAttribute(request);

		request.getRequestDispatcher("/page/main.jsp").include(request, response);
	}
}
