package thymeleafexamples.gtvg.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import thymeleafexamples.gtvg.business.entites.Product;
import thymeleafexamples.gtvg.business.services.ProductService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductCommentsController implements IGTVGController {

    public ProductCommentsController() {
        super();
    }

    public void process(
            final HttpServletRequest request, final HttpServletResponse response,
            final ServletContext servletContext, final ITemplateEngine templateEngine)
            throws Exception {

        final Integer prodId = Integer.valueOf(request.getParameter("prodId"));

        final ProductService productService = new ProductService();
        final Product product = productService.findById(prodId);

        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("prod", product);

        templateEngine.process("product/comments", ctx, response.getWriter());

    }

}
