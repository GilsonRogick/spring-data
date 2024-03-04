package com.alura.spring.data.service;

import com.alura.spring.data.model.Cargo;
import com.alura.spring.data.repository.CargoRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class CrudCargoService {

    private final CargoRepository cargoRepository;

    private Boolean system = true;

    public CrudCargoService(CargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cadastrar cargo");
            System.out.println("2 - Atualizar cargo");
            System.out.println("3 - Deletar cargo");
            System.out.println("4 - Listar cargos");
            if (scanner.hasNextInt()) {
                int exibirOpcoesCargo = scanner.nextInt(); // Adiciona menu para atualizar o cargo

                switch (exibirOpcoesCargo) {
                    case 1:
                        saveCargo(scanner);
                        break;
                    case 2:
                        System.out.println("Digite o ID do cargo que deseja atualizar:");
                        int updateCargoId = scanner.nextInt();
                        updateCargo(updateCargoId, scanner);
                        break;
                    case 3:
                        System.out.println("Digite o id do cargo que deseja deletar:");
                        int deleteCargoId = scanner.nextInt();
                        deleteCargo(deleteCargoId, scanner);
                        break;
                    case 4:
                        showCargos(scanner);
                        break;
                    case 0:
                        System.out.println("Saindo do sistema. Até logo!");
                        system = false;
                        break;
                    default:
                        system = false;
                        System.out.println("Ação inválida. Tente novamente.");
                }
            } else {
                System.out.println("Por favor, insira um valor inteiro válido.");
                scanner.next(); // Limpa o buffer do Scanner
            }
        }
        saveCargo(scanner);
    }

    private void saveCargo(Scanner scanner) {
        System.out.println("Descrição do cargo:");
        String descricao = scanner.next(); //pega a informação que o usuário escreveu no console
        Cargo cargo = new Cargo(); //cria um cargo
        cargo.setDescription(descricao); // seta a descrição informada pelo cliente
        cargoRepository.save(cargo); // salva o cargo no repositório
        System.out.println("Cargo salvo!");
    }

    private void updateCargo(Integer id, Scanner scanner) {
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

    private void deleteCargo(Integer id, Scanner scanner) {
        Cargo cargo = cargoRepository.findById(id).orElse(null);

        if (cargo != null) {
            cargoRepository.delete(cargo);
            System.out.println("Cargo deletado com sucesso!");
        } else {
            System.out.println("Cargo não encontrado!");
        }
    }

    private void showCargos(Scanner scanner) {
        Iterable<Cargo> cargos = cargoRepository.findAll();

        for (Cargo cargo : cargos) {
            System.out.println(cargo);
        }
    }
}
