package guru.springframework.domain;

import com.google.gson.annotations.Expose;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Expose
    @Version
    private transient Integer version;

    @Expose(serialize = false)
    private String productId;

    @Expose
    private String description;

    @Expose
    private String imageUrl;

    @Expose
    private BigDecimal price;

    /*@Expose
    private Date developedDate;*/

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /*public Date getDevelopedDate() { return developedDate; }

    public void setDevelopedDate(Date developedDate) { this.developedDate = developedDate; }*/
}
