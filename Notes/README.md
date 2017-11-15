# Servlet-Jsp-Blog

Servlet and JavaScript basics. learn from https://course.tianmaying.com/servlet-and-jsp

Because of unable to acess the `js/css/db_related` code, and I have not learnt `js/css` now, so I wrote some notes bellow.

Actually, the course is only simply shows how to use Servlet, JSP, EL, JSTL, Cookie, Session, Filter. Just some basic knowledges.

I have summarize it to a simple note.


# 笔记

## 1. 基于 Intellj IDEA 的项目搭建

1. 创建 Java Enterprise 项目，在 Additional Libraries and Frameworks 选择 `Web Application`，并勾选下边的 `Create web.xml`。输入项目名，选择项目路径。

2. 在 src 目录下右键 `New` -> `Servlet`，name 设为 `HelloWorldServlet`，package 设为 `com.xlui`，确定。

3. HelloWorldServlet:

```java
@WebServlet(value = "/HelloWorld")
public class HelloWorldServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append("Hello World!");
	}
}
```

4. 运行项目，访问 http://localhost:8080/HelloWorld

## 2. Servlet

Servlet 最初是任意客户端-服务端通讯协议的一层抽象，但在 Web 技术蓬勃发展的互联网时代，它几乎完全和 HTTP 通讯协议绑定在一起使用，所以我们常用的术语 Servlet —— 是 `HttpServlet` 的缩写。

1. Servlet 的几个要素：

- 程序实现角度，一个 Servlet 是继承了 javax.servlet.http.HttpServlet 的子类
- `doGet()` 方法中定义了处理 Http GET 请求的动作
- `doPost()` 方法中定义了处理 Http POST 请求的动作
- `HttpServletRequest` 对象中封装了 HTTP 请求信息
- `HttpServletResponse` 可以用来生成 HTTP 响应内容
- `@WebServlet("/HelloWorld")` 注解表明只有请求路径是 `/HelloWorld`（区分大小写） 时，才会进行处理

2. Servlet 容器是 Web 服务器和 Servlet 进行通讯的主要构件，它的主要职责：

- 管理 Servlet 程序的生命周期
- 将 URL 映射到指定的 Servlet 进行处理
- 与 Servlet 程序合作处理 HTTP 请求 —— 根据 HTTP 请求生成 HttpServletRequest 对象并传递给 Servlet 进行处理，将 Servlet 中的 HttpServletResponse 对象生成的内容返回给浏览器
- 并发请求的多线程处理、线程池管理
- Session 管理，HTTP 缓存

3. HttpServletResponse 的四种响应：

响应|代码实现|使用对象
---|---|---
直接返回 HTML 代码|`response.getWriter().append("HelloWorld");`|使用 PrinterWriter 对象
返回二进制数据（文件下载）|`response.getOutputStream().write();`|使用 OutputStream 对象
重定向到另一 URL|`response.sendRedirect("/index.html");`|使用 sendRedirect 方法
返回错误信息|`response.sendError(404, "Resource Not Found");`|使用 sendError 方法

4. Servlet 生命周期

Servlet 依托于 Servlet 容器运行，它包含三个方法，他们分别在特定时机被 Servlet 容器所调用：

- `init()`，当 Servlet 第一次被容器加载进入内存后调用，一般用于载入一些特定的资源和配置
- `service()`，一旦有对应 URL 的 HTTP 请求访问即被调用，它会根据请求中的 method 信息将请求分发至相应的方法进行处理（`doGet()`、`doPost()`），`service()` 方法一般不需要开发者重写
- `destroy()`，Servlet 被销毁时调用，一般用来释放、清理资源

## 3. 处理请求参数

假设请求一个这样的 url：`https://www.tianmaying.com/search?key=jsp`

