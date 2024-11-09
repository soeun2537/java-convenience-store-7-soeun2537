package store.dto.request.input;

import store.util.CommonParser;
import store.util.CommonValidator;

public class RegularPricePaymentStatusRequest {

    private final boolean regularPricePaymentStatus;

    private RegularPricePaymentStatusRequest(boolean regularPricePaymentStatus) {
        this.regularPricePaymentStatus = regularPricePaymentStatus;
    }

    public static RegularPricePaymentStatusRequest from(String input) {
        CommonValidator.validateNotNull(input);
        CommonValidator.validateYesOrNo(input);
        boolean status = CommonParser.parseBoolean(input);

        return new RegularPricePaymentStatusRequest(status);
    }

    public boolean isRegularPricePaymentStatus() {
        return regularPricePaymentStatus;
    }
}
