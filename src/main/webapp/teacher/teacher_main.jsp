<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>教务查询系统 - 教师</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">金桥教务查询系统</div>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="../img.png" class="layui-nav-img">
                    ${username}
                </a>
            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/logout">退出</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">我的课程</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/teacherServlet?action=myCourses" target="mainFrame">任课信息</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">系统设置</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/common/password_change.jsp" target="mainFrame">修改密码</a></dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe name="mainFrame" style="width:100%; height:100%; border:none;" src="${pageContext.request.contextPath}/teacherServlet?action=myCourses"></iframe>
    </div>

    <div class="layui-footer">
        © Golden Bridge Educational Administration System
    </div>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('element', function(){
        var element = layui.element;
    });
</script>
</body>
</html>