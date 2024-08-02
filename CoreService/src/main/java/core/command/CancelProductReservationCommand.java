package core.command;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CancelProductReservationCommand {

    @TargetAggregateIdentifier
    private String productId;
    private Integer quantity;
    private String orderId;
    private String userId;
    private String reason;
}
