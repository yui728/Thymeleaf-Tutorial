package thymeleafexamples.gtvg.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import thymeleafexamples.gtvg.business.entites.Order;
import thymeleafexamples.gtvg.business.services.OrderService;

import java.io.Writer;

public class OrderDetailsController implements IGTVGController {

    public OrderDetailsController() {
        super();
    }

    public void process(
            final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer)
            throws Exception {

        final Integer orderId = Integer.valueOf(webExchange.getRequest().getParameterValue("orderId"));

        final OrderService orderService = new OrderService();
        final Order order = orderService.findById(orderId);

        final WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        ctx.setVariable("order", order);

        System.out.println("orderDetail: view white.");
        templateEngine.process("order/details", ctx, writer);
    }

}
