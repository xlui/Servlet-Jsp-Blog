package style.dx.java.servlet;

import style.dx.java.model.Comment;
import style.dx.java.service.CommentService;
import style.dx.java.util.DateUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet(value = "/comment")
public class CommentServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String info;
		String cookieName = "comment_cookie" + request.getParameter("id");
		boolean isRepeat = false;
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(cookieName)) {
					isRepeat = true;
					break;
				}
			}
		}

		if (isRepeat) {
			Comment comment = new Comment();
			CommentService commentService = CommentService.getInstance();
			comment.setArticleId(Integer.valueOf(request.getParameter("id")));
			comment.setNickName(request.getParameter("w_nickname"));
			comment.setContent(request.getParameter("w_content"));
			comment.setTime(DateUtils.getString(new Date()));

			boolean result = commentService.addComment(comment);
			if (result) {
				info = "comment success!";
			} else {
				info = "comment failed!";
			}
		} else {
			info = "repeat submit comment!";
		}

		Cookie cookie = new Cookie(cookieName, DateUtils.getString(new Date()));
		cookie.setMaxAge(60 * 60);
		cookie.setPath("/");
		response.addCookie(cookie);

		request.setAttribute("info", info);
		request.getRequestDispatcher("/article").forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
