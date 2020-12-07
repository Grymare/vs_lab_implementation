package de.hska.iwi.vslab.coreserviceproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
public class CoreServiceProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Gibt alle Kategorien zurück die es in der Datenbank gibt
     * Die Rückgabe erfolgt über eine Liste (müssen wir zu XML wandeln)
     */

    @RequestMapping(value="/Product", method=RequestMethod.GET)
        public ResponseEntity<Product> getProducts() {

            Iterable<Product> categoryIterable = categoryRepository.findAll(); //schauen wie wir aus der DB auslesen können

            List<Category> categoryList = new ArrayList<Category>();

            for (Category cat : categoryIterable) {
                categoryList.add(cat);
            }

            return new ResponseEntity<List<Category>>(categoryList, HttpStatus.OK);


        }

    /**
     * Erstellt ein neues Product-Objekt mit dem übermittelten Kategoriennamen
     * 
     * @param productName Name der neuen Kategorie
     * @return gibt einen HTTP Status 200 zurück
     */
    @RequestMapping(value = "/Product", method = RequestMethod.POST)
    public HttpStatus postProduct(@RequestBody Product newProduct) {

        dbProduct.postProduct(newProduct); // schauen wie wir aus der DB auslesen können
        return HttpStatus.OK;
    }

    /**
     * Gibt alle Kategorien zurück die es in der Datenbank gibt
     * Die Rückgabe erfolgt über eine Liste (müssen wir zu XML wandeln)
     */

    @RequestMapping(value="/Product/", method=RequestMethod.GET)
        public ResponseEntity<Product> getProductsFiltered(@RequestParam(required=false) String searchtext, @RequestParam(required=false) Integer min, @RequestParam(required=false) Integer max) {

            //Abfrage mit DB durchführen
            List<Product> productList = dbProduct.findAll?idontfuckingknow(); //schauen wie wir aus der DB auslesen können
            

            if(productList.size()== 0) {
                return HttpStatus.NO_CONTENT;//TODO: Test response
            }
            
            return new ResponseEntity<>(productList, HttpStatus.OK);
        }


    @RequestMapping(value = "/Product/{productID}", method = RequestMethod.GET)
    public ResponseEntity<Product> getProduct(@PathVariable Integer productID) {

        Product product = dbProduct.findOne(productID); // schauen wie wir aus der DB auslesen können
        
        if(product = null){
            return HttpStatus.NOT_FOUND; //TODO: Test response
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @RequestMapping(value = "/Product/{productID}", method = RequestMethod.DELETE)
    public HttpStatus postProduct(@PathVariable Integer productID) {

        dbProduct.deleteOne(productID); // schauen wie wir aus der DB löschen können
        return HttpStatus.OK;
    }
}