<%--
  Created by IntelliJ IDEA.
  User: liuqi
  Date: 17-11-21
  Time: 下午9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>Tag | My Blog</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${root}/css/public.css">
    <link rel="stylesheet" href="${root}/css/tag.css">
</head>
<body>
    <div class="head_line"></div>

    <div class="container" id="main">
        <div id="header"></div>

        <div class="row c_center">
            <div class="col-md-3" id="left_content">
                <jsp:include page="side.jsp">
                    <jsp:param name="root" value="${root}"/>
                    <jsp:param name="tag" value="true"/>
                </jsp:include>
            </div>

            <div class="col-md-2" id="center_content"></div>

            <div class="col-md-7" id="right_content">
                <br><br>
                <div class="list-group">
                    <a href="#" class="list-group-item active">标签</a>
                    <c:forEach var="map" items="${id_tag_map}">
                        <div class="tag_name">
                            <span class="glyphicon glyphicon-triangle-bottom"></span>&nbsp;&nbsp;${map.key}
                        </div>
                        <div>
                            <ul class="list-group">
                                <c:forEach var="list" items="${map.value}">
                                    <li class="list-group-item">
                                        <div>
                                            <div>
                                                <div>
                                                    <a href="${root}/article?id=${list.id}">${list.title}</a>
                                                </div>
                                                <div class="c_right">
                                                    <img src="${root}/img/time.png" alt="Image"/>
                                                    ${list.time}&nbsp;&nbsp;
                                                </div>
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

    <div id="footer">
        <jsp:include page="foot.jsp"/>
    </div>
</body>
</html>