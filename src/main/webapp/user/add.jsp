<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/2/7
  Time: 18:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
    <script>
        $(function (){
            $("#btn").click(function() {
                $.ajax({
                    url:"<%=request.getContextPath()%>/users?m=insert",
                    data:$("#f").serialize(),
                    dataType:"json",
                    type:"post",
                    success:function (resp){
                        if(resp){
                            location = "<%=request.getContextPath()%>/user/list.jsp";
                        }
                    }
                });
            });
        })
    </script>
</head>
<body>
<form id="f">
    <table>
        <tr>
            <td>姓名</td>
            <td>
                <input type="text" name="username" id="username">
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="password" name="password" id="password">
            </td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="sex" class="sex" value="男">男
                <input type="radio" name="sex" class="sex" value="女">女
            </td>
        </tr>
        <tr>
            <td>生日</td>
            <td>
                <input type="text" name="birthday" id="birthday">
            </td>
        </tr>
        <tr>
            <td>住址</td>
            <td>
                <input type="text" name="address" id="address">
            </td>
        </tr>
        <tr>
            <td>爱好</td>
            <td>
                <input type="text" name="hobby" id="hobby">
            </td>
        </tr>

        <tr>
            <td>
                <input type="button" value="添加" id="btn">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
