package es.daw.mysql_prueba.entitys;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clientes")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String nombre;

    @Column(length = 100)
    private String apellido;

    @Column(length = 150)
    private String email;

    @OneToMany(mappedBy = "cliente")
    private Set<Pedido> pedidos;

    @OneToMany(mappedBy = "cliente")
    private Set<Direccion> direcciones;
}
