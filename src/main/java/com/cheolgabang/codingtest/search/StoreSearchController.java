package com.cheolgabang.codingtest.search;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Validated
@RestController
@RequestMapping("/api/v1/stores")
public class StoreSearchController {

    private final StoreSearchService storeSearchService;

    public StoreSearchController(StoreSearchService storeSearchService) {
        this.storeSearchService = storeSearchService;
    }

    @GetMapping("/search")
    public StoreSearchResponse search(
            @RequestParam double lat,
            @RequestParam double lng,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime orderedAt,
            @RequestParam(defaultValue = "0.0") @DecimalMin("0.0") @DecimalMax("5.0") double minRating,
            @RequestParam(defaultValue = "0") @Min(0) int page,
            @RequestParam(defaultValue = "20") @Min(1) @Max(100) int size
    ) {
        StoreSearchRequest request = new StoreSearchRequest(lat, lng, orderedAt, minRating, page, size);
        return storeSearchService.search(request);
    }
}
