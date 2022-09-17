package com.posada.santiago.alphapostsandcomments.application.config.jwt;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class JwtProvider {
    private String secretKey="god-saved-the-queen-of-uk";

    //In milliseconds
    private long validTime= 36000000; //10h*
}
