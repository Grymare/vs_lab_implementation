package de.hska.iwi.vslab.coreservicecategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.lang.Thread;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Service
public class CoreServiceCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    //private DatabaseCategory dbCategory;

    /**
     * Gibt alle Kategorien zurück die es in der Datenbank gibt
     * Die Rückgabe erfolgt über eine Liste (müssen wir zu XML wandeln)
     */
    @HystrixCommand(fallbackMethod = "getCategories_fallback")
    @RequestMapping(value="/category", method=RequestMethod.GET)
        public ResponseEntity<String> getCategories() {
            /*
            try {
                Thread.sleep(10000); // for test purposes only
            } catch(InterruptedException e) {
                System.out.println("got interrupted!");
            }
            */

            Iterable<Category> categoryIterable = categoryRepository.findAll(); 

            JSONArray json_array = new JSONArray();
            for (Category cat : categoryIterable) {
                json_array.put(cat.getJSONObject());
            }
            
            System.out.println(json_array.toString());
 
            return new ResponseEntity<String>(json_array.toString(), HttpStatus.OK);
        }

    @HystrixCommand
    public HttpStatus getCategories_fallback() {
        return HttpStatus.I_AM_A_TEAPOT;
    }

    /**
     * Erstellt ein neues Category-Objekt mit dem übermittelten Kategoriennamen
     * 
     * @param categoryName Name der neuen Kategorie
     * @return gibt einen HTTP Status 200 zurück
     */
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public HttpStatus postCategory(@RequestParam String categoryName) {

        Category category = new Category(categoryName);

        categoryRepository.save(category);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/category/{categoryID}", method = RequestMethod.GET)
    public ResponseEntity<Object> getCategory(@PathVariable Integer categoryID) {

        Optional<Category> categoryOptional = categoryRepository.findById(categoryID);
        
        if (categoryOptional.isEmpty()){
            return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);              
        }else{
            return new ResponseEntity<Object>(categoryOptional.get().getJSONObject().toString(), HttpStatus.OK); 
        }       


        
    }

    @RequestMapping(value = "/category/{categoryID}", method = RequestMethod.DELETE)
    public HttpStatus deleteCategory(@PathVariable Integer categoryID) {
        categoryRepository.deleteById(categoryID);
        return HttpStatus.OK;
    }
}