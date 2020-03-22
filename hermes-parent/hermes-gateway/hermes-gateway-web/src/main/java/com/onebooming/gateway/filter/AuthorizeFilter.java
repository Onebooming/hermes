package com.onebooming.gateway.filter;

import com.onebooming.gateway.util.JwtUtil;
import io.jsonwebtoken.Claims;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-14 20:12
 * @ApiNote
 */
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    //令牌头名字
    private static final String AUTHORIZE_TOKEN = "Authorization";

    private static final String USER_LOGIN_URL = "http://localhost:9001/oauth/login";

    /***
     * 全局过滤器
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取Request、Response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();


        //获取请求的URI
        String path = request.getURI().getPath();

        //如果是登录、goods等开放的微服务[这里的goods部分开放],则直接放行,这里不做完整演示，完整演示需要设计一套权限系统
//        if (path.startsWith("/api/user/login") || path.startsWith("/api/brand/search") || path.startsWith("/api/brand")) {
//            //放行
//            Mono<Void> filter = chain.filter(exchange);
//            return filter;
//        }
        //如果用户的url中，包含一些登录或者不需要权限登录的请求，则放行
        //这些放行的url在URLFilter中指定了
        if(URLFilter.hasAuthorize(path)){
            Mono<Void> filter = chain.filter(exchange);
            return filter;
        }

        //获取头文件中的令牌信息
        String tokent = request.getHeaders().getFirst(AUTHORIZE_TOKEN);

        //如果头文件中没有，则从请求参数中获取
        if (StringUtils.isEmpty(tokent)) {
            tokent = request.getQueryParams().getFirst(AUTHORIZE_TOKEN);
        }

        //从cookie中获取令牌
        HttpCookie first = request.getCookies().getFirst(AUTHORIZE_TOKEN);
        if(first != null){
            tokent = first.getValue();
        }

        //如果为空，则输出错误代码
        if (StringUtils.isEmpty(tokent)) {
            //设置方法不允许被访问，405错误代码
//            response.setStatusCode(HttpStatus.METHOD_NOT_ALLOWED);
//            return response.setComplete();
            //跳转登录页面
            //此时再测试，就可以识别未登录用户，跳转到登录页，然后根据登录状态，如果登录成功，则跳转到来源页。
            return needAuthorization(USER_LOGIN_URL+"?FROM="+request.getURI().getPath(),exchange);
        }

        //解析令牌数据
        try {
//            Claims claims = JwtUtil.parseJWT(tokent);
            //将令牌数据添加到头文件中
            //必须将令牌添加到请求头中，这样以后其他微服务的鉴权都从header中取到令牌信息
//            request.mutate().header(AUTHORIZE_TOKEN,claims.toString());
            if(!tokent.startsWith("bearer") && !tokent.startsWith("Bearer")){
                request.mutate().header(AUTHORIZE_TOKEN,"Bearer "+tokent);
            }else {
                request.mutate().header(AUTHORIZE_TOKEN,tokent);
            }

        } catch (Exception e) {
            e.printStackTrace();
            //解析失败，响应401错误
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        //放行
        return chain.filter(exchange);
    }

    /**
     * 响应设置，设置登录请求头
     * @param userLoginUrl
     * @param exchange
     * @return
     */
    public Mono<Void> needAuthorization(String userLoginUrl, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().set("Location",userLoginUrl);
        return exchange.getResponse().setComplete();
    }

    /***
     * 过滤器执行顺序
     * @return
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
