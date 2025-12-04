package es.daw.mysql_prueba.entitys;

import es.daw.mysql_prueba.enums.DireccionTipo;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "direcciones")
@Getter
@Setter
public class Direccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String calle;

    private String ciudad;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    private String pais;

    private String provincia;

    @Column(name = "tipo_direccion")
    private String direccionTipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "direccion")
    private Set<Pedido> pedidos;
}
