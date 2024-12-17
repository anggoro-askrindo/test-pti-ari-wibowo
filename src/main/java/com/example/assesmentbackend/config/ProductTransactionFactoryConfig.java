package com.example.assesmentbackend.config;

import com.example.assesmentbackend.service.ProductTransactionFactoryService;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.ServiceLocatorFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductTransactionFactoryConfig {
    @Bean("productFactory")
    public FactoryBean<Object> productServiceLocatorFactoryBean() {
        ServiceLocatorFactoryBean factoryBean = new ServiceLocatorFactoryBean();
        factoryBean.setServiceLocatorInterface(ProductTransactionFactoryService.class);
        return factoryBean;
    }
}
