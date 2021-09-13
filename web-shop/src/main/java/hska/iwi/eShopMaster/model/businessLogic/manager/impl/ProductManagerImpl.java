package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Product;
//import hska.iwi.eShopMaster.model.database.dataAccessObjects.ProductDAO;
//import hska.iwi.eShopMaster.model.database.dataobjects.Category;
//import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.ProductManagerREST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductManagerImpl implements ProductManager {
	//private ProductDAO helper;
	ProductManagerREST helper;


	public ProductManagerImpl() {
		helper = new ProductManagerREST();
	}

	public List<Product> getProducts() {
		return helper.getProducts();//.getObjectList();
	}
	
	
	public List<Product> getProductsForSearchValues(String searchDescription,
			Double searchMinPrice, Double searchMaxPrice) {	
		return null;
		//return new ProductDAO().getProductListByCriteria(searchDescription, searchMinPrice, searchMaxPrice);
	}

	public Product getProductById(int id) {
		return null;
		//return helper.getObjectById(id);
	}

	public Product getProductByName(String name) {
		return null;
		//return helper.getObjectByName(name);
	}
	
	public int addProduct(String name, double price, int categoryId, String details) {
		return 0;
		/*int productId = -1;
		
		CategoryManager categoryManager = new CategoryManagerImpl();
		Category category = categoryManager.getCategory(categoryId);
		
		if(category != null){
			Product product;
			if(details == null){
				product = new Product(name, price, category);	
			} else{
				product = new Product(name, price, category, details);
			}
			
			helper.saveObject(product);
			productId = product.getId();
		}
			 
		return productId;*/
	}
	

	public void deleteProductById(int id) {
		//helper.deleteById(id);
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
