<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>学生名单管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <%-- 顶部工具栏 --%>
<%--    这个模块有搜索和添加按钮--%>
    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <input type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn" lay-submit lay-filter="formSearch">搜索</button>
            <a href="${pageContext.request.contextPath}/admin?action=toAddStudent" class="layui-btn layui-btn-normal">添加学生信息</a>
        </div>
    </div>

    <%-- 数据表格 --%>
    <table class="layui-table">
        <colgroup>
            <col width="80">
            <col width="120">
            <col width="60">
            <col width="150">
            <col width="150">
            <col>
            <col width="180">
        </colgroup>
        <thead>
        <tr>
            <th>学号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>出生年份</th>
            <th>入学时间</th>
            <th>学院</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.students}" var="s">
            <tr>
                <td>${s.studentId}</td>
                <td>${s.studentName}</td>
                <td>${s.sex}</td>
                <td><fmt:formatDate value="${s.birthday}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${s.enrollmentDate}" pattern="yyyy-MM-dd"/></td>
                <td>${s.collegeName}</td>
                <td>
<%--                    这个模块写了修改和删除按钮--%>
                    <a href="${pageContext.request.contextPath}/admin?action=toEditStudent&studentId=${s.studentId}" class="layui-btn layui-btn-sm">修改</a>
                    <a href="javascript:;" onclick="deleteStudent(${s.studentId})" class="layui-btn layui-btn-danger layui-btn-sm">删除</a>
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
            location.href = '${pageContext.request.contextPath}/admin?action=listStudents&name=' + name;
            return false;
        });

        // 删除学生的JS方法
        window.deleteStudent = function(studentId){
            layer.confirm('确定删除该学生及其账户吗？', {icon: 3, title:'提示'}, function(index){
                $.ajax({
                    url: '${pageContext.request.contextPath}/admin',
                    type: 'post',
                    data: {
                        action: 'deleteStudent',
                        studentId: studentId
                    },
                    success: function(res){
                        if(res.trim() === 'success'){
                            layer.msg('删除成功！', {icon: 1});
                            setTimeout(function(){
                                location.reload();
                            }, 1000);
                        } else {
                            layer.msg('删除失败，该学生可能已选课或有成绩记录！', {icon: 2});
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