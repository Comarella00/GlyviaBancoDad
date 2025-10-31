package Glyvia.Glyvia.dto;
import lombok.Data;

@Data
public class PerguntasRequest {
    private String nome;
    private String genero;
    private String tipoInsulina;
    private String viaAplicacao;
    private Double pesoAtual;
    private Double altura;
    private Double metaGlicemica;
    private Double icr;
    private String dataNascimento;
}