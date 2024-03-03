package com.alura.spring.data;

import com.alura.spring.data.service.CrudCargoService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringDataApplication implements CommandLineRunner {

    private final CrudCargoService cargoService;
    private Boolean system = true;

    public SpringDataApplication(CrudCargoService cargoService) {
        this.cargoService = cargoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataApplication.class, args);
    }

    public void exibirOpcoesIniciais() {
        system = true;
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in); // pega as informações que o cliente inseriu

        while (system) {
            System.out.println("Qual ação você quer executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");

            if (scanner.hasNextInt()) {
                int exibirOpcoes = scanner.nextInt();

                switch (exibirOpcoes) {
                    case 1:
                        System.out.println("0 - Sair");
                        System.out.println("1 - Cadastrar cargo");
                        System.out.println("2 - Atualizar cargo");

                        int exibirOpcoesCargo = scanner.nextInt(); // Adiciona menu para atualizar o cargo

                        switch (exibirOpcoesCargo) {
                            case 1:
                                cargoService.inicial(scanner);
                                break;
                            case 2:
                                System.out.println("Digite o ID do cargo que deseja atualizar:");
                                int cargoId = scanner.nextInt();
                                cargoService.atualizar(cargoId, scanner);
                                break;
                        }
                    case 0:
                        System.out.println("Saindo do sistema. Até logo!");
                        system = false;
                        break;
                    default:
                        System.out.println("Ação inválida. Tente novamente.");
                }
            } else {
                System.out.println("Por favor, insira um valor inteiro válido.");
                scanner.next(); // Limpa o buffer do Scanner
            }

            int action = scanner.nextInt(); //pega o valor inteiroq ue o cliente digita no console
            if (action == 1) {
                cargoService.inicial(scanner);
            } else {
                system = false;
            }
        }
    }
}
