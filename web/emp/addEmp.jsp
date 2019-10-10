<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: liuhuan
  Date: 2019/10/8
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加员工界面</title>
</head>
<body>
<center>
    <h1>添加员工信息</h1>
    <form action="${pageContext.request.contextPath}/main?target=addEmp" method="post">
        <table border="1" cellspacing="0" cellpadding="5" width="50%">
            <tr>
                <td>姓名</td>
                <td><input type="text" name="ename" id=""></td>
            </tr>
            <tr>
                <td>性别</td>
                <td>
                    <input type="radio" name="esex" value="男" id="" checked>男
                    <input type="radio" name="esex" value="女">女
                </td>
            </tr>
            <tr>
                <td>入职时间</td>
                <td>
                    <input type="date" name="estartime" id="">
                </td>
            </tr>
            <tr>
                <td>工资</td>
                <td><input type="text" name="epay" id=""></td>
            </tr>
            <tr>
                <td>所属部门</td>
                <td>
                    <select name="did" id="">
                        <option value="">请选择</option>
                        <c:forEach items="${deptList}" var="dept">
                            <option value="${dept.did}">
                                    ${dept.dname}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="注册">
                </td>
            </tr>
        </table>
    </form>
</center>
</body>
</html>
