package thymeleafexamples.gtvg.web.filter;

import javax.servlet.*;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebApplication;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.IWebRequest;
import org.thymeleaf.web.servlet.JavaxServletWebApplication;
import thymeleafexamples.gtvg.business.entites.User;
import thymeleafexamples.gtvg.web.controller.IGTVGController;
import thymeleafexamples.gtvg.web.mapping.ControllerMappings;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

public class GTVGFilter implements Filter {

    private JavaxServletWebApplication application;
    private ITemplateEngine templateEngine;

    public GTVGFilter() {
        super();
    }

    private static void addUserToSession(final HttpServletRequest request) {
        // ユーザをセッションに追加する動作のシミュレート
        request.getSession(true).setAttribute("user", new User("John", "Apricot", "Antarctica", null));
    }

    public void init(final FilterConfig filterConfig) throws ServletException {
//        this.servletContext = filterConfig.getServletContext();
//        this.application = new GTVGApplication(this.servletContext);
        this.application = JavaxServletWebApplication.buildApplication(filterConfig.getServletContext());
        this.templateEngine = buildTemplateEngine(this.application);
    }

    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException {
        addUserToSession((HttpServletRequest) request);
        System.out.println("GTVGFilter.process");
        if(!process((HttpServletRequest)request, (HttpServletResponse)response)) {
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            chain.doFilter(request, response);
        }
    }

    public void destroy() {
        // 何も処理しない
    }

    private boolean process(HttpServletRequest request, HttpServletResponse response)
        throws ServletException {
        try {

            final IWebExchange webExchange = this.application.buildExchange(request, response);
            final IWebRequest webRequest = webExchange.getRequest();

            System.out.println("Start process method.");
            // リソースにアクセスするときは処理しない
            if (webRequest.getPathWithinApplication().startsWith("/css") ||
                    webRequest.getPathWithinApplication().startsWith("/images") ||
                    webRequest.getPathWithinApplication().startsWith("/favicon")) {
                return false;
            }

            /*
             * リクエストからコントローラを取得する。
             * コントローラを取得出来ない場合はfalseを返し、別のフィルタ/サーブレットにリクエストを処理させる
             */
            System.out.println("Get Controller from request.");
            final IGTVGController controller = ControllerMappings.resolveControllerForRequest(webRequest);
            if (controller == null) {
                return false;
            }

            /*
             * レスポンスのメンバを書き込む
             */
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            /*
             * レスポンスライターの取得
             */
            final Writer writer = response.getWriter();

            /*
             * コントローラとプロセスビューテンプレートを実行する
             * 結果をresponse writerに書き込む
             */
            System.out.println("Execution Controller Process.");
            controller.process(webExchange, this.templateEngine, writer);

            return true;

        } catch (Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (final IOException ignored) {
                // これは無視する
            }
            throw new ServletException();
        }
    }

    private static ITemplateEngine buildTemplateEngine(final IWebApplication application) {
        final WebApplicationTemplateResolver templateResolver = new WebApplicationTemplateResolver(application);

        // HTMLがデフォルトモード
        templateResolver.setTemplateMode(TemplateMode.HTML);
         // "home"でアクセスしたときに"/WEB-INF/templates/home.html"を読みこむようにする
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // テンプレートキャッシュのTTLを1時間に設定する
        // 設定しない場合はLRUによって破棄されるまでテンプレートキャッシュが残る
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));

        // キャッシュをtrueに設定する（デフォルト設定）
        // テンプレートファイルが変更されたときに自動的に更新したいのであれば、キャッシュをfalseに設定する
        templateResolver.setCacheable(true);

        final TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine;
    }
}
