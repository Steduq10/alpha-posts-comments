package com.posada.santiago.alphapostsandcomments.application.generic.models.usecases;

import com.posada.santiago.alphapostsandcomments.application.config.jwt.JwtTokenProvider;
import com.posada.santiago.alphapostsandcomments.application.generic.models.AuthenticationRequest;
import com.posada.santiago.alphapostsandcomments.application.generic.models.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class LoginUseCase {

    private final JwtTokenProvider jwtTokenProvider;

    private final ReactiveAuthenticationManager authenticationManager;

    public Mono<ServerResponse> logIn(Mono<AuthenticationRequest> authenticationRequest){
        return authenticationRequest
                .flatMap(authRequest -> this.authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()))
                        .onErrorMap(BadCredentialsException.class, err -> new Throwable(HttpStatus.FORBIDDEN.toString()))
                        .map(this.jwtTokenProvider::createToken))
                .flatMap(jwt-> {
                    //HttpHeaders httpHeaders = new HttpHeaders();
                    //httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt);
                    var tokenBody = Map.of("token", jwt);
                    return ServerResponse
                            .ok()
                           // .headers(httpHeaders1 -> httpHeaders1.add(HttpHeaders.AUTHORIZATION, "Bearer " + jwt))
                            .bodyValue(tokenBody);
                            //.bodyValue(new TokenResponse(jwt));

                });
    }
}
