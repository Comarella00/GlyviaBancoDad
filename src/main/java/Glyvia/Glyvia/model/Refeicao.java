package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Entity(name="REFEICAO")
@Table(name="REFEICAO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Refeicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRefeicao;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_glicemia")
    private Glicemia glicemia;

    @Lob
    @Column(name = "foto", nullable = false)
    private byte[] foto;

    @NotNull(message = "Obrigatório o campo descrição!")
    @Column(name = "descricao", length = 100, nullable = false)
    private String descricao;

    @NotNull(message = "Obrigatório o campo calorias!")
    @Column(name = "calorias", length = 100, nullable = false)
    private Double calorias;

    @NotNull(message = "Obrigatório o campo carboidratos!")
    @Column(name = "carboidratos", length = 100, nullable = false)
    private Double carboidratos;

    @NotNull(message = "Obrigatório o campo insulina!")
    @Column(name = "insulina", length = 100, nullable = false)
    private Double insulina;

    @NotNull(message = "Obrigatório o campo data!")
    @Column(name = "dataRefeicao", length = 100, nullable = false)
    private Date dataRefeicao;

    @NotNull(message = "Obrigatório o campo hora!")
    @Column(name = "horaRefeicao", length = 100, nullable = false)
    private Time horaRefeicao;
}
