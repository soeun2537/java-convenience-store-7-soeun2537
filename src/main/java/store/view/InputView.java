package store.view;

import camp.nextstep.edu.missionutils.Console;
import store.dto.request.input.AddingQuantityStatusRequest;
import store.dto.request.input.AdditionalPurchaseStatusRequest;
import store.dto.request.input.MembershipApplicationStatusRequest;
import store.dto.request.input.PurchaseProductsRequest;
import store.dto.request.input.RegularPricePaymentStatusRequest;

public class InputView {

    public static PurchaseProductsRequest readPurchaseProducts() {
        return PurchaseProductsRequest.from(input());
    }

    public static AddingQuantityStatusRequest readAddingQuantityStatus() {
        return AddingQuantityStatusRequest.from(input());
    }

    public static RegularPricePaymentStatusRequest readRegularPricePaymentStatus() {
        return RegularPricePaymentStatusRequest.from(input());
    }

    public static MembershipApplicationStatusRequest readMembershipApplicationStatus() {
        return MembershipApplicationStatusRequest.from(input());
    }

    public static AdditionalPurchaseStatusRequest readAdditionalPurchaseStatus() {
        return AdditionalPurchaseStatusRequest.from(input());
    }

    private static String input() {
        return Console.readLine();
    }
}
