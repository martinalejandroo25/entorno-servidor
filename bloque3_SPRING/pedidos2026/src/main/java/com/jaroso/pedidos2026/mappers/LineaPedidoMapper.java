package com.jaroso.pedidos2026.mappers;

import com.jaroso.pedidos2026.dtos.LineaPedidoCreateDto;
import com.jaroso.pedidos2026.dtos.LineaPedidoDto;
import com.jaroso.pedidos2026.entities.LineaPedido;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LineaPedidoMapper {
    LineaPedidoDto toDto(LineaPedido lineaPedido);
    LineaPedido lineaPedidoCreateDtoEntity(LineaPedidoCreateDto dto);
    List<LineaPedidoDto> lineasToDto(List<LineaPedido> lineas);
}
