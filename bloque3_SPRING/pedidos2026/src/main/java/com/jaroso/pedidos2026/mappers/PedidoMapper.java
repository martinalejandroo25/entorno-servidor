package com.jaroso.pedidos2026.mappers;

import com.jaroso.pedidos2026.dtos.PedidoCreateDto;
import com.jaroso.pedidos2026.dtos.PedidoDto;
import com.jaroso.pedidos2026.entities.Pedido;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(componentModel = "spring", uses = {LineaPedidoMapper.class})
public interface PedidoMapper {

    @Mapping(target = "total", expression = "java(calcularTotal(pedido))")
    PedidoDto toDto(Pedido pedido);
    PedidoDto pedidoCreateDtoToEntity(PedidoCreateDto dto);

    default BigDecimal calcularTotal(Pedido pedido){
        if(pedido == null || pedido.getLineasPedido() == null) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(
                pedido.getLineasPedido().stream()
                        .map(l -> l.getProducto().getPrecio() * l.getCantidad())
                        .reduce(0.0, Double::sum)
        );
    }

}
