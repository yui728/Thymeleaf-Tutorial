package thymeleafexamples.gtvg.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

import java.io.Writer;

public class SubscribeController implements IGTVGController {

    public SubscribeController() {
        super();
    }

    public void process(final IWebExchange webExchange, final ITemplateEngine templateEngine, final Writer writer)
            throws Exception {

        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        templateEngine.process("subscribe", ctx, writer);

    }

}
