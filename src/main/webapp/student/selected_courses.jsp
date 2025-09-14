<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>已选课程</title>
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
            <th>操作</th>
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
                    <a href="javascript:;" onclick="dropCourse(${c.courseId})" class="layui-btn layui-btn-danger layui-btn-sm">退课</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use(['layer', 'jquery'], function(){
        var layer = layui.layer;
        var $ = layui.jquery;

        window.dropCourse = function(courseId){
            layer.confirm('确定退选该课程吗？已打分的课程无法退选。', {icon: 3, title:'提示'}, function(index){
                $.ajax({
                    url: '${pageContext.request.contextPath}/studentServlet',
                    type: 'post',
                    data: {
                        action: 'dropCourse',
                        courseId: courseId
                    },
                    success: function(res){
                        if(res.trim() === 'success'){
                            layer.msg('退课成功！', {icon: 1});
                            setTimeout(function(){
                                location.reload();
                            }, 1000);
                        } else {
                            layer.msg('退课失败，可能已打分！', {icon: 2});
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