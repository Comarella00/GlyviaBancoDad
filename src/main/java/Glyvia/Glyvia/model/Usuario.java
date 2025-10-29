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
    private Long idUsuario;

    @Email
    @NotBlank(message = "Obrigatório o campo email!")
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @NotBlank(message = "Obrigatório o campo senha!")
    @Size(max = 30, message = "Limite máximo de 30 caracteres.")
    @Column(name = "senha", length = 30, nullable = false)
    private String senha;

    @NotBlank(message = "Obrigatório o campo nome!")
    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @NotBlank(message = "Obrigatório o campo gênero!")
    @Column(name = "genero", length = 100, nullable = false)
    private String genero;

    @NotNull(message = "Obrigatório o campo altura!")
    @Column(name = "altura", length = 100, nullable = false)
    private Double altura;

    @NotNull(message = "Obrigatório o campo peso atual!")
    @Column(name = "pesoAtual", length = 100, nullable = false)
    private Double pesoAtual;

    @NotBlank(message = "Obrigatório o campo tipo de insulina!")
    @Column(name = "tipoInsulina", length = 100, nullable = false)
    private String tipoInsulina;

    @NotBlank(message = "Obrigatório o campo via de aplicação")
    @Column(name = "viaAplicacao", length = 100, nullable = false)
    private String viaAplicacao;

    @NotNull(message = "Obrigatório o campo data de nascimento!")
    @Column(name = "dataNascimento", length = 100, nullable = false)
    private Date dataNascimento;

    @NotNull(message = "Obrigatório o campo icr!")
    @Column(name = "icr", length = 100, nullable = false)
    private Double icr;

    @NotNull(message = "Obrigatório o campo meta glicêmica!")
    @Column(name = "metaGlicemica", length = 100, nullable = false)
    private Double metaGlicemica;

}
