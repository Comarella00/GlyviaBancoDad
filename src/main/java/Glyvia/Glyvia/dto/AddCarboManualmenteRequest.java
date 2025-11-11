package Glyvia.Glyvia.dto;

import Glyvia.Glyvia.model.Glicemia;
import Glyvia.Glyvia.model.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddCarboManualmenteRequest {
        @NotNull
        private Long usuarioId; //usuario id

        @Column(name = "descricao", length = 100, nullable = false)
        private String descricao;

        @Column(name = "calorias", length = 100, nullable = false)
        private Double calorias;

        @Column(name = "carboidratos", length = 100, nullable = false)
        private Double carboidratos;

        @Column(name = "insulina", length = 100, nullable = false)
        private Double insulina;

        @Column(name = "dataRefeicao", length = 100, nullable = false)
        private Date dataRefeicao;

        @Column(name = "horaRefeicao", length = 100, nullable = false)
        private Time horaRefeicao;

}
