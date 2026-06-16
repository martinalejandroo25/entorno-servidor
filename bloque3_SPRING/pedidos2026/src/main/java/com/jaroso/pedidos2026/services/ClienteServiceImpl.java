package com.jaroso.pedidos2026.services;

import com.jaroso.pedidos2026.dtos.ClienteCreateDto;
import com.jaroso.pedidos2026.dtos.ClienteDto;
import com.jaroso.pedidos2026.entities.Cliente;
import com.jaroso.pedidos2026.mappers.ClienteMapper;
import com.jaroso.pedidos2026.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    private ClienteRepository repo;

    @Autowired
    private ClienteMapper mapper;

    @Override
    public ClienteDto create(ClienteCreateDto dto) {
        Cliente cliente = mapper.clienteCreateDtoEntity(dto);
        return mapper.toDto(repo.save(cliente));
    }

    @Override
    public List<ClienteDto> findAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public Optional<ClienteDto> findById(Long id) {
        return repo.findById(id).map(mapper::toDto);
    }

    @Override
    public boolean delete(Long id) {
        Optional<Cliente> cliente = repo.findById(id);
        if (cliente.isPresent()) {
            repo.delete(cliente.get());
            return true;
        } else {
            return false;
        }
    }
}