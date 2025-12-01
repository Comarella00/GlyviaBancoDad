package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name="CONSULTA")
@Table(name="CONSULTA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Long idConsulta;

    private String descricao;

    private LocalDate data;

    private LocalTime horario;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;
}
