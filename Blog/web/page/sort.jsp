<%--
  Created by IntelliJ IDEA.
  User: liuqi
  Date: 17-11-18
  Time: 下午1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>分类 | My Blog</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${root}/css/public.css">
    <link rel="stylesheet" href="${root}/css/sort.css">
</head>
<body>
    <div class="head_line"></div>

    <%-- container --%>
    <div class="container" id="main">
        <div id="header"></div>

        <div class="row c_center">
            <div class="col-md-3" id="left_content">
                <jsp:include page="side.jsp">
                    <jsp:param name="sort" value="true"/>
                    <jsp:param name="root" value="${root}"/>
                </jsp:include>
            </div>

            <div class="col-md-2" id="center_content"></div>

            <div class="col-md-7" id="right_content">
                <br><br>
                <div class="list-group">
                    <a href="#" class="list-group-item active">分类</a>
                    <c:forEach var="map" items="${sort_article_map}">
                        <div class="sort_name">
                            <span class="glyphicon glyphicon-triangle-bottom"></span>
                            &nbsp;&nbsp;${map.key}
                        </div>
                        <div>
                            <ul class="list-group">
                                <c:forEach var="list" items="${map.value}">
                                    <li class="list-group-item">
                                        <div>
                                            <div>
                                                <a href="${root}/article?id=${list.id}">${list.title}</a>
                                            </div>
                                            <div class="c_right">
                                                <img src="${root}/img/time.png" alt="">
                                                ${list.time}&nbsp;&nbsp;
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
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
