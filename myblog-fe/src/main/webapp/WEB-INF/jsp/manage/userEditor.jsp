<%--
  Created by IntelliJ IDEA.
  User: 张秦遥
  Date: 2017/6/16/0016
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function(){
        $('select').formSelect();
    });

    function check() {
        var username = $("#username").val();
        var password = $("#password").val();
        var confirmPassword = $("#confirmPassword").val();
        if (username == "") {
            $("#info").text("用户名不能为空！");
            showErrorInfo();
            return false;
        }
        if (password == "") {
            $("#info").text("密码不能为空！");
            showErrorInfo();
            return false;
        }
        if (confirmPassword == "") {
            $("#info").text("确认密码不能为空！");
            showErrorInfo();
            return false;
        }
        if (password != null && confirmPassword != null && password != "" && password != confirmPassword) {
            $("#info").text("两次密码输入不一致！");
            showErrorInfo();
            return false;
        }
        return true;
    }

</script>
<div class="row">
    <div class="col s12">
        <div class="card-panel">
            <form method="post" action="/manage/user/save/${user.userId}" onsubmit="return check()">
                <div class="input-field col s6">
                    <i class="material-icons prefix">account_circle</i>
                    <input id="username" name="username" type="text" class="validate " value="${user.username}">
                    <label for="username">用户名</label>
                </div>
                <br><br><br><br>

                <div class="input-field col s6">
                    <i class="material-icons prefix">lock</i>
                    <input id="password" name="password" type="password" class="validate">
                    <label for="password">密码</label>
                </div>
                <br><br><br><br>

                <div class="input-field col s6">
                    <i class="material-icons prefix">lock_outline</i>
                    <input id="confirmPassword" name="confirmPassword" type="password" class="validate">
                    <label for="password">确认密码</label>
                </div>
                <br><br><br><br>

                <div class="input-field col s6">
                    <i class="material-icons prefix">short_text</i>
                    <input id="biography" name="biography" type="text" class="validate" value="${user.biography}">
                    <label for="biography">个人简介</label>
                </div>
                <br><br><br><br>

                <div class="input-field col s6">
                    <i class="material-icons prefix">portrait</i>
                    <select id="image" name="image" class="icons">
                        <option value="" disabled selected>头像</option>
                        <c:forEach var="image" items="${images}">
                            <option value="${image}" data-icon="/images/user/${image}" }>${image}</option>
                        </c:forEach>
                    </select>
                </div>
                <br><br><br><br>

                <div class="input-field col s6">
                    <i class="material-icons prefix">photo</i>
                    <select id="background" name="background" class="icons">
                        <option value="" disabled selected>背景图片</option>
                        <c:forEach var="image" items="${images}">
                            <option value="${image}" data-icon="/images/user/${image}" }>${image}</option>
                        </c:forEach>
                    </select>
                </div>
                <br><br><br><br>

                <button class="btn waves-effect waves-light green hoverable" type="submit">保存</button>
            </form>
        </div>
    </div>
</div>