package style.dx.java.servlet;

import style.dx.java.service.TagService;
import style.dx.java.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tag")
public class TagServlet extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String param = StringUtils.decode(request.getParameter("get"));
		TagService tagService = TagService.getInstance();
		System.out.println(tagService.getAllTags());
		request.setAttribute("id_tag_map", tagService.getTagMappedArticles(param));

		request.getRequestDispatcher("/page/tag.jsp").forward(request, response);
	}
}
