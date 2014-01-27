package com.vmgs.dao;

import java.util.List;

import com.vmgs.entity.Category;


public interface CategoryDao {
	public Category getCategoryById(Integer id);

	public void addCategory(Category category);

	public List<Category> listCategory();

	public void removeCategory(Integer id);
	
	public Category getCategoryByName(String name);
	
	
}