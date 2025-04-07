package ssangsoo.cafekiosk.spring.domain.stock;

import org.springframework.data.jpa.repository.JpaRepository;
import ssangsoo.cafekiosk.spring.domain.product.Product;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    List<Stock> findAllByProductNumberIn(List<String> productNumber);
}