那么在 Servlet 中就是这样处理请求参数的：

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServetException, IOException {
	String key = request.getParameter("key");
}
```

HttpServletRequest 对象提供的方法列表：

方法名|功能
---|---
`Cookie[] getCookies()`|返回客户端所有的 Cookie 的数组
`Enumeration getAttributeNames()`|返回 request 对象所有属性名称的集合
`Enumeration getHeaderNames()`|返回所有 HTTP 头的名称集合
`Enumeration getPatameterNames()`|返回请求中所有参数的集合
`HttpSession getSession()`|返回 request 对应的 session 对象，如果没有，创建一个
`HttpSession getSession(boolean create)`|返回 request 对应的 session 对象，如果没有并且参数 create 为 true，则返回一个新的 session 对象
`Locale getLocale()`|返回当前页的 Locale 对象，可以在 respose 中设置
`Object getAttribute(String name)`|返回名称为 name 的属性值，如果不存在则返回 null
`ServletInputStream getInputStream()`|返回请求的输入流
`String getAuthType()`|返回认证方案的名称，用来保护 servlet，
`String getCharacterEncoding()`|返回 request 的字符编码集名称
`String getContentType()`|返回 request 主体的 MIME 类型，若未知则返回 null
`String getContentPath()`|返回 request url 中指明的上下文路径
`String getHeader(String name)`|返回 name 指定的信息头
`String getMethod()`|返回此 request 中的 HTTP 方法，如 GET POST
`String getParameter(String name)`|返回 request 中 name 指定的参数，不存在则返回 null
`String getPathInfo()`|返回任何额外的与此 request url 相关的路径
`String getProtocol()`|返回 request 所使用的协议名和版本
`String getQueryString()`|返回 request url 所包含的查询字符串
`String getRemoteAddr()`|返回客户端 ip 地址
`String getRemoteHost()`|返回客户端完整名称
`String getRemoteUser()`|返回客户端通过登录认证的用户，若未认证则返回 null
`String getRequestURI()`|返回 request 的 uri
`String getRequestedSessionId()`|返回 request 指定的 session id
`String getServletPath()`|返回所请求的 servlet 路径
`String[] getParameterValues(String name)`|返回指定名称的参数的所有值，若不存在则返回 null
`boolean isSecure()`|返回 request 是否使用了加密通道，如 HTTPS
`int getContentLength()`|返回 request 主体所包含的字节数，未知返回 -1
`int getIntHeader(String name)`|返回指定名称的 request 信息头的值
`int getServletPort()`|返回服务器端口号

## 4. 常用的表单控件

1. 单选按钮

两个 radio button 中的 name 一样，所以二者只能选择一个

```html
<form action="">
	<div class="radio">
		<label>
			<input type="radio" name="gender" value="male" checked/> 男
		</label>
	</div>
	<div class="radio">
		<label>
			<input type="radio" name="gender" value="female" checked/> 女
		</label>
	</div>
</form>
```

2. 复选框

```html
<div class="checkbox">
	<label>
		<input type="checkbox" name="lang" value="java"/>Java
	</label>
</div>
<div class="checkbox">
	<label>
		<input type="checkbox" name="lang" value="python"/>Python
	</label>
</div>
```

3. 下拉列表

```html
<select name="select" class="form-control">
	<option value="1">1</option>
	<option value="2">2</option>
	<option value="3">3</option>
</select>
```

4. Servlet 中获取表单控件的值

单选按钮本质上和普通输入框一样，HTTP 请求中表单数据编码格式为：

```
gender=male
```

Servlet 中通过 `request.getParameter("gender")` 获得数据。

复选按钮因为可以存在多个同时被选中的值，此时表单中数据编码是：

```
lang=java&lang=python
```

此时需要通过另一个 API 来获取：

```java
String[] values = request.getParameterValues("lang");
```

下拉列表获取方式与单选按钮相同。

5. 富文本编辑器

bootstrap-wysihtml5

6. 自动登录

```html
<input type="checkbox" name="remember-me" value="on"> 记住我
```

Servlet 中获取：

```java
String[] values = request.getParameterValues("remember-me");

if (values != null && !values[0].isEmpty()) {
    // 下次自动登录按钮被选中
}
```

## 5. JSP

Jave EE 中制定 JSP（JavaScript Pages）标准，是基于 HTML/XML 来动态生成 Web 页面内容

1. JSP 基本语法

在 JSP 中嵌入 Java 代码有三种形式：Java 表达式、Java 语句、Java 定义。

**Java 表达式**：使用语法标记对 `<%= %>`，通常用于展示某一个变量、或者一个表达式的值

**Java 语句**：使用语法标记对 `<% %>`，可以轻易实现循环、条件等：

```jsp
<p>Counting to three:</p>
<% for (int i = 1; i < 4; i++){  %>
	<p>This number is <%= i %>.</p>
<% } %>
<p>OK.</p>

