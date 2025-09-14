<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>编辑学生信息</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <form class="layui-form" action="${pageContext.request.contextPath}/adminServlet?action=editStudent" method="post">
        <input type="hidden" name="studentId" value="${student.studentId}">
        <div class="layui-form-item">
            <label class="layui-form-label">学号</label>
            <div class="layui-input-block">
                <input type="text" value="${student.studentId}" class="layui-input layui-disabled" disabled>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">姓名</label>
            <div class="layui-input-block">
                <input type="text" name="studentName" required lay-verify="required" placeholder="请输入姓名" autocomplete="off" class="layui-input" value="${student.studentName}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男" <c:if test="${student.sex eq '男'}">checked</c:if>>
                <input type="radio" name="sex" value="女" title="女" <c:if test="${student.sex eq '女'}">checked</c:if>>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-block">
                <input type="text" name="birthday" required lay-verify="required" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" id="birthday" value="<fmt:formatDate value="${student.birthday}" pattern="yyyy-MM-dd"/>">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">入学日期</label>
            <div class="layui-input-block">
                <input type="text" name="enrollmentDate" required lay-verify="required" placeholder="yyyy-MM-dd" autocomplete="off" class="layui-input" id="enrollmentDate" value="<fmt:formatDate value="${student.enrollmentDate}" pattern="yyyy-MM-dd"/>">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">所属学院</label>
            <div class="layui-input-block">
                <select name="collegeId" lay-verify="required">
                    <option value=""></option>
                    <c:forEach items="${requestScope.colleges}" var="c">
                        <option value="${c.collegeId}" <c:if test="${student.collegeId eq c.collegeId}">selected</c:if>>${c.collegeName}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="editStudent">提交修改</button>
            </div>
        </div>
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