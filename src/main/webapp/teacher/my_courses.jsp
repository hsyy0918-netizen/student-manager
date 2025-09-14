<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>我的课程</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <table class="layui-table">
        <colgroup>
            <col width="80">
            <col width="150">
            <col width="150">
            <col width="100">
            <col width="80">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>上课时间</th>
            <th>上课教室</th>
            <th>周学时</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.courses}" var="c">
            <tr>
                <td>${c.courseId}</td>
                <td>${c.courseName}</td>
                <td>${c.courseTime}</td>
                <td>${c.classroom}</td>
                <td>${c.classWeek}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/teacherServlet?action=studentsByCourse&courseId=${c.courseId}&courseName=${c.courseName}" class="layui-btn layui-btn-sm">管理学生/打分</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
</body>
</html>