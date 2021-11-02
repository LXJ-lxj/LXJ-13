<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<html>
<head>
    <title>登录</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script>
        $(function () {
            $("#submit").on('click', function () {
                $.ajax({
                    url: "/login",
                    type: 'POST',
                    data: {'username': $("#username").val(), 'password': $("#password").val()},
                    success: function (data) {
                        alert(data);
                    }
                });
            })
        });

    </script>
</head>
<body>
<h2>登录</h2>
<a href="${pageContext.request.contextPath}/registryPage">还没注册？</a>
用户名<input id="username" type="text"/>
密码<input id="password" type="password"/>
<input id="submit" type="submit" value="提交"/>
</body>
</html>
