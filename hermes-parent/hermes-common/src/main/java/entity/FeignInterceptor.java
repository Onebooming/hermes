package entity;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * @author Onebooming
 * @version 1.0
 * @date 2020-03-17 23:46
 * @ApiNote
 */
@Component
public class FeignInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        /**
         * 在管理系统中
         * 1-没有令牌，Feign调用之前生成令牌；
         * 2-Feign调用之前，令牌需要携带过去
         * 3-Feign调用之前，令牌需要存放到Headers文件中
         * 4-请求➡️Feign调用->拦截器RequestInterceptor->Feign调用之前拦截
         *
         * 在用户系统中：
         * 1-获取用户令牌
         * 2-将令牌封装到头文件中
         */
        try {
            /**
             * 用户当前请求的是对应的线程数据，如果开启了熔断，则默认是线程池隔离，会启用新的数据，需要将熔断策略换成信号量隔离，此时不会开启新的线程
             */
            //使用RequestContextHolder工具获取request相关变量：记录当前用户请求的所有数据，包含请求头和请求参数等
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                //取出request
                HttpServletRequest request = attributes.getRequest();
                //获取所有头文件信息的key
                Enumeration<String> headerNames = request.getHeaderNames();
                if (headerNames != null) {
                    while (headerNames.hasMoreElements()) {
                        //头文件的key
                        String name = headerNames.nextElement();
                        //头文件的value
                        String values = request.getHeader(name);
                        //将令牌数据添加到头文件中
                        requestTemplate.header(name, values);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
