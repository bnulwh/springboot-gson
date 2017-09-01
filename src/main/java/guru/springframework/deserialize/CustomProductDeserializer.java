package guru.springframework.deserialize;

import com.google.gson.*;
import guru.springframework.domain.Product;

import java.lang.reflect.Type;
import java.math.BigDecimal;

public class CustomProductDeserializer implements JsonDeserializer<Product>{
    @Override
    public Product deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Product product = new Product();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        Integer id = jsonObject.get("id").getAsInt();
        Integer version = jsonObject.get("version").getAsInt();
        String productId = jsonObject.get("product-id").getAsString();
        String description = jsonObject.get("description").getAsString();
        String imageUrl = jsonObject.get("image-url").getAsString();
        BigDecimal price = jsonObject.get("price").getAsBigDecimal();
        product.setId(id);
        product.setVersion(version);
        product.setProductId(productId);
        product.setDescription(description);
        product.setImageUrl(imageUrl);
        product.setPrice(price);

        return product;
    }
}
