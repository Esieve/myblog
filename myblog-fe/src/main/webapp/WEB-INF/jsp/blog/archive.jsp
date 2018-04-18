<%--
  Created by IntelliJ IDEA.
  User: 77239
  Date: 2017/2/19/0019
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/tag.jsp" %>
<script type="text/javascript">
    var options = [
        {
            selector: '#archive', offset: 0, callback: function (el) {
                Materialize.showStaggeredList($(el));
            }
        },
    ];
    Materialize.scrollFire(options);
</script>
<div class="row">
    <div class="col s12">
        <nav class="indigo lighten-1">
            <div class="nav-wrapper">
                <div class="col s12">
                    <a href="/blog" class="breadcrumb">首页</a>
                    <a class="breadcrumb">归档</a>
                </div>
            </div>
        </nav>
        <br>
        <div class="card-panel hoverable">
            <ul id="archive">
                <li>
                    <table class="responsive-table centered">
                        <tbody>
                        <c:forEach var="article" items="${articles}">
                            <tr>
                                <td><a href="/blog/article/${article.articleId}">${article.title}</a></td>
                                <td><fmt:formatDate value="${article.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </li>
            </ul>
        </div>
    </div>
</div>