package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Account;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.ProductOutput;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;
import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ListAllProductsAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -94109228677381902L;
	
	Account user;
	private List<ProductOutput> products;
	
	public String execute() throws Exception{
		String result = "input";
		System.out.println("listallaction-1");
		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (Account) session.get("webshop_user");
		
		if(user != null){
			System.out.println("list all products!");
			ProductManager productManager = new ProductManagerImpl();
			System.out.println("listallaction-2");
			this.products = productManager.getProducts();
			System.out.println(this.products);
			result = "success";
		}
		
		return result;
	}
	
	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}
	
	public List<ProductOutput> getProducts() {
		return products;
	}

	public void setProducts(List<ProductOutput> products) {
		this.products = products;
	}

}
