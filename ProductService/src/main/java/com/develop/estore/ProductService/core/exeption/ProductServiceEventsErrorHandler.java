package com.develop.estore.ProductService.core.exeption;

import javax.annotation.Nonnull;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

public class ProductServiceEventsErrorHandler implements ListenerInvocationErrorHandler {
    @Override
    public void onError(
            @Nonnull Exception exception, @Nonnull EventMessage<?> event, @Nonnull EventMessageHandler eventHandler)
            throws Exception {
        throw exception;
    }
}
