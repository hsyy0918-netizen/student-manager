<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>教务查询系统 - 管理员</title>
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
                    <c:if test="${role == 1}">
                        <a class="" href="javascript:;">信息管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${pageContext.request.contextPath}/student_list.jsp"
                                   target="mainFrame">学生管理</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/teacher_list.jsp"
                                   target="mainFrame">教师管理</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/course_list.jsp"
                                   target="mainFrame">课程管理</a></dd>
                        </dl>
                    </c:if>
                    <c:if test="${role == 3}">
                        <a class="" href="javascript:;">课程管理</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${pageContext.request.contextPath}/student?action=getAllCourseForStu"
                                   target="mainFrame">所有课程</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/student?action=getSelectedCourse"
                                   target="mainFrame">已选课程</a></dd>
                        </dl>
                    </c:if>
                    <c:if test="${role == 2}">
                        <a class="" href="javascript:;">我的课程</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${pageContext.request.contextPath}/teacher?action=getStudentByCourse"
                                   target="mainFrame">任课信息</a>
                            </dd>
                        </dl>
                    </c:if>
                </li>
                <c:if test="${role == 3}">
                    <li class="layui-nav-item">
                        <a class="" href="javascript:;">查看成绩</a>
                        <dl class="layui-nav-child">
                            <dd><a href="${pageContext.request.contextPath}/student?action=getFinishedCourse"
                                   target="mainFrame">已修课程</a></dd>
                        </dl>
                    </li>
                </c:if>

                //通用
                <li class="layui-nav-item">
                    <a href="javascript:;">系统设置</a>
                    <c:if test="${role == 1}">
                        <dl class="layui-nav-child">
                            <dd><a href="${pageContext.request.contextPath}/admin/password_reset.jsp" target="mainFrame">重置密码</a>
                            </dd>
                        </dl>
                    </c:if>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/admin/password_reset.jsp" target="mainFrame">修改密码</a>
                        </dd>
                    </dl>
                </li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe name="mainFrame" style="width:100%; height:100%; border:none;"></iframe>
    </div>

    <div class="layui-footer">
        © Golden Bridge Educational Administration System
    </div>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js" charset="utf-8"></script>
<script>
    layui.use('element', function () {
        var element = layui.element;
    });
</script>
</body>
</html>