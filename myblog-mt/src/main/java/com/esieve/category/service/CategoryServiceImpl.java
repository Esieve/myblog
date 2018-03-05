package com.esieve.category.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.esieve.category.bean.Category;
import com.esieve.category.dao.CategoryDao;
import com.esieve.common.bean.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(timeout = 300, loadbalance = "leastactive")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategories() {
        return categoryDao.getCategories();
    }

    @Override
    public Category getCategoryByCategoryId(int categoryId) {
        return categoryDao.getCategoryByCategoryId(categoryId);
    }

    @Override
    public OperationResult insertCategory(String categoryName) {
        int result = categoryDao.insertCategory(categoryName);

        OperationResult operationResult = new OperationResult();
        if (result == 1) {
            operationResult.setSuccess(true);
        } else {
            operationResult.setSuccess(false);
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
        }
        return operationResult;
    }
}
