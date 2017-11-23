<%--
  Created by IntelliJ IDEA.
  User: liuqi
  Date: 17-11-22
  Time: 下午2:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Login | My Blog</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${root}/css/login.css">
    <link rel="stylesheet" href="${root}/css/public.css">
</head>
<body>

<div class="container" id="main">
    <form class="form-signin" id="form" action="<c:url value="/login"/>" method="post">
        <a href="<c:url value="/index.jsp"/>"><h2 class="form-signin-heading">My Blog</h2></a>

        <c:if test="${login != null}">
            <br>
            <span class="label label-danger">${login}</span>
            <br><br>
        </c:if>

        <label for="input" class="sr-only">用户名：</label>
        <input type="text" id="input" class="form-control" placeholder="用户名" name="username" required/>
        <label for="inputPassword" class="sr-only">密码：</label>
        <input type="password" name="password" id="inputPassword" class="form-control" placeholder="密码" required/>
        <button class="btn btn-lg btn-primary btn-block" type="submit" id="submit">登录</button>

        <a href="${root}/index.jsp">默认用户名：1 默认密码：dev</a>
    </form>
</div>

<div id="footer">
    <jsp:include page="foot.jsp"/>
</div>
</body>
</html>