<% if (1 < 2) { %>
	<p>Condition is true</p>
<% } %>
```

**Java 定义**：使用语法标记对 `<%! %>`，用于定义临时变量或函数，相当于局部变量/函数。

```jsp
<%!
String makeStringUpper(String data) {
	return data.toUpperCase();
}
%>

<%= makeStringUpper("test") %>
```

2. JSP 中调用 Java 类

```jsp
<%@ page import="com.xlui.Utils" %>
<%@ page import="java.util.* %>
```

这样就引用了 Util 类，并且导入了 java.util 下所有的类。在 JSP 中调用：

```jsp
<%= Util.toUpperCase("test") %>
```

3. JSP 内置对象

- request，封装了 HTTP 请求信息 -- Header，Form 等
- response，封装了 HTTP 响应信息，可以修改响应内容
- out，输出 JSP 页面最终的 HTML 内容
- session，当前请求对应的用户 session 信息
- application，整个 web 应用对象，可以在所有用户之间共享数据
- pageContent，页面上下文，保存当前页面的一些属性
- config，提供一些配置信息，常用的方法有 getInitParameter 和 getInitParameterNames，以获得 Servlet 初始化时的参数
- page，当前 jsp 文件产生的类对象，不建议使用
- exception，JSP 文件运行时产生的例外对象，只能在使用了 `<%@ page isErrorPage="true" %>` 的文件中使用

## 6. 结合使用 Servlet 和 JSP

1. Servlet 处理请求，然后调用相应的 JSP 文件生成 HTML 内容返回给浏览器：

```java
// 处理请求

// 根据需要导向到的 JSP 页面创建 RequestDispatcher
RequestDispatcher dispatcher = request.getRequestDispatcher("view.jsp");

// 将请求转发到 jsp 页面
dispatcher.forward(request, response);
```

2. Servlet 向 JSP 中传递数据

在 Servlet 中，使用 HttpServletRequest 的 setAttribute 方法可以在当前请求中以键值对的形式存入数据：

```java
request.setAttribute(name, value);
```

在 JSP 中可以通过：

```jsp
<%= request.getAttribute(name) %> 得到在 Servlet 中存入的数据。
```

## 7. 页面重定向

```java
@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (login_success) {		// this condition depend on your project
			response.sendRedirect(request.getContextPath() + "/blogs");
		} else {
			request.setAttribute("message", "reason of why login failed");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
}
```

## 8. EL 表达式

EL 表达式用于简化 JSP 中的表达式，让 JSP 代码更加简单优雅。

EL 表达式的语法：

```
${expression}
```

比如 `${message}` 等同于 `<%= message %>`

EL 表达式可以使用 `.` 和 `[]` 这两个操作符访问 java bean 和内置 jsp 对象的各种属性。

如果要存取的属性名称包含特殊字符，如 `.` 或 `-`，就一定要使用 `[]`。例如，`${blog.short-description}` 是非法表达式，而 `${blog[short-description]}` 是合理的。

`[]` 还可以动态获取属性值，即 `${blog[attribute]}` attribute 可以是变量。

1. EL 存取变量顺序

对于 `${blog}` 默认是按照 Page->Request->Session->Application 的顺序查找变量。

其实相当于依次调用：

```
pageContent.getAttribute("blog")	# 页面
request.getAttribute("blog")		# 请求，可以包含几个页面（include 和 forward）
session.getAttribute("blog")		# 会话，一般一个用户产生一个会话
application.getAttribute("blog")	# web 应用
```

如果最终没有找到，返回 `null`。

2. EL 内置（隐式）对象

隐式对象|描述
---|---
pageContext|对应当前页面的 pageContext 对象，可以访问 jsp 隐式对象。如 `{pageContext.response}` 表示页面相应对象
pageScope|将页面范围的变量名对应到其值。例如，`{pageScope.objectName}` 访问一个 JSP 页面范围的对象，`{pageScope.objectName.attributeName}` 访问对象属性
requestScope|与上一个类似
sessionScope|同上
applicationScope|同上
param|`{param.name}` 相当于 `request.getParameter(name)`
paramValues|与上一个类似
header|同上
headerValues|同上
cookie|`${cookie.name.value}` 返回特定名称对应的第一个 cookie 值。
initParam|`{initParam.name}` 相当于调用 `ServletContext.getInitparameter(String name)`

## 9. JSTL

JSTL —— 扩展标签库，不仅提供了条件判断、循环等常用实现程序逻辑的标签，还提供很多扩充标签让开发者能够更加方便的实现业务功能。

JSTL与EL表达式相互配合使用，就能将基本在JSP中消除Java代码了。

下载 jar 并添加到项目

```jsp
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	String[] cities = {"beijing", "shanghai", "guangzhou"};
	pageContent.setAttribute("cities", cities);
