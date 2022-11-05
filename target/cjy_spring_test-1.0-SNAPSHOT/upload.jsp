<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/user/quick23" method="post" enctype="multipart/form-data">
<%--    名称<input type="text" name="username"  不记住输入记录autocomplete="off"><br/>--%>
    文件<input type="file" name="uploadFile"><br/>
    姓名<input type="text" name="username"  autocomplete="off"><br/>
    密码<input type="text" name="password"  autocomplete="off"><br/>
    邮箱<input type="text" name="email"  autocomplete="off"><br/>
    电话<input type="text" name="phoneNum"  autocomplete="off"><br/>

    年龄<input type="text" name="age" autocomplete="off"><br/>
    <input type="submit" value="提交"><br/>
</form>
<%--测试多文件上传--%>
<%--<form action="${pageContext.request.contextPath}/user/quick22" method="post" enctype="multipart/form-data">
    名称<input type="text" name="username"><br/>
    文件<input type="file" name="uploadFile"><br/>
    文件<input type="file" name="uploadFile2"><br/>
    <input type="submit" value="提交"><br/>
</form>--%>
</body>
</html>



