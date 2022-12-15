package thymeleafexamples.gtvg.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import thymeleafexamples.gtvg.business.entites.Order;
import thymeleafexamples.gtvg.business.services.OrderService;

import java.io.Writer;
import java.util.List;

public class OrderListController implements IGTVGController {

    public OrderListController() {
        super();
    }

    @Override
    public void process(
            final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer)
            throws Exception {

//        System.out.println("OrderListController process");
        final OrderService orderService = new OrderService();
        final List<Order> allOrders = orderService.findAll();

        final WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        ctx.setVariable("orders", allOrders);

//        System.out.println("OrderListController process templateEngine.process");
        templateEngine.process("order/list", ctx, writer);

    }
}
