package hska.iwi.eShopMaster.model.businessLogic.manager.REST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;



public class ProductManagerREST{
    @Autowired
    private LoadBalancerClient loadBalancer;
    private REST_Templates rest_templates  = new REST_Templates();


    public List<Product> getProducts(){
        //List<Product> products = new ArrayList<Product>();
        System.out.println("GET-PRODUCT-1");
        System.out.println(rest_templates.get_composite_url());
        try {
            System.out.println("GET-PRODUCT-2");
            OAuth2RestTemplate restTemplateComposite = rest_templates.get_rest_template_composite();

            System.out.println("GET-PRODUCT-3");
            String productsString = restTemplateComposite.getForObject(rest_templates.get_composite_url() + "/product", String.class);


            System.out.println("GET-PRODUCT-4");
            System.out.println(productsString);
            System.out.println("GET-PRODUCT-5");
            //System.out.println("Composite in loadByCompositename: " + composite.getCompositename());
            //return Arrays.asList(productsString);
            return null;

        } catch (Exception e) {
            System.out.println("GETTING products failed! in getProducts");
            System.out.println(e);
        }

        return null;//User.withUsername("fallback").username("fallback").password(encoder.encode("supersecret")).roles("ADMIN").build();
    }

    /*
    public Product getProductById(int id){
        
    }

    public Product getProductByName(String name){
        
    }

    public int addProduct(String name, double price, int categoryId, String details){
        
    }

    public List<Product> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice){

    }

    public boolean deleteProductsByCategoryId(int categoryId){

    }

    public void deleteProductById(int id){

    }
    */


}