%>

<c:forEach var="city" items="${cities}">
	${city}<br>
</c:forEach>
```

1. <c:set>

用于设置变量的值

```jsp
<c:set var="salary" scope="session" value="${2000 * 2}"/>
<C:out value="${salary}"/>
```

其中 scope 的值，就是 EL 介绍的四种范围，相当于调用相应对象的 setAttribute 方法，四种取值分别为：

- page
- request
- session
- application

2. <c:out>

用于显示一个表达式的结果

```jsp
<c:out value="${blog.creator.name}"/>
```

<c:out> 标签相对于直接使用 EL 表达式的好处在于，可以设置默认值，以及是否转换特殊字符（比如 < 转换成 `&lt;`)：

```jsp
<c:out value="${blog.url}" excapeXml="true" default="/blog"/>
```

3. <c:if>

```jsp
<c:set var="salary" value="${2000*2}"/>
<c:if test="${salary > 2000}">
    <p> My salary is: <c:out value="${salary}"/></p>
</c:if>
```

4. <c:choose>

```jsp
<c:set var="salary" scope="session" value="${2000*2}"/>
<p>Your salary is: <c:out value="${salary}"/></p>
<c:choose>
    <c:when test="${salary <= 0>}">
        Salary is very low to survive.
    </c:when>
    <c:when test="${salary > 2000}">
        Salary is very good.
    </c:when>
    <c:otherwise>
        No comment sir...
    </c:otherwise>
</c:choose>
```

5. <c:forEach> 有两种写法：

第一种类似于 `for(int i = 1; i < 5; i++)`：

```jsp
<c:forEach var="i" begin="1" end="5">
	Item <c:out value="${i}"/></p>
</c:forEach>
```

第二种类似于 `for (Blog blog: blogs)`：

```jsp
<c:forEach var="blog" items="${blogs}">
	<c:out value="${blog.title}"/>
</c:forEach>
```

还有一种 `<c:forTokens>` 可以设置分隔符将一个字符串分割成多个字符串：

```jsp
<c:forTokens items="david,ricky,cliff" delims="," var="name">
	<c:out value="${name}"/>
</c:forTokens>
```

## 10. JSP 指令与动作

1. JSP 指令

在 JSP 中，指令（directive）控制 JSP 编译器生成 Servlet 的行为，它能够设置 JSP 页面相关的属性以及控制生成 HTML 的内容。

指令以 `<%@ directive attribute="value" %>` 的形式存在于 JSP 源文件中，其中 directive 可以是三种类型之一：

- 页面指令 `page`：定义页面属性
- 标签库指令 `taglib`：描述了要使用的 JSP 标签库。该指令需要一个 prefix 和标签库的描述 uri
- 包含指令 `include`：告知 JSP 编译器把另一个文件内容完全包含至当前 JSP 文件中

2. JSP 动作

JSP 动作（Action）是在 JSP **执行** 处理阶段完成特定功能的标签。

与 JSP 指令元素不同的是，它是在请求处理阶段执行的，而指令则是在编译阶段被执行。

3. jsp:include 动作

`jsp:include` 是 JSP 内置的一个动作标签，和 JSP 的包含指令类似，它的作用同样是将其他 JSP 文件的内容包含至当前页面：

```jsp
<jsp:include page="xxx.jsp" />
```

和包含指令不同的是，`jsp:include` 在每次请求当前 jsp 文件的时候，才将目标 JSP 文件包含进来（而包含指令则是编译时）。

它还能够支持动态的引入：

```jsp
<jsp:include page="file_name">
    <jsp:param name="paramter_name" value="${paramter_value}" />
