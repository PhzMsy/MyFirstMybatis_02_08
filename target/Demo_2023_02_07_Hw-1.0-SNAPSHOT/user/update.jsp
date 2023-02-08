<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/2/7
  Time: 18:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
<script>
    const id = ${param.id};
    console.log(id);
    $(function (){
        console.log("进入到准备就绪函数中了")
        $.ajax({
            url:"<%=request.getContextPath()%>/users",
            data:{id:id,m:"queryById"},
            dataType:"json",
            type:"post",
            success:function (resp){
                console.log("进入到success中了")
                console.log(resp.id,resp.username,resp.password,resp.sex,resp.birthday,resp.address,resp.hobby)
                $("#id").val(resp.id);
                $("#username").val(resp.username);
                $("#password").val(resp.password);
                $(".sex[value="+resp.sex+"]").attr("checked",true);
                $("#birthday").val(resp.birthday);
                $("#address").val(resp.address);
                $("#hobby").val(resp.hobby);

            }
        });
        $("#btn").click(function() {
            $.ajax({
                url:"<%=request.getContextPath()%>/users?m=update",
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
<body>
<form id="f">
    <table>
        <tr>
            <td>姓名</td>
            <td>
                <input type="hidden" id="id" name="id">
                <input type="text" name="username" id="username">
            </td>
        </tr>
        <tr>
            <td>密码</td>
            <td>
                <input type="text" name="password" id="password">
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
                <input type="button" value="修改" id="btn">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
