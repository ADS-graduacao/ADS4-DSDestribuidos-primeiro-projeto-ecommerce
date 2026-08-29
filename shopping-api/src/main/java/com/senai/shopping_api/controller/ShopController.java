package com.senai.shopping_api.controller;

import com.senai.shopping_api.dto.ShopDTO;
import com.senai.shopping_api.dto.ShopReportDTO;
import com.senai.shopping_api.service.ShopService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shopping")
public class ShopController {

    private final ShopService shopService;

    @GetMapping()
    public List<ShopDTO> getShops() {
        return shopService.getAll();
    }

    @GetMapping("/shopByUser/{userIdentifier}")
    public List<ShopDTO> getShopsByUser(
            @PathVariable String userIdentifier) {

        return shopService.getByUser(userIdentifier);
    }

    @GetMapping("/shopByDate")
    public List<ShopDTO> getShopsByDate(
            @RequestBody ShopDTO shopDTO) {

        return shopService.getByDate(shopDTO);
    }

    @GetMapping("/{id}")
    public ShopDTO findById(@PathVariable Long id) {
        return shopService.findById(id);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ShopDTO newShop(
            @Valid @RequestBody ShopDTO shopDTO) {

        return shopService.save(shopDTO);
    }

    @GetMapping("/search")
    public List<ShopDTO> getShopsByFilter(

            @RequestParam(name = "dataInicio", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate dataInicio,

            @RequestParam(name = "dataFim", required = false)
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate dataFim,

            @RequestParam(name = "valorMinimo", required = false)
            Float valorMinimo) {

        return shopService.getShopsByFilter(
                dataInicio,
                dataFim,
                valorMinimo
        );
    }

    @GetMapping("/report")
    public ShopReportDTO getReportByDate(

            @RequestParam(name = "dataInicio", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate dataInicio,

            @RequestParam(name = "dataFim", required = true)
            @DateTimeFormat(pattern = "dd/MM/yyyy")
            LocalDate dataFim) {

        return shopService.getReportByDate(
                dataInicio,
                dataFim
        );
    }
}