<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>已修课程</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <table class="layui-table">
        <colgroup>
            <col width="80">
            <col width="150">
            <col width="100">
            <col width="80">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>任课教师</th>
            <th>学分</th>
            <th>成绩</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.courses}" var="c">
            <tr>
                <td>${c.courseId}</td>
                <td>${c.courseName}</td>
                <td>${c.teacherName}</td>
                <td>${c.score}</td>
                <td>
                    <c:if test="${c.mark != null}">
                        ${c.mark}
                    </c:if>
                    <c:if test="${c.mark == null}">
                        待出分
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>