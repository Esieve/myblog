package com.esieve.category.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.esieve.category.bean.Category;
import com.esieve.category.dao.CategoryDao;
import com.esieve.common.bean.OperationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 3000, loadbalance = "leastactive")
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public OperationResult<Category> getCategoryByCategoryId(int categoryId) {
        OperationResult<Category> result = new OperationResult<>();

        Category category = categoryDao.getCategoryByCategoryId(categoryId);
        if (category == null) {
            LOGGER.error("get category error, categoryId {}", categoryId);
            result.setInfo("未找到该类别");
        } else {
            result.setSuccess(true);
            result.setData(category);
        }
        return result;
    }

    @Override
    public OperationResult insertCategory(String categoryName) {
        int result = categoryDao.insertCategory(categoryName);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
            operationResult.setInfo("插入成功");
        } else {
            LOGGER.error("insert category error, categoryName {}", categoryName);
            operationResult.setInfo("插入失败");
        }
        return operationResult;
    }

    @Override
    public OperationResult updateCategory(Category category) {
        int result = categoryDao.updateCategory(category);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
            operationResult.setInfo("更新成功");
        } else {
            LOGGER.error("update category error, category {}", category);
            operationResult.setInfo("更新失败");
        }
        return operationResult;
    }

    @Override
    public OperationResult deleteCategory(int categoryId) {
        int result = categoryDao.deleteCategory(categoryId);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
            operationResult.setInfo("删除成功");
        } else {
            LOGGER.error("delete category error, categoryId {}", categoryId);
            operationResult.setInfo("删除失败");
        }
        return operationResult;
    }
}
