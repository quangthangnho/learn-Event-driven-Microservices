package com.develop.estore.ProductService.core.exeption;

import java.time.OffsetDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorRes {

    private OffsetDateTime timestamp;
    private String message;

    public CustomErrorRes(String message) {
        this.timestamp = OffsetDateTime.now();
        this.message = message;
    }
}
