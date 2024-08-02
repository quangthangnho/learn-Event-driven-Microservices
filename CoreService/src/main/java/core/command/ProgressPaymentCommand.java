package core.command;

import core.dto.PaymentDetails;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ProgressPaymentCommand {
    @TargetAggregateIdentifier
    private String paymentId;
    private String orderId;
    private PaymentDetails paymentDetails;
}
