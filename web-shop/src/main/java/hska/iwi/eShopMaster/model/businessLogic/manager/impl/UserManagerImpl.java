package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.UserManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.RoleDAO;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.UserDAO;
//import hska.iwi.eShopMaster.model.database.dataobjects.Role;
//import hska.iwi.eShopMaster.model.database.dataobjects.User;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Account;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.UserManagerREST;

/**
 * 
 * @author knad0001
 */

public class UserManagerImpl implements UserManager {
	//UserDAO helper;
	UserManagerREST helper;
	public UserManagerImpl() {
		helper = new UserManagerREST();
	}

	
	public void registerUser(String username, String name, String lastname, String password, int permission) {

		Account user = new Account(username, name, lastname, password, permission);

		helper.registerUser(user);

		//helper.saveObject(user);
	}

	
	public Account getUserByUsername(String username) {
		if (username == null || username.equals("")) {
			return null;
		}
		return helper.getUserByUsername(username);
	}

	public boolean deleteUserById(int id) {
		Account user = new Account();
		user.setId(id);
		//helper.deleteObject(user);
		return true;
	}
	/*
	public Role getRoleByLevel(int level) {
		RoleDAO roleHelper = new RoleDAO();
		return roleHelper.getRoleByLevel(level);
	}
	*/

	public boolean doesUserAlreadyExist(String username) {
		System.out.println("CHECK IF USER DOES ALREAD EXIST");
    	Account dbUser = this.getUserByUsername(username);
    	
    	if (dbUser != null){
			System.out.println("USER DOES ALREADY EXIST");
    		return true;
    	}
    	else {
			System.out.println("USER DOES NOT ALREADY EXIST");
    		return false;
    	}
		
	}
	

	public boolean validate(Account user) {
		if (user.getFirstname().isEmpty() || user.getPassword().isEmpty() || user.getPermission() != 1 || user.getLastname() == null || user.getUsername() == null) {
			return false;
		}
		
		return true;
	}

}
