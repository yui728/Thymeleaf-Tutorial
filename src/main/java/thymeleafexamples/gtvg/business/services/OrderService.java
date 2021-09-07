package thymeleafexamples.gtvg.business.services;

import thymeleafexamples.gtvg.business.entites.Order;
import thymeleafexamples.gtvg.business.entites.repositories.OrderRepository;

import java.util.List;

public class OrderService {

    public OrderService() {
        super();
    }


    public List<Order> findAll() {
        return OrderRepository.getInstance().findAll();
    }

    public Order findById(final Integer id) {
        return OrderRepository.getInstance().findById(id);
    }

    public List<Order> findByCustomerId(final Integer customerId) {
        return OrderRepository.getInstance().findByCustomerId(customerId);
    }
}
