package com.gzy.reactiveweblearn.config;

import com.gzy.reactiveweblearn.domain.User;
import com.gzy.reactiveweblearn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * 路由器函数 配置
 */

@Configuration
public class RouterFunctionConfiguration {

    /**
     *
     * Spring
     * 请求接口：ServerRequest
     * 响应接口：ServerResponse
     * 即可支持Servlet规范，又可自定义，比如Netty Web Server
     */


    /**
     * 定义GET请求，并缺返回所有的用户对象，URI: /user/findall
     */
    @Bean
    @Autowired
    public RouterFunction<ServerResponse> userFindAll(UserRepository userRepository) {
        return RouterFunctions.route(RequestPredicates.GET("/user/find/all"),
                request -> {
                    Collection<User> users = userRepository.findAll();
                    Mono<ServerResponse> response = null;
                    Flux<User> userFlux = Flux.fromIterable(users);

                    return ServerResponse.ok().body(userFlux, User.class);

                });
    }
}
