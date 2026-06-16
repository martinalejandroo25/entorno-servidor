package com.jaroso.pedidos2026.mappers;

import com.jaroso.pedidos2026.dtos.ClienteCreateDto;
import com.jaroso.pedidos2026.dtos.ClienteDto;
import com.jaroso.pedidos2026.entities.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    ClienteDto toDto(Cliente cliente);
    Cliente clienteCreateDtoEntity(ClienteCreateDto dto);
    //Resumen de pedidos
}