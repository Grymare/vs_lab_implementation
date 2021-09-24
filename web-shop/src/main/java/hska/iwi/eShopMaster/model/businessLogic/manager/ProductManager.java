package hska.iwi.eShopMaster.model.businessLogic.manager;

import hska.iwi.eShopMaster.model.businessLogic.manager.REST.Product;
import hska.iwi.eShopMaster.model.businessLogic.manager.REST.ProductOutput;

import java.util.List;

public interface ProductManager {

	public List<ProductOutput> getProducts();

	public Product getProductById(int id);

	public Product getProductByName(String name);

	public int addProduct(String name, double price, int categoryId, String details);

	public List<ProductOutput> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice);
	
	public boolean deleteProductsByCategoryId(int categoryId);
	
    public void deleteProductById(int id);
    
	
}
