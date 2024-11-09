package store.view;

import static store.constant.OutputMessage.*;

import java.util.List;
import store.dto.response.StocksResponse;
import store.dto.response.StocksResponse.InnerStockResponse;

public class OutputView {

    public static void printStartGuidance() {
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

    private static void println(String content) {
        System.out.println(content);
    }

    private static void printNewLine() {
        System.out.print(System.lineSeparator());
    }
}
