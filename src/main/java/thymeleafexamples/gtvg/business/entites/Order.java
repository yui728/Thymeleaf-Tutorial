package thymeleafexamples.gtvg.business.entites;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class Order {

    private Integer id = null;
    private Calendar date = null;
    private  Customer customer = null;
    private Set<OrderLine> orderLines = new LinkedHashSet<OrderLine>();

    public Order() {
        super();
    }

    public Integer getId() {
        return this.id;
    }
    public void setId(final Integer id) {
        this.id = id;
    }

    public Calendar getDate() {
        return this.date;
    }
    public void setDate(final Calendar date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return this.customer;
    }
    public void setCustomer(final Customer customer) {
        this.customer = customer;
    }

    public Set<OrderLine> getOrderLines() {
        return this.orderLines;
    }
}
