package de.hska.iwi.vslab.coreservicecategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CoreServiceCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;
    //private DatabaseCategory dbCategory;

    /**
     * Gibt alle Kategorien zurück die es in der Datenbank gibt
     * Die Rückgabe erfolgt über eine Liste (müssen wir zu XML wandeln)
     */

    @RequestMapping(value="/category", method=RequestMethod.GET)
        public ResponseEntity<Object> getCategories() {

            //schauen wie wir aus der DB auslesen können
            Iterable<Category> categoryIterable = categoryRepository.findAll(); 
            
            /*
            List<Category> categoryList = new ArrayList<Category>();

            for (Category cat : categoryIterable) {
                categoryList.add(cat);
            }*/

            JSONArray json_array = new JSONArray();
            for (Category cat : categoryIterable) {
                json_array.put(cat.getJSONObject());
            }
            
            System.out.println(json_array.toString());
 
            return new ResponseEntity<Object>(json_array.toString(), HttpStatus.OK);
        }

    /**
     * Erstellt ein neues Category-Objekt mit dem übermittelten Kategoriennamen
     * 
     * @param categoryName Name der neuen Kategorie
     * @return gibt einen HTTP Status 200 zurück
     */
    @RequestMapping(value = "/category{categoryName}", method = RequestMethod.POST)
    public HttpStatus postCategory(@PathVariable String categoryName) {

        Category catego = new Category(categoryName);

        categoryRepository.save(catego);
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