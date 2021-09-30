package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Account;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Category;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteCategoryAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1254575994729199914L;
	
	private int catId;
	private List<Category> categories;

	public String execute() throws Exception {
		System.out.println("ATTEMPT TO DELETE");
		String res = "input";
		
		Map<String, Object> session = ActionContext.getContext().getSession();
		Account user = (Account) session.get("webshop_user");
		System.out.println("ATTEMPT TO DELETE");
		if(user != null && (user.getPermission() == 0 )) {

			// Helper inserts new Category in DB:
			CategoryManager categoryManager = new CategoryManagerImpl();
			System.out.println("ATTEMPT TO DELETE");
			categoryManager.delCategoryById(catId);

			//categories = categoryManager.getCategories();
				
			res = "success";

		}
		
		return res;
		
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
}
