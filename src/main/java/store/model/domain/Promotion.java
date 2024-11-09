package store.model.domain;

import java.time.LocalDate;

public class Promotion {

    private String name;
    private Integer requiredCount;
    private Integer giftCount;
    private LocalDate startDate;
    private LocalDate endDate;

    private Promotion(String name,
                      Integer requiredCount,
                      Integer giftCount,
                      LocalDate startDate,
                      LocalDate endDate
    ) {
        this.name = name;
        this.requiredCount = requiredCount;
        this.giftCount = giftCount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public static Promotion of(String name,
                               Integer requiredCount,
                               Integer giftCount,
                               LocalDate startDate,
                               LocalDate endDate
    ) {
        return new Promotion(name, requiredCount, giftCount, startDate, endDate);
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
