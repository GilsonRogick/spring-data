package com.alura.spring.data.service;

import com.alura.spring.data.SpringDataApplication;
import com.alura.spring.data.model.Cargo;
import com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {
        salvar(scanner);

    }

    private void salvar(Scanner scanner) {
        System.out.println("Descrição do cargo:");
        String descricao = scanner.next(); //pega a informação que o usuário escreveu no console
        Cargo cargo = new Cargo(); //cria um cargo
        cargo.setDescription(descricao); // seta a descrição informada pelo cliente
        cargoRepository.save(cargo); // salva o cargo no repositório
        System.out.println("Cargo salvo!");
    }

    public void atualizar(Integer id, Scanner scanner) {
        Cargo cargo = cargoRepository.findById(id).orElse(null);

        if (cargo != null) {
            System.out.println("Nova descrição do cargo:");
            String descricao = scanner.next();
            cargo.setDescription(descricao);
            cargoRepository.save(cargo);
            System.out.println("Cargo atualizado com sucesso!");
        } else {
            System.out.println("Cargo não encontrado!");
        }
    }
}
