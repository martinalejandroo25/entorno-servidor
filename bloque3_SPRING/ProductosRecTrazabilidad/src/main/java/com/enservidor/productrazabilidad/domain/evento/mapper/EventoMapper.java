package com.enservidor.productrazabilidad.domain.evento.mapper;

import com.enservidor.productrazabilidad.domain.evento.EventoTrazabilidad;
import com.enservidor.productrazabilidad.domain.evento.dto.EventoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    @Mapping(target = "loteId", source = "lote.id")
    EventoDto.Response toResponse(EventoTrazabilidad evento);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    @Mapping(target = "lote", ignore = true)
    EventoTrazabilidad toEntity(EventoDto.Request request);
}
