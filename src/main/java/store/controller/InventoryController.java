package store.controller;

import static store.constant.PathConstant.PRODUCT_FILE_PATH;
import static store.constant.PathConstant.PROMOTION_FILE_PATH;

import store.service.inventory.InventoryService;

public class InventoryController {

    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void setup() {
        inventoryService.setupPromotions(PROMOTION_FILE_PATH.getPath());
        inventoryService.setupStocks(PRODUCT_FILE_PATH.getPath());
    }
}
