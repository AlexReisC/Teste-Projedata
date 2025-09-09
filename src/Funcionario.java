import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public Funcionario() {
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    @Override
    public String toString() {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.of("pt", "BR"));
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00", symbols);

        String salarioFormatado = decimalFormat.format(salario);
        String dataFormatada = this.getDataNascimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

        return "Funcionario: " + this.getNome() +
                "| Data de Nascimento: " + dataFormatada +
                "| Salario: " + salarioFormatado +
                "| Funcao: " + funcao + "\n";
    }

}
