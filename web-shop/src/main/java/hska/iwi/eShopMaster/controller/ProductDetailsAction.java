package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
//import hska.iwi.eShopMaster.model.database.dataobjects.Product;
//import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Account;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.ProductOutput;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
public class ProductDetailsAction extends ActionSupport {
	
	private Account user;
	private int id;
	private String searchValue;
	private Integer searchMinPrice;
	private Integer searchMaxPrice;
	private ProductOutput product;

	/**
	 * 
	 */
	private static final long serialVersionUID = 7708747680872125699L;

	public String execute() throws Exception {

		String res = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		Account user = (Account) session.get("webshop_user");
		
		if(user != null) {
			ProductManager productManager = new ProductManagerImpl();
			product = productManager.getProductById(id);
			
			res = "success";			
		}
		
		return res;		
	}
	
	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSearchValue() {
		return searchValue;
	}

	public void setSearchValue(String searchValue) {
		this.searchValue = searchValue;
	}

	public Integer getSearchMinPrice() {
		return searchMinPrice;
	}

	public void setSearchMinPrice(Integer searchMinPrice) {
		this.searchMinPrice = searchMinPrice;
	}

	public Integer getSearchMaxPrice() {
		return searchMaxPrice;
	}

	public void setSearchMaxPrice(Integer searchMaxPrice) {
		this.searchMaxPrice = searchMaxPrice;
	}

	public ProductOutput getProduct() {
		return product;
	}

	public void setProduct(ProductOutput product) {
		this.product = product;
	}
}
