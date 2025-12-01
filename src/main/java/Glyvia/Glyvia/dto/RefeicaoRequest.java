package Glyvia.Glyvia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefeicaoRequest {
    private Long idRefeicao;
    private Long idUsuario;
    private Long idGlicemia;
    private String descricao;
    private Double calorias;
    private Double carboidratos;
    private LocalDate dataRefeicao;
    private LocalTime horaRefeicao;
}
