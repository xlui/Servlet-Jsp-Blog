<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
            <td><a href="${root}/page/about.jsp"><span class="glyphicon glyphicon-user"></span>
                &nbsp;&nbsp;关于
            </a></td>
        </tr>
    </table>
</div>
<br>
