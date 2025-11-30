package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity(name="REMEDIO")
@Table(name="REMEDIO")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Remedio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    // Ex.: 1 = segunda, 2 = terça... 7 = domingo
    private Integer diasDaSemana;

    private LocalTime horario;

    private boolean tomado = false;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
