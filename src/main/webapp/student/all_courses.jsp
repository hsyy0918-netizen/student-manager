<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>所有课程</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <blockquote class="layui-elem-quote">所有课程</blockquote>
    <table class="layui-table">
        <colgroup>
            <col width="80">
            <col width="150">
            <col width="150">
            <col width="100">
            <col width="150">
            <col width="100">
            <col width="150">
            <col width="100">
        </colgroup>
        <thead>
        <tr>
            <th>课程号</th>
            <th>课程名</th>
            <th>任课教师</th>
            <th>学分</th>
            <th>上课时间</th>
            <th>上课地点</th>
            <th>开课学院</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.allCourses}" var="c">
            <tr>
                <td>${c.courseId}</td>
                <td>${c.courseName}</td>
                <td>${c.teacherName}</td>
                <td>${c.score}</td>
                <td>${c.courseTime}</td>
                <td>${c.classroom}</td>
                <td>${c.collegeName}</td>
                <td>
                    <c:set var="isAlreadyTaken" value="false"/>
                    <c:forEach items="${requestScope.takenCourses}" var="tc">
                        <c:if test="${tc.courseId == c.courseId}">
                            <c:set var="isAlreadyTaken" value="true"/>
                        </c:if>
                    </c:forEach>

                    <c:if test="${isAlreadyTaken}">
                        <button class="layui-btn layui-btn-sm layui-btn-disabled" disabled>已选</button>
                    </c:if>
                    <c:if test="${!isAlreadyTaken}">
                        <button class="layui-btn layui-btn-sm select-course-btn" data-course-id="${c.courseId}">选课</button>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use(['jquery', 'layer'], function(){
        var $ = layui.jquery;
        var layer = layui.layer;

        // 绑定选课按钮的点击事件
        $('.select-course-btn').click(function(){
            var courseId = $(this).data('course-id');
            var btn = $(this); // 缓存按钮对象

            $.ajax({
                url: '${pageContext.request.contextPath}/studentServlet',
                type: 'post',
                data: {
                    action: 'selectCourse',
                    courseId: courseId
                },
                success: function(res){
                    if(res.trim() === 'success'){
                        layer.msg('选课成功！', {icon: 1});
                        // 选课成功后，更新按钮状态
                        btn.removeClass('select-course-btn').addClass('layui-btn-disabled').attr('disabled', 'disabled').text('已选');
                    } else {
                        layer.msg('选课失败！', {icon: 2});
                    }
                }
            });
        });
    });
</script>
</body>
</html>