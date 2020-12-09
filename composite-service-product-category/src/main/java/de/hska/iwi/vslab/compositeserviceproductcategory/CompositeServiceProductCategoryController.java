package de.hska.iwi.vslab.compositeserviceproductcategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.client.RestTemplate;

@RestController
public class CompositeServiceProductCategoryController {

    /**
     * Gibt alle Kategorien zurück die es in der Datenbank gibt
     * Die Rückgabe erfolgt über eine Liste (müssen wir zu XML wandeln)
     */

    String url_category = "http://localhost:8771";

    String url_product = "http://localhost:8772";

    @RequestMapping(value="/product", method=RequestMethod.GET)
        public ResponseEntity<Object> getProducts() {

            RestTemplate restTemplate = new RestTemplate();
            

            ResponseEntity<String> productResponse = restTemplate.getForEntity(url_product + "/product", String.class);

            ResponseEntity<String> categoryResponse = restTemplate.getForEntity(url_category + "/category", String.class);
            

            JSONArray j_product_array = new JSONArray(productResponse.getBody().toString());
            JSONArray j_category_array = new JSONArray(categoryResponse.getBody().toString());

            System.out.println(j_product_array.toString());
            System.out.println("==================================");
            System.out.println(j_category_array.toString());
            System.out.println("==================================");

            ArrayList<Category> category_list = new ArrayList<>();

            for (int i = 0; i < j_category_array.length(); i++) {
                JSONObject j_cat =  j_category_array.getJSONObject(i);

                Category cat = new Category(
                    Integer.valueOf(j_cat.get("id").toString()),
                    (String) j_cat.get("name")
                );
                category_list.add(cat);
            }  
            
            for (int i = 0; i < j_product_array.length(); i++) {
                JSONObject j_prod =  j_product_array.getJSONObject(i);
                int catID = Integer.valueOf(j_prod.get("categoryID").toString());
                String catName = "";
                
                //find category name to corresponding category id
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
 
            return new ResponseEntity<Object>(j_product_array.toString(), HttpStatus.OK);

        }
     
     /**
      * 
    @RequestMapping(value = "/product{name, price, details, categoryId}", method = RequestMethod.POST)
    public HttpStatus postProduct(
        @RequestParam(required=false) String name, 
        @RequestParam(required=false) Double price, 
        @RequestParam(required=false) String details,
        @RequestParam(required=false) int categoryId) {

        Product prod = new Product(name, price, details, categoryId);

        productRepository.save(prod);
        return HttpStatus.OK;

    }

    @RequestMapping(value="/product/", method=RequestMethod.GET)
        public ResponseEntity<List<Product>> getProductsFiltered(
            @RequestParam(required=false, defaultValue = "") String searchtext, 
            @RequestParam(required=false, defaultValue = "0") double min, 
            @RequestParam(required=false, defaultValue = "-1") double max) {

            Iterable<Product> productIterable = productRepository.findAll(); 
            
            List<Product> productList = new ArrayList<Product>();

            for (Product produc : productIterable) {
                productList.add(produc);
            }
                                   
                        
            if( min != 0){
                System.out.println("MIN");
                System.out.println(min);
                productList = productList.stream()
                .filter(p -> p.getPrice() >= min)
                .collect(Collectors.toList());
            }
            if(max !=  -1){
                System.out.println("MAX");
                System.out.println(max);
                productList = productList.stream()
                .filter(p -> p.getPrice() <= max)
                .collect(Collectors.toList());
            }
            if(searchtext.isEmpty() == false){
                System.out.println("searchtext");
                System.out.println(searchtext);

                productList = productList.stream()
                .filter(p -> p.getName().contains(searchtext) || p.getDetails().contains(searchtext) )
                .collect(Collectors.toList());

            }
            
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }
    
    

    @RequestMapping(value = "/product/{productID}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable Integer productID) {

        
        if (productOptional.isEmpty()){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);              
        }else{
            return new ResponseEntity<Product>(productOptional.get(), HttpStatus.OK); 
        }  
    }

    @RequestMapping(value = "/category/{categoryID}", method = RequestMethod.DELETE)
    public HttpStatus deleteCategory(@PathVariable Integer categoryID) {
        //TODO Implement
        return HttpStatus.OK;
    }
    */

}