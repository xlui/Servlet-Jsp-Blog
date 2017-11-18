<%--
  Created by IntelliJ IDEA.
  User: liuqi
  Date: 17-11-18
  Time: 上午11:36
  Markdown to HTML
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Md2HTML</title>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <c:set var="root" value="${pageContext.request.contextPath}"/>
</head>
<link rel="stylesheet" href="${root}/editormd/css/style.css">
<link rel="stylesheet" href="${root}/editormd/css/editormd.preview.css">
<script src="${root}/editormd/js/zepto.min.js"></script>
<script src="${root}/editormd/js/editormd.js"></script>
<script src="${root}/editormd/js/jquery.min.js"></script>
<script src="${root}/editormd/lib/marked.min.js"></script>
<script src="${root}/editormd/lib/prettify.min.js"></script>
<script src="${root}/editormd/lib/raphael.min.js"></script>
<script src="${root}/editormd/lib/underscore.min.js"></script>
<script src="${root}/editormd/lib/sequence-diagram.min.js"></script>
<script src="${root}/editormd/lib/flowchart.min.js"></script>
<script src="${root}/editormd/lib/jquery.flowchart.min.js"></script>
<script src="${root}/editormd/js/editormd.js"></script>
<body style="background: #eee;">
<div id="mdView" style="background: #eee;">
    <textarea id="article_content">${article.content}</textarea>
</div>
<br>
<script type="text/javascript">
    $(function () {
        editormd.markdownToHTML("mdView", {
            htmlDecode: "style,script,iframe",
            emoji: true,
            taskList: true,
            tex: true,
            flowChart: true,
            sequenceDiagram: true
        });
    });
</script>
</body>
</html>
