package style.dx.java.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CharsetHeaderFilter", urlPatterns = "/*")
public class CharsetHeaderFilter implements Filter {
	public void destroy() {
	}

	/**
	 * CharsetHeaderFilter：设置 request 与 response 的一些参数
	 * @param req ServletRequest
	 * @param resp ServletResponse
	 * @param chain filter chain
	 * @throws ServletException ServletException
	 * @throws IOException IOException
	 */
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("expires", -1);
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {

	}

}
