package Glyvia.Glyvia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioUpdateRequest {
    private String email;
    private String senha;
    private String nome;
    private String genero;
    private String tipoInsulina;
    private String viaAplicacao;
    private Double pesoAtual;
    private Double altura;
    private Double metaGlicemica;
    private Double icr;
    private Double fs;
    private String dataNascimento;
}
