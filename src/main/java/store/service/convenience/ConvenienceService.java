package store.service.convenience;

import static store.constant.message.ErrorMessage.*;

import java.util.ArrayList;
import java.util.List;
import store.dto.request.input.PurchaseProductsRequest;
import store.dto.request.input.PurchaseProductsRequest.InnerPurchaseProductRequest;
import store.dto.response.ReceiptResponse;
import store.dto.response.StocksResponse;
import store.dto.server.StatusDto;
import store.model.PromotionManager;
import store.model.StockManager;
import store.model.domain.Stock;
import store.model.domain.Receipt;
import store.model.ReceiptManager;

public class ConvenienceService {

    private final StockManager stockManager;
    private final ReceiptManager receiptManager;
    private final PromotionProcessor promotionProcessor;
    private final GeneralProcessor generalProcessor;

    public ConvenienceService(PromotionManager promotionManager,
                              StockManager stockManager,
                              ReceiptManager receiptManager
    ) {
        this.stockManager = stockManager;
        this.receiptManager = receiptManager;
        PurchaseTransactionHandler transactionHandler =
                new PurchaseTransactionHandler(stockManager, promotionManager, receiptManager);
        this.promotionProcessor = new PromotionProcessor(transactionHandler, promotionManager);
        this.generalProcessor = new GeneralProcessor(transactionHandler);
    }

    public StocksResponse createStocksResponse() {
        return StocksResponse.from(stockManager.getStocks());
    }

    public void createReceipt() {
        receiptManager.createReceipt();
    }

    public List<StatusDto> purchaseProducts(PurchaseProductsRequest purchaseProductsRequest) {
        List<StatusDto> stockDtos = new ArrayList<>();

        for (InnerPurchaseProductRequest request : purchaseProductsRequest.getPurchaseProductsRequests()) {
            List<Stock> stocks = stockManager.findPromotionAndGeneralStocks(request.getProductName());
            validateNonExistentProduct(stocks);
            validateSufficientStocksQuantity(stocks.getFirst().getProductName(), request.getProductQuantity());
            stockDtos.add(purchaseProduct(stocks.getFirst(), request.getProductQuantity()));
        }

        return stockDtos;
    }

    private StatusDto purchaseProduct(Stock stock, Integer quantity) {
        if (stockManager.existsPromotionStock(stock.getProductName())) {
            return promotionProcessor.handlePromotion(stock, quantity);
        }
        return generalProcessor.handleGeneral(stock, quantity);
    }

    public void applyAddingQuantity(StatusDto statusDto) {
        promotionProcessor.processAddingQuantity(statusDto);
    }

    public void applyRegularPricePayment(StatusDto statusDto) {
        promotionProcessor.processRegularPricePayment(statusDto);
    }

    public void applyMembership() {
        Receipt receipt = receiptManager.get();
        receipt.applyMembership();
    }

    public ReceiptResponse createReceiptResponse() {
        Receipt receipt = receiptManager.get();
        if (receipt == null) {
            throw new IllegalStateException(NOT_FOUND_RECEIPT.getMessage());
        }
        return ReceiptResponse.from(receipt);
    }

    private void validateSufficientStocksQuantity(String productName, Integer quantity) {
        Integer totalQuantity = stockManager.calculatePromotionAndGeneralStockQuantity(productName);
        if (totalQuantity < quantity) {
            throw new IllegalArgumentException(INSUFFICIENT_STOCK.getMessage());
        }
    }

    private void validateNonExistentProduct(List<Stock> stocks) {
        if (stocks.isEmpty()) {
            throw new IllegalArgumentException(NOT_FOUND_PRODUCT.getMessage());
        }
    }
}
