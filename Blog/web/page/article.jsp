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
    <link rel="stylesheet" href="${root}/css/comment.css">
    <link rel="stylesheet" href="${root}/css/public.css">
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
                        | <span class="glyphicon glyphicon-comment">&nbsp;${article.comment}&nbsp;</span>
                    </h5>
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
        
        <div class="comment">
            <div class="r_div">
                <a href="#comment"><span class="glyphicon glyphicon-pencil">&nbsp;写评论...</span></a>
            </div>
            <c:if test="${comment != null}">
                <c:forEach var="comm" varStatus="status" items="${comment}">
                    <div class="row">
                        <div class="f_div">
                            <img src="${root}/img/comment.jpg" alt="评论图片加载失败" height="50" width="50" class="img-circle"/>
                            &nbsp;&nbsp;
                            <span style="color: #428bca;">${comm.nickName}</span>
                            <span>&nbsp;&nbsp;${comm.time}</span>
                        </div>
                        <div id="c_content" class="c_center">
                            <pre>${comm.content}</pre>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
        </div>

        <div id="comment">
            <form action="${root}/comment?id=${article.id}" method="post">
                <input type="text" style="width: 30%;" class="form-control" name="w_nickname" value="热心网友"/>
                <br>
                <textarea style="resize: none; width: 100%; height: 180px;" name="w_content"></textarea>
                <br><br>
                <input class="btn btn-default" type="submit" value="评论" onclick="onclick"/>
                <br>
            </form>
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