</jsp:include>
```

通过 `jsp:param` 标签，可以将某个参数传给目标 JSP 文件。在目标 JSP 中，`param_name` 变量就可以像普通上下文中的变量一样使用：`${param.paramter_name}`。即目标 JSP 文件中的表达式中的变量值，可以通过 `jsp:param` 标签传入。

注意在使用参数的页面中需要增加 `param` 前缀。而且使用这种方式，只能进行字符串传参，如果需要传入对象，则可以使用 `<c:set>` 标签：

```jsp
<c:set var="user" value="${user}" scope="request" />
<jsp:include page="file_name"/>
```

这样在使用参数页面，就能通过类似 `${user.usernaem}` 这样的表达式来引用对象属性了，而且不需要 `param` 前缀。

## 11. Cookie 的使用

Cookie 在 Servlet 规范中对应的类是 javax.servlet.http.Cookie，它的构造函数签名为：`Cookie(String key, String value)`

增加 Cookie 和获取 Cookie 是使用 Cookie 的两个常见的场景：

- 将 Cookie 发送给客户端：`HttpServletResponse.addCookie(Cookie cookie)`
- 获取 Cookie 数组：`HttpServletRequest.getCookies()`

此外，还有几个 Cookie 类常用的方法：

- `getMaxAge/setMaxAge`：获取/设置 Cookie 过期时间，以秒计算。如果不设置，则 Cookie 只在当前会话有效；若为负值，代表浏览器关闭 Cookie 即消失；若为 0，删除 Cookie；若为正数，则为过期秒数
- `getName/setName`：获取/设置 Cookie 名字
- `getValue/setValue`：获取/设置 Cookie 的值。

## 12. 会话 Session

Session 在 Servlet 规范中对应的类是 javax.servlet.http.Session，通过 HttpSessionRequest 对象获取 HttpSession 对象：

```java
HttpSession session = request.getSession();
```

获取 Session 对象之后，就能对 Session 对象进行操作。下面是常用的 HttpSession 接口：

方法|功能
---|---
`setAttribute(String name, Object value)`|向 Session 中增加键值对
`getAttribute(String name)`|获取 name 的属性值
`removeAttribute(String name)`|删除当前 Session 中属性对应的值
`getAttributeNames()`|返回当前 Session 中所有属性的名字
`invalidate()`|使 Session 失效
`getMaxInactiveInterval()`|返回 Session 失效时间，单位是秒
`setMaxInactiveInterval(int seconds)`|设置 Session 失效时间

## 13. Filter 拦截

Filter 在 Servlet/JSP 之前对 HTTP 请求进行拦截，可以同时存在多个 Filter 组成 Filter 链（Chain），在任意 Filter 中可以决定继续执行 Filter 链中下一个 Filter，还是把 response 直接返回到浏览器。Filter 链全部执行完成后，HTTP 请求才会到达相应的 Servlet/JSP 中。

以下是 Servlet 中对于 Filter 接口的定义

```java
public interface Filter {
	public void init(FilterConfig filterConfig) throws ServletException;
	public doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;
	public void destroy();
}
```

我们需要实现 Filter 接口来定制自己的 Filter：

```java
@WebFilter(filterName = "LoginFilter", urlPatterns = "/create")
public class LoginFilter implements Filter {
	public void destroy() {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		User user = (User) request.getSession().getAttribute("currentUser");    // 在 LoginServlet 中登录成功后设置 Attribute
		if (user == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "no user in session");
			return;
		}
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig config) throws ServletException {
	}
}
```

代码解释：

- `@WebFilter(filterName = "LoginFilter", urlPatterns = "/create")` 表示 LoginFilter 应该作为一个 Filter 加载，它拦截 URL 路径为 `/create` 请求（包括 GET 和 POST）。如果需要拦截多个 URL，则应该这样写：`urlPatterns = {"/create", "/about"}`

## 对于登录的优化

如果用户尚未登录却访问了一个需要登录的页面，就先跳转到登录页面，用户登录成功后再跳转到他之前要访问的目标页面。

在 Filter 中只需要通过 request.getRequestURI() 获取当前受保护页面的 URI

```java
public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
	HttpServletRequest request = (HttpServletRequest)req;
	User user = (User) request.getSession().getAttribute("currentUser");
	if (user == null) {
		HttpServletResponse response = (HttpServletResponse)resp;
		response.sendRefirect(request.getContextPath() + "/login?next=" + request.getRequestURI());
		return;
	}
	chain.doFilter(req, resp);
```

然后在登录 Servlet 中处理 `next` 参数并进行跳转。
