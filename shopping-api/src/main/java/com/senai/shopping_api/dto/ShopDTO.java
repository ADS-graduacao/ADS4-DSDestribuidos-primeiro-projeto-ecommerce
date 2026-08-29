package com.senai.shopping_api.dto;

import com.senai.shopping_api.model.Shop;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShopDTO {

    @NotBlank
    private String userIdentifier;

    private Float total;

    private LocalDateTime date;

    @NotNull
    private List<ItemDTO> items;

    public static ShopDTO convert(Shop shop) {

        ShopDTO shopDTO = new ShopDTO();

        shopDTO.setUserIdentifier(shop.getUserIdentifier());
        shopDTO.setTotal(shop.getTotal());
        shopDTO.setDate(shop.getDate());

        if (shop.getItems() != null) {
            shopDTO.setItems(
                    shop.getItems()
                            .stream()
                            .map(ItemDTO::convert)
                            .toList()
            );
        }

        return shopDTO;
    }
}