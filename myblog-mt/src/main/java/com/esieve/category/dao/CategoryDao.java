package com.esieve.category.dao;

import com.esieve.category.bean.Category;

import java.util.List;

/**
 * Created by 77239 on 2017/3/31/0031.
 */
public interface CategoryDao {

    List<Category> getCategories();

    Category getCategoryByCategoryId(int categoryId);

    int insertCategory(String categoryName);

    int updateCategory(Category category);

    int deleteCategory(int categoryId);

}
