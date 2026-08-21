package com.senai.ecommerce_api.model;

import com.senai.ecommerce_api.dto.UserDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "user")
public class UserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String telefone;
    private LocalDateTime data_cadastro;

    public static UserModel convert(UserDto userDto){
        UserModel user = new UserModel();
        user.setNome(userDto.getNome());
        user.setCpf(userDto.getCpf());
        user.setEndereco(userDto.getEndereco());
        user.setEmail(userDto.getEmail());
        user.setTelefone(userDto.getTelefone());
        user.setData_cadastro(userDto.getDataCadastro());
        return user;
    }
}
