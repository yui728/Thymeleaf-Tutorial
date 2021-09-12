package thymeleafexamples.gtvg.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import thymeleafexamples.gtvg.business.entites.Order;
import thymeleafexamples.gtvg.business.services.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;

public class OrderDetailsController implements IGTVGController {

    public OrderDetailsController() {
        super();
    }

    public void process(
            final HttpServletRequest request, final HttpServletResponse response,
            final ServletContext servletContext, final ITemplateEngine templateEngine)
            throws Exception {

        final Integer orderId = Integer.valueOf(request.getParameter("orderId"));

        final OrderService orderService = new OrderService();
        final Order order = orderService.findById(orderId);

        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("order", order);

        templateEngine.process("order/details", ctx, response.getWriter());

    }

}
