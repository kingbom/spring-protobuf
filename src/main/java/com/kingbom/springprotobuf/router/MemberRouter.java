package com.kingbom.springprotobuf.router;

import com.kingbom.springprotobuf.handle.MemberHandle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class MemberRouter {

    @Bean
    public RouterFunction<ServerResponse> contactRouters(MemberHandle handler) {
        return RouterFunctions.route(GET("/members").and(accept(APPLICATION_JSON)), handler::getMember)
               .andRoute(GET("/members/protobuf").and(accept(APPLICATION_JSON)), handler::getMemberProtobuf);
    }
}
