package com.example.zuulservice;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;


/**
 * zuul过滤器
 */
@Component
public class DemoFilter extends ZuulFilter {

    @Override
    public String filterType() {
//        过滤器类型
//        pre：路由之前
//        routing：路由之时
//        post： 路由之后
//        error：发送错误调用
        return "pre";
    }

    @Override
    public int filterOrder() {
//        通过int值来定义过滤器的执行顺序，数值越小优先级越高。
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        //是否执行过滤器
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //过滤器具体实现
        RequestContext  ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        //通过路由，即会调用api服务提供者
        ctx.setSendZuulResponse(true);
        //返回状态码
        ctx.setResponseStatusCode(200);

//        ctx.setSendZuulResponse(false);
//        ctx.setResponseStatusCode(401);
//        //可以把一些值放到ctx中，便于后面的filter获取使用
//        ctx.set("isOK",false);
//        //返回内容给客户端
//        ctx.setResponseBody("{\"result\":\"pre01Filter auth not correct!\"}");// 返回错误内容

        return null;
    }
}
