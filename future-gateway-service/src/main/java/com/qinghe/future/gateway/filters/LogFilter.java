package com.qinghe.future.gateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class LogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("*** Come in LogFilter ***");
//        String name = exchange.getRequest().getQueryParams().getFirst("username");
//        if (name==null) {
//            log.info("************用户名为null，非法用户，请离开*************");
//            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
//            return exchange.getResponse().setComplete();
//        }
//        log.info("用户名为:"+name);
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
