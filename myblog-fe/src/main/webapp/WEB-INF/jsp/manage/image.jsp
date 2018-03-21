<%--
  Created by IntelliJ IDEA.
  User: 张秦遥
  Date: 2017/2/22/0022
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
    $(document).ready(function () {
        $('.carousel').carousel();
    });
</script>

<div class="row">
    <div class="col s12">
        <div class="card-panel">
            <form action="/manage/image/delete" method="post">
                <div class="carousel">
                    <c:forEach var="userImage" items="${userImages}">
                        <div class="carousel-item black-text center">
                            <img src="/images/user/${userImage}">
                            <p><label>
                                <input type="checkbox" name="userImages" value="${userImage}"/>
                                <span>${userImage}</span>
                            </label></p>
                        </div>
                    </c:forEach>
                </div>

                <div class="carousel">
                    <c:forEach var="articleImage" items="${articleImages}">
                        <div class="carousel-item black-text center">
                            <img src="/images/article/${articleImage}">
                            <p><label>
                                <input type="checkbox" name="articleImages" value="${articleImage}"/>
                                <span>${articleImage}</span>
                            </label></p>
                        </div>
                    </c:forEach>
                </div>

                <div class="center-align">
                    <button class="btn waves-effect waves-light green hoverable" type="submit">删除</button>
                </div>
            </form>

            <div class="divider"></div>

            <form method="POST" enctype="multipart/form-data" action="/manage/image/upload">
                <div class="file-field input-field">
                    <div class="btn">
                        <span>用户图片</span>
                        <input type="file" multiple>
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" placeholder="Upload one or more files">
                    </div>
                </div>

                <div class="file-field input-field">
                    <div class="btn">
                        <span>文章图片</span>
                        <input type="file" multiple>
                    </div>
                    <div class="file-path-wrapper">
                        <input class="file-path validate" type="text" placeholder="Upload one or more files">
                    </div>
                </div>

                <div class="center-align">
                    <button class="btn waves-effect waves-light green hoverable" type="submit">上传</button>
                </div>
            </form>
        </div>
    </div>
</div>