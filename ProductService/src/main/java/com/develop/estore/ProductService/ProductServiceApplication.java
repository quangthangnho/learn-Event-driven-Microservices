package com.develop.estore.ProductService;

import org.axonframework.commandhandling.CommandBus;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import com.develop.estore.ProductService.command.interceptors.CreateProductCommandInterceptor;
import com.develop.estore.ProductService.core.exeption.ProductServiceEventsErrorHandler;

/**
 * @author admin
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Autowired
    public void registerProductCommandInterceptor(ApplicationContext applicationContext, CommandBus commandBus) {
        commandBus.registerDispatchInterceptor(applicationContext.getBean(CreateProductCommandInterceptor.class));
    }

    @Autowired
    public void registerListenerInvocationErrorHandler(EventProcessingConfigurer configurer) {
        configurer.registerListenerInvocationErrorHandler(
                "product-group", conf -> new ProductServiceEventsErrorHandler());

        //        configurer.registerListenerInvocationErrorHandler(
        //                "product-group", conf -> PropagatingErrorHandler.instance());
    }
}
