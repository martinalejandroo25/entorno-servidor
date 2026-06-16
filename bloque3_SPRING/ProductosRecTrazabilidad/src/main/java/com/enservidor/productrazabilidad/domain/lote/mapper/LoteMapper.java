package com.enservidor.productrazabilidad.domain.lote.mapper;

import com.enservidor.productrazabilidad.domain.lote.Lote;
import com.enservidor.productrazabilidad.domain.lote.dto.LoteDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface LoteMapper {

    @Mapping(target = "productoId", source = "producto.id")
    LoteDto.Response toResponse(Lote lote);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "producto", ignore = true)
    @Mapping(target = "eventos", ignore = true)
    Lote toEntity(LoteDto.Request request);

}
