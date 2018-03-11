package com.esieve.category.service;

import com.esieve.category.bean.Category;
import com.esieve.common.bean.OperationResult;

import java.util.List;

public interface CategoryService {
    OperationResult<List<Category>> getCategories();

    OperationResult<Category> getCategoryByCategoryId(int categoryId);

    OperationResult insertCategory(String categoryName);

    OperationResult updateCategory(Category category);

    OperationResult deleteCategory(int categoryId);
}
