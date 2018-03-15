package com.esieve.article.service;

import com.esieve.article.bean.Article;
import com.esieve.article.bean.Page;
import com.esieve.common.bean.OperationResult;

import java.util.List;

public interface ArticleService {
    Article getAbout();

    OperationResult updateAbout(String content);

    List<Article> searchArticles(String key);

    List<Article> pagination(Page pageUtil);

    Article getPreArticle(int articleId);

    Article getNextArticle(int articleId);

    OperationResult<Article> getArticleByArticleId(int articleId);

    List<Article> getArticles();

    List<Article> getArticlesByCategoryId(int categoryId);

    List<Article> getRecentArticles();

    List<Article> getMostViewedArticles();

    OperationResult updateArticle(Article article);

    OperationResult insertArticle(Article article);

    OperationResult deleteArticle(int articleId);

    OperationResult addClicks(int articleId);

    int countArticleNum();
}
