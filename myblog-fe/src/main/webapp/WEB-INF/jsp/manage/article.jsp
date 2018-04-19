<%--
  Created by IntelliJ IDEA.
  User: 77239
  Date: 2017/2/16/0016
  Time: 23:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/tag.jsp" %>
<script type="text/javascript">
    function check() {
        if(confirm("确定要删除这篇文章吗？")){
            return true;
        } else {
            return false;
        }
    }
</script>
<div class="row">
    <div class="col s12">
        <div class="card-panel">
            <table class="responsive-table centered striped">
                <thead>
                <th>名称</th>
                <th>时间</th>
                <th>操作</th>
                </thead>
            </table>

            <ul class="collapsible popout">
                <c:forEach var="article" items="${articles}">
                    <li>
                        <div class="collapsible-header">
                            <table class="responsive-table centered striped">
                                <tbody>
                                <tr>
                                    <td>${article.title }</td>
                                    <td><fmt:formatDate value="${article.publishTime}"
                                                        pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                    <td><a href="/manage/article/modify/${article.articleId}"
                                           class="waves-effect waves-light btn green hoverable">修改</a>
                                        <a class="waves-effect waves-light btn red hoverable"
                                           href="/manage/article/delete/${article.articleId}" onclick="return check()">删除</a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="collapsible-body">
                            <p>${article.content}</p>
                        </div>
                    </li>
                </c:forEach>
            </ul>

            <br>
            <div class="center">
                <a href="/manage/article/write" class="waves-effect waves-light btn green hoverable">写文章</a>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.collapsible').collapsible();
    });
</script>