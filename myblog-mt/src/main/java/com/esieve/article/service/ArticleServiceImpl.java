package com.esieve.article.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.esieve.article.bean.Article;
import com.esieve.article.bean.Page;
import com.esieve.article.dao.ArticleDao;
import com.esieve.common.bean.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 3000, loadbalance = "leastactive")
public class ArticleServiceImpl implements ArticleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleDao articleDao;

    @Override
    public Article getAbout() {
        return articleDao.getAbout();
    }

    @Override
    public OperationResult updateAbout(String content) {
        OperationResult operationResult = new OperationResult();

        int result = articleDao.updateAbout(content);
        if (result == 1) {
            operationResult.setSuccess(true);
            operationResult.setInfo("更新成功");
        } else {
            LOGGER.error("update about error");
            operationResult.setSuccess(false);
            operationResult.setInfo("更新失败");
        }
        return operationResult;
    }

    @Override
    public List<Article> searchArticles(String key) {
        return articleDao.getArticlesByKey(key);
    }

    @Override
    public List<Article> pagination(Page page) {
        return articleDao.getArticlesByRange(page);
    }

    @Override
    public Article getPreArticle(int articleId) {
        Article article = articleDao.getPreArticle(articleId);
        if (article == null) {
            return new Article(-1, "这已经是第一篇了");
        } else {
            return article;
        }
    }

    @Override
    public Article getNextArticle(int articleId) {
        Article article = articleDao.getNextArticle(articleId);
        if (article == null) {
            return new Article(-1, "这已经是最后一篇了");
        } else {
            return article;
        }
    }

    @Override
    public OperationResult<Article> getArticleByArticleId(int articleId) {
        OperationResult<Article> operationResult = new OperationResult<>();

        Article article = articleDao.getArticleByArticleId(articleId);
        if (article == null) {
            LOGGER.error("get article error, article {}", article);
            operationResult.setSuccess(false);
            operationResult.setInfo("该文章不存在");
        } else {
            operationResult.setSuccess(true);
            operationResult.setData(article);
        }
        return operationResult;
    }

    @Override
    public List<Article> getArticles() {
        return articleDao.getArticles();
    }

    @Override
    public List<Article> getArticlesByCategoryId(int categoryId) {
        return articleDao.getArticlesByCategoryId(categoryId);
    }

    @Override
    public List<Article> getRecentArticles() {
        return articleDao.getRecentArticlesTitle();
    }

    @Override
    public List<Article> getMostViewedArticles() {
        return articleDao.getArticlesByClicks();
    }

    @Override
    public OperationResult updateArticle(Article article) {
        OperationResult or = new OperationResult();

        int result = articleDao.updateArticle(article);
        if (result != 1) {
            LOGGER.error("update article error, article {}", article);
            or.setSuccess(false);
            or.setInfo("修改失败");
        } else {
            or.setSuccess(true);
            or.setInfo("修改成功");
        }
        return or;
    }

    @Override
    public OperationResult insertArticle(Article article) {
        OperationResult or = new OperationResult();

        int result = articleDao.insertArticle(article);
        if (result != 1) {
            LOGGER.error("insert article error, article {}", article);
            or.setSuccess(false);
            or.setInfo("保存失败");
        } else {
            or.setSuccess(true);
            or.setInfo("保存成功");
        }
        return or;
    }

    @Override
    public OperationResult deleteArticle(int articleId) {
        OperationResult or = new OperationResult();

        int result = articleDao.deleteArticle(articleId);
        if (result != 1) {
            LOGGER.error("delete article error, articleId {}", articleId);
            or.setSuccess(false);
            or.setInfo("删除失败");
        } else {
            or.setSuccess(true);
            or.setInfo("删除成功");
        }
        return or;
    }

    @Override
    public OperationResult addClicks(int articleId) {
        OperationResult or = new OperationResult();

        int result = articleDao.addClicks(articleId);
        if (result != 1) {
            LOGGER.error("add article clicks error, articleId {}", articleId);
            or.setSuccess(false);
            or.setInfo("点击量增加失败");
        } else {
            or.setSuccess(true);
            or.setInfo("点击量增加成功");
        }
        return or;
    }

    @Override
    public int countArticleNum() {
        return articleDao.countArticleNum();
    }

    @Override
    public int countArticleNumByCategoryId(int categoryId) {
        return articleDao.countArticleNumByCategoryId(categoryId);
    }
}
