<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>关于 | My Blog</title>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${root}/css/about.css">
    <link rel="stylesheet" href="${root}/css/public.css">
</head>
<body>
    <div class="head_line"></div>

    <%-- container --%>
    <div class="container" id="main">
        <div id="header"></div>
        <div class="row c_center">
            <div class="col-md-3" id="left_content">
                <c:set var="about" value="true"/>
                <jsp:include page="side.jsp">
                    <jsp:param name="about" value="${about}"/>
                    <jsp:param name="root" value="${root}"/>
                </jsp:include>
            </div>

            <div style="margin-top: 120px;">
                <h2>About me</h2>
                My Blog by <a href="https://github.com/xlui">xlui</a> 2017 11
                <br>
                <div style="text-align: center">
                    <h4>
                        Email: i@xlui.me <br><br>
                        GitHub: <a href="https://github.com/xlui">https://github.com/xlui</a> <br><br>
                        Blog: <a href="https://xlui.me">https://xlui.me</a> <br><br>
                    </h4>
                </div>
            </div>
        </div>
    </div>
    <%-- container --%>

    <div class="foot_line"></div>

    <div id="footer">
        <jsp:include page="foot.jsp"/>
    </div>
</body>
</html>