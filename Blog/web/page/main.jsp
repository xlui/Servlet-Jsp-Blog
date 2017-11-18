<%--
  Created by IntelliJ IDEA.
  User: liuqi
  Date: 2017/11/15
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>My Blog</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${root}/css/main.css">
  </head>
  <body>
    <div class="head_line"></div>

    <%-- container --%>
    <div class="container" id="main">

      <div id="header"></div>

      <div class="row c_center">

        <%-- 左侧栏 --%>
        <div class="col-md-3" id="left_content">
          <jsp:include page="side.jsp"/>
        </div>

        <%-- 中间栏 --%>
        <div>
          <div class="col-md-2" id="center_content"></div>
        </div>

        <%-- 右侧栏 --%>
        <div class="col-md-7" id="right_content">
          <br><br>
          <div class="list_group">
            <a href="#" class="list-group-item active">文章</a>
            <%-- 初始化文章列表 --%>
            <%--@elvariable id="article_list" type="java.util.List"--%>
            <c:forEach var="article" items="${article_list}">
              <div class="list-group-item">
                <h4><a href="${root}/article?id=${article.id}">${article.title}</a></h4>
                <br>
                <span>${article.time}&nbsp;&nbsp;|</span>
                <a href="${root}/sort?get=${article.sort}">${article.sort}</a>&nbsp;&nbsp;
                <br><br>
                <span>${article.content}</span>
                <br><br><br>
              </div>
            </c:forEach>
          </div>
        </div>
      </div>

      <div class="foot_line"></div>
    </div>
    <%-- container --%>

    <div id="footer">
      <jsp:include page="foot.jsp"/>
    </div>
  </body>
</html>
