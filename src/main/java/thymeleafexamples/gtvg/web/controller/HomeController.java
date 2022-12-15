package thymeleafexamples.gtvg.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;
import java.util.Calendar;

public class HomeController implements IGTVGController {

    public HomeController() {
        super();
    }

    public void process(
            final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer)
            throws Exception {

        /*
         * Controller????????????????
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDate date = LocalDate.now();
         */
        System.out.println("HomeController: Create WebContext");
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        // Controller????????????????
        // ctx.setVariable("today", date.format(dateTimeFormatter));
        // ??????????????????????
        System.out.println("HomeController: Set Variable");
        ctx.setVariable("today", Calendar.getInstance());

        System.out.println("HomeController: Call TemplateEngine Process");
        templateEngine.process("home", ctx, writer);

    }

}
