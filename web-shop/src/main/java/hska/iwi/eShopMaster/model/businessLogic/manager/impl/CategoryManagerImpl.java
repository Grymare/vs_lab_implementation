package hska.iwi.eShopMaster.model.businessLogic.manager.impl;


import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.CategoryManagerREST;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Category;
import java.util.List;

public class CategoryManagerImpl implements CategoryManager{
	private CategoryManagerREST helper;
	
	public CategoryManagerImpl() {
		helper = new CategoryManagerREST();
	}

	public List<Category> getCategories() {
		return helper.getCategories();
	}

	public Category getCategory(int id) {
		//return helper.getObjectById(id);
		return null;
	}

	public Category getCategoryByName(String name) {
		//return helper.getObjectByName(name);
		return null;
	}

	public void addCategory(String name) {
		//Category cat = new Category(name);
		//helper.saveObject(cat);
		helper.addCategory(name);
	}

	public void delCategory(Category cat) {
	
	// 		Products are also deleted because of relation in Category.java 
		helper.delCategory(cat);
	}

	public void delCategoryById(int id) {
		
		helper.delCategoryById(id);
	}
}
