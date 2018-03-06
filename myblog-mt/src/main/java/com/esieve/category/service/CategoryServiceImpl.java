package com.esieve.category.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.esieve.category.bean.Category;
import com.esieve.category.dao.CategoryDao;
import com.esieve.common.bean.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 300, loadbalance = "leastactive")
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public Category getCategoryByCategoryId(int categoryId) {
        Category category = categoryDao.getCategoryByCategoryId(categoryId);
        if (category == null) {
            LOGGER.warn("get category error, categoryId {}", categoryId);
        }
        return category;
    }

    @Override
    public OperationResult insertCategory(String categoryName) {
        int result = categoryDao.insertCategory(categoryName);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
            LOGGER.warn("insert category error, categoryName {}", categoryName);
        }
        return operationResult;
    }

    @Override
    public OperationResult updateCategory(Category category) {
        int result = categoryDao.updateCategory(category);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
            LOGGER.warn("update category error, category {}", category);
        }
        return operationResult;
    }

    @Override
    public OperationResult deleteCategory(int categoryId) {
        int result = categoryDao.deleteCategory(categoryId);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
            LOGGER.warn("delete category error, categoryId {}", categoryId);
        }
        return operationResult;
    }
}
