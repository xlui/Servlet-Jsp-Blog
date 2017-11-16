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

    <div class="container" id="main">

      <div id="header"></div>

      <div class="row c_center">

        <div class="col-md-3" id="left_content">

          <div id="title">
            <h2><a href="${root}/">My Blog</a></h2>
            <h5 class="text-muted">Hello Stranger!</h5>
          </div>

          <div class="c_center" id="person_info">
            <img src="${root}/img/avatar.jpg" alt="头像丢失" height="130" width="130" class="img-circle">
            <h4 class="text-muted">xlui</h4>
            <h5 class="text-muted">Talk is cheap, show me the code.</h5>
          </div>

          <div class="c_center">
            <div class="inline">
              <a href="#">${article_number}<br/>博客</a>
            </div>
            <div class="inline">
              <a href="${root}/sort?get=all"><span>${sort_number}</span><br/>分类</a>
            </div>
            <div class="inline">
              <a href="${root}/tags?get=all"><span>${tags_number}</span><br>标签</a>
            </div>
          </div>

          <div id="list">
            <table class="table table-hover c_center">
              <tr class="active">
                <td><a href="${root}/index.jsp"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;首页</a></td>
              </tr>
              <tr>
                <td><a href="${root}/sort?get=all"><span class="glyphicon glyphicon-list"></span>
                  &nbsp;&nbsp;分类
                </a></td>
              </tr>
              <tr>
                <td><a href="${root}/tags?get=all"><span class="glyphicon glyphicon-tags"></span>
                  &nbsp;&nbsp;标签
                </a></td>
              </tr>
              <tr>
                <td><a href="${root}/timeline"><span class="glyphicon glyphicon-book"></span>
                  &nbsp;&nbsp;时间轴
                </a></td>
              </tr>
              <tr>
                <td><a href="${root}/about.html"><span class="glyphicon glyphicon-user"></span>
                  &nbsp;&nbsp;关于
                </a></td>
              </tr>
            </table>
          </div>
        <br>
        </div>

        <div>
          <div class="col-md-2" id="center_content"></div>
        </div>

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
    <div id="footer">
      <div>
        <a href="https://github.com/xlui">&nbsp;&nbsp;GitHub</a>
        &nbsp;|
        <a href="#">&nbsp;&nbsp;My Blog</a>
        <br>
        copyright 2017
      </div>
      <div class="r_div">
        <a href="#"><button class="btn btn-default">返回顶部</button></a>
      </div>
    </div>
  </body>
</html>
