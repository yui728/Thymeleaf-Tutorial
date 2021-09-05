package thymeleafexamples.gtvg.business.entites;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private Integer id = null;
    private String name = null;
    private BigDecimal price = null;
    private boolean isStock = false;
    private List<Comment> comments = new ArrayList<Comment>();

    public Product(final Integer id, final String name, final boolean isStock, final BigDecimal price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.isStock = isStock;
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return this.price;
    }
    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public boolean getIsStock() {
        return this.isStock;
    }
    public void setIsStock(final boolean isStock) {
        this.isStock = isStock;
    }

    public List<Comment> getComments() {
        return this.comments;
    }

}
