<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加课程信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <form class="layui-form" action="${pageContext.request.contextPath}/adminServlet?action=addCourse" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">课程名称</label>
            <div class="layui-input-block">
                <input type="text" name="courseName" required lay-verify="required" placeholder="请输入课程名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">任课教师</label>
            <div class="layui-input-block">
                <select name="teacherId" lay-verify="required">
                    <option value=""></option>
                    <c:forEach items="${requestScope.teachers}" var="t">
                        <option value="${t.teacherId}">${t.teacherName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">上课时间</label>
            <div class="layui-input-block">
                <input type="text" name="courseTime" required lay-verify="required" placeholder="请输入上课时间（如：周一1-2节）" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">上课教室</label>
            <div class="layui-input-block">
                <input type="text" name="classroom" required lay-verify="required" placeholder="请输入上课教室" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">周学时</label>
            <div class="layui-input-block">
                <input type="number" name="classWeek" required lay-verify="required|number" placeholder="请输入周学时" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">课程性质</label>
            <div class="layui-input-block">
                <input type="text" name="courseType" required lay-verify="required" placeholder="请输入课程性质（如：必修、选修）" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开设学院</label>
            <div class="layui-input-block">
                <select name="collegeId" lay-verify="required">
                    <option value=""></option>
                    <c:forEach items="${requestScope.colleges}" var="c">
                        <option value="${c.collegeId}">${c.collegeName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学分</label>
            <div class="layui-input-block">
                <input type="number" name="score" required lay-verify="required|number" placeholder="请输入学分" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="addCourse">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use(['form'], function(){
        var form = layui.form;
    });
</script>
</body>
</html>