package com.senai.shopping_api.repository;

import com.senai.shopping_api.dto.ShopReportDTO;
import com.senai.shopping_api.model.Shop;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public class ReportRepositoryImpl implements ReportRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Shop> getShopByFilters(
            LocalDate dataInicio,
            LocalDate dataFim,
            Float valorMinimo) {

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT s ");
        sb.append("FROM shop s ");
        sb.append("WHERE s.date >= :dataInicio ");

        if (dataFim != null) {
            sb.append("AND s.date <= :dataFim ");
        }

        if (valorMinimo != null) {
            sb.append("AND s.total >= :valorMinimo ");
        }

        Query query = entityManager.createQuery(sb.toString());

        query.setParameter(
                "dataInicio",
                dataInicio.atStartOfDay()
        );

        if (dataFim != null) {
            query.setParameter(
                    "dataFim",
                    dataFim.atTime(23, 59, 59)
            );
        }

        if (valorMinimo != null) {
            query.setParameter("valorMinimo", valorMinimo);
        }

        return query.getResultList();
    }

    @Override
    public ShopReportDTO getReportByDate(
            LocalDate dataInicio,
            LocalDate dataFim) {

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT COUNT(sp.id), ");
        sb.append("SUM(sp.total), ");
        sb.append("AVG(sp.total) ");
        sb.append("FROM shopping.shop sp ");
        sb.append("WHERE sp.date >= :dataInicio ");
        sb.append("AND sp.date <= :dataFim ");

        Query query = entityManager.createNativeQuery(sb.toString());

        query.setParameter(
                "dataInicio",
                dataInicio.atStartOfDay()
        );

        query.setParameter(
                "dataFim",
                dataFim.atTime(23, 59, 59)
        );

        Object[] result = (Object[]) query.getSingleResult();

        ShopReportDTO shopReportDTO = new ShopReportDTO();

        shopReportDTO.setCount(
                ((BigInteger) result[0]).intValue()
        );

        shopReportDTO.setTotal(
                result[1] != null ? ((Number) result[1]).doubleValue() : 0.0
        );

        shopReportDTO.setMean(
                result[2] != null ? ((Number) result[2]).doubleValue() : 0.0
        );

        return shopReportDTO;
    }
}