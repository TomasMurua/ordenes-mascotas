package com.duoc.ordenesmascotas.controller;

import com.duoc.ordenesmascotas.dto.ProductoResponseDto;
import com.duoc.ordenesmascotas.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.hateoas.CollectionModel;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductoControllerHateoasTest {

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private ProductoController productoController;

    @Test
    void obtener_incluyeLinksHateoasSelfYProductos() {
        ProductoResponseDto fuente = new ProductoResponseDto(1L, "Croquetas Premium", "alimento", 12990.0);
        when(productoService.getById(1L)).thenReturn(fuente);

        ProductoResponseDto resultado = productoController.obtener(1L);

        assertThat(resultado.getLink("self")).isPresent();
        assertThat(resultado.getLink("productos")).isPresent();
        assertThat(resultado.getRequiredLink("self").getHref()).contains("/productos/1");
    }

    @Test
    void listar_devuelveCollectionConLinkSelf() {
        ProductoResponseDto fuente = new ProductoResponseDto(1L, "Croquetas Premium", "alimento", 12990.0);
        when(productoService.getAll()).thenReturn(List.of(fuente));

        CollectionModel<ProductoResponseDto> coleccion = productoController.listar();

        assertThat(coleccion.getContent()).hasSize(1);
        assertThat(coleccion.getLink("self")).isPresent();
        ProductoResponseDto item = coleccion.getContent().iterator().next();
        assertThat(item.getLink("self")).isPresent();
    }
}
