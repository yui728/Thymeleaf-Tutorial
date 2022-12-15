package thymeleafexamples.gtvg.web.mapping;

import org.thymeleaf.web.IWebRequest;
import thymeleafexamples.gtvg.web.controller.*;

import java.util.HashMap;
import java.util.Map;

public class ControllerMappings {

    private static Map<String, IGTVGController> controllersByURL;

    static {
        controllersByURL = new HashMap<String, IGTVGController>();
        controllersByURL.put("/", new HomeController());
        controllersByURL.put("/product/list", new ProductListController());
        controllersByURL.put("/product/comments", new ProductCommentsController());
        controllersByURL.put("/order/list", new OrderListController());
        controllersByURL.put("/order/details", new OrderDetailsController());
        controllersByURL.put("_/subsctibe", new SubscribeController());
        controllersByURL.put("/userprofile", new UserProfileController());
    }

    public static IGTVGController resolveControllerForRequest(final IWebRequest request) {
        final String path = request.getPathWithinApplication();
        return controllersByURL.get(path);
    }

    private ControllerMappings() {
        super();
    }
}
