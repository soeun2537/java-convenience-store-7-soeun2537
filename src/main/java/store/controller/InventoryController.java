package store.controller;

import static store.constant.PathConstant.*;

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
