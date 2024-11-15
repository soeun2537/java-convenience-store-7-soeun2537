package store.view;

import static store.constant.message.OutputMessage.START_GUIDANCE;
import static store.constant.message.OutputMessage.EXISTING_STOCKS_GUIDANCE;
import static store.constant.message.OutputMessage.EXISTING_STOCKS;
import static store.constant.message.OutputMessage.PURCHASE_PRODUCTS_GUIDANCE;
import static store.constant.message.OutputMessage.ADDING_QUANTITY_STATUS_GUIDANCE;
import static store.constant.message.OutputMessage.REGULAR_PRICE_PAYMENT_STATUS_GUIDANCE;
import static store.constant.message.OutputMessage.MEMBERSHIP_APPLICATION_STATUS_GUIDANCE;
import static store.constant.message.OutputMessage.ADDITIONAL_PURCHASE_STATUS_GUIDANCE;
import static store.constant.message.OutputMessage.RECEIPT_TITLE_HEADER;
import static store.constant.message.OutputMessage.RECEIPT_PURCHASE_PRODUCTS_HEADER;
import static store.constant.message.OutputMessage.RECEIPT_PURCHASE_PRODUCT_LINE;
import static store.constant.message.OutputMessage.RECEIPT_GIFTS_HEADER;
import static store.constant.message.OutputMessage.RECEIPT_GIFT_LINE;
import static store.constant.message.OutputMessage.RECEIPT_DIVISION_HEADER;
import static store.constant.message.OutputMessage.RECEIPT_TOTAL_PURCHASE_AMOUNT;
import static store.constant.message.OutputMessage.RECEIPT_PROMOTION_DISCOUNT;
import static store.constant.message.OutputMessage.RECEIPT_MEMBERSHIP_DISCOUNT;
import static store.constant.message.OutputMessage.RECEIPT_FINAL_AMOUNT;

import java.util.List;
import store.dto.response.ReceiptResponse;
import store.dto.response.ReceiptResponse.InnerReceiptStockResponse;
import store.dto.response.StocksResponse;
import store.dto.response.StocksResponse.InnerStockResponse;

public class OutputView {

    public static void printStartGuidance() {
        printNewLine();
        println(START_GUIDANCE.getMessage());
    }

    public static void printStocks(StocksResponse response) {
        println(EXISTING_STOCKS_GUIDANCE.getMessage());
        printNewLine();
        printStockDetails(response.getStocksResponse());
    }

    private static void printStockDetails(List<InnerStockResponse> stockResponses) {
        for (InnerStockResponse stockResponse : stockResponses) {
            println(EXISTING_STOCKS.getMessage(
                    stockResponse.getName(),
                    stockResponse.getPrice(),
                    stockResponse.getQuantity(),
                    stockResponse.getPromotionName()
            ));
        }
    }

    public static void printPurchaseProductsGuidance() {
        printNewLine();
        println(PURCHASE_PRODUCTS_GUIDANCE.getMessage());
    }

    public static void printAddingQuantityStatusGuidance(String productName, Integer productQuantity) {
        printNewLine();
        println(ADDING_QUANTITY_STATUS_GUIDANCE.getMessage(productName, productQuantity));
    }

    public static void printRegularPricePaymentStatusGuidance(String productName, Integer productQuantity) {
        printNewLine();
        println(REGULAR_PRICE_PAYMENT_STATUS_GUIDANCE.getMessage(productName, productQuantity));
    }

    public static void printMembershipApplicationStatusGuidance() {
        printNewLine();
        println(MEMBERSHIP_APPLICATION_STATUS_GUIDANCE.getMessage());
    }

    public static void printAdditionalPurchaseStatusGuidance() {
        printNewLine();
        println(ADDITIONAL_PURCHASE_STATUS_GUIDANCE.getMessage());
    }

    public static void printReceipt(ReceiptResponse response) {
        printNewLine();
        println(RECEIPT_TITLE_HEADER.getMessage());
        printPurchaseProducts(response.getPurchaseStocks());
        printGiftProducts(response.getGiftStocks());
        printReceiptSummary(response);
    }

    private static void printPurchaseProducts(List<InnerReceiptStockResponse> purchaseStocks) {
        println(RECEIPT_PURCHASE_PRODUCTS_HEADER.getMessage());
        for (InnerReceiptStockResponse purchaseStock : purchaseStocks) {
            println(RECEIPT_PURCHASE_PRODUCT_LINE.getMessage(
                    purchaseStock.getProductName(),
                    purchaseStock.getQuantity(),
                    purchaseStock.getTotalPrice()
            ));
        }
    }

    private static void printGiftProducts(List<InnerReceiptStockResponse> giftStocks) {
        println(RECEIPT_GIFTS_HEADER.getMessage());
        for (InnerReceiptStockResponse giftStock : giftStocks) {
            println(RECEIPT_GIFT_LINE.getMessage(
                    giftStock.getProductName(),
                    giftStock.getQuantity()
            ));
        }
    }

    private static void printReceiptSummary(ReceiptResponse response) {
        println(RECEIPT_DIVISION_HEADER.getMessage());
        println(RECEIPT_TOTAL_PURCHASE_AMOUNT.getMessage(
                response.getTotalPurchaseQuantity(),
                response.getTotalPurchaseAmount()
        ));
        println(RECEIPT_PROMOTION_DISCOUNT.getMessage(response.getPromotionDiscount()));
        println(RECEIPT_MEMBERSHIP_DISCOUNT.getMessage(response.getMembershipDiscount()));
        println(RECEIPT_FINAL_AMOUNT.getMessage(response.getFinalAmount()));
    }

    public static void printErrorMessage(RuntimeException e) {
        printNewLine();
        println(e.getMessage());
    }

    private static void println(String content) {
        System.out.println(content);
    }

    private static void printNewLine() {
        System.out.print(System.lineSeparator());
    }
}
