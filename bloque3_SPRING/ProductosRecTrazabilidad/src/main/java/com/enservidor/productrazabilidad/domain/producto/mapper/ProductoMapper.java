package com.enservidor.productrazabilidad.domain.producto.mapper;

import com.enservidor.productrazabilidad.domain.producto.Producto;
import com.enservidor.productrazabilidad.domain.producto.dto.ProductoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper {

    @Mapping(target = "usuarioId", source = "usuario.id")
    ProductoDto.Response toResponse(Producto producto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    @Mapping(target = "lotes", ignore = true)
    Producto toEntity(ProductoDto.Request request);
}
