package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Entity(name="LEMBRETE")
@Table(name="LEMBRETE")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class Lembrete {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLembrete;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @Column(name = "remedio", length = 100, nullable = false)
    private String remedio;

    @Column(name = "consulta", length = 100, nullable = false)
    private String consulta;

    @Column(name = "dataRemedio", length = 100, nullable = false)
    private Date dataRemedio;

    @Column(name = "dataConsulta", length = 100, nullable = false)
    private Date dataConsulta;

    @Column(name = "horaRemedio", length = 100, nullable = false)
    private Time horaRemedio;

    @Column(name = "horaConsulta", length = 100, nullable = false)
    private Time horaConsulta;

    @Column(name = "notificacoesRemedio", length = 100, nullable = false)
    private Boolean notificacoesRemedio = false;

    @Column(name = "notificacoesConsulta", length = 100, nullable = false)
    private Boolean notificacoesConsulta = false;
}
