<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<html>
<head>
    <title>登录页</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        // $(document).ready(function () {
        //
        // });

        // $().ready(function () {
        //
        // });

        $(function () {
            $("#submit").on('click', function () {
                $.ajax({
                    url: "/login",
                    type: "POST",
                    data: {'name': $("#username").val(), 'password': $("#password").val()},
                    success: function (data) {
                        alert(data); //弹出对话框
                    }
                });
            });
        });
    </script>
</head>
<body>
<h>登入你的账号</h>
<div>
    用户名:<input id="username" placeholder="username" type="text" />
</div>
<div>
    密码:<input id="password" placeholder="password" type="password" />
</div>
<input id="submit" type="submit" value="提交">
<div>
    还未注册? <a href="${pageContext.request.contextPath}/registerPage">注册页</a>
</div>
</body>
</html>
