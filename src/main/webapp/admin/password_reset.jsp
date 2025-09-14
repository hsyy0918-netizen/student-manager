<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>重置密码</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <blockquote class="layui-elem-quote layui-text">
        通过此页面，您可以为指定用户重置密码，重置后密码将恢复为默认值 **123**。
    </blockquote>
    <form class="layui-form" lay-filter="passwordResetForm">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="username" required lay-verify="required" placeholder="请输入用户学号/工号" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="resetPassword">重置密码</button>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/layui/layui.js"></script>
<script>
    layui.use(['form', 'jquery', 'layer'], function(){
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;

        form.on('submit(resetPassword)', function(data){
            $.ajax({
                url: '${pageContext.request.contextPath}/adminServlet',
                type: 'post',
                data: {
                    action: 'resetPassword',
                    username: data.field.username
                },
                success: function(res){
                    if(res.trim() === 'success'){
                        layer.msg('密码重置成功！', {icon: 1});
                    } else {
                        layer.msg('密码重置失败，请检查用户名是否正确！', {icon: 2});
                    }
                }
            });
            return false;
        });
    });
</script>
</body>
</html>