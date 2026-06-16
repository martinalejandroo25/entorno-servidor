package com.jaroso.pedidos2026.services;

import com.jaroso.pedidos2026.dtos.LineaPedidoCreateDto;
import com.jaroso.pedidos2026.dtos.PedidoCreateDto;
import com.jaroso.pedidos2026.dtos.PedidoDto;
import com.jaroso.pedidos2026.entities.*;
import com.jaroso.pedidos2026.mappers.PedidoMapper;
import com.jaroso.pedidos2026.repositories.ClienteRepository;
import com.jaroso.pedidos2026.repositories.PedidoRepository;
import com.jaroso.pedidos2026.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoMapper mapper;

    @Override
    @Transactional
    public PedidoDto create(PedidoCreateDto dto) {
        //Obtener el cliente que está haciendo el pedido
        Cliente cliente = clienteRepository.findById(dto.clienteId()).orElseThrow();
        //Creamos un pedido nuevo
        Pedido pedido = new Pedido();
        //Le asignamos el cliente
        pedido.setCliente(cliente);
        //Sacamos las líneas dto, y las tenemos que ir añadiendo al pedido
        for(LineaPedidoCreateDto lineaDto : dto.lineas()) {
            Producto producto = productoRepository.findById(lineaDto.productoId()).orElseThrow();
            LineaPedido linea = new LineaPedido();
            linea.setCantidad(lineaDto.cantidad());
            linea.setProducto(producto);
            pedido.addLineaPedido(linea);
        }
        //Poner fecha y estado al pedido
        pedido.setFecha(LocalDate.now());
        pedido.setEstado(EstadoPedido.PENDIENTE);

        //Insertamos el pedido en la BD
        pedido = pedidoRepository.save(pedido);
        //Devolvemos el pedido creado como dto
        return mapper.toDto(pedido);

    }

    @Override
    public Optional<PedidoDto> findById(Long id) {
        return pedidoRepository.findById(id).map(mapper::toDto);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        if (pedido.isPresent()) {
            pedidoRepository.delete(pedido.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    @Transactional
    public List<PedidoDto> findAll() {
        return pedidoRepository.findAll().stream().map(mapper::toDto).toList();
    }
}