package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Product;
//import hska.iwi.eShopMaster.model.database.dataAccessObjects.ProductDAO;
//import hska.iwi.eShopMaster.model.database.dataobjects.Category;
//import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.ProductManagerREST;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.ProductOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductManagerImpl implements ProductManager {
	//private ProductDAO helper;
	ProductManagerREST helper;


	public ProductManagerImpl() {
		helper = new ProductManagerREST();
	}

	public List<ProductOutput> getProducts() {
		return helper.getProducts();//.getObjectList();
	}
	
	
	public List<ProductOutput> getProductsForSearchValues(String searchDescription,
			Double searchMinPrice, Double searchMaxPrice) {	
		return helper.getProductsForSearchValues(searchDescription, searchMinPrice, searchMaxPrice);
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
		int productId = -1;
		
		//CategoryManager categoryManager = new CategoryManagerImpl();
		//Category detailscategory = categoryManager.getCategory(categoryId);
		
	
		Product product;
		if(details == null){
			product = new Product(name, price, categoryId);	
		} else{
			product = new Product(name, price, details, categoryId );
		}
		
		helper.addProduct(product);
		productId = product.getId();
			 
		return productId;
	}
	

	public void deleteProductById(int id) {
		//helper.deleteById(id);
		helper.deleteProductById(id);
		


	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
