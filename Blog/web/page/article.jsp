<%@ page language="java" contentType="text/html; ISO-8859-1; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>${article.title} | My Blog</title>
    <%-- Bootstrap --%>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" type="text/css" href="${root}/css/article.css">
    <script type="text/javascript" src="${root}/js/article.js"></script>
</head>
<body>
    <div class="head_line"></div>
    <div class="container" id="main">

        <div class="head">
            <div id="title">
                <h2><a href="${root}/index.jsp">My Blog</a></h2>
            </div>
        </div>

        <div id="article">
            <div id="a_head">
                <h3>${article.title}</h3>
                <br>
                <div>
                    <h5>
                        发表时间：<span>${article.time}</span>
                        | 分类：<a href="${root}/sort?get=${article.sort}">${article.sort}</a>
                        | 作者：${article.author}
                    </h5>
                </div>
                <div class="r_div">
                    <%-- todo: features--%>
                </div>
                <div id="tag">
                    标签：
                    <c:forEach var="t" items="${article_tags}">
                        <a href="${root}/tag?get=${t.tag}">${t.tag}&nbsp;</a>
                    </c:forEach>
                </div>
            </div>
        </div>

        <div class="line"></div>

        <div id="a_content">
            <jsp:include page="${root}/page/show.jsp"/>
        </div>

        <div>
            <div class="f_div">
                <span class="glyphicon glyphicon-chevron-left"></span>
                <c:choose>
                    <c:when test="${article_previous != null}">
                        <a href="${root}/article?id=${article_previous.id}">&nbsp;上一篇：${article_previous.title}</a>
                    </c:when>
                    <c:otherwise>
                        &nbsp;没有更早的文章了
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="r_div">
                <c:choose>
                    <c:when test="${article_next != null}">
                        <a href="${root}/article?id=${article_next.id}">&nbsp;下一篇：${article_next.title}</a>
                    </c:when>
                    <c:otherwise>
                        &nbsp;没有更多的文章了
                    </c:otherwise>
                </c:choose>
                <span class="glyphicon glyphicon-chevron-right"></span>
            </div>
        </div>

        <div class="line"></div>

    </div>
    <%-- footer --%>
    <div id="footer">
        <a href="${root}/index.jsp">My Blog 首页&nbsp;&nbsp;</a>|
        <a href="#">&nbsp;&nbsp;返回顶部</a>
    </div>
</body>
</html>