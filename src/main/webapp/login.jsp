<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>教务查询系统 - 登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css">
    //样式
    <style>
        .login-panel {
            width: 380px;
            margin: 100px auto;
            padding: 25px 20px 10px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0,0,0,.1);
        }
        .login-title {
            font-size: 24px;
            text-align: center;
            margin-bottom: 20px;
        }
        .layui-form-item {
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="layui-main">
    <div class="login-panel">
        <h2 class="login-title">教务查询系统</h2>
        <form class="layui-form" action="${pageContext.request.contextPath}/login" method="post">
            <div class="layui-form-item">
                <input type="text" name="username" required lay-verify="required" placeholder="用户名" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <input type="password" name="password" required lay-verify="required" placeholder="密码" autocomplete="off" class="layui-input">
            </div>
            <div class="layui-form-item">
                <button class="layui-btn layui-btn-fluid" lay-submit lay-filter="login">登录</button>
            </div>
<%--            提交按钮在这--%>
            <c:if test="${not empty error}">
                <div class="layui-form-item" style="text-align: center; color: red;">
                        ${error}
                </div>
            </c:if>
        </form>
    </div>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use('form', function(){
        var form = layui.form;
    });
</script>
</body>
</html>