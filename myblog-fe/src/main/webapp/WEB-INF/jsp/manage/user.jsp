<%--
  Created by IntelliJ IDEA.
  User: 张秦遥
  Date: 2017/6/16/0016
  Time: 17:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript">
    function check(userId) {
        if (userId == 1) {
            $("#info").text("初始用户不能删除！");
            showErrorInfo();
            return false;
        }

        if(confirm("确定要删除这个用户吗？")){
            return true;
        } else {
            return false;
        }
    }
</script>
<div class="row">
    <div class="col s12">
        <div class="card-panel">
            <table class="responsive-table centered">
                <thead>
                <tr>
                    <th>头像</th>
                    <th>用户名</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr class="hoverable">
                        <td><img src="/images/user/${user.image}" height="50px" class="circle"></td>
                        <td>${user.username}</td>
                        <td>
                            <a href="/manage/user/modify/${user.userId}"
                               class="waves-effect waves-light btn green hoverable">修改</a>
                            <a class="waves-effect waves-light btn red hoverable"
                               href="/manage/user/delete/${user.userId}" onclick="return check(${user.userId})">删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <br>
            <div class="center">
                <a href="/manage/user/register" class="waves-effect waves-light btn green hoverable">注册用户</a>
            </div>
        </div>
    </div>
</div>