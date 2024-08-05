package core.query;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FetchUserPaymentDetailsQuery {

    private String userId;
    private String orderId;
}
