package es.daw.mysql_prueba.enums;

public enum StatusPedido {
    PENDIENTE,
    ENVIADO,
    ENTREGADO,
    CANCELADO;

    public static boolean exists(String status) {
        if (status == null) return false;
        try {
            valueOf(status.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}
