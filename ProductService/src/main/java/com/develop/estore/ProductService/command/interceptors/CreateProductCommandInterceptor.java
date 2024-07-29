package com.develop.estore.ProductService.command.interceptors;

import java.util.List;
import java.util.function.BiFunction;
import javax.annotation.Nonnull;

import org.axonframework.commandhandling.CommandMessage;
import org.axonframework.messaging.MessageDispatchInterceptor;
import org.springframework.stereotype.Component;

import com.develop.estore.ProductService.command.CreateProductCommand;
import com.develop.estore.ProductService.core.data.entity.ProductLookupEntity;
import com.develop.estore.ProductService.core.data.repository.ProductLookupRepository;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CreateProductCommandInterceptor implements MessageDispatchInterceptor<CommandMessage<?>> {

    private final ProductLookupRepository productLookupRepository;

    public CreateProductCommandInterceptor(ProductLookupRepository productLookupRepository) {
        this.productLookupRepository = productLookupRepository;
    }

    @Nonnull
    @Override
    public BiFunction<Integer, CommandMessage<?>, CommandMessage<?>> handle(
            @Nonnull List<? extends CommandMessage<?>> messages) {
        return (index, command) -> {
            log.info("Intercepted command: {}", command.getPayloadType());
            if (CreateProductCommand.class.equals(command.getPayloadType())) {
                CreateProductCommand createProductCommand = (CreateProductCommand) command.getPayload();
                ProductLookupEntity productLookupEntity = productLookupRepository.findByProductIdOrTitle(
                        createProductCommand.getProductId(), createProductCommand.getTitle());
                if (productLookupEntity != null) {
                    throw new IllegalArgumentException(String.format(
                            "Product with productId %s or title %s already exists",
                            createProductCommand.getProductId(), createProductCommand.getTitle()));
                }
            }
            return command;
        };
    }
}
