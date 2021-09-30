package hska.iwi.eShopMaster.model.businessLogic.manager.REST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import org.apache.http.annotation.ThreadingBehavior;

//import com.fasterxml.jackson.databind.ObjectMapper;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

public class ProductManagerREST {
    @Autowired
    private LoadBalancerClient loadBalancer;
    private REST_Templates rest_templates = new REST_Templates();

    public List<ProductOutput> getProducts() {
        // List<Product> products = new ArrayList<Product>();
        System.out.println(rest_templates.get_composite_url());
        try {
            OAuth2RestTemplate restTemplateComposite = rest_templates.get_rest_template_composite();
            String productsString = restTemplateComposite.getForObject(rest_templates.get_composite_url() + "/product",
                    String.class);
            System.out.println(productsString);

            Gson gson = new Gson();
            ProductOutput[] productArray = gson.fromJson(productsString, ProductOutput[].class);
            System.out.println("GET-PRODUCT-1");
            for (ProductOutput thing : productArray) {
                System.out.println(thing.toString());
            }

            return Arrays.asList(productArray);

        } catch (Exception e) {
            System.out.println("GETTING products failed! in getProducts");
            System.out.println(e);
        }

        return null;// User.withUsername("fallback").username("fallback").password(encoder.encode("supersecret")).roles("ADMIN").build();
    }

    /*
     * public Product getProductById(int id){
     * 
     * }
     * 
     * public Product getProductByName(String name){
     * 
     * }
     */
    public int addProduct(Product product) {
        System.out.println(rest_templates.get_product_url());
        System.out.println("add product");
        try {
            OAuth2RestTemplate restTemplateProduct = rest_templates.get_rest_template_product();
            ResponseEntity<String> productsString = restTemplateProduct.postForEntity( rest_templates.get_product_url() + "/product"
                    + "?name=" + product.getName() + "&price=" + product.getPrice() + "&categoryId=" + product.getCategoryId() + "&details=" + product.getDetails(), null, String.class);
            System.out.println(productsString);
        } catch (Exception e) {
            System.out.println("ADDING products failed!");
            System.out.println(e);
        }

        return 1;
    }

    public List<ProductOutput> getProductsForSearchValues(String searchValue, Double searchMinPrice,
            Double searchMaxPrice) {

        System.out.println("getProductsForSearchValues-0");
        System.out.println(rest_templates.get_composite_url());
        System.out.println("?searchtext=" + searchValue + "&min=" + searchMinPrice + "&max=" + searchMaxPrice);

        try {
            OAuth2RestTemplate restTemplateComposite = rest_templates.get_rest_template_composite();

            System.out.println("getProductsForSearchValues-1");

            String productsString = restTemplateComposite.getForObject(rest_templates.get_composite_url() + "/product/"
                    + "?searchtext=" + searchValue + "&min=" + searchMinPrice + "&max=" + searchMaxPrice, String.class);

            System.out.println(productsString);

            Gson gson = new Gson();
            ProductOutput[] productArray = gson.fromJson(productsString, ProductOutput[].class);
            System.out.println("getProductsForSearchValues-2");
            for (ProductOutput thing : productArray) {
                System.out.println(thing.toString());
            }

            return Arrays.asList(productArray);

        } catch (Exception e) {
            System.out.println("GETTING getProductsForSearchValues failed! in getProducts");
            System.out.println(e);
        }

        return null;// User.withUsername("fallback").username("fallback").password(encoder.encode("supersecret")).roles("ADMIN").build();

    }

    /*
     * public boolean deleteProductsByCategoryId(int categoryId){
     * 
     * }
     * 
     * public void deleteProductById(int id){
     * 
     * }
     */
}
