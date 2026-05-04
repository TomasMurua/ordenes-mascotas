package com.duoc.ordenesmascotas.service;

import com.duoc.ordenesmascotas.dto.ProductoRequestDto;
import com.duoc.ordenesmascotas.dto.ProductoResponseDto;
import com.duoc.ordenesmascotas.exception.ResourceNotFoundException;
import com.duoc.ordenesmascotas.model.Producto;
import com.duoc.ordenesmascotas.repository.ProductoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    private Producto productoMuestra;

    @BeforeEach
    void setUp() {
        productoMuestra = new Producto("Croquetas Premium", "alimento", 12990.0);
        productoMuestra.setId(1L);
    }

    @Test
    void getById_devuelveDtoMapeadoCuandoExiste() {
        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoMuestra));

        ProductoResponseDto dto = productoService.getById(1L);

        assertThat(dto.getId()).isEqualTo(1L);
        assertThat(dto.getNombre()).isEqualTo("Croquetas Premium");
        assertThat(dto.getCategoria()).isEqualTo("alimento");
        assertThat(dto.getPrecio()).isEqualTo(12990.0);
    }

    @Test
    void getById_lanzaResourceNotFoundCuandoNoExiste() {
        when(productoRepository.findById(404L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> productoService.getById(404L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("404");
    }

    @Test
    void create_persisteYDevuelveDto() {
        ProductoRequestDto req = new ProductoRequestDto();
        req.setNombre("Pelota chillona");
        req.setCategoria("juguete");
        req.setPrecio(3990.0);
        Producto guardado = new Producto("Pelota chillona", "juguete", 3990.0);
        guardado.setId(7L);
        when(productoRepository.save(any(Producto.class))).thenReturn(guardado);

        ProductoResponseDto dto = productoService.create(req);

        assertThat(dto.getId()).isEqualTo(7L);
        assertThat(dto.getNombre()).isEqualTo("Pelota chillona");
    }

    @Test
    void getAll_mapeaListaCompleta() {
        Producto otro = new Producto("Arena Sanitaria", "higiene", 5990.0);
        otro.setId(2L);
        when(productoRepository.findAll()).thenReturn(List.of(productoMuestra, otro));

        List<ProductoResponseDto> dtos = productoService.getAll();

        assertThat(dtos).hasSize(2);
        assertThat(dtos.get(1).getCategoria()).isEqualTo("higiene");
    }
}
