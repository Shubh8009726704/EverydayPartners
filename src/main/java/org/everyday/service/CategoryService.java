package org.everyday.service;

import java.util.List;

import org.everyday.model.Category;
import org.springframework.data.domain.Page;


public interface CategoryService {

	public Category saveCategory(Category category);

	public Boolean existCategory(String name);

	public List<Category> getAllCategory();

	
	 public Boolean deleteCategory(int id);
	 
	  public Category getCategoryById(int id);
	  
	  public List<Category> getAllActiveCategory();
	  
	  public Page<Category> getAllCategorPagination(Integer pageNo,Integer
	  pageSize);
	 

}