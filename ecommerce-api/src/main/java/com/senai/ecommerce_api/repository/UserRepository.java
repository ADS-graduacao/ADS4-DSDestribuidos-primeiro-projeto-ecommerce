package com.senai.ecommerce_api.repository;

import com.senai.ecommerce_api.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    UserModel findByCpf(String cpf);

    List<UserModel> queryByNomeLike(String nome);

}