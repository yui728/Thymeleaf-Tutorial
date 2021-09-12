package thymeleafexamples.gtvg.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import thymeleafexamples.gtvg.business.entites.Order;
import thymeleafexamples.gtvg.business.services.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class OrderListController implements IGTVGController {

    public OrderListController() {
        super();
    }

    @Override
    public void process(
            HttpServletRequest request, HttpServletResponse response,
            ServletContext servletContext, ITemplateEngine templateEngine)
            throws Exception {

        final OrderService orderService = new OrderService();
        final List<Order> allOrders = orderService.findAll();

        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("orders", allOrders);

        templateEngine.process("order/list", ctx, response.getWriter());

    }
}
