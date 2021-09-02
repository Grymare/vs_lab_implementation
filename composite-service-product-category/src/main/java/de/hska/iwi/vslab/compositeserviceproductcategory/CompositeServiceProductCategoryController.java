package de.hska.iwi.vslab.compositeserviceproductcategory;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.common.AuthenticationScheme;

@RestController
@Component
public class CompositeServiceProductCategoryController {

    //private JSONArray j_product_cache = new JSONArray();
    //private JSONArray j_category_cache = new JSONArray();
    JSONArray j_prod_cat_array = new JSONArray();

    @Autowired
    private LoadBalancerClient loadBalancer;

    private String get_product_url(){
        //obsolet der service name kann auch direkt angesprochen werden 
        ServiceInstance serviceInstance=loadBalancer.choose("core-service-product");
        System.out.println(serviceInstance.getUri());
        return serviceInstance.getUri().toString();
    }

    private String get_category_url(){
        ServiceInstance serviceInstance=loadBalancer.choose("core-service-category");
        System.out.println(serviceInstance.getUri());
        return serviceInstance.getUri().toString();
    }

    private OAuth2RestTemplate get_rest_template_product(){

        ClientCredentialsResourceDetails resourceDetailsProduct  = new ClientCredentialsResourceDetails();
        resourceDetailsProduct.setAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsProduct.setClientAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsProduct.setId("1");
        resourceDetailsProduct.setTokenName("Core_Product");
        resourceDetailsProduct.setAccessTokenUri("http://172.18.0.11:8300/oauth/token");
        resourceDetailsProduct.setClientId("coreProductId");
        resourceDetailsProduct.setClientSecret("coreProductSecret");
        resourceDetailsProduct.setGrantType("client_credentials");
        resourceDetailsProduct.setScope(Arrays.asList("read", "write"));
        AccessTokenRequest atrProduct = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resourceDetailsProduct, new DefaultOAuth2ClientContext(atrProduct));
    }

    private OAuth2RestTemplate get_rest_template_category(){

        ClientCredentialsResourceDetails resourceDetailsCategory  = new ClientCredentialsResourceDetails();
        resourceDetailsCategory.setAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsCategory.setClientAuthenticationScheme(AuthenticationScheme.header);
        resourceDetailsCategory.setId("1");
        resourceDetailsCategory.setTokenName("Core_Category");
        resourceDetailsCategory.setAccessTokenUri("http://172.18.0.11:8300/oauth/token");
        resourceDetailsCategory.setClientId("coreCategoryId");
        resourceDetailsCategory.setClientSecret("coreCategorySecret");
        resourceDetailsCategory.setGrantType("client_credentials");
        resourceDetailsCategory.setScope(Arrays.asList("read", "write"));
        AccessTokenRequest atrCategory = new DefaultAccessTokenRequest();
        return new OAuth2RestTemplate(resourceDetailsCategory, new DefaultOAuth2ClientContext(atrCategory));
    }


    /**
     * Gibt alle Kategorien zurück die es in der Datenbank gibt Die Rückgabe erfolgt
     * über eine Liste (müssen wir zu XML wandeln)
     */
    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getProducts_fallback", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public ResponseEntity<String> getProducts() {
        System.out.println("HERE1");
        // Load Balancer
        String url_product = get_product_url();
        String url_category = get_category_url();
        System.out.println("HERE2");
        // Load Balancer

        // SECURITY
        OAuth2RestTemplate restTemplateProduct = get_rest_template_product();
        OAuth2RestTemplate restTemplateCategory = get_rest_template_category();
        // SECURITY

        //RestTemplate restTemplate = new RestTemplate();
        System.out.println("HERE3");
        ResponseEntity<String> productResponse = restTemplateProduct.getForEntity(url_product + "/product", String.class);
        System.out.println("HERE3.1");
        ResponseEntity<String> categoryResponse = restTemplateCategory.getForEntity(url_category + "/category", String.class);
        System.out.println("HERE4");
        JSONArray j_product_array = new JSONArray(productResponse.getBody().toString());
        JSONArray j_category_array = new JSONArray(categoryResponse.getBody().toString());
        System.out.println("HERE5");
        //j_product_cache = j_product_array;
        //j_category_cache = j_category_array;

        ArrayList<Category> category_list = new ArrayList<>();

        for (int i = 0; i < j_category_array.length(); i++) {
            JSONObject j_cat = j_category_array.getJSONObject(i);

            Category cat = new Category(Integer.valueOf(j_cat.get("id").toString()), (String) j_cat.get("name"));
            category_list.add(cat);
        }

        for (int i = 0; i < j_product_array.length(); i++) {
            JSONObject j_prod = j_product_array.getJSONObject(i);
            int catID = Integer.valueOf(j_prod.get("categoryID").toString());
            String catName = "";

            // find category name to corresponding category id
            for (Category cat : category_list) {
                if (cat.getId() == catID) {
                    catName = cat.getName();
                    break;
                }
            }

            j_prod.put("categoryName", catName);
        }

        j_prod_cat_array = j_product_array;

        System.out.println("==================================");
        System.out.println(j_product_array.toString());

        return new ResponseEntity<String>(j_product_array.toString(), HttpStatus.OK);
    }

    @HystrixCommand // HystrixCommand kann entfernt werden
    public ResponseEntity<String> getProducts_fallback() {
        System.out.println("getProducts_fallback");

        if (j_prod_cat_array.isEmpty()){
            return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
        }else{
            return new ResponseEntity<String>(j_prod_cat_array.toString(), HttpStatus.I_AM_A_TEAPOT);
        }
    }

    /**
     * Gibt das Produkt der gesuchten productID zurück Die Rückgabe erfolgt über
     * eine Liste (müssen wir zu XML wandeln)
     */
    @RequestMapping(value = "/product/{productID}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getProduct_fallback", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })    
    public ResponseEntity<String> getProduct(@PathVariable Integer productID) {


        String url_product = get_product_url();
        String url_category = get_category_url();
        
        OAuth2RestTemplate restTemplateProduct = get_rest_template_product();
        OAuth2RestTemplate restTemplateCategory = get_rest_template_category();

        ResponseEntity<String> productResponse = restTemplateProduct.getForEntity(url_product + "/product/" + productID,
                String.class);
        JSONObject j_product = new JSONObject(productResponse.getBody().toString());

        if (j_product.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        int categoryID = Integer.valueOf(j_product.get("categoryID").toString());
        ResponseEntity<String> categoryResponse = restTemplateCategory.getForEntity(url_category + "/category/" + categoryID,
                String.class);
        JSONObject j_category = new JSONObject(categoryResponse.getBody().toString());
        if (j_category.isEmpty()) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }

        String catName = (String) j_category.get("name");
        j_product.put("categoryName", catName);

        return new ResponseEntity<String>(j_product.toString(), HttpStatus.OK);
    }

    @HystrixCommand
    public ResponseEntity<String> getProduct_fallback(@PathVariable Integer productID) {
        System.out.println("getProduct_fallback");
        if (j_prod_cat_array.isEmpty()){
            return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
        }

        for (int i = 0; i < j_prod_cat_array.length(); i++) {
            JSONObject j_prod = j_prod_cat_array.getJSONObject(i);

            if (Integer.valueOf(j_prod.get("id").toString()) == productID){
                return new ResponseEntity<String>(j_prod.toString(), HttpStatus.OK);
            }
        }

        return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);  

    }

    /**
     * Löscht eine Kategorie und die dazugehörigen Produkte.
     */
    @HystrixCommand(fallbackMethod = "deleteCategory_fallback", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })  
    @RequestMapping(value = "/category/{categoryID}", method = RequestMethod.DELETE)
    public HttpStatus deleteCategory(@PathVariable Integer categoryID) {

        String url_product = get_product_url();
        String url_category = get_category_url();

        OAuth2RestTemplate restTemplateProduct = get_rest_template_product();
        OAuth2RestTemplate restTemplateCategory = get_rest_template_category();

        ResponseEntity<String> productResponse = restTemplateProduct.getForEntity(url_product + "/product", String.class);
        JSONArray j_product_array = new JSONArray(productResponse.getBody().toString());
        

        //Alternativ kann auch nur geprüft werden, ob es noch produkte zu der kategorie gibt
        //Falls ja wird diese nicht gelöscht
        //der client selbst muss diese produkte dann zuerst entfernen
        for (int i = 0; i < j_product_array.length(); i++) {
            JSONObject j_product = j_product_array.getJSONObject(i);
            int catID = Integer.valueOf(j_product.get("categoryID").toString());

            // Delete product
            if (catID == categoryID) {
                int prodID = Integer.valueOf(j_product.get("id").toString());
                restTemplateProduct.delete(url_product + "/product/" + prodID);
            }
        }

        // Delete category
        restTemplateCategory.delete(url_category + "/category/" + categoryID);

        return HttpStatus.OK;
    }

    @HystrixCommand
    public HttpStatus deleteCategory_fallback(@PathVariable Integer categoryID) {
        System.out.println("deleteCategory_fallback");
        return HttpStatus.I_AM_A_TEAPOT;
    }


    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getProductsFiltered_fallback", commandProperties = {
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })  
    // Searches for product with specified search criteria
    public ResponseEntity<String> getProductsFiltered(
            @RequestParam(required = false, defaultValue = "") String searchtext,
            @RequestParam(required = false, defaultValue = "0") double min,
            @RequestParam(required = false, defaultValue = "-1") double max) {
        
        String url_product = get_product_url();
        String url_category = get_category_url();

        OAuth2RestTemplate restTemplateProduct = get_rest_template_product();
        OAuth2RestTemplate restTemplateCategory = get_rest_template_category();

        String uri_param = "?searchtext=" + searchtext + "&min=" + Double.toString(min) + "&max="
                + Double.toString(max);

        ResponseEntity<String> productResponse = restTemplateProduct.getForEntity(url_product + "/product/" + uri_param,
                String.class);
        ResponseEntity<String> categoryResponse = restTemplateCategory.getForEntity(url_category + "/category", String.class);

        JSONArray j_product_array = new JSONArray(productResponse.getBody().toString());
        JSONArray j_category_array = new JSONArray(categoryResponse.getBody().toString());

        System.out.println(j_product_array);
        System.out.println(j_category_array);

        ArrayList<Category> category_list = new ArrayList<>();

        // filter for categories which are present in the filtered products
        for (int i = 0; i < j_category_array.length(); i++) {
            JSONObject j_cat = j_category_array.getJSONObject(i);

            Category cat = new Category(Integer.valueOf(j_cat.get("id").toString()), (String) j_cat.get("name"));
            category_list.add(cat);
        }

        for (int i = 0; i < j_product_array.length(); i++) {
            JSONObject j_prod = j_product_array.getJSONObject(i);
            int catID = Integer.valueOf(j_prod.get("categoryID").toString());
            String catName = "";

            // find category name to corresponding category id
            for (Category cat : category_list) {
                if (cat.getId() == catID) {
                    catName = cat.getName();
                    break;
                }
            }

            j_prod.put("categoryName", catName);
        }
        System.out.println("==================================");
        System.out.println(j_product_array.toString());

        return new ResponseEntity<String>(j_product_array.toString(), HttpStatus.OK);
    }


    @HystrixCommand
    public ResponseEntity<String> getProductsFiltered_fallback(
            @RequestParam(required = false, defaultValue = "") String searchtext,
            @RequestParam(required = false, defaultValue = "0") double min,
            @RequestParam(required = false, defaultValue = "-1") double max) {
        System.out.println("getProductsFiltered_fallback");

        return new ResponseEntity<String>(HttpStatus.I_AM_A_TEAPOT);
    }

}