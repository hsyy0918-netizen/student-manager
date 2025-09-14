<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>课程管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <%-- 顶部工具栏 --%>
    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <input type="text" name="name" placeholder="请输入课程名称" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn" lay-submit lay-filter="formSearch">搜索</button>
            <a href="${pageContext.request.contextPath}/adminServlet?action=toAddCourse" class="layui-btn layui-btn-normal">添加课程</a>
        </div>
    </div>

    <%-- 数据表格 --%>
    <table class="layui-table">
        <colgroup>
            <col width="80">
            <col width="150">
            <col width="100">
            <col width="150">
            <col width="100">
            <col width="80">
            <col width="100">
            <col>
            <col width="80">
            <col width="180">
        </colgroup>
        <thead>
        <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>任课教师</th>
            <th>上课时间</th>
            <th>上课教室</th>
            <th>周学时</th>
            <th>课程性质</th>
            <th>开设学院</th>
            <th>学分</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.courses}" var="c">
            <tr>
                <td>${c.courseId}</td>
                <td>${c.courseName}</td>
                <td>${c.teacherName}</td>
                <td>${c.courseTime}</td>
                <td>${c.classroom}</td>
                <td>${c.classWeek}</td>
                <td>${c.courseType}</td>
                <td>${c.collegeName}</td>
                <td>${c.score}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/adminServlet?action=toEditCourse&courseId=${c.courseId}" class="layui-btn layui-btn-sm">修改</a>
                    <a href="javascript:;" onclick="deleteCourse(${c.courseId})" class="layui-btn layui-btn-danger layui-btn-sm">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <c:if test="${not empty errorMsg}">
        <div style="color: red; text-align: center;">${errorMsg}</div>
    </c:if>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use(['layer', 'jquery', 'form'], function(){
        var layer = layui.layer;
        var $ = layui.jquery;
        var form = layui.form;

        // 搜索功能的监听
        form.on('submit(formSearch)', function(data){
            var name = data.field.name;
            location.href = '${pageContext.request.contextPath}/adminServlet?action=listCourses&name=' + name;
            return false;
        });

        // 删除课程的JS方法
        window.deleteCourse = function(courseId){
            layer.confirm('确定删除该课程吗？', {icon: 3, title:'提示'}, function(index){
                $.ajax({
                    url: '${pageContext.request.contextPath}/adminServlet',
                    type: 'post',
                    data: {
                        action: 'deleteCourse',
                        courseId: courseId
                    },
                    success: function(res){
                        if(res.trim() === 'success'){
                            layer.msg('删除成功！', {icon: 1});
                            setTimeout(function(){
                                location.reload();
                            }, 1000);
                        } else {
                            layer.msg('删除失败，可能已有学生选择该课程！', {icon: 2});
                        }
                    }
                });
                layer.close(index);
            });
        };
    });
</script>
</body>
</html>