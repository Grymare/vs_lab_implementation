package de.hska.iwi.vslab.coreservicecategory;

import java.util.ArrayList;
import java.util.Optional;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class CoreServiceCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    private ArrayList<Category> category_cache = new ArrayList<Category>();
    /**
     * Gibt alle Kategorien zurück die es in der Datenbank gibt
     * Die Rückgabe erfolgt über eine Liste (müssen wir zu XML wandeln)
     */
    @RequestMapping(value="/category", method=RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getCategories_fallback", 
    commandProperties = {  @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public ResponseEntity<String> getCategories() {
        /*
        try {
            Thread.sleep(10000); // for test purposes only
        } catch(InterruptedException e) {
            System.out.println("got interrupted!");
        }
        */

        Iterable<Category> categoryIterable = categoryRepository.findAll(); 
        category_cache = Lists.newArrayList(categoryIterable);
        System.out.println(categoryIterable);
        JSONArray json_array = new JSONArray();

        for (Category cat : categoryIterable) {
            json_array.put(cat.getJSONObject());
        }  

        return new ResponseEntity<String>(json_array.toString(), HttpStatus.OK);
    }

    @HystrixCommand
    public ResponseEntity<String> getCategories_fallback() {

        System.out.println("category fallback method");

        if (category_cache.isEmpty()){
            return new ResponseEntity<String>("[]", HttpStatus.I_AM_A_TEAPOT); 
        }

        JSONArray json_array = new JSONArray();
        for (Category cat : category_cache) {
            json_array.put(cat.getJSONObject());
        }

        return new ResponseEntity<String>(json_array.toString(), HttpStatus.I_AM_A_TEAPOT);
        
        
    }

    /**
     * Erstellt ein neues Category-Objekt mit dem übermittelten Kategoriennamen
     * 
     * @param categoryName Name der neuen Kategorie
     * @return gibt einen HTTP Status 200 zurück
     */
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "postCategory_fallback", 
    commandProperties = {  @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public HttpStatus postCategory(@RequestParam String categoryName) {

        Category category = new Category(categoryName);

        System.out.println(category.getId());

        categoryRepository.save(category);
        return HttpStatus.OK;
    }

    @HystrixCommand
    public HttpStatus postCategory_fallback(@RequestParam String categoryName) {
        return HttpStatus.I_AM_A_TEAPOT;
    }
    @RequestMapping(value = "/category/{categoryID}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getCategory_fallback", 
    commandProperties = {  @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public ResponseEntity<String> getCategory(@PathVariable Integer categoryID) {

        Optional<Category> categoryOptional = categoryRepository.findById(categoryID);
        
        if (categoryOptional.isEmpty()){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);              
        }else{
            return new ResponseEntity<String>(categoryOptional.get().getJSONObject().toString(), HttpStatus.OK); 
        }       
        
    }

    @HystrixCommand
    public ResponseEntity<String> getCategory_fallback(@PathVariable Integer categoryID) {

        for (Category cat : category_cache) {
            if (cat.getId() == categoryID){
                return new ResponseEntity<String>(cat.getJSONObject().toString(), HttpStatus.I_AM_A_TEAPOT); 
            }
        }  
        return new ResponseEntity<String>("[]", HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping(value = "/category/{categoryID}", method = RequestMethod.DELETE)
    @HystrixCommand(fallbackMethod = "deleteCategory_fallback", 
    commandProperties = {  @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public HttpStatus deleteCategory(@PathVariable Integer categoryID) {
        categoryRepository.deleteById(categoryID);
        return HttpStatus.OK;
    }

    @HystrixCommand
    public HttpStatus deleteCategory_fallback(@PathVariable Integer categoryID) {
        return HttpStatus.I_AM_A_TEAPOT;
    }

}