package ssangsoo.cafekiosk.unit.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ssangsoo.cafekiosk.unit.beverages.Beverage;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class Order {

    private final LocalDateTime orderDateTime;
    private final List<Beverage> beverages;

}
