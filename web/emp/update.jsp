<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: liuhuan
  Date: 2019/10/8
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改页面</title>
</head>
<body>
<%--将查询的一行数据显示到该页面，并且这些数据都应该是可修改的。--%>
<center>
    <form action="${pageContext.request.contextPath}/main?target=update" method="post">
<%--        隐藏域，标签是隐藏的，这里用来保存eid--%>
        <input type="hidden" name="eid" value="${emp.eid}" />
        <table border="1" cellspacing="0" cellpadding="5">
            <tr>
                <td>姓名</td>
                <td><input type="text" name="ename" value="${emp.ename}"/></td>
            </tr>
            <tr>
                <td>性别</td>
                <td>
                    <input type="radio" name="esex" value="男" <c:if test="${'男' == emp.esex}">checked</c:if>/>男
                    <input type="radio" name="esex" value="女" <c:if test="${'女' == emp.esex}">checked</c:if> />女
                </td>
            </tr>
            <tr>
                <td>入职日期:</td>
<%--                由于数据date和标签date是不同的，不兼容的，所以需要使用<fmt>标签转换--%>
                <td>
                    <input type="date" name="estartime" value="<fmt:formatDate value='${emp.estartime}' pattern='yyyy-MM-dd'/>"/>
                </td>
            </tr>
            <tr>
                <td>工资</td>
                <td>
                    <input type="text" name="epay" value="${emp.epay}" />
                </td>
            </tr>
            <tr>
                <td>部门</td>
                <td>
                    <select name="did">
                        <option>--请选择--</option>
                        <c:forEach items="${deptList}" var="dept">
                            <option value="${dept.did}" <c:if test="${dept.did==emp.did}">selected</c:if>>
                                    ${dept.dname}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="修改"/>
                </td>
            </tr>
        </table>

    </form>
</center>
</body>
</html>
