<%--
  Created by IntelliJ IDEA.
  User: 77239
  Date: 2017/2/16/0016
  Time: 23:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/common/tag.jsp" %>
<div class="row">
    <div class="col s12">
        <div class="card-panel">
            <form action="/manage/article/save/${article.articleId}" method="post" onsubmit="return check()">
                <div class="input-field col s6">
                    <i class="material-icons prefix">title</i>
                    <input id="title" name="title" type="text" class="validate" value="${article.title}">
                    <label for="title">标题</label>
                </div>
                <br><br><br><br>
                <div class="input-field col s6">
                    <i class="material-icons prefix">view_list</i>
                    <select id="categoryId" name="categoryId">
                        <option value="" disabled selected>类别</option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.categoryId}" ${category.categoryId==article.category.categoryId?'selected':''}>
                                    ${category.categoryName}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <br><br><br><br>
                <div class="input-field col s6">
                    <i class="material-icons prefix">add_a_photo</i>
                    <select id="image" name="image" class="icons">
                        <option value="" disabled selected>图片</option>
                        <c:forEach var="image" items="${images}">
                            <option value="${image}"
                                    data-icon="/images/article/${image}" ${article.image==image?'selected':'' }>${image}</option>
                        </c:forEach>
                    </select>
                </div>
                <br><br><br><br>
                <div>
                    <textarea class="ckeditor" id="content" name="content">${article.content}</textarea>
                </div>
                <br>
                <button class="btn waves-effect waves-light green hoverable" type="submit" name="action">保存</button>
            </form>
        </div>
    </div>
</div>
<script>
    ClassicEditor
        .create( document.querySelector( '.ckeditor' ) )
        .catch( error => {
        console.error( error );
    } );

    $(document).ready(function(){
        $('select').formSelect();
    });

    function check() {
        var title = document.getElementById("title").value;
        var category = document.getElementById("categoryId").value;
        if (title == null || title == "") {
            $("#info").text("标题不能为空！");
            showErrorInfo();
            return false;
        }

        if (category == null || category == "") {
            $("#info").text("类别不能为空！");
            showErrorInfo();
            return false;
        }
        return true;
    }
</script>