package com.senai.shopping_api.repository;

import com.senai.shopping_api.dto.ShopReportDTO;
import com.senai.shopping_api.model.Shop;

import java.time.LocalDate;
import java.util.List;

public interface ReportRepository {

    List<Shop> getShopByFilters(
            LocalDate dataInicio,
            LocalDate dataFim,
            Float valorMinimo
    );

    ShopReportDTO getReportByDate(
            LocalDate dataInicio,
            LocalDate dataFim
    );
}