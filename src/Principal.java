import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = inserirFuncionarios();
    }

    public static List<Funcionario> inserirFuncionarios(){
        List<Funcionario> funcionarios = new ArrayList<>();
        Funcionario funcionario = new Funcionario();

        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("\n----- Inserindo funcionários -----");

            while (true) {
                System.out.println("Nome do funcionário: ");
                funcionario.setNome(scanner.nextLine());

                System.out.println("Data de nascimento do funcionário (dd/MM/aaaa): ");
                String input = scanner.nextLine();

                boolean dataValida = validarDataDeNascimento(input);
                while(!dataValida) {
                    input = scanner.nextLine();
                    dataValida = validarDataDeNascimento(input);
                }
                LocalDate data = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                funcionario.setDataNascimento(data);

                System.out.println("Função do funcionário: ");
                funcionario.setFuncao(scanner.nextLine());

                System.out.println("Salário do funcionário (1999,99): ");
                try {
                    funcionario.setSalario(scanner.nextBigDecimal());
                } catch (InputMismatchException e){
                    System.out.println("Insira um salário válido");
                    funcionario.setSalario(scanner.nextBigDecimal());
                }
                funcionarios.add(funcionario);

                scanner.nextLine();
                System.out.println("Continuar inserindo funcionarios? (S/N)");
                String continuar = scanner.nextLine();
                if (!continuar.equals("S")){
                    break;
                }
            }
        }

        return funcionarios;
    }

    private static boolean validarDataDeNascimento(String input) {
        try {
            if (!input.matches("(\\d{2})/(\\d{2})/(\\d{4})")) {
                System.out.println("Insira uma data de nascimento válida (dd/MM/aaaa): ");
                return false;
            }

            if (LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy")).isAfter(LocalDate.now())) {
                System.out.println("A data de nascimento não pode ser maior que a data atual");
                return false;
            }
        } catch (DateTimeParseException e) {
            System.out.println("O dia deve ser um número entre 0 e 31 ou o mês deve ser entre 1 e 12");
            return false;
        }
        return true;
    }

}