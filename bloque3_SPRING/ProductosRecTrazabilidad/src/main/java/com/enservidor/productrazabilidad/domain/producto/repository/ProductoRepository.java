package com.enservidor.productrazabilidad.domain.producto.repository;

import com.enservidor.productrazabilidad.domain.producto.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    //Listar productos asociados a un usuario
    List<Producto> findByUsuarioId(Long usuarioId);

    //Buscar por codigo y usuario
    Optional<Producto> findByCodigoAndUsuarioId(String codigo, Long usuarioId);

    //Verificar si el codigo ya existe para este usuario
    boolean existsByCodigoAndUsuarioId(String codigo, Long usuarioId);
}
