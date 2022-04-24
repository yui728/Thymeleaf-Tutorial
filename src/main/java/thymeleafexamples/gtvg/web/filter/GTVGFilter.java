package thymeleafexamples.gtvg.web.filter;

import org.thymeleaf.ITemplateEngine;
import thymeleafexamples.gtvg.business.entites.User;
import thymeleafexamples.gtvg.web.application.GTVGApplication;
import thymeleafexamples.gtvg.web.controller.IGTVGController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GTVGFilter implements Filter {

    private ServletContext servletContext;
    private GTVGApplication application;

    public GTVGFilter() {
        super();
    }

    private static void addUserToSession(final HttpServletRequest request) {
        // ユーザをセッションに追加する動作のシミュレート
        request.getSession(true).setAttribute("user", new User("John", "Apricot", "Antarctica", null));
    }

    public void init(final FilterConfig filterConfig) throws ServletException {
        this.servletContext = filterConfig.getServletContext();
        this.application = new GTVGApplication(this.servletContext);
    }

    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException {
        addUserToSession((HttpServletRequest) request);
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
            System.out.println("Start process method.");
            // リソースにアクセスするときは処理しない
            if(request.getRequestURI().startsWith("/css") ||
                request.getRequestURI().startsWith("/images") ||
                request.getRequestURI().startsWith("/favicon")) {
                return false;
            }

            /*
             * リクエストからコントローラを取得する。
             * コントローラを取得出来ない場合はfalseを返し、別のフィルタ/サーブレットにリクエストを処理させる
             */
            System.out.println("Get Controller from request.");
            IGTVGController controller = this.application.resolveControllerForRequest(request);
            if(controller == null) {
                return false;
            }

            /*
             *  テンプレートエンジンのインスタンスを取得する
             */
            System.out.println("Get template engine instance.");
            ITemplateEngine templateEngine = this.application.getTemplateEngine();

            /*
             * レスポンスヘッダを書き込む
             */
            System.out.println("Write response header");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma,", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);

            /*
             * コントローラとプロセスビューテンプレートを実行する
             * 結果をresponse writerに書き込む
             */
            System.out.println("Execution Controller Process.");
            controller.process(request, response, this.servletContext, templateEngine);

            return true;

        }  catch(Exception e) {
            try {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            } catch (final IOException ignored) {
                // これは無視する
            }
            throw new ServletException();
        }
    }

}
