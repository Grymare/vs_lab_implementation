package de.hska.iwi.vslab.coreservicecategory;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping(value="/Category", method=RequestMethod.GET)
        public ResponseEntity<Category> getCategories() {
            categoryRepository.findAll();
            List<Category> categoryList = categoryRepository.findAll(); //schauen wie wir aus der DB auslesen können
            return new ResponseEntity<>(categoryList, HttpStatus.OK);
        }

    /**
     * Erstellt ein neues Category-Objekt mit dem übermittelten Kategoriennamen
     * 
     * @param categoryName Name der neuen Kategorie
     * @return gibt einen HTTP Status 200 zurück
     */
    @RequestMapping(value = "/Category", method = RequestMethod.POST)
    public HttpStatus postCategory(@PathVariable String categoryName) {

        Category catego = new Category();
        catego.setName(categoryName);

        categoryRepository.save(catego);
        return HttpStatus.OK;
    }

    @RequestMapping(value = "/Category/{categoryID}", method = RequestMethod.GET)
    public ResponseEntity<Category> getCategory(@PathVariable Integer categoryID) {
        Category category = categoryRepository.findOne(categoryID); // schauen wie wir aus der DB auslesen können
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @RequestMapping(value = "/Category/{categoryID}", method = RequestMethod.DELETE)
    public HttpStatus postCategory(@PathVariable Integer categoryID) {

        categoryRepository.delete(categoryID); // schauen wie wir aus der DB löschen können
        return HttpStatus.OK;
    }
}