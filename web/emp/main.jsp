<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: liuhuan
  Date: 2019/9/29
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户信息界面</title>
</head>
<body>
<center>
    <h1>用户信息管理界面</h1>
    <form action="${pageContext.request.contextPath}/main?target=list" method="post" id="mainForm">
<%--使用隐藏域保存pageNow 那么该表单提交时 就会传入所有需要的数据--%>
        <input type="hidden" id="pageNow" name="pageNow" value="${pb.pageNow}"/>
        姓名<input type="text" name="name" value="${qs.name}"/>
        入职日期<input type="date"  name="startTime" value="${qs.startTime}"/>
         - <input type="date" name="endTime" value="${qs.endTime}"/>
        部门名称:
        <select name="s_did">
            <option value="">--请选择--</option>
            <c:forEach items="${deptList}" var="dept">
<%--                当第二次回到该页面的时候，由于request域对象经过传参，会携带表单数据 即有s_did的值，--%>
<%--                那么就可以借此确定下拉框选择哪一个 在遍历的时候如果两者的did相等就选择--%>
                <option value="${dept.did}" <c:if test="${dept.did==qs.did}">selected</c:if>>
                    ${dept.dname}
                </option>
            </c:forEach>
        </select>
        <input type="submit" value="查询"/>
        <input type="button" value="添加员工" id="addEmp"/>
        <input type="button" value="批量删除" id="batchDel">
    </form>

<%--    这个表单在js里面提交，所以要定义id属性--%>
    <form action="${pageContext.request.contextPath}/main?target=batchDel"  method="post" id="myForm">
    <table border="1" cellpadding="10" cellspacing="0" >
        <tr>
            <td>全选:<input type="checkbox" id="checkAll"></td>
            <td>编号</td>
            <td>姓名</td>
            <td>性别</td>
            <td>入职日期</td>
            <td>工资</td>
            <td>部门名称</td>
            <td>操作</td>
        </tr>
<%--        直接使用el表达式里面写域参数的名称，因为一般同一个界面不会被传入两个同名的参数--%>
        <c:forEach items="${pb.data}" var="emp">
            <tr>
                <td><input type="checkbox" name="checkId" value="${emp.eid}"></td>
                <td>${emp.eid}</td>
                <td>${emp.ename}</td>
                <td>${emp.esex}</td>
                <td><fmt:formatDate value="${emp.estartime}" pattern="yyyy年MM月dd日"/></td>
                <td>${emp.epay}</td>
                <td>${emp.dname}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/main?target=queryOneLine&eid=${emp.eid}" >修改</a>
                    <a href="${pageContext.request.contextPath}/main?target=delete&eid=${emp.eid}" >删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </form>
    <p>
        第${pb.pageNow}页/共${pb.pageTotal}页
        <a href="javascript:toPage(1)" >首页</a>&nbsp;
        <a href="javascript:toPage(${pb.pageNow - 1})" >上一页</a>&nbsp;
        <a href="javascript:toPage(${pb.pageNow + 1})" >下一页</a>&nbsp;
        <a href="javascript:toPage(${pb.pageTotal})">尾页</a>
        <select id="jumpSelect">
            <c:forEach begin="1" end="${pb.pageTotal}" var="i">
                <option value="${i}" <c:if test="${i == pb.pageNow}">selected</c:if>>${i}</option>
            </c:forEach>
        </select>
        <input type="number" id="num" size="6" value="${pb.pageNow}"/>
        <input type="button" id="jumpButton" value="跳转">
    </p>
</center>
<script>
    //添加按钮的跳转操作
    var addEmp = document.getElementById("addEmp");
    addEmp.onclick=function () {
        location.href="${pageContext.request.contextPath}/emp/addEmp.jsp";
    }
    
    //全选按钮的操作
    var checkAll = document.getElementById("checkAll");
    checkAll.onchange=function () {
        var checkId = document.getElementsByName("checkId");
        if (checkAll.checked){
            for (var i =0;i < checkId.length;i++){
                checkId[i].checked=true;
            }
        }else{
            for (var i =0;i < checkId.length;i++){
                checkId[i].checked=false;
            }
        }
    }

    //批量删除
    var batchDel = document.getElementById("batchDel");
    batchDel.onclick=function () {
        //在这里提交包含checkBoxS的表单
        var myForm = document.getElementById("myForm");
        myForm.submit();
    }

</script>
<%--各种操作的提示--%>
<script>
    // 插入是否成功的判断
    if (${insert != null}){
        <c:if test="${insert}">
        alert("插入成功");
        </c:if>
        <c:if test="${!insert}">
        alert("插入失败");
        </c:if>
    }
    //更新是否成功的判断
    if (${updte != null}){
        <c:if test="${update}">
        alert("更新成功");
        </c:if>a
        <c:if test="${!update}">
        alert("更新失败");
        </c:if>
    }
    //删除是否成功的判断
    if (${delete != null}){
        <c:if test="${delete}">
        alert("删除成功");
        </c:if>
        <c:if test="${!delete}">
        alert("删除失败");
        </c:if>
    }

    //批量删除是否成功的判断
    if (${batchDel != null}){
        <c:if test="${batchDel}">
        alert("批量删除成功");
        </c:if>
        <c:if test="${!batchDel}">
        alert("批量删除失败");
        </c:if>
    }
</script>
<%--分页--%>
<script>
    //提交表单
   function toPage(p) {
       //将隐藏域的value设置为传入的值，然后再提交表单
       var pageNow = document.getElementById("pageNow");
       pageNow.value = p;
       //提交表单
       var mainForm = document.getElementById("mainForm");
       mainForm.submit();
   }

   //复选框跳转
    var jumpSelect = document.getElementById("jumpSelect");
    jumpSelect.onchange=function () {
        var pageNow = jumpSelect.value;
        toPage(pageNow);
    }

    //输入后点击跳转
    var jumpButton = document.getElementById("jumpButton");
    jumpButton.onclick=function () {
        //先获取输入框的数据
        var num = document.getElementById("num");
        //再跳转
        toPage(num.value);
    }
</script>
</body>
</html>
