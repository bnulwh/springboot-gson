package guru.springframework.serialize;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import guru.springframework.domain.Product;

import java.lang.reflect.Type;

public class CustomProductSerializer implements JsonSerializer<Product> {
    @Override
    public JsonElement serialize(Product product, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id", product.getId());
        jsonObject.addProperty("version", product.getVersion());
        jsonObject.addProperty("product-id", product.getProductId());
        jsonObject.addProperty("description", product.getDescription());
        jsonObject.addProperty("image-url", product.getImageUrl());
        jsonObject.addProperty("price", product.getPrice());
        return jsonObject;
    }
}
