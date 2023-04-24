package ru.rikmasters.gatewayserver;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.handler.RoutePredicateHandlerMapping;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.HashMap;

@Configuration
@Component
@RequiredArgsConstructor
@SuppressWarnings("java:S1313")
public class GatewayConfig {

//    private final AuthFilter authFilter;

    private final String API_DRIVER_PATH = "/drivers/**";
    private final String API_VEHICLE_PATH = "/vehicle/**";

    private final String DRIVER_SERVICE = "driver-service";
    private final String VEHICLE_SERVICE = "vehicle-service";


    @Bean
    public RouteLocator routesDev(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(DRIVER_SERVICE, predicateSpec -> predicateSpec.path(API_DRIVER_PATH)
//                        .filters(gatewayFilterSpec -> gatewayFilterSpec.filter(filter))
                        .uri("lb://driver-service/"))
                .route(VEHICLE_SERVICE, predicateSpec -> predicateSpec.path(API_VEHICLE_PATH)
//                        .filters(gatewayFilterSpec -> gatewayFilterSpec.filter(filter))
                        .uri("lb://vehicle-service/"))
                .build();
    }

    @Bean
    //disable cors, fix preflight options requests error
    public CorsConfiguration corsConfiguration(RoutePredicateHandlerMapping routePredicateHandlerMapping) {
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        Arrays.asList(HttpMethod.OPTIONS, HttpMethod.PUT, HttpMethod.GET, HttpMethod.DELETE, HttpMethod.POST)
                .forEach(corsConfiguration::addAllowedMethod);
        corsConfiguration.addAllowedOrigin("*");
        routePredicateHandlerMapping.setCorsConfigurations(new HashMap<String, CorsConfiguration>() {{
            put("/**", corsConfiguration);
        }});
        return corsConfiguration;
    }
}
