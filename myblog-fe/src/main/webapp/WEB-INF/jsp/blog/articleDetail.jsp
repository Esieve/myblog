<%--
  Created by IntelliJ IDEA.
  User: 77239
  Date: 2017/2/19/0019
  Time: 21:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/tag.jsp" %>
<div class="row">
    <div class="col s12">
        <nav class="indigo lighten-1">
            <div class="nav-wrapper">
                <div class="col s12">
                    <a href="/blog" class="breadcrumb">首页</a>
                    <a href="/blog/category/${article.category.categoryId}"
                       class="breadcrumb">${article.category.categoryName}</a>
                    <a class="breadcrumb">${article.title}</a>
                </div>
            </div>
        </nav>
        <br>
        <div class="card hoverable">
            <div class="card-content">
                <span class="card-title">${article.title}</span>
                <fmt:formatDate value="${article.publishTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                <span>|</span>
                <a href=/blog/category/${article.category.categoryId}>${article.category.categoryName}</a>
                <span>|</span>
                ${article.clicks + 1}&nbspViews
                <span>|</span>
                author：<a onclick="return false;" style="cursor: pointer;">${article.user.username}</a>
                <p>${article.content}</p>
            </div>
            <div class="card-action">
                修改于：<fmt:formatDate value="${article.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                <br>
                上一篇：<a href="/blog/article/${preArticle.articleId}"
                       <c:if test='${preArticle.articleId==-1}'>onclick="return false;"</c:if> >${preArticle.title}</a>
                <br>
                下一篇：<a href="/blog/article/${nextArticle.articleId}"
                       <c:if test='${nextArticle.articleId==-1}'>onclick="return false;"</c:if> >${nextArticle.title}</a>
                <br>
                转载请注明出处~
            </div>
        </div>
    </div>
</div>
<jsp:include page="messageDetail.jsp"></jsp:include>
