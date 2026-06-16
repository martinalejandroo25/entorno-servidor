package com.jaroso.pedidos2026.mappers;

import com.jaroso.pedidos2026.dtos.ProductoBasicoDto;
import com.jaroso.pedidos2026.dtos.ProductoCreateDto;
import com.jaroso.pedidos2026.dtos.ProductoDto;
import com.jaroso.pedidos2026.entities.Producto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductoMapper {
    ProductoDto toDto(Producto producto);
    Producto productoCreateDtoToEntity(ProductoCreateDto dto);
    ProductoBasicoDto toBasicoDto(Producto producto);
}