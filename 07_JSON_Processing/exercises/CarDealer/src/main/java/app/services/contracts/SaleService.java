package app.services.contracts;

import app.domain.json_dto.p06_sales_with_applied_discount.SaleWithDiscountDto;

import java.util.List;

public interface SaleService {

    void createRandomSales();

    List<SaleWithDiscountDto> getSalesWithAppliedDiscount();
}
