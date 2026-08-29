package com.senai.shopping_api.service;

import com.senai.shopping_api.dto.ShopDTO;
import com.senai.shopping_api.dto.ShopReportDTO;
import com.senai.shopping_api.model.Shop;
import com.senai.shopping_api.repository.ShopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public List<ShopDTO> getAll() {

        List<Shop> shops = shopRepository.findAll();

        return shops.stream()
                .map(ShopDTO::convert)
                .toList();
    }

    public List<ShopDTO> getByUser(String userIdentifier) {

        List<Shop> shops =
                shopRepository.findAllByUserIdentifier(userIdentifier);

        return shops.stream()
                .map(ShopDTO::convert)
                .toList();
    }

    public List<ShopDTO> getByDate(ShopDTO shopDTO) {

        List<Shop> shops =
                shopRepository.findAllByDateGreaterThan(
                        shopDTO.getDate()
                );

        return shops.stream()
                .map(ShopDTO::convert)
                .toList();
    }

    public ShopDTO findById(long id) {

        return shopRepository.findById(id)
                .map(ShopDTO::convert)
                .orElse(null);
    }

    public ShopDTO save(ShopDTO shopDTO) {

        float total = shopDTO.getItems()
                .stream()
                .map(Item -> Item.getPrice())
                .reduce(0.0f, Float::sum);

        shopDTO.setTotal(total);

        shopDTO.setDate(LocalDateTime.now());

        Shop shop = Shop.convert(shopDTO);

        shop = shopRepository.save(shop);

        return ShopDTO.convert(shop);
    }

    public List<ShopDTO> getShopsByFilter(
            LocalDate dataInicio,
            LocalDate dataFim,
            Float valorMinimo) {

        List<Shop> shops =
                shopRepository.getShopByFilters(
                        dataInicio,
                        dataFim,
                        valorMinimo
                );

        return shops.stream()
                .map(ShopDTO::convert)
                .toList();
    }

    public ShopReportDTO getReportByDate(
            LocalDate dataInicio,
            LocalDate dataFim) {

        return shopRepository.getReportByDate(
                dataInicio,
                dataFim
        );
    }
}