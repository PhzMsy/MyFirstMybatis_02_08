<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2023/2/7
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="<%=request.getContextPath()%>/js/jquery-3.6.0.min.js"></script>
    <script>
        $(function (){
            $.ajax({
                url: "<%=request.getContextPath()%>/users",
                data:{m:"query"},
                dataType:"json",
                type:"post",
                success:function (resp){
                    for (let i of resp) {
                        $("#ta").append("<tr>" +
                            "<td></td>" +
                            "<td>"+i.id+"</td>" +
                            "<td>"+i.username+"</td>" +
                            "<td>"+i.password+"</td>" +
                            "<td>"+i.sex+"</td>" +
                            "<td>"+i.birthday+"</td>" +
                            "<td>"+i.address+"</td>" +
                            "<td>"+i.hobby+"</td>" +
                            "<td><input type='button' value='修改' class='update'><input type='hidden' value='"+i.id+"'><input type='button' value='删除' class='delete'></td>" +
                            "</tr>")
                    }
                }
            })

            $("body").on('click','.update',function(){
                let id = $(this).next().val();
                console.log(id);
                location = "<%=request.getContextPath()%>/user/update.jsp?id="+id;
            })
            $("body").on('click','.delete',function(){
                let id = $(this).prev().val();
                $.ajax({
                    url:"<%=request.getContextPath()%>/users?m=delete&id="+id,
                    dataType: "json",
                    success:function (resp){
                        if (resp){
                            location.reload();
                        }
                    }
                })
            })

        })
    </script>
</head>
<body>
    <table id="ta">
        <tr>
            <td></td>
            <td>编号</td>
            <td>姓名</td>
            <td>密码</td>
            <td>性别</td>
            <td>生日</td>
            <td>住址</td>
            <td>爱好</td>
            <td>
                操作     <input type="button" value="添加" id="add">
            </td>
        </tr>
    </table>
</body>
<script>
    // 获取id是add的元素
    let add = document.getElementById("add");
    // 绑定点击事件
    add.onclick = function (){
        // 跳转
        location = "<%=request.getContextPath()%>/user/add.jsp";
    }
</script>
</html>
