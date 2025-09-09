import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = inserirFuncionarios();
        funcionarios.forEach(System.out::print);

        removerFuncionario(funcionarios, "João");
        atualizarSalario(funcionarios);
        funcionarios.forEach(System.out::print);

        Map<String, List<Funcionario>> porFuncao = agruparFuncionariosPorFuncao(funcionarios);
        System.out.println("\n---- Funcionários agrupados por função ----");
        porFuncao.forEach((s, f) -> {
            System.out.println(s + ": " + f);
        });

        int mes = 10;
        System.out.println("\n---- Funcionários aniversariantes do mes " + mes);
        funcionariosAniversariantes(funcionarios, mes).forEach(System.out::print);

        mes = 12;
        System.out.println("\n---- Funcionários aniversariantes do mes " + mes);
        funcionariosAniversariantes(funcionarios, mes).forEach(System.out::print);

        funcionarioMaisVelho(funcionarios);

        funcionariosOrdemAlfabetica(funcionarios);

        imprimirSalariosTotais(funcionarios);

        BigDecimal salarioMinimo = BigDecimal.valueOf(1220.00);
        salariosMinimosPorFuncionario(funcionarios, salarioMinimo);
    }

    public static List<Funcionario> inserirFuncionarios(){
        List<Funcionario> funcionarios = new LinkedList<>();
        Funcionario funcionario;

        try(Scanner scanner = new Scanner(System.in)) {
            System.out.println("\n----- Inserindo funcionários -----");

            while (true) {
                funcionario = new Funcionario();
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

    public static void removerFuncionario(List<Funcionario> funcionarios, String nome){
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals(nome));
    }

    public static void atualizarSalario(List<Funcionario> funcionarios){
        if (funcionarios.isEmpty()) {
            return;
        }

        BigDecimal aumento;
        BigDecimal salarioFinal;
        for (Funcionario funcionario : funcionarios) {
            aumento = funcionario.getSalario().multiply(BigDecimal.valueOf(0.1));
            salarioFinal = funcionario.getSalario().add(aumento);
            funcionario.setSalario(salarioFinal);
        }
    }

    public static Map<String, List<Funcionario>> agruparFuncionariosPorFuncao(List<Funcionario> funcionarios){
        Map<String, List<Funcionario>> map = new HashMap<>();
        if (funcionarios.isEmpty()) {
            return map;
        }

        funcionarios.forEach(funcionario -> {
            map.computeIfAbsent(funcionario.getFuncao(), k -> new LinkedList<>());
            map.get(funcionario.getFuncao()).add(funcionario);
        });

        return map;
    }

    public static void funcionariosOrdemAlfabetica(List<Funcionario> funcionarios) {
        List<String> nomes = new ArrayList<>(funcionarios.stream()
                .map(Funcionario::getNome)
                .toList());

        Collections.sort(nomes);
        System.out.println("\n----- Funcionários em Ordem Alfabética -----");
        nomes.forEach(System.out::print);
    }

    public static void funcionarioMaisVelho(List<Funcionario> funcionarios){
        Optional<Funcionario> funcionario = funcionarios.stream()
                .max(Comparator.comparing(Pessoa::getDataNascimento));

        System.out.println("Funcionário com maior idade: ");
        funcionario.ifPresent(f -> {
            System.out.println(f.getNome() + " - " + Duration.between(f.getDataNascimento(), LocalDate.now()).toDays() / 365);
        });
    }

    public static void imprimirSalariosTotais(List<Funcionario> funcionarios){
        if (funcionarios.isEmpty()) {
            System.out.println("Total dos salário dos funcionários: R$ 0,00" );
        }

        funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal::add)
                .ifPresent(bigDecimal -> System.out.println("Total dos salários dos funcionários R$ " + bigDecimal));
    }

    public static List<Funcionario> funcionariosAniversariantes(List<Funcionario> funcionarios, int mes){
        return funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == mes)
                .toList();
    }

    public static void salariosMinimosPorFuncionario(List<Funcionario> funcionarios, BigDecimal salarioMinimo){
        if (salarioMinimo.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Salário mínimo inválido!");
        }

        Map<String, BigDecimal> salariosPorFuncionario = new HashMap<>();
        funcionarios.forEach(funcionario -> {
            salariosPorFuncionario.put(funcionario.getNome(), funcionario.getSalario().divide(salarioMinimo, RoundingMode.HALF_DOWN));
        });

        System.out.println("\n---- Salários mínimos por funcionário ----");
        salariosPorFuncionario.forEach(
                (func, qtd) -> System.out.print(func + ": " + qtd + " salário(s).")
        );
    }
}