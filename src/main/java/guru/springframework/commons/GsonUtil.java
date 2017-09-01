package guru.springframework.commons;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import guru.springframework.deserialize.CustomProductDeserializer;
import guru.springframework.domain.Product;
import guru.springframework.serialize.CustomProductSerializer;

import java.io.InputStreamReader;
import java.io.Reader;

public class GsonUtil {

    /*Simple serialization of product object into json without pretty printing*/
    public String simpleJson(Product product){
        Gson gson = new Gson();
        String json = gson.toJson(product);
        System.out.println(json);
        return json;
    }

    /*GsonBuilder for pretty printing of Json during serializing of product object*/
    public String simpleJsonWithPrettyPrinting(Product product){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(product);
        System.out.println(json);
        return json;
    }

    /*GsonBuilder for @Expose annotation*/
    public String simpleJsonWithExposeFields(Product product){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.excludeFieldsWithoutExposeAnnotation().setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(product);
        System.out.println(json);
        return json;
    }

    /*Custom serialization of product object*/
    public String simpleJsonWithCustomSerialization(Product product){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Product.class, new CustomProductSerializer()).setPrettyPrinting();
        //gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        String json = gson.toJson(product);
        System.out.println(json);
        return json;
    }

    /*Custom deseialization of given JSON*/
    public String objectWithCustomDeserialization() throws Exception{
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Product.class, new CustomProductDeserializer());
        Gson gson = gsonBuilder.create();
        try(Reader reader = new InputStreamReader(GsonUtil.class.getResourceAsStream("/common/product.json"))){
            Product product = gson.fromJson(reader, Product.class);
            System.out.println(product.getId());
            System.out.println(product.getVersion());
            System.out.println(product.getProductId());
            System.out.println(product.getDescription());
            System.out.println(product.getImageUrl());
            System.out.println(product.getPrice());
        }
        return "success";
    }
}
