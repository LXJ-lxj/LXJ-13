<%--
  Created by IntelliJ IDEA.
  User: hu
  Date: 2020/4/23
  Time: 下午2:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户密码修改页</title>
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
                    url: "/update",
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
<h>修改你的密码</h>
<div>
    用户名:<input id="username" placeholder="username" type="text" />
</div>
<div>
    密码:<input id="password" placeholder="password" type="password" />
</div>
<input id="submit" type="submit" value="提交">

</body>
</html>
