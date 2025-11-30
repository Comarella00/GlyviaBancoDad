package Glyvia.Glyvia.model;

import jakarta.persistence.*;
import lombok.*;

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


}
