package com.esieve.category.dao;

import com.esieve.category.bean.Category;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by 77239 on 2017/3/31/0031.
 */
public interface CategoryDao {

    @Select("SELECT * FROM category")
    List<Category> getCategories();

    @Select("SELECT * FROM category WHERE category_id=#{categoryId}")
    Category getCategoryByCategoryId(int categoryId);

    @Insert("INSERT INTO category VALUES (NULL ,#{categoryName})")
    int insertCategory(String categoryName);

    @Update("UPDATE category SET category_name=#{categoryName} WHERE category_id=#{categoryId}")
    int updateCategory(Category category);

    @Delete("DELETE FROM category WHERE category_id=#{categoryId}")
    int deleteCategory(int categoryId);

}
