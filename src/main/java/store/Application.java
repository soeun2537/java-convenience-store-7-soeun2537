package store;

import store.controller.ConvenienceController;
import store.controller.InventoryController;
import store.model.PromotionManager;
import store.model.StockManager;
import store.model.ReceiptManager;
import store.service.convenience.ConvenienceService;
import store.service.inventory.InventoryService;

public class Application {
    public static void main(String[] args) {
        PromotionManager promotionManager = PromotionManager.getInstance();
        StockManager stockManager = StockManager.getInstance();
        ReceiptManager receiptManager = new ReceiptManager();
        stockManager.clearStocks();

        InventoryService inventoryService = new InventoryService(promotionManager, stockManager);
        ConvenienceService convenienceService = new ConvenienceService(promotionManager, stockManager, receiptManager);
        InventoryController inventoryController = new InventoryController(inventoryService);
        ConvenienceController convenienceController = new ConvenienceController(convenienceService);
        start(inventoryController, convenienceController);
    }

    private static void start(InventoryController inventoryController, ConvenienceController convenienceController) {
        inventoryController.setup();
        convenienceController.run();
    }
}
