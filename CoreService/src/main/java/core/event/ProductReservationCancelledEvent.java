package core.event;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductReservationCancelledEvent {

    private String productId;
    private Integer quantity;
    private String orderId;
    private String userId;
    private String reason;
}
