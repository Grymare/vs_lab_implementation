package hska.iwi.eShopMaster.controller;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Account;
import hska.iwi.eShopMaster.model.businessLogic.manager.impl.CategoryManagerImpl;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Category;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class InitCategorySiteAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1108136421569378914L;

	private String pageToGoTo;
	private Account user;

	private List<Category> categories;

	public String execute() throws Exception {
		
		String res = "input";

		Map<String, Object> session = ActionContext.getContext().getSession();
		user = (Account) session.get("webshop_user");
		boolean isAdmin = true;
		if(user != null && isAdmin) {

			CategoryManager categoryManager = new CategoryManagerImpl();
			this.setCategories(categoryManager.getCategories());
			
			if(pageToGoTo != null){
				if(pageToGoTo.equals("c")){
					res = "successC";	
				}
				else if(pageToGoTo.equals("p")){
					res = "successP";
				}				
			}
		}
		
		return res;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public String getPageToGoTo() {
		return pageToGoTo;
	}

	public void setPageToGoTo(String pageToGoTo) {
		this.pageToGoTo = pageToGoTo;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

}
