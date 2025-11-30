package Glyvia.Glyvia.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RemedioRequest {
    private String descricao;
    private Integer diasDaSemana;
    private LocalTime horario;
}
