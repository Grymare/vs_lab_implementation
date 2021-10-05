package hska.iwi.eShopMaster.model.businessLogic.manager.REST;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.database.dataAccessObjects.CategoryDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import java.util.Arrays;
import com.google.gson.Gson;

import java.util.List;

public class CategoryManagerREST {

	@Autowired
	private LoadBalancerClient loadBalancer;
	private REST_Templates rest_templates = new REST_Templates();

	public List<Category> getCategories() {
		System.out.println(rest_templates.get_category_url());
		try {
			OAuth2RestTemplate restTemplateCategory = rest_templates.get_rest_template_category();
			String categoryString = restTemplateCategory.getForObject(rest_templates.get_category_url() + "/category",
					String.class);
			System.out.println(categoryString);

			Gson gson = new Gson();
			Category[] categoryArray = gson.fromJson(categoryString, Category[].class);
			System.out.println("GET-CATEGORY-1");
			for (Category thing : categoryArray) {
				System.out.println(thing.toString());
			}

			return Arrays.asList(categoryArray);

		} catch (Exception e) {
			System.out.println("GETTING categories failed!");
			System.out.println(e);
		}

		return null;// User.withUsername("fallback").username("fallback").password(encoder.encode("supersecret")).roles("ADMIN").build();
	}

	public Category getCategory(int id) {
		// return helper.getObjectById(id);
		return null;
	}

	public Category getCategoryByName(String name) {
		// return helper.getObjectByName(name);
		return null;
	}

	public void addCategory(String name) {
		
		List<Category> categories = getCategories();
		
		for(Category cat: categories){
			if (cat.getName().equals(name)){
				System.out.println("ADDCATEGORY - category "+name+" already exists");
				return;
			}
		}
		System.out.println("ADDCATEGORY category  "+name+" does not already exists");
		try {
			OAuth2RestTemplate restTemplateCategory = rest_templates.get_rest_template_category();
			System.out.println("ADDCATEGORY");
			ResponseEntity <String> responseString = restTemplateCategory.postForEntity(rest_templates.get_category_url() + "/category"+"?categoryName="+name, null,
					String.class);
			System.out.println(responseString);
			
			return;

		} catch (Exception e) {
			System.out.println("ADDING categories failed!");
			System.out.println(e);
		}
		
	}

	public void delCategory(Category cat) {

		// Products are also deleted because of relation in Category.java
		// helper.deleteById(cat.getId());
		
		try {
			OAuth2RestTemplate restTemplateComposite = rest_templates.get_rest_template_composite();
			System.out.println("DELCATEGORY");
			restTemplateComposite.delete(rest_templates.get_composite_url() + "/category/" + "?categoryID=" + cat.getId());		
			return;

		} catch (Exception e) {
			System.out.println("DELETING category failed!");
			System.out.println(e);
		}

	}

	public void delCategoryById(int id) {

		// Products are also deleted because of relation in Category.java
		// helper.deleteById(cat.getId());
		
		try {
			OAuth2RestTemplate restTemplateComposite = rest_templates.get_rest_template_composite();
			System.out.println("DELCATEGORY");
			restTemplateComposite.delete(rest_templates.get_composite_url() + "/category/" + id);		
			return;

		} catch (Exception e) {
			System.out.println("DELETING category failed!");
			System.out.println(e);
		}
	}
}
