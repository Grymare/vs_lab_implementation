package de.hska.iwi.vslab.coreserviceproduct;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Component
public class CoreServiceProductController {

    @Autowired
    private ProductRepository productRepository;

    private ArrayList<Product> product_cache = new ArrayList<Product>();

    /**
     * Gibt alle Kategorien zurück die es in der Datenbank gibt Die Rückgabe erfolgt
     * über eine Liste (müssen wir zu XML wandeln)
     */

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getProducts_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public ResponseEntity<String> getProducts() {

        Iterable<Product> productIterable = productRepository.findAll(); // schauen wie wir aus der DB auslesen können
        product_cache = Lists.newArrayList(productIterable);
        JSONArray json_array = new JSONArray();
        for (Product product : productIterable) {
            json_array.put(product.getJSONObject());
        }

        System.out.println(json_array.toString());

        return new ResponseEntity<String>(json_array.toString(), HttpStatus.OK);

    }

    @HystrixCommand
    public ResponseEntity<String> getProducts_fallback() {

        System.out.println("products fallback method");

        if (product_cache.isEmpty()) {
            return new ResponseEntity<String>("[]", HttpStatus.I_AM_A_TEAPOT);
        }

        JSONArray json_array = new JSONArray();
        for (Product prod : product_cache) {
            json_array.put(prod.getJSONObject());
        }

        return new ResponseEntity<String>(json_array.toString(), HttpStatus.I_AM_A_TEAPOT);
    }

    /**
     * Erstellt ein neues Product-Objekt mit dem übermittelten Kategoriennamen
     * 
     * @param productName Name der neuen Kategorie
     * @return gibt einen HTTP Status 200 zurück
     */
    @RequestMapping(value = "/product{name, price, details, categoryId}", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "postProduct_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public HttpStatus postProduct(@RequestParam(required = false) String name,
            @RequestParam(required = false) Double price, @RequestParam(required = false) String details,
            @RequestParam(required = false) int categoryId) {

        Product prod = new Product(name, price, details, categoryId);

        productRepository.save(prod);
        return HttpStatus.OK;

    }

    @HystrixCommand
    public HttpStatus postProduct_fallback(@RequestParam(required = false) String name,
            @RequestParam(required = false) Double price, @RequestParam(required = false) String details,
            @RequestParam(required = false) int categoryId) {

        return HttpStatus.I_AM_A_TEAPOT;
    }

    /**
     * Gibt alle Producte zurück die den Filterkriterien entsprechen Die Rückgabe
     * erfolgt über eine Liste (müssen wir zu XML wandeln)
     */

    @RequestMapping(value = "/product/", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getProductsFiltered_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public ResponseEntity<String> getProductsFiltered(
            @RequestParam(required = false, defaultValue = "") String searchtext,
            @RequestParam(required = false, defaultValue = "0") double min,
            @RequestParam(required = false, defaultValue = "-1") double max) {

        Iterable<Product> productIterable = productRepository.findAll();

        List<Product> productList = new ArrayList<Product>();

        for (Product produc : productIterable) {
            productList.add(produc);
        }

        if (min != 0) {
            System.out.println("MIN");
            System.out.println(min);
            productList = productList.stream().filter(p -> p.getPrice() >= min).collect(Collectors.toList());
        }
        if (max != -1) {
            System.out.println("MAX");
            System.out.println(max);
            productList = productList.stream().filter(p -> p.getPrice() <= max).collect(Collectors.toList());
        }
        if (searchtext.isEmpty() == false) {
            System.out.println("searchtext");
            System.out.println(searchtext);

            productList = productList.stream()
                    .filter(p -> p.getName().contains(searchtext) || p.getDetails().contains(searchtext))
                    .collect(Collectors.toList());

        }

        JSONArray json_array = new JSONArray();
        for (Product cat : productList) {
            json_array.put(cat.getJSONObject());
        }

        return new ResponseEntity<String>(json_array.toString(), HttpStatus.OK);
    }

    @HystrixCommand
    public ResponseEntity<String> getProductsFiltered_fallback(
            @RequestParam(required = false, defaultValue = "") String searchtext,
            @RequestParam(required = false, defaultValue = "0") double min,
            @RequestParam(required = false, defaultValue = "-1") double max) {

        List<Product> productList = product_cache;

        if (min != 0) {
            System.out.println("MIN");
            System.out.println(min);
            productList = productList.stream().filter(p -> p.getPrice() >= min).collect(Collectors.toList());
        }
        if (max != -1) {
            System.out.println("MAX");
            System.out.println(max);
            productList = productList.stream().filter(p -> p.getPrice() <= max).collect(Collectors.toList());
        }
        if (searchtext.isEmpty() == false) {
            System.out.println("searchtext");
            System.out.println(searchtext);

            productList = productList.stream()
                    .filter(p -> p.getName().contains(searchtext) || p.getDetails().contains(searchtext))
                    .collect(Collectors.toList());

        }

        JSONArray json_array = new JSONArray();
        for (Product cat : productList) {
            json_array.put(cat.getJSONObject());
        }

        return new ResponseEntity<String>(json_array.toString(), HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping(value = "/product/{productID}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "getProduct_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public ResponseEntity<String> getProduct(@PathVariable Integer productID) {

        Optional<Product> productOptional = productRepository.findById(productID);
        
        if (productOptional.isEmpty()){
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);              
        }else{
            return new ResponseEntity<String>(productOptional.get().getJSONObject().toString(), HttpStatus.OK); 
        } 

    }

    @HystrixCommand
    public ResponseEntity<String> getProduct_fallback(@PathVariable Integer productID) {

        for (Product prod : product_cache) {
            if (prod.getId() == productID) {
                return new ResponseEntity<String>(prod.getJSONObject().toString(), HttpStatus.I_AM_A_TEAPOT);
            }
        }
        return new ResponseEntity<String>("[]", HttpStatus.I_AM_A_TEAPOT);
    }

    @RequestMapping(value = "/product/{productID}", method = RequestMethod.DELETE)
    @HystrixCommand(fallbackMethod = "postProduct_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "2") })
    public HttpStatus postProduct(@PathVariable Integer productID) {

        productRepository.deleteById(productID);
        return HttpStatus.OK;
    }

    @HystrixCommand
    public HttpStatus postProduct_fallback(@PathVariable Integer productID) {
        return HttpStatus.I_AM_A_TEAPOT;
    }
}