package com.jaroso.pedidos2026.services;

import com.jaroso.pedidos2026.dtos.ProductoCreateDto;
import com.jaroso.pedidos2026.dtos.ProductoDto;
import com.jaroso.pedidos2026.entities.Producto;
import com.jaroso.pedidos2026.mappers.ProductoMapper;
import com.jaroso.pedidos2026.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository repo;

    @Autowired
    private ProductoMapper mapper;

    public ProductoDto create(ProductoCreateDto dto) {
        Producto producto = mapper.productoCreateDtoToEntity(dto);
        return mapper.toDto(repo.save(producto));
    }
    @Transactional(readOnly = true)
    public List<ProductoDto> findAll() {
        return repo.findAll().stream().map(mapper::toDto).toList();
    }

    public Optional<ProductoDto> findById(Long id) {
        return repo.findById(id).map(mapper::toDto);
    }

    @Transactional
    public void delete(ProductoDto dto) {
        if (!repo.existsById(dto.id())) return;
        else {
            Optional<Producto> producto = repo.findById(dto.id());
            producto.ifPresent(value -> repo.delete(value));
        }
    }




}