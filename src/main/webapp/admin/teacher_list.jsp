<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>教师名单管理</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <%-- 顶部工具栏 --%>
    <div class="layui-form">
        <div class="layui-form-item">
            <div class="layui-inline">
                <input type="text" name="name" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
            <button class="layui-btn" lay-submit lay-filter="formSearch">搜索</button>
            <a href="${pageContext.request.contextPath}/adminServlet?action=toAddTeacher" class="layui-btn layui-btn-normal">添加教师信息</a>
        </div>
    </div>

    <%-- 数据表格 --%>
    <table class="layui-table">
        <colgroup>
            <col width="80">
            <col width="120">
            <col width="60">
            <col width="150">
            <col width="100">
            <col width="100">
            <col width="150">
            <col>
            <col width="180">
        </colgroup>
        <thead>
        <tr>
            <th>工号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>出生年份</th>
            <th>学历</th>
            <th>职称</th>
            <th>入职时间</th>
            <th>学院</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.teachers}" var="t">
            <tr>
                <td>${t.teacherId}</td>
                <td>${t.teacherName}</td>
                <td>${t.sex}</td>
                <td><fmt:formatDate value="${t.birthday}" pattern="yyyy-MM-dd"/></td>
                <td>${t.degree}</td>
                <td>${t.title}</td>
                <td><fmt:formatDate value="${t.hireDate}" pattern="yyyy-MM-dd"/></td>
                <td>${t.collegeName}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/adminServlet?action=toEditTeacher&teacherId=${t.teacherId}" class="layui-btn layui-btn-sm">修改</a>
                    <a href="javascript:;" onclick="deleteTeacher(${t.teacherId})" class="layui-btn layui-btn-danger layui-btn-sm">删除</a>
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
            location.href = '${pageContext.request.contextPath}/adminServlet?action=listTeachers&name=' + name;
            return false;
        });

        // 删除教师的JS方法
        window.deleteTeacher = function(teacherId){
            layer.confirm('确定删除该教师及其账户吗？', {icon: 3, title:'提示'}, function(index){
                $.ajax({
                    url: '${pageContext.request.contextPath}/adminServlet',
                    type: 'post',
                    data: {
                        action: 'deleteTeacher',
                        teacherId: teacherId
                    },
                    success: function(res){
                        if(res.trim() === 'success'){
                            layer.msg('删除成功！', {icon: 1});
                            setTimeout(function(){
                                location.reload();
                            }, 1000);
                        } else {
                            layer.msg('删除失败！', {icon: 2});
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