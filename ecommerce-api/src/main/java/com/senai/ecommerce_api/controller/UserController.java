package com.senai.ecommerce_api.controller;

import com.senai.ecommerce_api.dto.UserDto;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    public static List<UserDto> usuarios = new ArrayList<UserDto>();

    @PostConstruct
    public void initiateList() {
        UserDto userDto = new UserDto();
        userDto.setNome("Eduardo");
        userDto.setCpf("123");
        userDto.setEndereco("Rua a");
        userDto.setEmail("eduardo@email.com");
        userDto.setTelefone("1234-3454");
        userDto.setDataCadastro(LocalDateTime.now());

        UserDto UserDto2 = new UserDto();
        UserDto2.setNome("Luiz");
        UserDto2.setCpf("456");
        UserDto2.setEndereco("Rua b");
        UserDto2.setEmail("luiz@email.com");
        UserDto2.setTelefone("1234-3454");
        UserDto2.setDataCadastro(LocalDateTime.now());

        UserDto UserDto3 = new UserDto();
        UserDto3.setNome("Bruna");
        UserDto3.setCpf("678");
        UserDto3.setEndereco("Rua c");
        UserDto3.setEmail("bruna@email.com");
        UserDto3.setTelefone("1234-3454");
        UserDto3.setDataCadastro(LocalDateTime.now());

        usuarios.add(userDto);
        usuarios.add(UserDto2);
        usuarios.add(UserDto3);
    }

    @GetMapping("/{cpf}")
    public UserDto getUsersFiltro(@PathVariable String cpf) {
        return usuarios .stream()
                .filter(userDTO -> userDTO.getCpf().equals(cpf))
                .findFirst() .orElseThrow(() -> new RuntimeException("Usuário não encontrado."));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto inserir(@RequestBody @Valid UserDto userDTO) {
        userDTO.setDataCadastro(LocalDateTime.now());
        usuarios.add(userDTO);
        return userDTO;
    }

    @DeleteMapping("/{cpf}")
    public boolean remover(@PathVariable String cpf) {
        return usuarios
                .removeIf(userDTO -> userDTO.getCpf().equals(cpf));
    }




}


