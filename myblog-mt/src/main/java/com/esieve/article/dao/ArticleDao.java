package com.esieve.article.dao;

import com.esieve.article.bean.Article;
import com.esieve.common.bean.OperationResult;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArticleDao {
    @Select("SELECT content, publish_time, update_time FROM article WHERE is_about = 1")
    Article getAbout();

    @Update("UPDATE article SET content=#{content}, update_time=now() WHERE is_about = 1")
    OperationResult updateAbout(String content);

    Article getPreArticle(int articleId);

    Article getNextArticle(int articleId);

    @Select("SELECT * FROM article WHERE article_id=#{articleId}")
    Article getArticleByArticleId(int articleId);

    @Select("SELECT article_id,title,content,publish_time FROM article WHERE is_about=0 ORDER BY publish_time DESC")
    List<Article> getArticles();

    List<Article> getArticlesByKey(String key);

    List<Article> getArticlesByRange(PageUtil pageUtil);

    List<Article> getArticlesByClicks();

    List<Article> getArticlesByCategoryId(int categoryId);

    List<Article> getRecentArticlesTitle();

    OperationResult updateArticle(Article article);

    @Insert("INSERT INTO article VALUES (null,#{categoryId},1,#{title},#{content},#{publishTime},#{updateTime},0,#{image},0)")
    OperationResult insertArticle(Article article); // todo userId

    @Delete("DELETE FROM article WHERE article_id = #{articleId}")
    OperationResult deleteArticle(int articleId);

    OperationResult addClicks(int articleId);

    OperationResult countArticleNum();
}
