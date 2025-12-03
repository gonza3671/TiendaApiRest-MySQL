package es.daw.mysql_prueba.converters;

import es.daw.mysql_prueba.enums.StatusPedido;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusPedidoConverter implements AttributeConverter<StatusPedido, String> {

    @Override
    public String convertToDatabaseColumn(StatusPedido attribute) {
        if (attribute == null) {
            return null;
        }
        return attribute.name();
    }

    @Override
    public StatusPedido convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return StatusPedido.valueOf(dbData.toUpperCase());
    }
}

// cosa rara lo ha hecho la IA