package thymeleafexamples.gtvg.web.application;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import thymeleafexamples.gtvg.web.controller.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class GTVGApplication {
    private TemplateEngine templateEngine;
    private Map<String, IGTVGController> controllerByURL;

    public GTVGApplication(final ServletContext servletContext) {

        super();

        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);

        // テンプレートモードは既定ではHTMLだが、常にコード上で明示的に記載しておいたほうが良い
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // "home"を"/WEB-INF/templates/home.html"に変換するためのコード
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // TTLのテンプレートキャッシュを1時間に設定する
        // 設定しない場合は、LRUによって解放されるまでキャッシュが有効になる
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));

        // キャッシュはtrueが既定値
        // テンプレートが更新されたときに自動的に更新したい場合はfalseを設定する
        templateResolver.setCacheable(true);

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);

        this.controllerByURL = new HashMap<String, IGTVGController>();
        this.controllerByURL.put("/", new HomeController());
        this.controllerByURL.put("/product/list", new ProductListController());
        this.controllerByURL.put("/product/comments", new ProductCommentsController());
        this.controllerByURL.put("/order/list", new OrderListController());
        this.controllerByURL.put("/order/details", new OrderDetailsController());
        this.controllerByURL.put("/subscribe", new SubscribeController());
        this.controllerByURL.put("/userprofile", new UserProfileController());
    }

    public IGTVGController resolveControllerForRequest(final HttpServletRequest request) {
        final String path = getRequestPath(request);
        return this.controllerByURL.get(path);
    }

    public ITemplateEngine getTemplateEngine() {
        return this.templateEngine;
    }

    private static String getRequestPath(final HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        final String contextPath = request.getContextPath();

        final int fragmentIndex = requestURI.indexOf(';');
        if(fragmentIndex != -1) {
            requestURI = requestURI.substring(0, fragmentIndex);
        }

        if(requestURI.startsWith(contextPath)) {
            return requestURI.substring(contextPath.length());
        }

        return requestURI;
    }
}