# Cheolgabang-style Online Coding Test Starter

This starter project is prepared for a 180-minute Java backend coding test with 3 tasks.
It provides only skeleton code and test templates so you can focus on implementation.

## Stack

- Java 17
- Spring Boot 3.x
- Validation + Global exception handling
- JUnit 5

## Run

```bash
./gradlew test
./gradlew bootRun
```

If wrapper is missing, generate once:

```bash
gradle wrapper
```

## API Skeleton

### Problem 1

- `POST /api/v1/orders/quote`
- Files:
  - `src/main/java/com/cheolgabang/codingtest/quote/QuoteController.java`
  - `src/main/java/com/cheolgabang/codingtest/quote/QuoteServiceStub.java`
  - `src/main/java/com/cheolgabang/codingtest/quote/QuoteRequest.java`
  - `src/main/java/com/cheolgabang/codingtest/quote/QuoteResponse.java`
  - `src/main/java/com/cheolgabang/codingtest/quote/QuoteFixture.java`
- Start from `QuoteServiceStub#quote`.

### Problem 2

- `PATCH /api/v1/orders/{orderId}/status`
- Header: `Idempotency-Key`
- Files:
  - `src/main/java/com/cheolgabang/codingtest/status/OrderStatusController.java`
  - `src/main/java/com/cheolgabang/codingtest/status/OrderStatusServiceStub.java`
  - `src/main/java/com/cheolgabang/codingtest/status/StatusUpdateRequest.java`
  - `src/main/java/com/cheolgabang/codingtest/status/StatusTransitionRules.java`
- Start from `OrderStatusServiceStub#updateStatus`.

### Problem 3

- `GET /api/v1/stores/search`
- Files:
  - `src/main/java/com/cheolgabang/codingtest/search/StoreSearchController.java`
  - `src/main/java/com/cheolgabang/codingtest/search/StoreSearchServiceStub.java`
  - `src/main/java/com/cheolgabang/codingtest/search/StoreSearchRequest.java`
  - `src/main/java/com/cheolgabang/codingtest/search/StoreSearchResponse.java`
- Start from `StoreSearchServiceStub#search`.

## Test Templates

- `src/test/java/com/cheolgabang/codingtest/quote/QuoteControllerTemplateTest.java`
- `src/test/java/com/cheolgabang/codingtest/status/OrderStatusServiceTemplateTest.java`
- `src/test/java/com/cheolgabang/codingtest/search/StoreSearchServiceTemplateTest.java`

Enable and extend disabled tests as you implement each problem.

## Suggested Working Order (180 min)

1. Problem 1 full implementation + boundary tests
2. Problem 2 transition rules + idempotency + version conflict
3. Problem 3 optimization + sorting + pagination

## Important

This starter intentionally contains `NotImplementedYetException` in service stubs.
Replace stub logic with real implementation before final submission.
