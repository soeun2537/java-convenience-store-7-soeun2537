package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.dto.request.input.AddingQuantityStatusRequest;
import store.dto.request.input.PurchaseProductsRequest;

public class InputView {

    public static PurchaseProductsRequest readPurchaseProducts() {
        return PurchaseProductsRequest.from(input());
    }

    public static AddingQuantityStatusRequest readAddingQuantityStatus() {
        return AddingQuantityStatusRequest.from(input());
    }

    private static String input() {
        return Console.readLine();
    }
}
