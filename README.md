# 프리코스 4주차 과제

## ✏️ 구현 기능 목록

### ✔️ 정상 입력 처리

#### 입력

- 구매할 상품명과 수량을 입력받는다.
    - 상품명과 수량은 하이픈(-)으로 구분하고, 개별 상품은 대괄호로([]) 묶어 쉼표(,)로 구분한다.
    - 수량을 문자열에서 숫자로 변환한다.
    - 양 끝에 공백이 포함된 경우, 공백을 제거한다.
    - 상품명에 대한 유효성을 검증한다.
        - 상품명은 문자여야 한다.
        - 상품명은 비어있을 수 없다.
        - 상품명은 보유하고 있는 상품 목록에 존재해야 한다.
    - 수량에 대한 유효성을 검증한다.
        - 수량은 숫자여야 한다.
        - 수량은 음수일 수 없다.
        - 수량은 0일 수 없다.
        - 수량은 비어있을 수 없다.
- 프로모션 적용 가능 수량보다 적게 구매한 경우, 해당 수량만큼 추가 여부를 입력받는다.
    - 추가 수량 여부에 대한 유효성을 검증한다.
        - 추가 수량 여부는 문자여야 한다.
        - 추가 수량 여부는 'Y' 또는 'N' 형식이어야 한다.
        - 추가 수량 여부는 비어있을 수 없다.
- 프로모션 재고 부족 시 일부 수량 정가 결제 여부를 입력받는다.
    - 정가 결제 여부에 대한 유효성을 검증한다.
        - 정가 결제 여부는 문자여야 한다.
        - 정가 결제 여부는 'Y' 또는 'N' 형식이어야 한다.
        - 정가 결제 여부는 비어있을 수 없다.
- 멤버십 적용 여부를 입력받는다.
    - 멤버십 적용 여부에 대한 유효성을 검증한다.
        - 멤버십 적용 여부는 문자여야 한다.
        - 멤버십 적용 여부는 'Y' 또는 'N' 형식이어야 한다.
        - 멤버십 적용 여부는 비어있을 수 없다.
- 추가 구매 여부를 입력받는다.
    - 추가 구매 여부에 대한 유효성을 검증한다.
        - 추가 구매 여부는 문자여야 한다.
        - 추가 구매 여부는 'Y' 또는 'N' 형식이어야 한다.
        - 추가 구매 여부는 비어있을 수 없다.

#### 출력

- 환영 인사를 출력한다.
- 보유하고 있는 상품명, 가격, 프로모션, 재고 안내 메시지를 출력한다.
    - 가격은 1000 단위마다 쉼표(,)로 구분한다.
    - 재고가 0이면 "재고 없음"을 출력한다.
- 프로모션 적용 가능 수량보다 적게 구매한 경우 프로모션 혜택 안내 메시지를 출력한다.
- 프로모션 재고 부족 시 정가 결제 여부 안내 메시지를 출력한다.
- 멤버십 할인 적용 여부 안내 메시지를 출력한다.
- 영수증을 출력한다.
    - 구매 상품 내역
    - 증정 상품 내역
    - 금액 정보: 총 구매액, 행사 할인 금액, 멤버십 할인 금액, 최종 결제 금액
- 추가 구매 여부 안내 메시지를 출력한다.

#### 기능

- 상품 목록과 프로모션 파일을 불러온다.
- 입력한 상품명과 수량을 파싱한다.
- 오늘 날짜가 프로모션 기간 내에 포함되는지 확인한다.
- 프로모션 재고 + 정가 재고가 구매 요청 개수 이상인지 확인한다.
- 프로모션 수량보다 적게 구매한 경우를 체크한다.
- 프로모션 재고가 부족하여 일부 수량을 정가로 결제해야 하는 경우를 체크한다.
- 프로모션 재고 내에서 가능한 만큼 우선 적용하며, 프로모션 재고가 부족할 경우 일반 재고를 추가로 차감한다.
- 상품 구매 후 해당 상품 재고를 차감한다.
- 멤버십 회원일 경우 프로모션 미적용 금액의 30%를 할인받는다.
- 멤버십 할인 금액이 8,000원을 초과하면 8,000원까지만 할인을 적용한다.
- 할인 적용 전 총 금액을 계산한다.
- 프로모션 할인 금액을 계산한다.
- 멤버십 할인 금액을 계산한다.
- 최종 결제 금액을 계산한다.

