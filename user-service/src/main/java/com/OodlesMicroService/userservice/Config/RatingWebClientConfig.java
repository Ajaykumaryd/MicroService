package com.OodlesMicroService.userservice.Config;


import com.OodlesMicroService.userservice.Client.RatingClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.LoadBalancedExchangeFilterFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class RatingWebClientConfig {

    @Autowired
    private LoadBalancedExchangeFilterFunction filterFunction;

    @Bean
    public WebClient ratingWebClient() {
        return WebClient.builder()
                .baseUrl("http://rating-service")
                .filter(filterFunction)
                .build();
    }
    @Bean
    public RatingClient RatingClient() {
        HttpServiceProxyFactory httpServiceProxyFactory
                = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(ratingWebClient()))
                .build();
        return httpServiceProxyFactory.createClient(RatingClient.class);
    }









}
