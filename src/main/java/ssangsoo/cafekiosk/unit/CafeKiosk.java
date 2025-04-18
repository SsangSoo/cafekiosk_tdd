package ssangsoo.cafekiosk.unit;

import lombok.Getter;
import ssangsoo.cafekiosk.unit.beverages.Beverage;
import ssangsoo.cafekiosk.unit.order.Order;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class CafeKiosk {

    public static final LocalTime SHOP_OPEN_TIME = LocalTime.of(10, 0);
    public static final LocalTime SHOP_CLOSE_TIME = LocalTime.of(22, 0);
    
    private final List<Beverage> beverages = new ArrayList<>();


    public void add(Beverage beverage) {
        add(beverage, 1);
    }

    public void add(Beverage beverage, int count) {
        // 검증
        if(count <= 0) {
            throw new IllegalArgumentException("음료는 1잔 이상 주문하실 수 있습니다");
        }


        // 비즈니스 로직
        for(int i=0; i<count; i++) {
            beverages.add(beverage);
        }
    }

    public void remove(Beverage beverage) {
        beverages.remove(beverage);
    }

    public void clear() {
        beverages.clear();
    }



    public int calculateTotalPrice() {
        return beverages.stream()
                .mapToInt(Beverage::getPrice)
                .sum();
    }

    public Order createOrder() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalTime currentTime = currentDateTime.toLocalTime();

        if(currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("주문을 가능한 시간이 아닙니다. 관리자에게 문의하세요.");
        }

        return new Order(currentDateTime, beverages);
    }

    public Order createOrder(LocalDateTime currentDateTime) {
        LocalTime currentTime = currentDateTime.toLocalTime();

        if(currentTime.isBefore(SHOP_OPEN_TIME) || currentTime.isAfter(SHOP_CLOSE_TIME)) {
            throw new IllegalArgumentException("주문을 가능한 시간이 아닙니다. 관리자에게 문의하세요.");
        }

        return new Order(currentDateTime, beverages);
    }
}