package store.model.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import store.constant.TestPathConstant;
import store.model.PromotionManager;
import store.model.StockManager;
import store.model.domain.Receipt.InnerReceipt;
import store.service.inventory.InventoryService;

class ReceiptTest {

    private Receipt receipt;
    private PromotionManager promotionManager;

    @BeforeEach
    void beforeEach() {
        promotionManager = PromotionManager.getInstance();
        StockManager stockManager = StockManager.getInstance();
        stockManager.clearStocks();

        InventoryService inventoryService = new InventoryService(promotionManager, stockManager);
        inventoryService.setupPromotions(TestPathConstant.PROMOTION_FILE_PATH.getPath());
        inventoryService.setupStocks(TestPathConstant.PRODUCT_FILE_PATH.getPath());

        receipt = Receipt.createAndInitialize();
    }

    @Test
    @DisplayName("영수증 생성 및 초기화 확인")
    void createAndInitialize() {
        // when
        Receipt newReceipt = Receipt.createAndInitialize();

        // then
        assertThat(newReceipt.getPurchasedStocks()).isEmpty();
        assertThat(newReceipt.getGiftStocks()).isEmpty();
        assertThat(newReceipt.calculateMembershipDiscount()).isEqualTo(0);
    }

    @Test
    @DisplayName("구매한 상품을 추가")
    void addPurchasedStock() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 3;
        Promotion promotion = promotionManager.findPromotion("탄산2+1").get();

        // when
        receipt.addPurchasedStock(product, quantity, promotion);

        // then
        InnerReceipt purchasedStock = receipt.getPurchasedStocks().getFirst();
        assertThat(purchasedStock.getProductName()).isEqualTo("콜라");
        assertThat(purchasedStock.getQuantity()).isEqualTo(3);
    }

    @Test
    @DisplayName("증정 상품을 추가")
    void addGiftStock() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 3;
        Promotion promotion = promotionManager.findPromotion(product.getPromotionName()).get();

        // when
        receipt.addGiftStock(product, quantity, promotion);

        // then
        InnerReceipt purchasedStock = receipt.getGiftStocks().getFirst();
        assertThat(purchasedStock.getProductName()).isEqualTo("콜라");
        assertThat(purchasedStock.getQuantity()).isEqualTo(3);
    }

    @Test
    @DisplayName("멤버십 적용 확인: true")
    void applyMembership_true() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer purchaseQuantity = 5;
        Integer giftQuantity = 1;
        Promotion promotion = promotionManager.findPromotion(product.getPromotionName()).get();
        receipt.addPurchasedStock(product, purchaseQuantity, promotion);
        receipt.addGiftStock(product, giftQuantity, promotion);

        // when
        receipt.applyMembership();

        // then
        assertThat(receipt.calculateMembershipDiscount()).isNotZero();
    }

    @Test
    @DisplayName("멤버십 적용 확인: false")
    void applyMembership_false() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer purchaseQuantity = 5;
        Integer giftQuantity = 1;
        Promotion promotion = promotionManager.findPromotion(product.getPromotionName()).get();
        receipt.addPurchasedStock(product, purchaseQuantity, promotion);
        receipt.addGiftStock(product, giftQuantity, promotion);

        // when
//        receipt.applyMembership();

        // then
        assertThat(receipt.calculateMembershipDiscount()).isZero();
    }

    @Test
    @DisplayName("총 구매 수량 계산")
    void calculateTotalPurchaseQuantity() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 5;
        Promotion promotion = promotionManager.findPromotion(product.getPromotionName()).get();
        receipt.addPurchasedStock(product, quantity, promotion);
        receipt.addPurchasedStock(product, quantity, promotion);

        // when & then
        assertThat(receipt.calculateTotalPurchaseQuantity()).isEqualTo(10);
    }

    @Test
    @DisplayName("총 구매액 계산")
    void calculateTotalPurchaseAmount() {
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 5;
        Promotion promotion = promotionManager.findPromotion(product.getPromotionName()).get();
        receipt.addPurchasedStock(product, quantity, promotion);
        receipt.addPurchasedStock(product, quantity, promotion);

        // when & then
        assertThat(receipt.calculateTotalPurchaseAmount()).isEqualTo(10000);
    }

    @Test
    @DisplayName("행사 할인 금액 계산")
    void calculatePromotionDiscount() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer quantity = 1;
        Promotion promotion = promotionManager.findPromotion(product.getPromotionName()).get();
        receipt.addGiftStock(product, quantity, promotion);

        // when & then
        assertThat(receipt.calculatePromotionDiscount()).isEqualTo(1000);
    }

    @Test
    @DisplayName("멤버십 할인 금액 계산")
    void calculateMembershipDiscount() {
        // given
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer purchaseQuantity = 4;
        Integer giftQuantity = 1;
        Promotion promotion = promotionManager.findPromotion(product.getPromotionName()).get();
        receipt.addPurchasedStock(product, purchaseQuantity, promotion);
        receipt.addGiftStock(product, giftQuantity, promotion);
        receipt.applyMembership();

        // when & then
        assertThat(receipt.calculateMembershipDiscount()).isEqualTo(300);
    }

    @Test
    @DisplayName("최종 결제 금액 계산")
    void calculateFinalAmount() {
        Product product = Product.of("콜라", 1000, "탄산2+1");
        Integer purchaseQuantity = 4;
        Integer giftQuantity = 1;
        Promotion promotion = promotionManager.findPromotion(product.getPromotionName()).get();
        receipt.addPurchasedStock(product, purchaseQuantity, promotion);
        receipt.addGiftStock(product, giftQuantity, promotion);
        receipt.applyMembership();

        // when & then
        assertThat(receipt.calculateFinalAmount()).isEqualTo(2700);
    }
}
