package com.esieve.category.dao;

import com.esieve.category.bean.Category;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:service.xml")
public class CategoryDaoTest {

    @Autowired
    private CategoryDao categoryDao;

    private int categoryId = 7;

    @Test
    public void insertCategory() {
        int result = categoryDao.insertCategory("test");
        Assert.assertEquals(1, result);
    }

    @Test
    public void getCategories() {
        List<Category> categories = categoryDao.getCategories();
        Assert.assertNotNull(categories);
        Assert.assertNotEquals(0, categories.size());
    }

    @Test
    public void getCategoryByCategoryId() {
        Category category = categoryDao.getCategoryByCategoryId(categoryId);
        Assert.assertNotNull(category);
        Assert.assertEquals(categoryId, category.getCategoryId());

        category = categoryDao.getCategoryByCategoryId(-1);
        Assert.assertNull(category);
    }

    @Test
    public void updateCategory() {
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setCategoryName("test2");
        int result = categoryDao.updateCategory(category);
        Assert.assertEquals(1, result);
    }

    @Test
    public void deleteCategory() {
        int result = categoryDao.deleteCategory(categoryId);
        Assert.assertEquals(1, result);
    }
}