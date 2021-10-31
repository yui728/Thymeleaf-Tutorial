package thymeleafexamples.gtvg.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class HomeController implements IGTVGController {

    public HomeController() {
        super();
    }

    public void process(
            final HttpServletRequest request, final HttpServletResponse response,
            final ServletContext servletContext, final ITemplateEngine templateEngine)
            throws Exception {

        /*
         * Controller????????????????
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        LocalDate date = LocalDate.now();
         */

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        // Controller????????????????
        // ctx.setVariable("today", date.format(dateTimeFormatter));
        // ??????????????????????
        ctx.setVariable("today", Calendar.getInstance());

        templateEngine.process("home", ctx, response.getWriter());

    }

}
