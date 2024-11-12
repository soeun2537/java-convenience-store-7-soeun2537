package store.dto.request.file;

import static store.constant.InputConstant.PRODUCT_SEPARATOR;

import java.util.List;
import store.util.CommonParser;
import store.util.CommonValidator;

public class StockRequest {

    private final String name;
    private final Integer price;
    private final Integer quantity;
    private final String promotionName;

    private StockRequest(String name, Integer price, Integer quantity, String promotionName) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotionName = promotionName;
    }

    public static StockRequest from(String line) {
        List<String> separatedLine = CommonParser.separateBySeparator(line, PRODUCT_SEPARATOR.getContent());

        String name = parseName(separatedLine);
        Integer price = parsePrice(separatedLine);
        Integer quantity = parseQuantity(separatedLine);
        String promotionName = parsePromotionName(separatedLine);

        return new StockRequest(name, price, quantity, promotionName);
    }

    private static String parseName(List<String> line) {
        String name = line.get(0);
        CommonValidator.validateNotNull(name);
        return name;
    }

    private static Integer parsePrice(List<String> line) {
        String price = line.get(1);
        CommonValidator.validateNotNull(price);
        CommonValidator.validateNumeric(price);
        return CommonParser.convertStringToInteger(price);
    }

    private static Integer parseQuantity(List<String> line) {
        String quantity = line.get(2);
        CommonValidator.validateNotNull(quantity);
        CommonValidator.validateNumeric(quantity);
        return CommonParser.convertStringToInteger(quantity);
    }

    private static String parsePromotionName(List<String> line) {
        return line.get(3);
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getPromotionName() {
        return promotionName;
    }
}
