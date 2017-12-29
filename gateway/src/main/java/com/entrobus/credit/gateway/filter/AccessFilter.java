package com.entrobus.credit.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

@Component
public class AccessFilter extends ZuulFilter{
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
    public Object run() {
        return null;
    }
}
