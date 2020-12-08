package de.hska.iwi.vslab.coreserviceproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.json.JSONObject;
import org.json.JSONArray;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CoreServiceProductController {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Gibt alle Kategorien zurück die es in der Datenbank gibt
     * Die Rückgabe erfolgt über eine Liste (müssen wir zu XML wandeln)
     */

    @RequestMapping(value="/product", method=RequestMethod.GET)
        public ResponseEntity<Object> getProducts() {

            Iterable<Product> productIterable = productRepository.findAll(); //schauen wie wir aus der DB auslesen können

            JSONArray json_array = new JSONArray();
            for (Product cat : productIterable) {
                json_array.put(cat.getJSONObject());
            }
            
            System.out.println(json_array.toString());
 
            return new ResponseEntity<Object>(json_array.toString(), HttpStatus.OK);

        }

    /**
     * Erstellt ein neues Product-Objekt mit dem übermittelten Kategoriennamen
     * 
     * @param productName Name der neuen Kategorie
     * @return gibt einen HTTP Status 200 zurück
     */
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

    /**
     * Gibt alle Producte zurück die den Filterkriterien entsprechen
     * Die Rückgabe erfolgt über eine Liste (müssen wir zu XML wandeln)
     */

    @RequestMapping(value="/product/", method=RequestMethod.GET)
        public ResponseEntity<List<Product>> getProductsFiltered(
            @RequestParam(required=false, defaultValue = "") String searchtext, 
            @RequestParam(required=false, defaultValue = "0") double min, 
            @RequestParam(required=false, defaultValue = "-1") double max) {

            Iterable<Product> productIterable = productRepository.findAll(); //schauen wie wir aus der DB auslesen können

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

        Optional<Product> productOptional = productRepository.findById(productID);
        
        if (productOptional.isEmpty()){
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);              
        }else{
            return new ResponseEntity<Product>(productOptional.get(), HttpStatus.OK); 
        }  
    }

    @RequestMapping(value = "/product/{productID}", method = RequestMethod.DELETE)
    public HttpStatus postProduct(@PathVariable Integer productID) {

        productRepository.deleteById(productID);
        return HttpStatus.OK;
    }
}