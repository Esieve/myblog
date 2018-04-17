package com.esieve.article.dao;

import com.esieve.article.bean.Article;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by 张秦遥 on 2017/6/26/0026.
 */
@Repository
public class RedisDao {
    @Autowired
    private JedisPool jedisPool;

    private RuntimeSchema<Article> schema = RuntimeSchema.createFrom(Article.class);

    public Article getArticleByIdFromCache(int articleId) {
        Jedis jedis = jedisPool.getResource();
        String key = "article" + articleId;
        byte[] bytes = jedis.get(key.getBytes());
        if (bytes != null) {
            Article article = schema.newMessage();
            ProtostuffIOUtil.mergeFrom(bytes, article, schema);
            return article;
        }
        jedis.close();
        return null;
    }

    public String saveArticleToCache(Article article) {
        Jedis jedis = jedisPool.getResource();
        String key = "article" + article.getArticleId();
        byte[] bytes = ProtostuffIOUtil.toByteArray(article, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        String result = jedis.setex(key.getBytes(), 60 * 60, bytes);
        jedis.close();
        return result;
    }
}
