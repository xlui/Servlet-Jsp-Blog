<%--@elvariable id="user" type="style.dx.java.model.User"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div id="title">
    <h2><a href="${param.root}/">My Blog</a></h2>
    <h5 class="text-muted">Hello ${user.username}!</h5>
</div>

<div class="c_center" id="person_info">
    <img src="${param.root}/img/avatar.jpg" alt="头像丢失" height="130" width="130" class="img-circle">
    <h4 class="text-muted">xlui</h4>
    <h5 class="text-muted">Talk is cheap, show me the code.</h5>
</div>

<div class="c_center">
    <br>
</div>
<%--<div class="c_center">--%>
    <%--<div class="inline ">--%>
        <%--<a href="${param.root}/"><span>${article_number}</span><br/>博客</a>--%>
    <%--</div>--%>
    <%--<div class="inline ">--%>
        <%--<a href="${param.root}/sort?get=all"><span>${sort_number}</span><br/>分类</a>--%>
    <%--</div>--%>
    <%--<div class="inline ">--%>
        <%--<a href="${param.root}/tag?get=all"><span>${tag_number}</span><br/>标签</a>--%>
    <%--</div>--%>
<%--</div>--%>

<div id="list">
    <table class="table table-hover c_center">
        <c:choose>
            <c:when test="${param.home}">
                <tr class="active">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>
            <td><a href="${param.root}/index"><span class="glyphicon glyphicon-home"></span>&nbsp;&nbsp;首页</a></td>
        </tr>

        <c:choose>
            <c:when test="${param.sort}">
                <tr class="active">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>
            <td><a href="${param.root}/sort?get=all"><span class="glyphicon glyphicon-list"></span>
                &nbsp;&nbsp;分类
            </a></td>
        </tr>

        <c:choose>
            <c:when test="${param.tag}">
                <tr class="active">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>
            <td><a href="${param.root}/tag?get=all"><span class="glyphicon glyphicon-tags"></span>
                &nbsp;&nbsp;标签
            </a></td>
        </tr>

        <c:choose>
            <c:when test="${param.timeline}">
                <tr class="active">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>
            <td><a href="${param.root}/timeline"><span class="glyphicon glyphicon-book"></span>
                &nbsp;&nbsp;时间轴
            </a></td>
        </tr>

        <c:choose>
            <c:when test="${param.about != null}">
                <tr class="active">
            </c:when>
            <c:otherwise>
                <tr>
            </c:otherwise>
        </c:choose>
            <td><a href="${param.root}/about"><span class="glyphicon glyphicon-user"></span>
                &nbsp;&nbsp;关于
            </a></td>
        </tr>
    </table>
</div>
<br>
