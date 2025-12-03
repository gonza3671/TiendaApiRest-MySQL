package es.daw.mysql_prueba.entitys;

import es.daw.mysql_prueba.converters.StatusPedidoConverter;
import es.daw.mysql_prueba.entitys.pedido_producto.DetallePedido;
import es.daw.mysql_prueba.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pedido_id")
    private Long id;

    @CreationTimestamp
    @Column(name = "fecha_pedido")
    private LocalDateTime fecha;

    @Convert(converter = StatusPedidoConverter.class)
    private StatusPedido estado;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // mappedby apunta al campo "pedido" en DetallePedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetallePedido> detallePedidos = new HashSet<>();
}
