package hska.iwi.eShopMaster.model.businessLogic.manager;

//import hska.iwi.eShopMaster.model.database.dataobjects.Role;
import hska.iwi.eShopMaster.model.database.dataobjects.User;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Account;


public interface UserManager {
    
    public void registerUser(String username, String name, String lastname, String password, int permission);
    
    public Account getUserByUsername(String username);
    
    public boolean deleteUserById(int id);
    
    //public Role getRoleByLevel(int level);
    
    public boolean doesUserAlreadyExist(String username);
}
