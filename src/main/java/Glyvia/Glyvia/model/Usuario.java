package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Entity(name="USUARIO")
@Table(name="USUARIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Obrigatório o campo email!")
    @Email(message = "Formato de email inválido!")
    private String email;

    @NotBlank(message = "Obrigatório o campo senha!")
    private String senha;

    // Campos opcionais (sem validação obrigatória)
    private String nome;
    private String genero;
    private String tipoInsulina;
    private String viaAplicacao;
    private Double pesoAtual;
    private Double altura;
    private Double metaGlicemica;
    private Double icr; // índice de carboidrato/insulina
    private String dataNascimento;

    public Usuario(String email, String senha) {
    }
}