### ⚠️ 예외

IllegalArgumentException을 발생시키고, "\[ERROR]"로 시작하는 오류 메시지를 출력한다.

- 잘못된 형식의 입력: \[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.
- 존재하지 않는 상품 입력: \[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.
- 재고 초과 구매 시: \[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.
- 기타 잘못된 입력: \[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.

#### 검증 항목

- 상품명이 문자가 아닐 경우
- 상품명이 비어있을 경우
- 존재하지 않는 상품명을 입력했을 경우
- 수량이 숫자가 아닐 경우
- 수량이 음수일 경우
- 수량이 0일 경우
- 수량이 비어있을 경우
- 재고 초과 수량을 입력했을 경우
- 상품명과 수량이 올바르지 않은 형식으로 입력된 경우
- 추가 여부가 문자가 아닐 경우
- 추가 여부가 'Y' 또는 'N' 형식이 아닐 경우
- 추가 여부가 비어있을 경우
- 정가 결제 여부가 문자가 아닐 경우
- 정가 결제 여부가 'Y' 또는 'N' 형식이 아닐 경우
- 정가 결제 여부가 비어있을 경우
- 멤버십 할인 적용 여부가 문자가 아닐 경우
- 멤버십 할인 적용 여부가 'Y' 또는 'N' 형식이 아닐 경우
- 멤버십 할인 적용 여부가 비어 있을 수 경우
- 추가 구매 여부가 문자가 아닐 경우
- 추가 구매 여부가 'Y' 또는 'N' 형식이 아닐 경우
- 추가 구매 여부가 비어있을 경우

<br>

## 📌 최종 기능 명세

### Controller

| Class                  | Method | Input | Output | Description      |
|------------------------|--------|-------|--------|------------------|
| ConvenienceController	 | run	   | 	     | 	      | 편의점의 주요 프로세스를 실행 |
| InventoryController    | setup  |       |        | 상품 및 프로모션을 설정    |

### model.domain

| Class     | Method                           | Input                       | Output  | Description           |
|-----------|----------------------------------|-----------------------------|---------|-----------------------|
| Product   | of                               |                             |         | 새로운 상품 인스턴스 생성        |
| Promotion | of                               |                             |         | 새로운 프로모션 인스턴스 생성      |
| Stock     | of                               |                             |         | 새로운 재고 인스턴스 생성        |
|           | addQuantity                      |                             |         | 재고의 수량 추가             |
|           | reduceQuantity                   |                             |         | 재고의 수량 차감             |
| Receipt   | createAndInitialize              |                             |         | 새로운 영수증 인스턴스 생성 및 초기화 |
|           | addPurchasedStock                | Product, Integer, Promotion |         | 구매한 상품을 영수증에 추가       |
|           | addGiftStock                     | Product, Integer, Promotion |         | 증정 상품을 영수증에 추가        |
|           | applyMembership	                 |                             |         | 멤버십 할인 적용 설정          |
|           | calculateTotalPurchaseQuantity		 |                             | Integer | 구매한 상품의 총 수량을 계산      |
|           | calculateTotalPurchaseAmount		   |                             | Integer | 구매한 상품의 총 금액을 계산      |
|           | calculatePromotionDiscount		     |                             | Integer | 프로모션 할인 금액 계산         |
|           | calculateMembershipDiscount		    |                             | Integer | 멤버십 할인 금액 계산          |
|           | calculateFinalAmount		           |                             | Integer | 최종 결제 금액 계산           |

### model

| Class            | Method                                    | Input            | Output                | Description               |
|------------------|-------------------------------------------|------------------|-----------------------|---------------------------|
| PromotionManager | getInstance                               | 	                | PromotionManager      | 싱글톤 인스턴스 반환               |
|                  | addPromotion                              | Promotion	       |                       | 프로모션을 관리 목록에 추가           |
|                  | findPromotion                             | String           | Optional\<Promotion>	 | 프로모션 이름을 통해 해당 프로모션 조회    |
|                  | validateWithinPeriod	                     | Promotion        | boolean               | 지정된 프로모션이 유효 기간 내에 있는지 확인 |
| StockManager     | getInstance                               |                  | StockManager          | 싱글톤 인스턴스 반환               |
|                  | addStock                                  | Stock            |                       | 재고를 관리 목록에 추가             |
|                  | clearStocks                               |                  |                       | 관리 목록 초기화                 |
|                  | getProductNames                           |                  | Set\<String>          | 중복되지 않는 상품 이름 반환          |
|                  | reduceStockQuantity                       | Product, Integer |                       | 재고 차감(프로모션 재고 우선 차감)      |
|                  | existsPromotionStock                      | String           | boolean               | 프로모션 재고 존재 여부 반환          |
|                  | findPromotionAndGeneralStocks             | String           | List\<Stock>          | 상품 이름으로 존재하는 모든 재고 반환     |
|                  | calculatePromotionAndGeneralStockQuantity | String           | Integer               | 상품 이름으로 존재한 모든 재고 수량 합 반환 |
| ReceiptManager   | createReceipt                             |                  |                       | 영수증 생성                    |
|                  | get                                       |                  | Receipt               | 생성된 영수증 인스턴스 반환           |
| Status           | 		                                        |                  |                       | 결제 상태 표현                  |

### service

| Class                      | Method                     | Input                     | Output           | Description        |
|----------------------------|----------------------------|---------------------------|------------------|--------------------|
| ConvenienceService         | createStocksResponse	      | 	                         | StocksResponse	  | 현재 재고 응답 생성        |
|                            | createReceipt              | 	                         | 	                | 	새로운 영수증 생성        |
|                            | purchaseProducts	          | PurchaseProductsRequest   | List\<StatusDto> | 	상품 구매를 처리하고 상태 반환 |
|                            | applyAddingQuantity        | 	StatusDto                | 		               | 추가 수량 결제 적용        |
|                            | applyRegularPricePayment   | 	StatusDto                | 		               | 정가 결제 적용           |
|                            | applyMembership            | 		                        |                  | 멤버십 할인 적용          |
|                            | createReceiptResponse      | 		                        | ReceiptResponse  | 영수증 응답을 생성         |
| PromotionProcessor         | handlePromotion            | Stock, Integer            | StatusDto        |                    |
|                            | processAddingQuantity      | StatusDto                 |                  | 추가 수량 결제 처리        |
|                            | processRegularPricePayment | StatusDto                 |                  | 정가 결제 처리           |
| GeneralProcessor           | handleGeneral              | Stock, Integer            | StatusDto        | 일반 상품 구매 처리        |
| PurchaseTransactionHandler | processWithGift            | Product, Integer, boolean |                  | 증정 상품 있는 결제 처리     |
|                            | processWithoutGift         | Product, Integer          |                  | 증정 상품 없는 결제 처리     |

### util

| Class              | Method                 | Input          | Output           | Description              |
|--------------------|------------------------|----------------|------------------|--------------------------|
| CommonValidator    | validateNotNull        | String         |                  | 문자열이 null이 아닌지 검증        |
|                    | validateNumeric        | String         |                  | 문자열이 숫자 형식인지 검증          |
|                    | validateYesOrNo        | String         |                  | 문자열이 Y,y,N,n 형식인지 검증     |
|                    | validateDate           | String         |                  | 문자열이 날짜 형식인지 검증          |
|                    | validateNonNegative    | String         |                  | 문자열이 음수가 아닌지 검증          |
| CommonParser       | replaceSquareBrackets  | String         | String           | 대괄호를 제거                  |
|                    | separateBySeparator    | String, String | List\<String>    | 구분자로 문자열 분리              |
|                    | convertStringToInteger | String         | Integer          | 문자열을 정수로 변환              |
|                    | parseBoolean           | String         | boolean          | 문자열을 boolean으로 변환        |
|                    | parseDate              | String         | LocalDate        | 문자열을 날짜 형식으로 변환          |
| MarkDownFileReader | readFile               | String         | List\<String>    | MarkDown 파일을 line 단위로 추출 |
| MarkDownFileParser | readPromotionFile      | String         | PromotionRequest | 프로모션 파일을 line 단위로 추출     |
|                    | readStockFile          | String         | StockRequest     | 상품 파일을 line 단위로 추출       |

### view

| Class      | Method                                   | Input            | Output                             | Description         |
|------------|------------------------------------------|------------------|------------------------------------|---------------------|
| InputView  | readPurchaseProducts                     |                  | PurchaseProductsRequest            | 구매할 상품 입력           |
|            | readAddingQuantityStatus                 |                  | AddingQuantityStatusRequest        | 추가 수량 여부 입력         |
|            | readRegularPricePaymentStatus            |                  | RegularPricePaymentStatusRequest   | 정가 결제 여부 입력         |
|            | readMembershipApplicationStatus          |                  | MembershipApplicationStatusRequest | 멤버십 적용 여부 입력        |
|            | readAdditionalPurchaseStatus             |                  | AdditionalPurchaseStatusRequest    | 추가 결제 여부 입력         |
| OutputView | printStartGuidance                       |                  |                                    | 시작 안내 메시지 출력        |
|            | printStocks                              | StocksResponse   |                                    | 현재 재고 출력            |
|            | printPurchaseProductsGuidance            |                  |                                    | 구매할 상품 안내 메시지 출력    |
|            | printAddingQuantityStatusGuidance        | String, Integer  |                                    | 추가 수량 여부 안내 메시지 출력  |
|            | printRegularPricePaymentStatusGuidance   | String, Integer  |                                    | 정 결제 여부 안내 메시지 출력   |
|            | printMembershipApplicationStatusGuidance |                  |                                    | 멤버십 적용 여부 안내 메시지 출력 |
|            | printAdditionalPurchaseStatusGuidance    |                  |                                    | 추가 결제 여부 안내 메시지 출력  |
|            | printReceipt                             | ReceiptResponse  |                                    | 영수증 출력              |
|            | printErrorMessage                        | RuntimeException |                                    | 에러 메시지 출력           |

<br>

## 💻 커밋 컨벤션

> [**AngularJS 커밋 컨벤션**](https://gist.github.com/stephenparish/9941e89d80e2bc58a153) 참고

| Type     | Description      |
|----------|------------------|
| feat     | 새로운 기능 추가        |
| fix      | 버그 수정            |
| docs     | 문서 변경            |
| style    | 코드 포맷 변경         |
| refactor | 코드 리팩토링          |
| test     | 테스트 추가 및 수정      |
| chore    | 빌드 작업 및 도구 관련 변경 |

<br>

## ✅ 체크 리스트

### 과제 진행 요구 사항

- [x] 편의점 비공개 저장소를 생성한다.
- [x] 우아한테크코스 계정을 collaborator로 초대한다.
- [x] 진행한 과제는 저장소의 main 브랜치에 커밋한다.
- [x] 기능을 구현하기 전에 README.md에 구현할 기능 목록을 정리해 추가한다.
- [x] Git의 커밋은 README.md에 정리된 기능 목록 단위로 나눈다. (AngularJS Git Commit Message Conventions 참고)

### 기능 요구 사항

구매자의 할인 혜택과 재고 상황을 고려하여 최종 결제 금액을 계산하고 안내하는 결제 시스템을 구현한다.

- [x] 사용자가 입력한 상품의 가격과 수량을 기반으로 최종 결제 금액을 계산한다.
    - [x] 총 구매액은 각 상품의 가격과 수량을 곱하여 계산하며, 프로모션 및 멤버십 할인 정책을 반영하여 최종 결제 금액을 산출힌디.
- [x] 구매 내역과 산출한 금액 정보를 영수증으로 출력한다.
- [x] 영수증 출력 후 추가 구매 여부를 선택할 수 있다.
- [x] 잘못된 값을 입력할 경우 IllegalArgumentException를 발생시키고, "\[ERROR]"로 시작하는 에러 메시지를 출력하며 해당 부분부터 다시 입력받는다.
    - [x] IllegalArgumentException, IllegalStateException 등 명확한 예외를 사용해 처리한다.

#### 재고 관리

- [x] 각 상품의 재고 수량을 고려하여 결제 가능 여부를 확인한다.
- [x] 고객이 상품을 구매할 때마다 결제된 수량만큼 해당 상품의 재고에서 차감하여 최신 재고 상태를 유지한다.

#### 프로모션 할인

- [x] 오늘 날짜가 프로모션 기간 내에 포함된 경우에만 할인을 적용한다.
- [x] 프로모션은 N개 구매 시 1개 무료 증정(Buy N Get 1 Free)의 형태로 진행된다.
- [x] 1+1 또는 2+1 프로모션이 각각 지정된 상품에 적용되며, 동일 상품에 여러 프로모션은 적용되지 않는다.
- [x] 프로모션 혜택은 프로모션 재고 내에서만 적용 가능하며, 프로모션 재고가 부족할 경우 일반 재고를 사용한다.
- [x] 프로모션 수량보다 적게 구매한 경우 혜택을 받기 위해 추가 수량을 가져올 수 있음을 안내한다.
- [x] 프로모션 재고가 부족하여 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 정가로 결제하게 됨을 안내힌다.

#### 멤버십 할인

- [x] 멤버십 회원은 프로모션이 적용되지 않은 금액의 30%를 할인받는다.
- [x] 프로모션 적용 후 남은 금액에 대해 멤버십 할인을 적용한다.
- [x] 멤버십 할인 최대 한도는 8,000원이다.

#### 영수증 출력

- [x] 영수증은 고객의 구매 내역과 할인을 요약하여 출력한다.
- [x] 영수증 항목 구성은 다음과 같다.
    - [x] 구매 상품 내역: 구매한 상품명, 수량, 가격
    - [x] 증정 상품 내역: 프로모션에 따라 무료로 제공된 증정 상품 목록
    - [x] 금액 정보
        - [x] 총 구매액: 구매한 상품의 총 수량과 총 금액
        - [x] 행사할인: 프로모션에 의해 할인된 금액
        - [x] 멤버십 할인: 멤버십에 의해 추가로 할인된 금액
        - [x] 내실 돈: 최종 결제 금액
- [x] 영수증 항목은 보기 좋게 정렬하여 금액과 수량을 쉽게 확인할 수 있도록 한다.

### 입출력 요구 사항

#### 입력

- [x] 상품 목록과 프로모션 목록 파일을 불러온다.
    - [x] src/main/resources/products.md과 src/main/resources/promotions.md 파일을 사용한다.
    - [x] 두 파일 모두 내용 형식을 유지하면서 값은 수정할 수 있다.
- [x] 구매할 상품과 수량 (상품명과 수량은 하이픈(-)으로, 개별 상품은 대괄호([])로 묶어 쉼표(,)로 구분)
- [x] 프로모션 적용 가능 수량보다 적게 구매한 경우, 해당 수량만큼 추가 여부 (Y/N)
    - [x] Y: 증정 받을 수 있는 상품을 추가
    - [x] N: 증정 받을 수 있는 상품을 추가하지 않음
- [x] 프로모션 재고 부족 시 일부 수량 정가로 결제할지 여부 (Y/N)
    - [x] Y: 일부 수량에 대해 정가로 결제
    - [x] N: 정가로 결제해야 하는 수량을 제외한 후 결제
- [x] 멤버십 할인 적용 여부 (Y/N)
    - [x] Y: 멤버신 할인을 적용
    - [x] N: 멤버십 할인을 적용하지 않음
- [x] 추가 구매 여부 (Y/N)
    - [x] Y: 재고가 업데이트된 상품 목록을 확인 후 추가 구매를 진행
    - [x] N: 구매를 종료

#### 출력

- [x] 환영 인사
- [x] 상품명, 가격, 프로모션, 재고 안내 메시지 (재고가 0이면 '재고 없음')
- [x] 프로모션 적용 가능 수량보다 적게 구매한 경우 프로모션 혜택 안내 메시지
- [x] 프로모션 재고 부족 시 정가 결제 여부 안내 메시지
- [x] 멤버십 할인 적용 여부 안내 메시지
- [x] 구매 상품 내역, 증정 상품 내역, 금액 정보
- [x] 추가 구매 여부 안내 메시지
- [x] 예외 상황 시 오류 메시지 ("\[ERROR]"로 시작)
    - [x] 잘못된 형식의 입력: \[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.
    - [x] 존재하지 않는 상품 입력: \[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요.
    - [x] 재고 초과 구매 시: \[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.
    - [x] 기타 잘못된 입력: \[ERROR] 잘못된 입력입니다. 다시 입력해 주세요.

### 실행 결과 예시

```
안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 10개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 5개
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-3],[에너지바-5]

멤버십 할인을 받으시겠습니까? (Y/N)
Y 

===========W 편의점=============
상품명		수량	금액
콜라		3 	3,000
에너지바 		5 	10,000
===========증	정=============
콜라		1
==============================
총구매액		8	13,000
행사할인			-1,000
멤버십할인			-3,000
내실돈			 9,000

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y

안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 7개 탄산2+1
- 콜라 1,000원 10개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 재고 없음
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[콜라-10]

현재 콜라 4개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)
Y

멤버십 할인을 받으시겠습니까? (Y/N)
N

===========W 편의점=============
상품명		수량	금액
콜라		10 	10,000
===========증	정=============
콜라		2
==============================
총구매액		10	10,000
행사할인			-2,000
멤버십할인			-0
내실돈			 8,000

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
Y

안녕하세요. W편의점입니다.
현재 보유하고 있는 상품입니다.

- 콜라 1,000원 재고 없음 탄산2+1
- 콜라 1,000원 7개
- 사이다 1,000원 8개 탄산2+1
- 사이다 1,000원 7개
- 오렌지주스 1,800원 9개 MD추천상품
- 오렌지주스 1,800원 재고 없음
- 탄산수 1,200원 5개 탄산2+1
- 탄산수 1,200원 재고 없음
- 물 500원 10개
- 비타민워터 1,500원 6개
- 감자칩 1,500원 5개 반짝할인
- 감자칩 1,500원 5개
- 초코바 1,200원 5개 MD추천상품
- 초코바 1,200원 5개
- 에너지바 2,000원 재고 없음
- 정식도시락 6,400원 8개
- 컵라면 1,700원 1개 MD추천상품
- 컵라면 1,700원 10개

구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])
[오렌지주스-1]

현재 오렌지주스은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)
Y

멤버십 할인을 받으시겠습니까? (Y/N)
Y

===========W 편의점=============
상품명		수량	금액
오렌지주스		2 	3,600
===========증	정=============
오렌지주스		1
==============================
총구매액		2	3,600
행사할인			-1,800
멤버십할인			-0
내실돈			 1,800

감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)
N
```

### 프로그래밍 요구 사항

- [x] JDK 21에서 실행 가능해야 한다.
- [x] 프로그램의 시작점은 Application의 main()이다.
- [x] build.gradle 파일은 변경할 수 없으며, 제공된 라이브러리만 사용해야 한다.
- [x] 프로그램 종료 시 System.exit()를 호출하지 않는다.
- [x] 별도의 지시가 없는 한 파일, 패키지 등의 이름을 바꾸거나 이동하지 않는다.
- [x] 자바 코드 컨벤션을 준수하여 프로그래밍한다. 기본적으로 Google Java Style Guide를 따른다.
- [x] Indent(인덴트, 들여쓰기) depth는 최대 2까지만 허용한다. (메서드를 분리하는 것을 추천)
- [x] 3항 연산자를 사용하지 않는다.
- [x] 메서드는 한 가지 일만 하도록 최대한 작게 만든다.
- [x] JUnit 5와 AssertJ를 사용하여 테스트 코드를 작성한다.
- [x] else 예약어 및 switch-case 문 사용을 금지한다.
- [x] Enum을 사용하여 구현한다.
- [x] UI 로직((System.out, System.in, Scanner)을 제외한 구현 기능에 대해 단위 테스트를 작성한다.
- [x] 메서드 길이는 10라인을 초과하지 않는다.
- [x] 입출력을 담당하는 클래스를 별도로 구현한다.
    - [x] 클래스 이름, 메서드 반환 유형, 시그니처 등은 자유롭게 수정할 수 있다.
- [x] camp.nextstep.edu.missionutils에서 제공하는 DateTimes 및 Console API를 사용하여 구현해야 한다.
    - [x] DateTimes.now()를 사용하여 현재 날짜와 시간을 가져온다.
    - [x] Console.readLine()을 사용하여 사용자 입력을 처리한다.