package com.cheolgabang.codingtest.quote.impl;

import com.cheolgabang.codingtest.common.NotImplementedYetException;
import com.cheolgabang.codingtest.quote.*;
import com.cheolgabang.codingtest.quote.enums.Coupon;
import com.cheolgabang.codingtest.quote.enums.DeliveryFee;
import com.cheolgabang.codingtest.quote.enums.QuoteReasonCode;
import com.cheolgabang.codingtest.quote.enums.Store;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.cheolgabang.codingtest.quote.QuoteFixture.MENU_PRICES;

@Service
public class QuoteServiceImpl implements QuoteService {

    @Override
    public QuoteResponse quote(QuoteRequest request) {

        /*
        * 엔드포인트: POST /api/v1/orders/quote
요청 필드: storeId, orderedAt, distanceKm, items[{menuId, qty}], couponCode(optional)
고정 데이터:
S1 최소주문 12000, 영업 10 (lines 0-22, column 0), 브레이크 15 (lines 0-16, column 0)
S1 메뉴 M1=6000(active), M2=4500(active), M3=5500(inactive)
쿠폰 LUNCH10(10%, max 3000, min 15000, 11:00-14:00)
쿠폰 WELCOME3000(정액 3000, min 12000)
배달비 <=1.5km (line 2000), <=3.0km (line 3000), >3.0km (line 4500)
규칙: 영업시간/브레이크 검사, 메뉴 유효성 검사, 수량 1~20, 최소주문 검사, 쿠폰 적용 후 최종금액 계산
응답 필드: available, reasonCode, subtotal, discount, deliveryFee, finalPrice
필수 테스트: 영업시간 경계값, 브레이크 경계값(15:00 닫힘/16:00 열림), 비활성 메뉴, 최소주문 미달, 쿠폰 시간대 불일치
        * */

        // 고객이 주문한 items들을 다 조회한다.
        List<QuoteItemRequest> itemRequests = request.getItems();

        // 주문 메뉴들 정보
        List<String> orderMenuIds = itemRequests.stream().map(QuoteItemRequest::getMenuId).toList();

        boolean isOrderAvailable = Store.canOrder(orderMenuIds);
        boolean isAvailableStore = Store.isAvailableStore(request.getStoreId());
        boolean isOpen = Store.isOpen(request.getStoreId(), request.getOrderedAt().toLocalTime());

        if(!isAvailableStore){
            return new QuoteResponse(false, QuoteReasonCode.STORE_NOT_FOUND, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        if(!isOrderAvailable){
            return new QuoteResponse(false, QuoteReasonCode.INVALID_MENU, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        if(!isOpen) {
            return new QuoteResponse(false, QuoteReasonCode.STORE_CLOSED, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        }

        //MENU_PRICES 이 안에 있는 menuId와 qty를 비교해서 최종 결제 금액을 내려야함.

        BigDecimal totalPrice = BigDecimal.ZERO;
        BigDecimal sumPrice;
        BigDecimal discountedAmount = BigDecimal.ZERO;
        BigDecimal deliveryFee;

        Map<String, Integer> orderDetail = itemRequests.stream().collect(Collectors.toMap(QuoteItemRequest::getMenuId, QuoteItemRequest::getQty, (oldV, newV) -> newV));

        // item들 안에 있는 항목들의 주문 금액을 total로 계산하라. 이건 메뉴만 정보
        Set<String> menuId =  MENU_PRICES.keySet();

        Set<String> orderItem = orderDetail.keySet();

        // menuId와 orderItem의. 값을 비교해서 일치하는 것의 가격과 수량을 곱한다.
        for(String itemId : orderItem) {
            if(MENU_PRICES.containsKey(itemId)) {
                totalPrice = totalPrice.add(BigDecimal.valueOf((long) MENU_PRICES.get(itemId) * orderDetail.get(itemId)));
            }
        }

        // 일치하는 쿠폰 정보를 찾고 해당 쿠폰 정보의 최소, 최대 금액에 해당하는지 확인
        for(Coupon coupon : Coupon.values()){
            Enum couponId = request.getCouponCode();
            if(coupon.equals(request.getCouponCode())) {
                // 쿠폰이 적용 가능한 시간이라면
                if(coupon.isApply(request.getCouponCode(), totalPrice, request.getOrderedAt())) {
                    // 쿠폰에 맞는 계산을 진행
                    discountedAmount = coupon.calculateSum(coupon, totalPrice);
                }
            }
        }

        // 배달비 계산
        deliveryFee = DeliveryFee.calculateDeliveryFee(request.getDistanceKm());

        sumPrice = discountedAmount.add(deliveryFee);

        return new QuoteResponse(true, QuoteReasonCode.OK, totalPrice, totalPrice.subtract(sumPrice), deliveryFee, sumPrice);

    }
}
