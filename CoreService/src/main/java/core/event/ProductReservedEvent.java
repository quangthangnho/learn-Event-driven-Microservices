package core.event;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductReservedEvent {

    private final String productId;
    private final String orderId;
    private final Integer quantity;
    private final String userId;
}
