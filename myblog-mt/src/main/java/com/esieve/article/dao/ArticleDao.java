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

    @Select("SELECT article_id,title FROM article WHERE article_id = " +
            "(SELECT article_id FROM article WHERE article_id < #{articleId} and is_about=0 ORDER BY article_id DESC LIMIT 1)")
    Article getPreArticle(int articleId);

    @Select("SELECT article_id,title FROM article WHERE article_id = " +
            "(SELECT article_id FROM article WHERE article_id > #{articleId} and is_about=0 ORDER BY article_id ASC LIMIT 1)")
    Article getNextArticle(int articleId);

    @Select("SELECT * FROM article WHERE article_id=#{articleId}")
    Article getArticleByArticleId(int articleId);

    @Select("SELECT article_id,title,content,publish_time FROM article WHERE is_about=0 ORDER BY publish_time DESC")
    List<Article> getArticles();

    @Select("SELECT * FROM article WHERE " +
            "(title LIKE CONCAT('%', #{key}, '%') OR content LIKE CONCAT('%', #{key}, '%')) AND is_about=0 ORDER BY publish_time DESC")
    List<Article> getArticlesByKey(String key);

    @Select("SELECT * FROM article WHERE is_about=0 ORDER BY publish_time DESC LIMIT #{start}, #{pageSize}")
    List<Article> getArticlesByRange(Page page);

    @Select("SELECT article_id,title FROM article WHERE is_about=0 ORDER BY clicks DESC LIMIT 5")
    List<Article> getArticlesByClicks();

    @Select("SELECT article_id,title,publish_time FROM article WHERE category_id=#{categoryId} ORDER BY publish_time DESC")
    List<Article> getArticlesByCategoryId(int categoryId);

    @Select("SELECT article_id,title FROM article WHERE is_about=0 ORDER BY publish_time DESC LIMIT 5")
    List<Article> getRecentArticlesTitle();

    @Update("UPDATE article SET category_id=#{categoryId},title=#{title},content=#{content},image=#{image},update_time=now() WHERE article_id=#{articleId}")
    int updateArticle(Article article);

    @Insert("INSERT INTO article VALUES (null,#{categoryId},#{userId},#{title},#{content},now(),now(),0,#{image},#{isAbout})")
    int insertArticle(Article article);

    @Delete("DELETE FROM article WHERE article_id = #{articleId}")
    int deleteArticle(int articleId);

    @Update("UPDATE article SET clicks=clicks+1 WHERE article_id=#{articleId}")
    int addClicks(int articleId);

    @Select("SELECT COUNT(*) AS total FROM article WHERE is_about=0")
    int countArticleNum();

    @Select("SELECT COUNT(*) AS total FROM article WHERE is_about=0 AND category_id=#{categoryId}")
    int countArticleNumByCategoryId(int categoryId);
}
