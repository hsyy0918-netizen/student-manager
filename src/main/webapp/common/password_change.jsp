<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/layui/css/layui.css" media="all">
</head>
<body>
<div style="padding: 15px;">
    <form class="layui-form" lay-filter="passwordChangeForm">
        <div class="layui-form-item">
            <label class="layui-form-label">旧密码</label>
            <div class="layui-input-block">
                <input type="password" name="oldPassword" required lay-verify="required" placeholder="请输入旧密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">新密码</label>
            <div class="layui-input-block">
                <input type="password" name="newPassword" required lay-verify="required|pass" placeholder="请输入新密码" autocomplete="off" class="layui-input" id="newPassword">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">确认密码</label>
            <div class="layui-input-block">
                <input type="password" name="confirmPassword" required lay-verify="required|confirmPass" placeholder="请再次输入新密码" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="changePassword">修改密码</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
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

        form.verify({
            pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ],
            confirmPass: function(value){
                if(value !== $('#newPassword').val()){
                    return '两次输入的密码不一致！';
                }
            }
        });

        form.on('submit(changePassword)', function(data){
            $.ajax({
                url: '${pageContext.request.contextPath}/studentServlet?action=changePassword', // 教师也可以用这个，因为action一样
                type: 'post',
                data: data.field,
                success: function(res){
                    if(res.trim() === 'success'){
                        layer.msg('密码修改成功，请重新登录！', {icon: 1});
                        setTimeout(function(){
                            parent.location.href = '${pageContext.request.contextPath}/logout';
                        }, 2000);
                    } else {
                        layer.msg('密码修改失败，旧密码错误！', {icon: 2});
                    }
                }
            });
            return false;
        });
    });
</script>
</body>
</html>