<%--
  Created by IntelliJ IDEA.
  User: 77239
  Date: 2017/2/22/0022
  Time: 9:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    function check() {
        if(confirm("确定要删除这个链接吗？")){
            return true;
        } else {
            return false;
        }
    }
</script>
<div class="row">
    <div class="col s12">
        <div class="card-panel">
            <table class="centered">
                <thead>
                <tr>
                    <th>链接名称</th>
                    <th>链接地址</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="link" items="${links}">
                    <tr class="hoverable">
                        <td>${link.linkName }</td>
                        <td>${link.url}</td>
                        <td>
                            <a href="/manage/link/modify/${link.linkId}"
                               class="waves-effect waves-light btn green hoverable">修改</a>
                            <a class="waves-effect waves-light btn red hoverable"
                               href="/manage/link/delete/${link.linkId}" onclick="return check()">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <div class="center">
                <a href="/manage/link/add" class="waves-effect waves-light btn green hoverable">添加链接</a>
            </div>
        </div>
    </div>
</div>