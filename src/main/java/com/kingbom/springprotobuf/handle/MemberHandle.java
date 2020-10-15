package com.kingbom.springprotobuf.handle;

import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.kingbom.springprotobuf.Member;
import com.kingbom.springprotobuf.MemberOuterClass;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
@Component
@RequiredArgsConstructor
public class MemberHandle {


    public Mono<ServerResponse> getMember(ServerRequest request) {
        return Mono.just(buildMember())
                .flatMap(dto -> ok().contentType(APPLICATION_JSON).body(fromValue(dto)))
                .doOnSuccess(o -> log.info("Successfully"))
                .doOnError(o -> log.error("Error"));
    }

    public Mono<ServerResponse> getMemberProtobuf(ServerRequest request) {
         return Mono.just(buildMemberKindProtobuf())
                .flatMap(dto -> ok().contentType(APPLICATION_JSON).body(fromValue(dto)))
                .doOnSuccess(o -> log.info("Successfully"))
                .doOnError(o -> log.error("Error"));
    }

    public Member buildMember() {
        return Member.builder()
                .id("101010")
                .name("Jaruwit")
                .height(177.5)
                .weight(70.5)
                .build();
    }


    public String buildMemberKindProtobuf() {
        var member = MemberOuterClass.Member.newBuilder();
        member.setId("101010");
        member.setName("Jaruwit");
        member.setHeight(177.5);
        member.setWeight(70.5);



        //todo if you need response  more than one item
        /*MemberOuterClass.Members.Builder members = MemberOuterClass.Members.newBuilder();
        members.addMembers(member);*/

        String response = "";
        try {
            response =  JsonFormat.printer().print(member);
        } catch (InvalidProtocolBufferException e) {
            log.error("InvalidProtocolBufferException ", e);
        }
        return response;

    }

}
