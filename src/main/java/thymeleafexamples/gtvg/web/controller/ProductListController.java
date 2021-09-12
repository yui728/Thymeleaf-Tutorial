package thymeleafexamples.gtvg.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import thymeleafexamples.gtvg.business.entites.Product;
import thymeleafexamples.gtvg.business.services.ProductService;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ProductListController implements IGTVGController {

    public ProductListController() {
        super();
    }

    public void process(
            final HttpServletRequest request, final HttpServletResponse response,
            final ServletContext servletContext, final ITemplateEngine templateEngine)
            throws Exception {

        final ProductService productService = new ProductService();
        final List<Product> allProducts = productService.findAll();

        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("prods", allProducts);

        templateEngine.process("product/list", ctx, response.getWriter());

    }

}
