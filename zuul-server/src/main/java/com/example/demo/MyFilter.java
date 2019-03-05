package com.example.demo;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by lichao01 on 2019/3/4.
 */
@Component
public class MyFilter extends ZuulFilter{
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        if(requestContext.getRequest().getServletPath().contains("pb")){
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Enumeration<String> enumeration = requestContext.getRequest().getHeaders("token");
        if(enumeration.hasMoreElements()){
         String token = enumeration.nextElement();
         if(token == null){
                requestContext.setResponseStatusCode(401);
                requestContext.setSendZuulResponse(false);
                try {
                    requestContext.getResponse().getWriter().write("token invalid");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            requestContext.setResponseStatusCode(401);
            requestContext.setSendZuulResponse(false);
            try {
                requestContext.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
