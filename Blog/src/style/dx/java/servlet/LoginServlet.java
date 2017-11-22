package style.dx.java.servlet;

import style.dx.java.model.User;
import style.dx.java.service.ArticleService;
import style.dx.java.service.UserService;
import style.dx.java.utils.SideUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserService userService = UserService.getInstance();
		ArticleService articleService = ArticleService.getInstance();

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.login(username, password);
		if (user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			request.setAttribute("article_list", articleService.getArticle());
			SideUtils.setAttribute(request);
			request.getRequestDispatcher("/page/main.jsp").forward(request, response);
		} else {
			log("[LOG] login failed!");
			request.setAttribute("login", "Invalid username or password!");
			request.getRequestDispatcher("/page/login.jsp").forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/page/login.jsp").include(request, response);
	}
}
