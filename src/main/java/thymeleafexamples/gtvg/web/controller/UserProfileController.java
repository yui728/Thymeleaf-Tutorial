package thymeleafexamples.gtvg.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public class UserProfileController implements IGTVGController {

    public UserProfileController() {
        super();
    }

    public void process(final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer)
            throws Exception {

        final WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        templateEngine.process("userprofile", ctx, writer);

    }

}
