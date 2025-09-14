<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${requestScope.courseName} - 学生成绩管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <blockquote class="layui-elem-quote">
        当前课程：${requestScope.courseName}
    </blockquote>
    <table class="layui-table">
        <colgroup>
            <col width="100">
            <col width="150">
            <col>
            <col width="150">
        </colgroup>
        <thead>
        <tr>
            <th>学生学号</th>
            <th>学生姓名</th>
            <th>成绩</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.students}" var="s">
            <tr>
                <td>${s.studentId}</td>
                <td>${s.studentName}</td>
                <td>
                        <%-- 成绩显示区域 --%>
                    <span id="mark-${s.studentId}">
                        <c:if test="${s.mark != null}">
                            ${s.mark}
                        </c:if>
                    </span>
                        <%-- 未打分时的输入框 --%>
                    <c:if test="${s.mark == null}">
                        <div class="layui-input-inline" style="width: 100px;">
                            <input type="text" id="grade-input-${s.studentId}" class="layui-input grade-input" value="" placeholder="0-100">
                        </div>
                    </c:if>
                </td>
                <td>
                    <c:if test="${s.mark == null}">
                        <button class="layui-btn layui-btn-sm save-grade-btn" data-student-id="${s.studentId}" data-course-id="${requestScope.courseId}">保存</button>
                    </c:if>
                    <c:if test="${s.mark != null}">
                        <button class="layui-btn layui-btn-sm layui-btn-disabled" disabled>已打分</button>
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

        // 绑定保存按钮的点击事件
        $('.save-grade-btn').click(function(){
            var btn = $(this);
            var studentId = btn.data('student-id');
            var courseId = btn.data('course-id');
            var input = $('#grade-input-' + studentId);
            var mark = input.val();

            // 验证成绩输入是否合法
            if (!mark || isNaN(mark) || mark < 0 || mark > 100) {
                layer.msg('请输入0-100之间的有效成绩', {icon: 2});
                return;
            }

            $.ajax({
                url: '${pageContext.request.contextPath}/teacherServlet',
                type: 'post',
                data: {
                    action: 'gradeStudent',
                    studentId: studentId,
                    courseId: courseId,
                    mark: mark
                },
                success: function(res){
                    if(res.trim() === 'success'){
                        layer.msg('成绩保存成功！', {icon: 1});
                        // 成功后，更新页面UI
                        $('#mark-' + studentId).text(mark);
                        input.remove(); // 移除输入框
                        btn.closest('td').html('<button class="layui-btn layui-btn-sm layui-btn-disabled" disabled>已打分</button>');
                    } else {
                        layer.msg('成绩保存失败！', {icon: 2});
                    }
                }
            });
        });
    });
</script>
</body>
</html>