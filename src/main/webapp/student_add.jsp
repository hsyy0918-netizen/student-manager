 <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>添加学生信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <form class="layui-form" action="${pageContext.request.contextPath}/adminServlet?action=addStudent" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="studentName" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男" checked>
                <input type="radio" name="sex" value="女" title="女">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-block">
                <input type="text" name="birthday" required lay-verify="required" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" id="birthday">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">入学日期</label>
            <div class="layui-input-block">
                <input type="text" name="enrollmentDate" required lay-verify="required" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" id="enrollmentDate">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">所属学院</label>
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
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="addStudent">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
        <c:if test="${not empty errorMsg}">
            <div class="layui-form-item" style="text-align: center; color: red;">
                    ${errorMsg}
            </div>
        </c:if>
    </form>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use(['form', 'laydate'], function(){
        var form = layui.form;
        var laydate = layui.laydate;
        laydate.render({elem: '#birthday'});
        laydate.render({elem: '#enrollmentDate'});
    });
</script>
</body>
</html>