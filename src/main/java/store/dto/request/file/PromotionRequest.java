package store.dto.request.file;

import java.time.LocalDate;
import java.util.List;
import store.util.CommonParser;
import store.util.CommonValidator;

public class PromotionRequest {

    private final String name;
    private final Integer requiredCount;
    private final Integer giftCount;
    private final LocalDate startDate;
    private final LocalDate endDate;

    private PromotionRequest(String name, Integer requiredCount, Integer giftCount,
                             LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.requiredCount = requiredCount;
        this.giftCount = giftCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static PromotionRequest from(String line) {
        List<String> separatedLine = CommonParser.separateBySeparator(line, ",");

        String name = parseName(separatedLine);
        Integer requiredCount = parseRequiredCount(separatedLine);
        Integer giftCount = parseGiftCount(separatedLine);
        LocalDate startDate = parseStartDate(separatedLine);
        LocalDate endDate = parseEndDate(separatedLine);

        return new PromotionRequest(name, requiredCount, giftCount, startDate, endDate);
    }

    private static String parseName(List<String> line) {
        String name = line.get(0);
        CommonValidator.validateNotNull(name);
        return name;
    }

    private static Integer parseRequiredCount(List<String> line) {
        String purchaseRequiredCount = line.get(1);
        CommonValidator.validateNotNull(purchaseRequiredCount);
        CommonValidator.validateNumeric(purchaseRequiredCount);
        return CommonParser.convertStringToInteger(purchaseRequiredCount);
    }

    private static Integer parseGiftCount(List<String> line) {
        String giftCount = line.get(2);
        CommonValidator.validateNotNull(giftCount);
        CommonValidator.validateNumeric(giftCount);
        return CommonParser.convertStringToInteger(giftCount);
    }

    private static LocalDate parseStartDate(List<String> line) {
        String startDate = line.get(3);
        CommonValidator.validateNotNull(startDate);
        CommonValidator.validateDate(startDate);
        return CommonParser.parseDate(startDate);
    }

    private static LocalDate parseEndDate(List<String> line) {
        String endDate = line.get(4);
        CommonValidator.validateNotNull(endDate);
        CommonValidator.validateDate(endDate);
        return CommonParser.parseDate(endDate);
    }

    public String getName() {
        return name;
    }

    public Integer getRequiredCount() {
        return requiredCount;
    }

    public Integer getGiftCount() {
        return giftCount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
