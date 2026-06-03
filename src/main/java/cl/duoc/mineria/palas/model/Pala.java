package cl.duoc.mineria.palas.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "palas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String modelo;

    @Column(nullable = false, length = 50)
    private String sector;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado_operativo", nullable = false, length = 30)
    private EstadoPala estadoPala;
}