package store.dto.request.input;

import store.util.CommonParser;
import store.util.CommonValidator;

public class AddingQuantityStatusRequest {

    private final boolean addingQuantityStatus;

    private AddingQuantityStatusRequest(boolean addingQuantityStatus) {
        this.addingQuantityStatus = addingQuantityStatus;
    }

    public static AddingQuantityStatusRequest from(String input) {
        CommonValidator.validateNotNull(input);
        CommonValidator.validateYesOrNo(input);
        boolean status = CommonParser.parseBoolean(input);

        return new AddingQuantityStatusRequest(status);
    }

    public boolean isAddingQuantityStatus() {
        return addingQuantityStatus;
    }
}
