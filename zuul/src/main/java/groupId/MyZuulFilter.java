package groupId;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.http.protocol.RequestContent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
@Component
public class MyZuulFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul filter exception: ");

        RequestContext context=RequestContext.getCurrentContext();
        HttpServletRequest request=context.getRequest();
        System.out.println("进入路由的过滤器： "+request.getServletPath());
        context.setSendZuulResponse(false);
        context.setResponseStatusCode(401);
        context.setResponseBody("zuul response");

        return null;
    }
}
