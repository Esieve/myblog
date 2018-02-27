package com.esieve.category.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.esieve.category.bean.Category;
import com.esieve.category.dao.CategoryDao;
import com.esieve.common.bean.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategories() {
        return null;
    }

    @Override
    public Category getCategoryByCategoryId(int categoryId) {
        return null;
    }

    @Override
    public OperationResult insertCategory(String categoryName) {
        return null;
    }

    @Override
    public OperationResult updateCategory(Category category) {
        return null;
    }

    @Override
    public OperationResult deleteCategory(int categoryId) {
        return null;
    }
}
