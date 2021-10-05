package hska.iwi.eShopMaster.controller;

//import hska.iwi.eShopMaster.model.database.dataAccessObjects.ProductDAO;
//import hska.iwi.eShopMaster.model.database.dataobjects.User;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Account;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Category;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.ProductManagerImpl;

public class DeleteProductAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3666796923937616729L;

	private int id;

	public String execute() throws Exception {
		
		String res = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		Account user = (Account) session.get("webshop_user");
		
		if(user != null && (user.getPermission() == 0 )) {
			
			ProductManager productManager = new ProductManagerImpl();
			productManager.deleteProductById(id);

			//new ProductDAO().deleteById(id);
			res = "success";
		}
		
		return res;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
