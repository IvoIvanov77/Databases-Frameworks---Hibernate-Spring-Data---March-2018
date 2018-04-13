package app.services.contracts;

import app.domain.xml_dto.p06_sales_with_applied_discount.WrapperCarWithDiscountDtos;

public interface SaleService {

    void createRandomSales();

    WrapperCarWithDiscountDtos getSalesWithAppliedDiscount();
}
