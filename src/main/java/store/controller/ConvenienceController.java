package store.controller;

import static store.constant.ConvenienceConstant.*;

import java.util.List;
import java.util.function.Supplier;
import store.model.Status;
import store.dto.request.input.AddingQuantityStatusRequest;
import store.dto.request.input.AdditionalPurchaseStatusRequest;
import store.dto.request.input.MembershipApplicationStatusRequest;
import store.dto.request.input.PurchaseProductsRequest;
import store.dto.request.input.RegularPricePaymentStatusRequest;
import store.dto.response.ReceiptResponse;
import store.dto.response.StocksResponse;
import store.dto.server.StatusDto;
import store.service.convenience.ConvenienceService;
import store.view.InputView;
import store.view.OutputView;

public class ConvenienceController {

    private final ConvenienceService convenienceService;

    public ConvenienceController(ConvenienceService convenienceService) {
        this.convenienceService = convenienceService;
    }

    public void run() {
        do {
            execute(this::processStart);
            execute(() -> processStatuses(processPurchaseProducts()));
            execute(this::processMembershipApplicationStatus);
            execute(this::processReceipt);
        } while (execute(this::processAdditionalPurchaseStatus));
    }

    private void processStart() {
        OutputView.printStartGuidance();
        StocksResponse stocksResponse = convenienceService.createStocksResponse();
        OutputView.printStocks(stocksResponse);
    }

    private List<StatusDto> processPurchaseProducts() {
        OutputView.printPurchaseProductsGuidance();
        PurchaseProductsRequest purchaseProductsRequest = InputView.readPurchaseProducts();
        convenienceService.createReceipt();
        return convenienceService.purchaseProducts(purchaseProductsRequest);
    }

    private void processStatuses(List<StatusDto> statusDtos) {
        for (StatusDto statusDto : statusDtos) {
            if (statusDto.getStatus().equals(Status.ADDING_QUANTITY)) {
                execute(() -> processAddingQuantityStatus(statusDto));
                continue;
            }
            if (statusDto.getStatus().equals(Status.REGULAR_PRICE_PAYMENT)) {
                execute(() -> processRegularPricePaymentStatus(statusDto));
            }
        }
    }

    private void processAddingQuantityStatus(StatusDto statusDto) {
        OutputView.printAddingQuantityStatusGuidance(statusDto.getProductName(), statusDto.getQuantity());
        AddingQuantityStatusRequest addingQuantityStatusRequest = InputView.readAddingQuantityStatus();
        if (addingQuantityStatusRequest.isAddingQuantityStatus()) {
            convenienceService.applyAddingQuantity(statusDto);
        }
    }

    private void processRegularPricePaymentStatus(StatusDto statusDto) {
        OutputView.printRegularPricePaymentStatusGuidance(statusDto.getProductName(), statusDto.getQuantity());
        RegularPricePaymentStatusRequest regularPricePaymentStatusRequest = InputView.readRegularPricePaymentStatus();
        if (regularPricePaymentStatusRequest.isRegularPricePaymentStatus()) {
            convenienceService.applyRegularPricePayment(statusDto);
        }
    }

    private void processMembershipApplicationStatus() {
        OutputView.printMembershipApplicationStatusGuidance();
        MembershipApplicationStatusRequest membershipApplicationStatusRequest = InputView.readMembershipApplicationStatus();
        if (membershipApplicationStatusRequest.isMembershipApplicationStatus()) {
            convenienceService.applyMembership();
        }
    }

    private void processReceipt() {
        ReceiptResponse receiptResponse = convenienceService.createReceiptResponse();
        OutputView.printReceipt(receiptResponse);
    }

    private boolean processAdditionalPurchaseStatus() {
        OutputView.printAdditionalPurchaseStatusGuidance();
        AdditionalPurchaseStatusRequest additionalPurchaseStatusRequest = InputView.readAdditionalPurchaseStatus();
        return additionalPurchaseStatusRequest.isAdditionalPurchaseStatus();
    }

    private void execute(Runnable action) {
        execute(action, 0);
    }

    private void execute(Runnable action, int attempts) {
        try {
            action.run();
        } catch (RuntimeException e) {
            if (attempts == MAX_RETRIES) {
                OutputView.printErrorMessage(e);
                return;
            }
            OutputView.printErrorMessage(e);
            execute(action, attempts + 1);
        }
    }

    private boolean execute(Supplier<Boolean> action) {
        return execute(action, 0);
    }

    private boolean execute(Supplier<Boolean> action, int attempts) {
        try {
            return action.get();
        } catch (RuntimeException e) {
            if (attempts == MAX_RETRIES) {
                OutputView.printErrorMessage(e);
                return false;
            }
            OutputView.printErrorMessage(e);
            return execute(action, attempts + 1);
        }
    }
}
