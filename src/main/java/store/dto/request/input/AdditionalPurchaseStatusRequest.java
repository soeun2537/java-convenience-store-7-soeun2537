package store.dto.request.input;

import store.util.CommonParser;
import store.util.CommonValidator;

public class AdditionalPurchaseStatusRequest {

    private final boolean additionalPurchaseStatus;

    private AdditionalPurchaseStatusRequest(boolean additionalPurchaseStatus) {
        this.additionalPurchaseStatus = additionalPurchaseStatus;
    }

    public static AdditionalPurchaseStatusRequest from(String input) {
        CommonValidator.validateNotNull(input);
        CommonValidator.validateYesOrNo(input);
        boolean status = CommonParser.parseBoolean(input);

        return new AdditionalPurchaseStatusRequest(status);
    }

    public boolean isAdditionalPurchaseStatus() {
        return additionalPurchaseStatus;
    }
}
