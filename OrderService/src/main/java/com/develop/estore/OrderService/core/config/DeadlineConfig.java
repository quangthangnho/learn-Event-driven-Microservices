package com.develop.estore.OrderService.core.config;

import org.axonframework.config.ConfigurationScopeAwareProvider;
import org.axonframework.deadline.DeadlineManager;
import org.axonframework.deadline.SimpleDeadlineManager;
import org.axonframework.spring.messaging.unitofwork.SpringTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class DeadlineConfig {

    @Bean
    public SpringTransactionManager springTransactionManager(PlatformTransactionManager transactionManager) {
        return new SpringTransactionManager(transactionManager);
    }

    @Bean
    public DeadlineManager deadlineManager(
            org.axonframework.config.Configuration configuration, SpringTransactionManager springTransactionManager) {
        return SimpleDeadlineManager.builder()
                .scopeAwareProvider(new ConfigurationScopeAwareProvider(configuration))
                .transactionManager(springTransactionManager)
                .build();
    }
}
