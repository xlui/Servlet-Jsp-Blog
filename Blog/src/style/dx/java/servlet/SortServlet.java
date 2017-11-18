package style.dx.java.servlet;

import style.dx.java.service.ArticleService;
import style.dx.java.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(value = "/sort")
public class SortServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String get = StringUtils.decode(request.getParameter("get"));
		ArticleService articleService = ArticleService.getInstance();
		request.setAttribute("sort_article_map", articleService.getSort(get));
		request.getRequestDispatcher("/page/sort.jsp").forward(request, response);
	}
}
