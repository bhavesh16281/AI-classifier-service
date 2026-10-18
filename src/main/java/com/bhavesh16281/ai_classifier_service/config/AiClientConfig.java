package com.bhavesh16281.ai_classifier_service.config;
import org.springframework.boot.restclient.RestClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;

@Configuration 
public class AiClientConfig {
    
    @Bean
    public  RestClientCustomizer restClientCustomizer() {
        return restTemplate -> {

            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            requestFactory.setConnectTimeout(5000); // Set connection timeout (in milliseconds)
            requestFactory.setReadTimeout(8000);    // Set read timeout (in milliseconds)

            restTemplate.requestFactory(requestFactory);
        };
    }
}
