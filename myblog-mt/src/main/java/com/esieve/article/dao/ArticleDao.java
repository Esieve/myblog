package com.esieve.article.dao;

import com.esieve.article.bean.Article;
import com.esieve.article.bean.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ArticleDao {
    @Select("SELECT content, publish_time, update_time FROM article WHERE is_about = 1")
    Article getAbout();

    @Update("UPDATE article SET content=#{content}, update_time=now() WHERE is_about = 1")
    int updateAbout(String content);

    Article getPreArticle(int articleId);

    Article getNextArticle(int articleId);

    @Select("SELECT * FROM article WHERE article_id=#{articleId}")
    Article getArticleByArticleId(int articleId);

    @Select("SELECT article_id,title,content,publish_time FROM article WHERE is_about=0 ORDER BY publish_time DESC")
    List<Article> getArticles();

    List<Article> getArticlesByKey(String key);

    List<Article> getArticlesByRange(Page pageUtil);

    List<Article> getArticlesByClicks();

    List<Article> getArticlesByCategoryId(int categoryId);

    List<Article> getRecentArticlesTitle();

    int updateArticle(Article article);

    @Insert("INSERT INTO article VALUES (null,#{categoryId},#{userId},#{title},#{content},#{publishTime},#{updateTime},0,#{image},0)")
    int insertArticle(Article article);

    @Delete("DELETE FROM article WHERE article_id = #{articleId}")
    int deleteArticle(int articleId);

    int addClicks(int articleId);

    @Select("SELECT COUNT(*) AS total FROM article WHERE is_about=0")
    int countArticleNum();
}
