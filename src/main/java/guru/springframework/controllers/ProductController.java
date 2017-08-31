package guru.springframework.controllers;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.Date;

import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")

public class ProductController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = "application/json")
    public Iterable<Product> list(Model model){
        Iterable<Product> productList = productService.listAllProducts();

        return productList;
    }

    @RequestMapping(value = "/show/{id}", method= RequestMethod.GET, produces = "application/json")
    public Product showProduct(@PathVariable Integer id, Model model){
       Product product = productService.getProductById(id);
       /*Gson gson = new Gson();
       String json = gson.toJson(product);
       System.out.println(json);*/
       GsonBuilder gsonBuilder = new GsonBuilder();
       gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
       Gson gson = gsonBuilder.create();
       //Gson gson = new GsonBuilder().setPrettyPrinting().create();
       String json = gson.toJson(product);
       System.out.println(json);
       System.out.println(new Date());
       return product;
    }


    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity saveProduct(@RequestBody Product product){
       /* Gson gson = new Gson();
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource("common/product.json").getFile());*/
        //JsonReader reader = new JsonReader(new FileReader("../common/product.json"));

        /*Review data = gson.fromJson(reader, Review.class);
        data.toScreen();*/

        //Gson gson = new Gson();
        //Product product1 = gson.fromJson(new FileReader("product.json"));
        productService.saveProduct(product);
        return new ResponseEntity("Product saved successfully", HttpStatus.OK);
    }


    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody Product product){
        Product storedProduct = productService.getProductById(id);
        storedProduct.setDescription(product.getDescription());
        storedProduct.setImageUrl(product.getImageUrl());
        storedProduct.setPrice(product.getPrice());
        productService.saveProduct(storedProduct);
        return new ResponseEntity("Product updated successfully", HttpStatus.OK);
    }


    @RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity delete(@PathVariable Integer id){
        productService.deleteProduct(id);
        return new ResponseEntity("Product deleted successfully", HttpStatus.OK);

    }

}
