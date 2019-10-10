<%--
  Created by IntelliJ IDEA.
  User: liuhuan
  Date: 2019/9/29
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
<%--  做一个操作就是跳转到mainServlet查询数据库 并将其显示到界面--%>
<jsp:forward page="/main?target=list"></jsp:forward>
  </body>
</html>
